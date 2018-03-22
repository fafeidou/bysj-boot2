package cn.bysj.core.controller.student;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.bysj.core.pojo.Student;
import cn.bysj.core.pojo.vo.ThesistopicCustom;
import cn.bysj.core.pojo.weixin.Weixinstudentstate;
import cn.bysj.core.service.student.StudentThesistopicService;
import cn.bysj.core.service.teacher.StudentService;
import cn.bysj.core.service.weixin.WeixinstudentstateService;
import cn.bysj.core.web.common.Constants;
import cn.bysj.core.web.encode.Md5Pwd;
import cn.bysj.core.web.utils.weixin.CheckUtil;
import cn.bysj.core.web.utils.weixin.MessageUtil;

@Controller
@RequestMapping("/wx/student/")
public class StudentWxController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private Md5Pwd md5Pwd;
	@Autowired
	private WeixinstudentstateService weixinstudentstateService;
	@Value("${WXUrl}")
	private String WXUrl;
	@RequestMapping(value = "weiXin.do", method = { RequestMethod.GET },produces="text/plain;charset=UTF-8")
	public void weiXinGet(ModelMap model, String signature, String timestamp, String nonce, String echostr,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
	}

	@RequestMapping(value = "weiXin.do", method = { RequestMethod.POST },produces="text/plain;charset=UTF-8")
	public void weiXinPost(HttpServletRequest request, ModelMap model, String signature, String timestamp, String nonce,
			String echostr, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		Map<String, String> map = MessageUtil.xmlToMap(request);

		String fromUserName = map.get("FromUserName");
		String toUserName = map.get("ToUserName");
		String msgType = map.get("MsgType");
		String content = map.get("Content");
		String message = null;
		if (MessageUtil.MESSAGE_TEXT.equals(msgType)) {
			if ("查看".equals(content)) {
				// 查看数据库中是否已经绑定
				Weixinstudentstate weixinstudentstate = weixinstudentstateService.getStateByFromUserName(fromUserName);
				// 已经绑定了，回复已经绑定,查出信息记录到session中
				if (weixinstudentstate != null) {
					content = "<a href=\""+ WXUrl +"/wx/student/toLogin.do?studentId="
							+ weixinstudentstate.getStudentId() + "\">查看</a>";
				} else {
					// 没有绑定去登陆界面

					content = "<a href=\""+ WXUrl +"/wx/student/toLogin.do?fromUserName="
							+ fromUserName + "\">绑定</a>";
				}
				message = MessageUtil.ininText(toUserName, fromUserName, content);
			} else {
				message = MessageUtil.ininText(toUserName, fromUserName, MessageUtil.menuText());
			}

		} else if (MessageUtil.MESSAGE_EVENT.equals(msgType)) {
			String eventType = map.get("Event");
			// 关注回复
			if (MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)) {
				message = MessageUtil.ininText(toUserName, fromUserName, MessageUtil.menuText());
			}
		}
		out.print(message);
		out.close();
	}

	/**
	 * 跳转login
	 */
	@RequestMapping(value = "/toLogin.do")
	public String toLogin(HttpServletRequest request, String fromUserName, Integer studentId) {
		HttpSession session = request.getSession();// 获取session
		if (fromUserName != null) {
			session.setAttribute(Constants.FROM_USER_NAME, fromUserName);
			return "/weixin/student/login";
		}
		if (studentId != null) {
			Student stduent = studentService.getstudentByStudentId(studentId);
			session.setAttribute(Constants.STUDENT_SESSION, stduent);
			Weixinstudentstate weixinstudentstate = weixinstudentstateService.getStateByStudentId(studentId);
			if (weixinstudentstate != null) {
				session.setAttribute(Constants.FROM_USER_NAME, weixinstudentstate.getFromUserName());
			}
		}
		return "/weixin/student/index";
	}

	/**
	 * 微信登录
	 */
	@RequestMapping(value = "/login.do")
	public String login(HttpServletRequest request, Student student, ModelMap model) {
		HttpSession session = request.getSession();// 获取session
		// 判断用户名是否空
		if (student != null && StringUtils.isNotBlank(student.getStudentNo())) {
			if (null != student && StringUtils.isNotBlank(student.getPassword())) {
				// 根据学号查询学生信息
				Student s = studentService.getStudentByStudentNo(student.getStudentNo());
				if (s != null) {
					if (s.getPassword().equals(md5Pwd.encode(student.getPassword()))) {
						session.setAttribute(Constants.STUDENT_SESSION, s);
						// 插入数据
						String fromUserName = (String) session.getAttribute(Constants.FROM_USER_NAME);
						if (fromUserName != null) {
							Weixinstudentstate weixinstudentstate = new Weixinstudentstate();
							weixinstudentstate.setFromUserName(fromUserName);
							weixinstudentstate.setStudentId(s.getStudentId());
							weixinstudentstate.setState(true);
							weixinstudentstateService.AddWeiXinStudentState(weixinstudentstate);
							session.setAttribute(Constants.FROM_USER_NAME, fromUserName);
						}
						return "/weixin/student/index";
					} else {
						model.addAttribute("message", "密码输入错误");
					}
				} else {
					model.addAttribute("message", "学号输入错误");
				}
			} else {
				model.addAttribute("message", "请输入密码");
			}
		} else {
			model.addAttribute("message", "请输入学号");
		}

		return "/weixin/student/login";
	}

	@RequestMapping("listMyselfThesistopic.do")
	public String listMyselfThesistopic(ThesistopicCustom thesistopicCustom, Integer page, Integer rows,
			HttpServletRequest request, ModelMap model) throws Exception {
		List<ThesistopicCustom> list = new ArrayList<ThesistopicCustom>();
		// 根据学生的id查询所有论文
		HttpSession session = request.getSession();
		Student student = (Student) session.getAttribute(Constants.STUDENT_SESSION);
		thesistopicCustom.setStudentId(student.getStudentId());
		list = studentThesistopicService.getThesistopicCustomListMyself(thesistopicCustom, page, rows);
		model.addAttribute("list", list);
		return "/weixin/student/listMyselfThesistopic";

	}

	@RequestMapping("logout.do")
	public String logout(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();// 获取session
		// 删除相关信息
		String fromUserName = (String) session.getAttribute(Constants.FROM_USER_NAME);
		weixinstudentstateService.deleteWeiXinStudentStateByFromUserName(fromUserName);
		session.removeAttribute(Constants.STUDENT_SESSION);
		return "/weixin/student/login";
	}

	@Autowired
	private StudentThesistopicService studentThesistopicService;
}
