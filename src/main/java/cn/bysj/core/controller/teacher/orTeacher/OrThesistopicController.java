package cn.bysj.core.controller.teacher.orTeacher;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bysj.core.pojo.Teacher;
import cn.bysj.core.pojo.Topicsourcetype;
import cn.bysj.core.pojo.Topictype;
import cn.bysj.core.pojo.vo.TeacherCustom;
import cn.bysj.core.pojo.vo.ThesistopicCustom;
import cn.bysj.core.process.result.DataGridResultInfo;
import cn.bysj.core.service.teacher.TeacherService;
import cn.bysj.core.service.teacher.ThesistopicService;
import cn.bysj.core.service.teacher.TopicsourcetypeService;
import cn.bysj.core.service.teacher.TopictypeService;
import cn.bysj.core.web.common.Constants;

/**
 * 
 * ClassName: ThesistopicController
 * 
 * @Description: 教研秘书管理课题
 * @author it小祥
 * @date 2017年1月17日
 */
@Controller
@RequestMapping("/teacher/orTeacher/otherThesistopic/")
public class OrThesistopicController {
	@Autowired
	private TeacherService teacherService;
	@Value("${initPassword}")
	private String initPassword;
	@Autowired
	private TopicsourcetypeService topicsourcetypeService;
	@Autowired
	private TopictypeService topictypeService;
	@Autowired
	private ThesistopicService thesistopicService;

	@RequestMapping("toListOtherThesistopic.do")
	public String toListOtherThesistopic(ModelMap model, HttpServletRequest request) {
		// 查出所有的课题来源类型
		List<Topicsourcetype> topicsourcetypes = topicsourcetypeService.getTopicsourcetype();
		model.addAttribute("topicsourcetypes", topicsourcetypes);
		// 查出所有课题类型
		List<Topictype> topictypes = topictypeService.getTopictypes();
		model.addAttribute("topictypes", topictypes);
		// 查出该院系的所有老师
		HttpSession session = request.getSession();
		TeacherCustom teacherCustom = (TeacherCustom) session.getAttribute(Constants.TEACHER_SESSION);
		List<Teacher> teachers = teacherService.getTeacherListByDeparmentId(teacherCustom.getDepartmentId(), null,
				null);
		model.addAttribute("teachers", teachers);
		return "/teacher/orTeacher/otherThesistopic/listOtherThesistopic";
	}

	// 用户查询页面的结果集
	// 最终DataGridResultInfo通过@ResponseBody将java对象转成json

	@RequestMapping("listOtherThesistopic.do")
	public @ResponseBody DataGridResultInfo listOtherThesistopic(ThesistopicCustom thesistopicCustom, int page, int rows,
			HttpServletRequest request) throws Exception {
		int total = 0;
		List<ThesistopicCustom> list = new ArrayList<ThesistopicCustom>();
		// 根据传来的条件查出所有老师 , 查出该院系中的所有课题
		HttpSession session = request.getSession();
		TeacherCustom teacherCustom = (TeacherCustom) session.getAttribute(Constants.TEACHER_SESSION);
		thesistopicCustom.setDepartmentId(teacherCustom.getDepartmentId());
		total = thesistopicService.getThesistopicCustomCountForOrteacher(thesistopicCustom);
		list = thesistopicService.getThesistopicCustomListForOrteacher(thesistopicCustom, page, rows);
		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		// 填充 total
		dataGridResultInfo.setTotal(total);
		// 填充 rows
		dataGridResultInfo.setRows(list);
		return dataGridResultInfo;

	}

	// 查看课题详细信息
	@RequestMapping("viewDetails.do")
	public String viewDetails(Integer thesisTopicId, ModelMap model, HttpServletRequest request) {

		ThesistopicCustom thesistopicCustom = thesistopicService.getThesistopicCustomById(thesisTopicId);

		model.addAttribute("thesistopicCustom", thesistopicCustom);
		return "/teacher/orTeacher/otherThesistopic/detaileThesistopic";
	}
	
}
