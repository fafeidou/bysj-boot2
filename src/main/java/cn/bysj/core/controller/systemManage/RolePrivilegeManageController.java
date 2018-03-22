package cn.bysj.core.controller.systemManage;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.bysj.core.pojo.Departments;
import cn.bysj.core.pojo.Role;
import cn.bysj.core.pojo.TeacherExample;
import cn.bysj.core.pojo.TechroleKey;
import cn.bysj.core.pojo.Trsection;
import cn.bysj.core.service.systemManage.DepartmentManageService;
import cn.bysj.core.service.systemManage.RoleManageService;
import cn.bysj.core.service.systemManage.TeacherManageService;
import cn.bysj.core.service.systemManage.TrsetionManageService;
import cn.bysj.core.web.utils.ResponseUtils;
import cn.itcast.common.page.Pagination;

/**
 * 角色权限 ClassName: RolePrivilegeManageController
 * 
 * @Description: TODO
 * @author it小祥
 * @date 2016年11月5日
 */
@Controller
@RequestMapping("/manage/role/")
public class RolePrivilegeManageController {

	@Autowired
	private DepartmentManageService departmentManageService;
	@Autowired
	private TrsetionManageService trsetionManageService;
	@Autowired
	private TeacherManageService teacherManageService;
	@Autowired
	private RoleManageService roleManageService;

	/*
	 * 教师信息展示以及对应哪些角色
	 */
	@RequestMapping("toAssignRoles.do")
	public String toAssignRoles(String departmentId, String trsectionId,
			Integer pageNo, ModelMap model) {
		// 查询所有院系
		List<Departments> departments = departmentManageService
				.getAllDepartments();
		model.addAttribute("departments", departments);
		List<Trsection> trsections = trsetionManageService
				.getTrsectionsByDepartmentId(departments.get(0)
						.getDepartmentId());
		model.addAttribute("trsections", trsections);
		// 查询所有教研室 ------ 首次展示不需要展示教研室
		// List<Trsection> trsections =
		// trsetionManageService.getAllTrsections();
		// model.addAttribute("trsections", trsections);
		// 查询分页
		StringBuilder params = new StringBuilder();
		TeacherExample example = new TeacherExample();
		// 如果页号为null 或小于1 置为1
		// 页号
		example.setPageNo(Pagination.cpn(pageNo));

		// 设置每页数
		example.setPageSize(6);
		Pagination pagination = teacherManageService.getTeachersListWithPage(
				example, trsectionId);
		// 分页页面展示
		String url = "/manage/role/toAssignRoles.do";
		if (StringUtils.isNotBlank(trsectionId)) {
			params.append("&trsectionId=").append(trsectionId);
		}
		pagination.pageView(url, params.toString());
		model.addAttribute("pagination", pagination);

		// 查询出所有角色
		List<Role> roles = roleManageService.getAllRoles();
		model.addAttribute("roles", roles);

		return "/manager/role/assignRoles";
	}

	/*
	 * ---------------------------过期 修改教师的角色 角色没有 说明该角色是哪个教研室 、 哪个院系、
	 * 只能通过teacher去查 你既然选择教研室主任，一定是他所在院系、所在教研室的教研室主任
	 */
	/*
	 * public void updateTeacherRole(String teacherId,String
	 * roleIds,HttpServletResponse response) { Integer parseTeacherId =
	 * Integer.parseInt(teacherId); String[] strRoleIds = roleIds.split(",");
	 * List<Integer> parseRoleIds = new ArrayList<Integer>(); for(int i = 0;i <
	 * strRoleIds.length;i ++){ parseRoleIds.add(i,
	 * Integer.parseInt(strRoleIds[i])); }
	 * roleManageService.updateTeacherRole(parseTeacherId,parseRoleIds); String
	 * message = "恭喜您,修改教师角色成功！"; JSONObject jo = new JSONObject();
	 * jo.put("message", message); ResponseUtils.renderJson(response,
	 * jo.toString()); }
	 */
	/*
	 * 教师只能有一种角色
	 */
	@RequestMapping(value = "updateTeacherRole.do")
	public void updateTeacherRole(TechroleKey techroleKey,
			HttpServletResponse response) {
		String message = null;
		Integer flag = roleManageService.updateTeacherRole(techroleKey);
		if(flag > 0){
			 message = "恭喜您,修改教师角色成功！";
		}else{
			 message = "修改教师角色失败!";
		}
		JSONObject jo = new JSONObject();
		jo.put("message", message);
		ResponseUtils.renderJson(response, jo.toString());
	}

}
