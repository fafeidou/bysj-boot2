package cn.bysj.core.controller.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bysj.core.pojo.Student;
import cn.bysj.core.pojo.vo.StudentCustom;
import cn.bysj.core.process.result.ResultInfo;
import cn.bysj.core.process.result.SubmitResultInfo;
import cn.bysj.core.service.teacher.StudentService;
import cn.bysj.core.web.common.Constants;
import cn.bysj.core.web.encode.Md5Pwd;

/**
 * 学生个人信息管理 ClassName: StudentMyselfInfoController
 * 
 * @Description: TODO
 * @author it小祥
 * @date 2017年1月23日
 */
@Controller
@RequestMapping("/student/myselfInfo/")
public class StudentMyselfInfoController {
	@Value("${initPassword}")
	private String initPassword;
	@Autowired
	private StudentService studentService;

	@RequestMapping("toListMyselfInfo.do")
	public String toListMyselfInfo(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		StudentCustom studentCustom = (StudentCustom) session.getAttribute(Constants.STUDENT_SESSION);
		Student student = studentService.getstudentByStudentId(studentCustom.getStudent().getStudentId());
		model.addAttribute("student", student);
		return "/student/myselfInfo/listMyselfInfo";
	}

	@RequestMapping("editMyselfInfo.do")
	public @ResponseBody SubmitResultInfo editThesisTopic(Student student1, HttpServletRequest request)
			throws Exception {

		ResultInfo resultInfo = new ResultInfo();

		HttpSession session = request.getSession();
		StudentCustom studentCustom = (StudentCustom) session.getAttribute(Constants.STUDENT_SESSION);
		Student student = studentService.getstudentByStudentId(studentCustom.getStudent().getStudentId());
		resultInfo.setType(ResultInfo.TYPE_RESULT_WARN);

		if (StringUtils.isNotBlank(student1.getPhone())) {
			if (StringUtils.isNotBlank(student1.getQq())) {
				if (StringUtils.isNotBlank(student1.getEmail())) {
					// 如果信息未完善，将状态改为等待选择
					if (student.getStudentState() == 0) {
						student1.setStudentState(1);
					}
					// 更新
					studentService.updateStudent(student1);
					resultInfo.setType(ResultInfo.TYPE_RESULT_SUCCESS);
					resultInfo.setMessage("修改成功！");
				} else {
					resultInfo.setMessage("邮箱不能为空！");
				}
			} else {
				resultInfo.setMessage("QQ号不能为空！");
			}
		} else {
			resultInfo.setMessage("手机号不能为空！");
		}
		// 将执行结果返回页面
		SubmitResultInfo submitResultInfo = new SubmitResultInfo(resultInfo);
		return submitResultInfo;
	}

	@RequestMapping("toModifyPassword.do")
	public String toModifyPassword(ModelMap model, HttpServletRequest request) {
		return "/student/myselfInfo/modifyPassword";
	}

	@RequestMapping("modifyPassword.do")
	public @ResponseBody SubmitResultInfo modifyPassword(String oldPassword, String newPassword, String confirmPassword,
			HttpServletRequest request) throws Exception {
		// 默认为成功
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setType(ResultInfo.TYPE_RESULT_WARN);

		HttpSession session = request.getSession();
		StudentCustom studentCustom = (StudentCustom) session.getAttribute(Constants.STUDENT_SESSION);
		Student s = studentService.getstudentByStudentId(studentCustom.getStudent().getStudentId());
		String pwd = s.getPassword();
		oldPassword = md5Pwd.encode(oldPassword);

		if (pwd.equals(oldPassword)) { // 输入的旧密码与原密码一致
			if (newPassword.equals(confirmPassword)) {// 判断输入的两个新密码是否一致
				if (!md5Pwd.encode(confirmPassword).equals(pwd)) {// 如果新密码与原密码不同，执行更新密码操作
					s.setPassword(md5Pwd.encode(confirmPassword));
					studentService.updateStudent(s);
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

	@Autowired
	private Md5Pwd md5Pwd;
}
