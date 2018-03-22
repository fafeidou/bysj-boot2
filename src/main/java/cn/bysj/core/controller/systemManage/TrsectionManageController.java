package cn.bysj.core.controller.systemManage;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.bysj.core.pojo.Departments;
import cn.bysj.core.pojo.Teacher;
import cn.bysj.core.pojo.Trsection;
import cn.bysj.core.pojo.TrsectionExample;
import cn.bysj.core.service.systemManage.DepartmentManageService;
import cn.bysj.core.service.systemManage.TeacherManageService;
import cn.bysj.core.service.systemManage.TrsetionManageService;
import cn.bysj.core.web.utils.ResponseUtils;
import cn.itcast.common.page.Pagination;

/**
 * 教研室管理 ClassName: DepartmentManageController
 * 
 * @Description: TODO
 * @author it小祥
 * @date 2016年11月5日
 */
@Controller
@RequestMapping("/manage/trsection/")
public class TrsectionManageController {
	@Autowired
	private TrsetionManageService trsectionManageService;
	@Autowired
	private DepartmentManageService departmentManageService;
	@Autowired
	private TeacherManageService teacherManageService;

	/*
	 * 跳转到院系管理界面
	 */
	@RequestMapping("toAdd.do")
	public String toDepartmentAdd(String message, ModelMap model) throws Exception {
		List<Departments> departments = departmentManageService.getAllDepartments();
		model.addAttribute("departments", departments);
		if (StringUtils.isNotBlank(message)) {
			message = URLDecoder.decode(message, "UTF-8");
			model.addAttribute("message", message);
		}
		return "/manager/trsection/add";
	}

	/*
	 * 模糊查询姓名
	 */
	@RequestMapping("searchdepartmentName.do")
	public void searchdepartmentName(String departmentName, HttpServletResponse response) {
		List<Departments> departments = null;
		if (StringUtils.isNotBlank(departmentName)) {
			departments = departmentManageService.getDepartmentsLikeName(departmentName);
		}

		JSONObject jo = new JSONObject();
		if (departments != null && departments.size() > 0) {
			for (int i = 0; i < departments.size(); i++) {
				jo.put("message" + i, departments.get(i).getDepartmentName());
			}
		}
		ResponseUtils.renderJson(response, jo.toString());
	}

	/*
	 * 添加一个教研室
	 */
	@RequestMapping(value = "add.do")
	public String add(Trsection trsection, Integer departmentId, ModelMap model) throws Exception {
		String message = null;
		// 判断院系是否为空
		if (departmentId != null) {
			trsection.setDepartmentId(departmentId);
			// 判断教研室名字是否为空
			if (StringUtils.isNotBlank(trsection.getSectionName())) {
				// 判断教研室名字是否存在
				List<Trsection> trsections = trsectionManageService.getTrsectionsByName(trsection.getSectionName());
				if (!(trsections.size() > 0 && trsections != null)) {
					trsectionManageService.addTrsection(trsection);
					message = "教研室:" + trsection.getSectionName() + "添加成功!";
				} else {
					// 教研室名字已经存在
					message = "教研室名字已经存在,添加失败!";
				}
			} else {
				// 教研室名字为空
				message = "教研室名字为空,添加失败!";
			}
		} else {
			message = "院系名称为空,添加失败!";
		}
		// TODO 重定向乱码问题未解决
		if (StringUtils.isNotBlank(message)) {
			message = URLEncoder.encode(message, "UTF-8");
			model.addAttribute("message", message);
		}
		return "redirect:/manage/trsection/toAdd.do";
	}

	/*
	 * 去编辑教研室界面
	 */
	@RequestMapping(value = "toEdit.do")
	public String toEdit(Departments department, Integer pageNo, ModelMap model) {
		// 查询所有院系
		List<Departments> departments = departmentManageService.getAllDepartments();
		model.addAttribute("departments", departments);

		// 查询分页
		StringBuilder params = new StringBuilder();
		// 分页对象
		TrsectionExample example = new TrsectionExample();
		// 如果页号为null 或小于1 置为1
		// 页号
		example.setPageNo(Pagination.cpn(pageNo));
		// 设置每页数
		example.setPageSize(5);
		Pagination pagination = trsectionManageService.getDepartmentsListWithPage(example, department);
		// 分页页面展示
		String url = "/manage/department/toEdit.do";
		if (department.getDepartmentId() != null) {
			params.append("&departmentName=").append(department.getDepartmentId());
			model.addAttribute("departmentName", department.getDepartmentId());
		}
		pagination.pageView(url, params.toString());

		model.addAttribute("pagination", pagination);

		return "/manager/trsection/edit";
	}

	/*
	 * 更新教研室的名字
	 */
	@RequestMapping(value = "update.do")
	public void update(Trsection trsection, HttpServletResponse response) {
		int returnkey = trsectionManageService.updateTrsectionNameById(trsection);
		String message = null;
		if (returnkey > 0) {
			message = "教研室名字修改成功!";
		} else {
			message = "教研室名字修改失败!";
		}

		JSONObject jo = new JSONObject();
		jo.put("message", message);
		ResponseUtils.renderJson(response, jo.toString());
	}

	/*
	 * 删除教研室
	 */
	@RequestMapping("delete.do")
	public void delete(Trsection trsection, HttpServletResponse response) {
		JSONObject jo = new JSONObject();
		String message = null;
		// 判断这个教研室中是否有老师，有老师不能删除
		List<Teacher> teachers = teacherManageService.getTeachersByTrsectionId(trsection.getTrsectionId());

		if (teachers.size() > 0 && teachers != null) {
			message = "教研室中有老师,删除失败!";
		} else {
			// 没有教师可以删除
			Integer flag = trsectionManageService.deleteTrsectionById(trsection.getTrsectionId());
			if (flag > 0) {
				message = "(" + trsection.getSectionName() + ")" + "删除成功!";
			} else {
				message = "删除失败!";
			}
		}
		jo.put("message", message);
		ResponseUtils.renderJson(response, jo.toString());

	}
}
