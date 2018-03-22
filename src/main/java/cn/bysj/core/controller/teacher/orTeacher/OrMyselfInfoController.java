package cn.bysj.core.controller.teacher.orTeacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bysj.core.pojo.Teacher;
import cn.bysj.core.pojo.vo.TeacherCustom;
import cn.bysj.core.process.result.ResultInfo;
import cn.bysj.core.process.result.SubmitResultInfo;
import cn.bysj.core.service.teacher.TeacherService;
import cn.bysj.core.web.common.Constants;
import cn.bysj.core.web.encode.Md5Pwd;

/**
 * 
 * ClassName: OrMyselfInfoController
 * 
 * @Description: 个人信息管理
 * @author it小祥
 * @date 2017年1月20日
 */
@Controller
@RequestMapping("/teacher/orTeacher/myselfInfo/")
public class OrMyselfInfoController {
	@Autowired
	private TeacherService teacherService;
	@Value("${initPassword}")
	private String initPassword;
	@Autowired
	private Md5Pwd md5Pwd;

	@RequestMapping("toListMyselfInfo.do")
	public String toListMyselfInfo(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		TeacherCustom teacherCustom = (TeacherCustom) session.getAttribute(Constants.TEACHER_SESSION);
		Teacher teacher = teacherService.getTeacherByTeacherId(teacherCustom.getTeacher().getTeacherId());
		model.addAttribute("teacher", teacher);
		return "/teacher/orTeacher/myselfInfo/listMyselfInfo";
	}

	@RequestMapping("editMyselfInfo.do")
	public @ResponseBody SubmitResultInfo editThesisTopic(Teacher teacher) throws Exception {
		// 默认为成功
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setType(ResultInfo.TYPE_RESULT_SUCCESS);
		resultInfo.setMessage("修改成功！");
		teacherService.updateTeacherByTeacherId(teacher);
		// 将执行结果返回页面
		SubmitResultInfo submitResultInfo = new SubmitResultInfo(resultInfo);
		return submitResultInfo;
	}

	@RequestMapping("toModifyPassword.do")
	public String toModifyPassword(ModelMap model, HttpServletRequest request) {
		return "/teacher/orTeacher/myselfInfo/modifyPassword";
	}

	@RequestMapping("modifyPassword.do")
	public @ResponseBody SubmitResultInfo modifyPassword(String oldPassword, String newPassword, String confirmPassword,
			HttpServletRequest request) throws Exception {
		// 默认为成功
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setType(ResultInfo.TYPE_RESULT_WARN);

		HttpSession session = request.getSession();
		TeacherCustom teacherCustom = (TeacherCustom) session.getAttribute(Constants.TEACHER_SESSION);
		Teacher teacher = teacherService.getTeacherByTeacherId(teacherCustom.getTeacher().getTeacherId());

		String pwd = teacher.getPassword();
		oldPassword = md5Pwd.encode(oldPassword);

		if (pwd.equals(oldPassword)) { // 输入的旧密码与原密码一致
			if (newPassword.equals(confirmPassword)) {// 判断输入的两个新密码是否一致
				if (!md5Pwd.encode(confirmPassword).equals(pwd)) {// 如果新密码与原密码不同，执行更新密码操作
					teacher.setPassword(md5Pwd.encode(confirmPassword));
					teacherService.updateTeacherByTeacherId(teacher);
					resultInfo.setType(ResultInfo.TYPE_RESULT_SUCCESS);
					resultInfo.setMessage("恭喜您，密码修改成功！");
				} else {
					resultInfo.setMessage("密码没有改动！");
				}
			} else {
				resultInfo.setMessage("抱歉，密码输入不一致！");
			}
		} else {
			resultInfo.setMessage("和原密码不一致,修改失败！");
		}
		// 将执行结果返回页面
		SubmitResultInfo submitResultInfo = new SubmitResultInfo(resultInfo);
		return submitResultInfo;
	}
}
