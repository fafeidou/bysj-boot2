package cn.bysj.core.controller.teacher.trSecretary;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bysj.core.pojo.Classes;
import cn.bysj.core.pojo.vo.TeacherCustom;
import cn.bysj.core.process.result.DataGridResultInfo;
import cn.bysj.core.process.result.ResultInfo;
import cn.bysj.core.process.result.SubmitResultInfo;
import cn.bysj.core.service.teacher.ClassesService;
import cn.bysj.core.web.common.Constants;

/**
 * 教研室主任管理班级 ClassName: ClassController
 * 
 * @Description: TODO
 * @author it小祥
 * @date 2017年1月15日
 */
@Controller
@RequestMapping("/teacher/trSecretary/classManage/")
public class ClassController {
	@Autowired
	private ClassesService classesService;

	// 跳转到学生列表界面
	@RequestMapping("toListClass.do")
	public String toListClass() {
		return "/teacher/trSecretary/classManage/listClass";
	}

	// 用户查询页面的结果集
	// 最终DataGridResultInfo通过@ResponseBody将java对象转成json
	@RequestMapping("listClass.do")
	public @ResponseBody DataGridResultInfo listClass(Classes classes, int page, // 页码
			int rows, HttpServletRequest request) throws Exception {
		int total = 0;
		List<Classes> list = new ArrayList<Classes>();
		// 根据班级名称进行模糊查询
		if (StringUtils.isNotBlank(classes.getClassName())) {
			total = classesService.getClassesCountByClassesLikeName(classes.getClassName());
			list = classesService.getClassesListByClassesLikeName(classes.getClassName(), page, rows);
		} else {
			// 根据session中教师所在的院系id去查询
			HttpSession session = request.getSession();
			TeacherCustom teacherCustom = (TeacherCustom) session.getAttribute(Constants.TEACHER_SESSION);
			total = classesService.getClassesCountByDeparmentId(teacherCustom.getDepartmentId());
			list = classesService.getClassesListByDeparmentId(teacherCustom.getDepartmentId(), page, rows);
		}

		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		// 填充 total
		dataGridResultInfo.setTotal(total);
		// 填充 rows
		dataGridResultInfo.setRows(list);
		return dataGridResultInfo;

	}

	@RequestMapping("toAddClasses.do")
	public String toAddClasses() {
		return "/teacher/trSecretary/classManage/addClass";
	}

	@RequestMapping("addClasses.do")
	public @ResponseBody SubmitResultInfo addClasses(Classes classes, HttpServletRequest request) throws Exception {
		// 默认为成功
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setType(ResultInfo.TYPE_RESULT_SUCCESS);
		resultInfo.setMessage("添加成功！");

		// 从session中获取到departmentId
		HttpSession session = request.getSession();
		TeacherCustom teacherCustom = (TeacherCustom) session.getAttribute(Constants.TEACHER_SESSION);
		classes.setDepartmentId(teacherCustom.getDepartmentId());
		// 判断是否存在有相同名字的院系
		Classes c = classesService.getClassesByClassesName(classes.getClassName());

		if (c != null) {
			resultInfo.setType(ResultInfo.TYPE_RESULT_SUCCESS);
			resultInfo.setMessage("院系名已经存在,添加失败！");
		} else {
			classesService.addClasses(classes);
		}

		// 将执行结果返回页面
		SubmitResultInfo submitResultInfo = new SubmitResultInfo(resultInfo);
		return submitResultInfo;

	}

	/*
	 * 删除班级
	 */
	@RequestMapping("deleteClass.do")
	public @ResponseBody SubmitResultInfo deleteClass(Integer classisId) throws Exception {
		// 默认为成功
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setType(ResultInfo.TYPE_RESULT_SUCCESS);
		resultInfo.setMessage("删除成功！");
		classesService.deleteClassesByClassisId(classisId);
		SubmitResultInfo submitResultInfo = new SubmitResultInfo(resultInfo);
		return submitResultInfo;
	}

	@RequestMapping("toEditClasses.do")
	public String toEditClasses(Integer classisId, ModelMap model) {
		Classes classes = classesService.getClassesByClassesId(classisId);
		model.addAttribute("classes", classes);
		return "/teacher/trSecretary/classManage/editClass";
	}

	/*
	 * 修改班级信息
	 */
	@RequestMapping("editClasses.do")
	public @ResponseBody SubmitResultInfo editClasses(Classes classes) {
		// 默认为成功
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setType(ResultInfo.TYPE_RESULT_SUCCESS);
		resultInfo.setMessage("修改成功！");
		classesService.updateClassesByClassisId(classes);
		SubmitResultInfo submitResultInfo = new SubmitResultInfo(resultInfo);
		return submitResultInfo;
	}
}
