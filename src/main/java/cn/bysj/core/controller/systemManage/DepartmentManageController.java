package cn.bysj.core.controller.systemManage;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import cn.bysj.core.service.teacher.ClassesService;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.bysj.core.pojo.Classes;
import cn.bysj.core.pojo.Departments;
import cn.bysj.core.pojo.DepartmentsExample;
import cn.bysj.core.pojo.Trsection;
import cn.bysj.core.service.systemManage.DepartmentManageService;
import cn.bysj.core.service.systemManage.TrsetionManageService;
import cn.bysj.core.web.utils.ResponseUtils;
import cn.itcast.common.page.Pagination;

/**
 * 院系管理 ClassName: DepartmentManageController
 * 
 * @Description: TODO
 * @author it小祥
 * @date 2016年11月5日
 */
@Controller
@RequestMapping("/manage/department/")
public class DepartmentManageController {
	@Autowired
	private DepartmentManageService departmentManageService;
	@Autowired
	private TrsetionManageService trsetionManageService;
	@Autowired
	private ClassesService classesService;

	/*
	 * 跳转到院系管理界面
	 */
	@RequestMapping("toAdd.do")
	public String toDepartmentAdd() {
		return "/manager/department/add";
	}

	/*
	 * 添加院系
	 */
	@RequestMapping("add.do")
	public String add(Departments department, ModelMap model) {
		// 插入状态的返回
		String message = "";

		// 判断数据库里面有没有该数据,如果就就不让插入了
		List<Departments> list = departmentManageService.findDepartmentByName(department);
		if (list.size() > 0 && list != null) {
			message = "插入数据失败，该院系已经存在了!";
		} else {
			if (StringUtils.isNotBlank(department.getDepartmentName())) {
				Integer i = departmentManageService.AddDepartment(department);
				if (i > 0) {
					message = "院系:" + department.getDepartmentName() + "-->插入数据成功!";
				}
			} else {
				message = "不能插入空数据";
			}

		}
		model.addAttribute("message", message);
		model.addAttribute("department", department);
		return "/manager/department/add";
	}

	/**
	 * 跳转到编辑院系界面
	 */
	@RequestMapping("toEdit.do")
	public String toEdit(String departmentName, Integer pageNo, ModelMap model) {
		StringBuilder params = new StringBuilder();
		// 分页对象
		DepartmentsExample example = new DepartmentsExample();
		// 如果页号为null 或小于1 置为1
		// 页号
		example.setPageNo(Pagination.cpn(pageNo));
		// 设置每页数
		example.setPageSize(10);
		Pagination pagination = departmentManageService.getDepartmentsListWithPage(example, departmentName);
		// 分页页面展示
		String url = "/manage/department/toEdit.do";
		if (org.apache.commons.lang3.StringUtils.isNotBlank(departmentName)) {
			params.append("&departmentName=").append(departmentName);
			model.addAttribute("departmentName", departmentName);
		}
		pagination.pageView(url, params.toString());

		model.addAttribute("pagination", pagination);
		return "/manager/department/edit";
	}

	/*
	 * 更新院系信息
	 */
	@RequestMapping("update.do")
	public void update(Departments department, HttpServletResponse response) {
		// 修改
		departmentManageService.updateDepartmentByKey(department);
		JSONObject jo = new JSONObject();
		jo.put("message", "修改成功");
		ResponseUtils.renderJson(response, jo.toString());
	}

	/*
	 * 删除院系
	 */
	@RequestMapping("delete.do")
	public void delete(Departments department, HttpServletResponse response) {
		JSONObject jo = new JSONObject();
		String message = null;
		// 如果院系中由教研室或者有班级 不能删除
		// 判断这个院系中是否有教研室
		List<Trsection> trsections = trsetionManageService.getTrsectionsByDepartmentId(department.getDepartmentId());

		if (trsections.size() > 0 && trsections != null) {
			message = "院系有教研室信息,删除失败!";
		} else {
			// 判断这个院系中是否班级
			List<Classes> classes = classesService.getClassByDepartmentID(department.getDepartmentId());
			if (classes.size() > 0 && classes != null) {
				message = "院系中有班级信息,删除失败!";
			} else {
				departmentManageService.deleteDepartmentByID(department);
				message = "(" + department.getDepartmentName() + ")" + "删除成功!";
			}
		}

		jo.put("message", message);
		ResponseUtils.renderJson(response, jo.toString());

	}
}
