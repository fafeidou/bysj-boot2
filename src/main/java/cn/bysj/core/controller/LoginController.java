package cn.bysj.core.controller;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.bysj.core.config.KaptchaConfig;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.octo.captcha.service.image.ImageCaptchaService;

import cn.bysj.core.pojo.Student;
import cn.bysj.core.pojo.Systemmanager;
import cn.bysj.core.pojo.Teacher;
import cn.bysj.core.pojo.vo.Menu;
import cn.bysj.core.pojo.vo.StudentCustom;
import cn.bysj.core.pojo.vo.TeacherCustom;
import cn.bysj.core.service.systemManage.SystemmanagerService;
import cn.bysj.core.service.teacher.StudentService;
import cn.bysj.core.service.teacher.TeacherService;
import cn.bysj.core.web.common.Constants;
import cn.bysj.core.web.encode.Md5Pwd;
import cn.bysj.core.web.session.SessionProvider;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.TimeUnit;

/**
 * 登录管理 ClassName: LoginController
 * 
 * @Description: TODO
 * @author it小祥
 * @date 2016年11月21日
 */
@Controller
@RequestMapping("/system/")
public class LoginController {
	@Autowired
	private SessionProvider sessionProvider;
	@Autowired
	private ImageCaptchaService imageCaptchaService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private Md5Pwd md5Pwd;
	@Autowired
	private StudentService studentService;
	@Autowired
	private SystemmanagerService systemmanagerService;

	@RequestMapping("/defaultKaptcha")
	public void defaultKaptcha(HttpServletRequest httpServletRequest,HttpServletResponse response) throws Exception{

		KaptchaConfig.writeToResponse(KaptchaConfig.createText(),response);
//		response.setDateHeader("Expires", 0);
//		response.setHeader("Cache-Control",
//				"no-store, no-cache, must-revalidate");
//		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
//		response.setHeader("Pragma", "no-cache");
//		response.setContentType("image/jpeg");
//
//		String capText = captchaProducer.createText();
//		System.out.println("capText: " + capText);
//
//		try {
//			String uuid= UUIDUtils.getUUID32().trim().toString();
////			redisTemplate.opsForValue().set(uuid, capText,60*5, TimeUnit.SECONDS);
//			Cookie cookie = new Cookie("captchaCode",uuid);
//			response.addCookie(cookie);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//
//		BufferedImage bi = captchaProducer.createImage(capText);
//		ServletOutputStream out = response.getOutputStream();
//		ImageIO.write(bi, "jpg", out);
//		try {
//			out.flush();
//		} finally {
//			out.close();
//		}
	}

	/**
	 * 跳转login
	 */
	@RequestMapping(value = "/login.do")
	public String login(HttpServletRequest request) {
        // 获取session
		HttpSession session = request.getSession();
		if (session.getAttribute(Constants.TEACHER_SESSION) != null) {
			return "/first";
		} else if (session.getAttribute(Constants.STUDENT_SESSION) != null) {
			return "/student/first";
		} else if (session.getAttribute(Constants.MANAGER_SESSION) != null) {
			return "/manager/index";
		}
		return "/login";
	}

	/**
	 * 教师登录
	 */
	@RequestMapping(value = "/teacherLogin.do")
	public String teacherLogin(Teacher teacher, String captcha, ModelMap model, HttpServletRequest request)
			throws Exception {
		HttpSession session = request.getSession();// 获取session
		if (session.getAttribute(Constants.TEACHER_SESSION) != null) {
			return "/first";
		}
		// 判断验证码
		// 1):验证码是否为null
		if (StringUtils.isNotBlank(captcha)) {
			// 1:JSSSIONID
			// 2:验证码
			Boolean validateResponseForID = imageCaptchaService
					.validateResponseForID(sessionProvider.getSessionId(request), captcha);
			if (validateResponseForID) {
				// 判断用户名是否为空
				if (null != teacher && StringUtils.isNotBlank(teacher.getEmployeeNum())) {
					// 判断密码是否为空
					if (null != teacher && StringUtils.isNotBlank(teacher.getPassword())) {
						// 根据工号去查寻教师姓名
						Teacher t = teacherService.getTeacherByTeacherEmployeeNum(teacher);
						if (t != null) {
							// 和前台传来的密码比较
							if (t.getPassword().equals(md5Pwd.encode(teacher.getPassword()))) {
								TeacherCustom teacherCustom = teacherService.getTeacherCustom(t);
								teacherCustom.setTeacher(t);
								session.setAttribute(Constants.TEACHER_SESSION, teacherCustom);
								return "/first";
							} else {
								model.addAttribute("error", "密码输入错误");
							}
						} else {
							model.addAttribute("error", "工号输入错误");
						}
					} else {
						model.addAttribute("error", "请输入密码");
					}
				} else {
					model.addAttribute("error", "请输入工号");
				}
			} else {
				model.addAttribute("error", "验证码输入错误");
			}
		} else {
			model.addAttribute("error", "请填写验证码");
		}
		// 判断工号是否为空

		// 判断密码是否为空

		// 查询数据库 看账号密码是否正确

		// 查询出教师的所有角色,赋给教师

		// 密码正确先查出普通教师所有菜单 , 并且将教师信息写到session中
		model.addAttribute("employeeNum", teacher.getEmployeeNum());
		return "/login";
	}

