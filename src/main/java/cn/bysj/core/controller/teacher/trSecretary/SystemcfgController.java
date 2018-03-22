package cn.bysj.core.controller.teacher.trSecretary;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bysj.core.pojo.Systemcfg;
import cn.bysj.core.pojo.vo.SystemcfgCustom;
import cn.bysj.core.pojo.vo.TeacherCustom;
import cn.bysj.core.process.result.ResultInfo;
import cn.bysj.core.process.result.SubmitResultInfo;
import cn.bysj.core.service.teacher.SystemcfgService;
import cn.bysj.core.web.common.Constants;

/**
 * 教研室主任管理系统 ClassName: SystemcfgController
 * 
 * @Description: TODO
 * @author it小祥
 * @date 2017年1月19日
 */
@Controller
@RequestMapping("/teacher/trSecretary/systemcfgManage/")
public class SystemcfgController {
	@Autowired
	private SystemcfgService systemcfgService;

	// 跳转到学生列表界面
	@RequestMapping("toListSystemcfg.do")
	public String toListSystemcfg(HttpServletRequest request, ModelMap model) {
		HttpSession session = request.getSession();
		TeacherCustom teacherCustom = (TeacherCustom) session.getAttribute(Constants.TEACHER_SESSION);
		SystemcfgCustom systemcfgCustom = systemcfgService.getSystemcfgByDapartmentId(teacherCustom.getDepartmentId());
		if (systemcfgCustom != null) {
			model.addAttribute("systemcfgCustom", systemcfgCustom);
		}
		return "/teacher/trSecretary/systemcfgManage/listSystemcfg";
	}

	/*
	 * 修改班级信息
	 */
	@RequestMapping("editSystemcfg.do")
	public @ResponseBody SubmitResultInfo editSystemcfg(Systemcfg systemcfg) {
		// 默认为成功
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setType(ResultInfo.TYPE_RESULT_SUCCESS);
		resultInfo.setMessage("修改成功！");
		systemcfgService.updateSystemcfg(systemcfg);
		SubmitResultInfo submitResultInfo = new SubmitResultInfo(resultInfo);
		return submitResultInfo;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
	}
}
