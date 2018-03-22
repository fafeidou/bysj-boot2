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

import cn.bysj.core.pojo.Trsection;
import cn.bysj.core.pojo.vo.TeacherCustom;
import cn.bysj.core.process.result.DataGridResultInfo;
import cn.bysj.core.process.result.ResultInfo;
import cn.bysj.core.process.result.SubmitResultInfo;
import cn.bysj.core.service.teacher.TrsectionService;
import cn.bysj.core.web.common.Constants;

/**
 * 教研秘书教研室管理 ClassName: DepartmentManageController
 * 
 * @Description: TODO
 * @author it小祥
 * @date 2016年11月5日
 */
@Controller
@RequestMapping("/teacher/trSecretary/")
public class TrsectionController {
	@Autowired
	private TrsectionService trsectionService;

	@RequestMapping("toListTrsection.do")
	public String toListTrsection() {
		return "/teacher/trSecretary/listTrsection";
	}

	// 用户查询页面的结果集
	// 最终DataGridResultInfo通过@ResponseBody将java对象转成json
	@RequestMapping("listTrsection.do")
	public @ResponseBody DataGridResultInfo listTrsection(Trsection trsection,
			int page,// 页码
			int rows, HttpServletRequest request) throws Exception {

		int total = 0;
		List<Trsection> list = new ArrayList<Trsection>();
		// 根据教研室名称进行模糊查询
		if (StringUtils.isNotBlank(trsection.getSectionName())) {
			total = trsectionService
					.getTrsectionCountByTrSectionLikeName(trsection
							.getSectionName());
			list = trsectionService.getTrsectionListByTrSectionLikeName(
					trsection.getSectionName(), page, rows);
		} else {
			// 根据session中教师所在的院系id去查询
			HttpSession session = request.getSession();
			TeacherCustom teacherCustom = (TeacherCustom) session
					.getAttribute(Constants.TEACHER_SESSION);
			total = trsectionService
					.getTrsectionCountByDeparmentId(teacherCustom
							.getDepartmentId());
			list = trsectionService.getTrsectionListByDeparmentId(
					teacherCustom.getDepartmentId(), page, rows);
		}

		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		// 填充 total
		dataGridResultInfo.setTotal(total);
		// 填充 rows
		dataGridResultInfo.setRows(list);
		return dataGridResultInfo;

	}

	@RequestMapping("toAddTrsection.do")
	public String toAddTrsection() {
		return "/teacher/trSecretary/addTrsection";
	}

	@RequestMapping("addTrsection.do")
	public @ResponseBody SubmitResultInfo addTrsection(Trsection trsection,
			HttpServletRequest request) throws Exception {
		// 默认为成功
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setType(ResultInfo.TYPE_RESULT_SUCCESS);
		resultInfo.setMessage("操作成功！");

		// 从session中获取到departmentId
		HttpSession session = request.getSession();
		TeacherCustom teacherCustom = (TeacherCustom) session
				.getAttribute(Constants.TEACHER_SESSION);
		trsection.setDepartmentId(teacherCustom.getDepartmentId());
		trsectionService.addTrsection(trsection);

		// 将执行结果返回页面
		SubmitResultInfo submitResultInfo = new SubmitResultInfo(resultInfo);
		return submitResultInfo;

	}
	/*
	 * 删除教研室信息
	 */
	@RequestMapping("deleteTrsection.do")
	public @ResponseBody SubmitResultInfo deleteTrsection(Integer trsectionId)
			throws Exception {
		// 默认为成功
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setType(ResultInfo.TYPE_RESULT_SUCCESS);
		resultInfo.setMessage("删除成功！");

		trsectionService.deleteTrsectionByTrsectionId(trsectionId);

		SubmitResultInfo submitResultInfo = new SubmitResultInfo(resultInfo);
		return submitResultInfo;
	}
	@RequestMapping("toEditTrsection.do")
	public  String toEditTrsection(Integer trsectionId,ModelMap model){
		Trsection trsection = trsectionService.getTrsectionByTrsectionId(trsectionId);
		model.addAttribute("trsection", trsection);
		return "/teacher/trSecretary/editTrsection";
	}
	/*
	 * 修改教研室信息
	 */
		 @RequestMapping("editTrsection.do")
		public @ResponseBody SubmitResultInfo editTrsection(Trsection trsection){
			// 默认为成功
				ResultInfo resultInfo = new ResultInfo();
				resultInfo.setType(ResultInfo.TYPE_RESULT_SUCCESS);
				resultInfo.setMessage("修改成功！");
				trsectionService.updateTrsectionByTrsectionId(trsection);
				SubmitResultInfo submitResultInfo = new SubmitResultInfo(resultInfo);
				return submitResultInfo;
	 }
}