	/**
	 * 
	 * @Description: 学生登录
	 * @param @param
	 *            student
	 * @param @param
	 *            captcha
	 * @param @param
	 *            model
	 * @param @param
	 *            request
	 * @param @return
	 * @param @throws
	 *            Exception
	 * @return String
	 * @throws @author
	 *             it小祥
	 * @date 2017年1月22日
	 */
	@RequestMapping(value = "/studentLogin.do")
	public String studentLogin(Student student, String captcha, ModelMap model, HttpServletRequest request)
			throws Exception {
		HttpSession session = request.getSession();// 获取session
		if (session.getAttribute(Constants.STUDENT_SESSION) != null) {
			return "/student/first";
		}
		// 判断验证码
		// 1):验证码是否为null
		if (StringUtils.isNotBlank(captcha)) {
			// 1:JSSSIONID
			// 2:验证码
			Boolean validateResponseForID = imageCaptchaService
					.validateResponseForID(sessionProvider.getSessionId(request), captcha);
			if (validateResponseForID) {
				// 判断用户名是否为空
				if (null != student && StringUtils.isNotBlank(student.getStudentNo())) {
					// 判断密码是否为空
					if (null != student && StringUtils.isNotBlank(student.getPassword())) {
						// 根据学号查询学生信息
						Student s = studentService.getStudentByStudentNo(student.getStudentNo());
						if (s != null) {
							// 和前台传来的密码比较
							if (s.getPassword().equals(md5Pwd.encode(student.getPassword()))) {
								StudentCustom studentCustom = studentService.getStudentCustom(s);
								session.setAttribute(Constants.STUDENT_SESSION, studentCustom);
								return "/student/first";
							} else {
								model.addAttribute("error", "密码输入错误");
							}
						} else {
							model.addAttribute("error", "学号输入错误");
						}
					} else {
						model.addAttribute("error", "请输入密码");
					}
				} else {
					model.addAttribute("error", "请输入学号");
				}
			} else {
				model.addAttribute("error", "验证码输入错误");
			}
		} else {
			model.addAttribute("error", "请填写验证码");
		}
		model.addAttribute("studentNo", student.getStudentNo());
		return "/login";
	}

	/**
	 * 管理员登录
	 */
	@RequestMapping(value = "/systemManagerLogin.do")
	public String systemManagerLogin(Systemmanager systemmanager, String captcha, ModelMap model,
			HttpServletRequest request) throws Exception {
        // 获取session
		HttpSession session = request.getSession();
		if (session.getAttribute(Constants.MANAGER_SESSION) != null) {
			return "/manager/index";
		}
		// 判断验证码
		// 1):验证码是否为null
		if (StringUtils.isNotBlank(captcha)) {
			// 1:JSSSIONID
			// 2:验证码
			Boolean validateResponseForID = imageCaptchaService
					.validateResponseForID(sessionProvider.getSessionId(request), captcha);
			if (validateResponseForID) {
				// 判断用户名是否为空
				if (null != systemmanager && StringUtils.isNotBlank(systemmanager.getName())) {
					// 判断密码是否为空
					if (null != systemmanager && StringUtils.isNotBlank(systemmanager.getPassword())) {
						// 根据学号查询学生信息
						Systemmanager s = systemmanagerService.getSystemmanagerByName(systemmanager.getName());
						if (s != null) {
							// 和前台传来的密码比较
							if (s.getPassword().equals(systemmanager.getPassword())) {
								session.setAttribute(Constants.MANAGER_SESSION, s);
								return "/manager/index";
							} else {
								model.addAttribute("error", "密码输入错误");
							}
						} else {
							model.addAttribute("error", "用户名输入错误");
						}
					} else {
						model.addAttribute("error", "请输入密码");
					}
				} else {
					model.addAttribute("error", "请输入用户名");
				}
			} else {
				model.addAttribute("error", "验证码输入错误");
			}
		} else {
			model.addAttribute("error", "请填写验证码");
		}
		model.addAttribute("systemmanager", systemmanager.getName());
		return "/login";
	}

	/**
	 * 获取菜单菜单，并转成json
	 */
	@RequestMapping("teacherMenu.do")
	public @ResponseBody Menu usermenu(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		TeacherCustom teacherCustom = (TeacherCustom) session.getAttribute(Constants.TEACHER_SESSION);
		return teacherCustom.getMenu();
	}

	/**
	 * 加载welcome界面
	 */
	@RequestMapping("welcome.do")
	public String welcome() {
		return "/welcome";
	}

	/**
	 * 
	 * @Description: 加载studentWelcome界面
	 * @param @param
	 *            session
	 * @param @return
	 * @param @throws
	 *            Exception
	 * @return String
	 * @throws @author
	 *             it小祥
	 * @date 2017年1月23日
	 */
	@RequestMapping("/student/welcome.do")
	public String studentWelcome() {
		return "/student/welcome";
	}

	/**
	 * 注销 公用的
	 */
	@RequestMapping("logout.do")
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		return "redirect:/system/login.do";
	}


}
