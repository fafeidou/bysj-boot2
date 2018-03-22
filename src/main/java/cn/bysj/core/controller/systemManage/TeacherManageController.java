package cn.bysj.core.controller.systemManage;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.bysj.core.pojo.Departments;
import cn.bysj.core.pojo.Teacher;
import cn.bysj.core.pojo.TeacherExample;
import cn.bysj.core.pojo.Trsection;
import cn.bysj.core.service.systemManage.DepartmentManageService;
import cn.bysj.core.service.systemManage.TeacherManageService;
import cn.bysj.core.service.systemManage.TrsetionManageService;
import cn.bysj.core.web.encode.Md5Pwd;
import cn.bysj.core.web.utils.ResponseUtils;
import cn.itcast.common.page.Pagination;

/**
 * 教师管理 ClassName: DepartmentManageController
 * 
 * @Description: TODO
 * @author it小祥
 * @param <teacherManageService>
 * @param <teacherManageService>
 * @date 2016年11月5日
 */
@Controller
@RequestMapping("/manage/teacher/")
public class TeacherManageController {
	@Autowired
	private DepartmentManageService departmentManageService;
	@Autowired
	private TrsetionManageService trsetionManageService;
	@Autowired
	private TeacherManageService teacherManageService;
	@Autowired
	private Md5Pwd md5Pwd;
	@Value("${initPassword}")
	private String initPassword;

	/*
	 * 跳转到教师添加页面
	 */
	@RequestMapping("toAdd.do")
	public String toAdd(String message, ModelMap model) throws Exception {
		// 查询所有院系
		List<Departments> departments = departmentManageService
				.getAllDepartments();
		// 查询所有教研室
		List<Trsection> trsections = trsetionManageService.getAllTrsections();
		model.addAttribute("departments", departments);
		model.addAttribute("trsections", trsections);
		if (message != null) {
			message = URLDecoder.decode(message, "UTF-8");
			model.addAttribute("message", message);
		}
		return "/manager/teacher/add";
	}

	/*
	 * 教研室的联动
	 */
	@RequestMapping("trsection.do")
	public void trsection(String code, HttpServletResponse response) {
		int trsectionId = Integer.parseInt(code);
		// 根据院系ID查出所有教研室
		List<Trsection> trsections = trsetionManageService
				.getTrsectionsByDepartmentId(trsectionId);
		JSONObject jo = new JSONObject();
		jo.put("trsections", trsections);
		ResponseUtils.renderJson(response, jo.toString());
	}

	/*
	 * 添加教师
	 */
	@RequestMapping("add.do")
	public String add(Teacher teacher, ModelMap model) throws Exception {
		String message = null;
		// 判断是否为空
		if (teacher.getEmployeeNum() != null
				&& teacher.getTeacherName() != null
				&& teacher.getProfessionalRank() != null) {
			// 判断老师是否已经存在
			List<Teacher> teachers = teacherManageService
					.getTeacherByEmployeeNum(teacher);
			if (teachers.size() > 0 && teachers != null) {
				message = "该教师的学号已经存在,添加失败!";
			} else {
				// 说明不会重复
				
				teacher.setPassword(md5Pwd.encode(initPassword));
				teacherManageService.addTeacher(teacher);
				message = "教师:" + teacher.getTeacherName() + "添加成功!";
			}
		} else {
			message = "教师信息不完整";
		}
		message = URLEncoder.encode(message, "UTF-8");
		// 设置教师在学校状态为在职
		if (message != null) {
			model.addAttribute("message", message);
		}
		return "redirect:/manage/teacher/toAdd.do";
	}

	/*
	 * 教师信息展示
	 */
	@RequestMapping("toEdit.do")
	public String toEdit(String teacherName, String departmentId,
			String trsectionId, Integer pageNo, ModelMap model) {
		// 查出所有教研室
		List<Departments> departments = departmentManageService
				.getAllDepartments();
		model.addAttribute("departments", departments);
		List<Trsection> trsections = trsetionManageService
				.getTrsectionsByDepartmentId(departments.get(0)
						.getDepartmentId());
		model.addAttribute("trsections", trsections);
		// 查询分页
		StringBuilder params = new StringBuilder();
		TeacherExample example = new TeacherExample();
		// 如果页号为null 或小于1 置为1
		// 页号
		example.setPageNo(Pagination.cpn(pageNo));
		// 设置每页数
		example.setPageSize(6);
		String url = "/manage/teacher/toEdit.do";
		Pagination pagination = null;
		// 根据姓名模糊查询教师
		if (StringUtils.isNotBlank(teacherName)) {
			pagination = teacherManageService.getTeachersLikeNameWithPage(
					example, teacherName);
			params.append("&teacherName=").append(teacherName);
		} else {
			// 根据教研室去查
			pagination = teacherManageService.getTeachersListWithPage(example,
					trsectionId);
			// 分页页面展示
			if (StringUtils.isNotBlank(trsectionId)) {
				params.append("&trsectionId=").append(trsectionId);
			}
		}
		pagination.pageView(url, params.toString());
		model.addAttribute("pagination", pagination);
		return "/manager/teacher/edit";

	}

	/*
	 * 修改教师信息
	 */
	@RequestMapping("update.do")
	public void update(Teacher teacher, HttpServletResponse response) {
		String message = null;
		int flag = teacherManageService.updateTeacherMessage(teacher);
		if (flag > 0) {
			message = "恭喜您,修改教师信息成功！";
		} else {
			message = "修改信息失败!";
		}
		JSONObject jo = new JSONObject();
		jo.put("message", message);
		ResponseUtils.renderJson(response, jo.toString());
	}

	@RequestMapping("initTeacherPassword.do")
	public void initTeacherPassword(Teacher teacher,
			HttpServletResponse response) {
		String message = null;
		teacher.setPassword(md5Pwd.encode(initPassword));
		int flag = teacherManageService.updateTeacherMessage(teacher);
		if (flag > 0) {
			message = "初始化成功！初始化密码为:" + initPassword ;
		} else {
			message = "初始化密码失败!";
		}
		JSONObject jo = new JSONObject();
		jo.put("message", message);
		ResponseUtils.renderJson(response, jo.toString());
	}
}
