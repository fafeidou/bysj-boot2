package cn.bysj.core.controller.teacher.trDirector;

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
import cn.bysj.core.pojo.Thesistopic;
import cn.bysj.core.pojo.Topicsourcetype;
import cn.bysj.core.pojo.Topictype;
import cn.bysj.core.pojo.vo.TeacherCustom;
import cn.bysj.core.pojo.vo.ThesistopicCustom;
import cn.bysj.core.process.result.DataGridResultInfo;
import cn.bysj.core.process.result.ResultInfo;
import cn.bysj.core.process.result.SubmitResultInfo;
import cn.bysj.core.service.teacher.TeacherService;
import cn.bysj.core.service.teacher.ThesistopicService;
import cn.bysj.core.service.teacher.TopicsourcetypeService;
import cn.bysj.core.service.teacher.TopictypeService;
import cn.bysj.core.web.common.Constants;

/**
 * 
 * ClassName: ThesistopicController
 * 
 * @Description: 教研室主任管理课题
 * @author it小祥
 * @date 2017年1月17日
 */
@Controller
@RequestMapping("/teacher/trDirector/thesistopicManage/")
public class TrThesistopicController {
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

	@RequestMapping("toListThesistopic.do")
	public String toListThesistopic(ModelMap model, HttpServletRequest request) {
		// 查出所有的课题来源类型
		List<Topicsourcetype> topicsourcetypes = topicsourcetypeService.getTopicsourcetype();
		model.addAttribute("topicsourcetypes", topicsourcetypes);
		// 查出所有课题类型
		List<Topictype> topictypes = topictypeService.getTopictypes();
		model.addAttribute("topictypes", topictypes);
		// 查出该院系的所有老师
		HttpSession session = request.getSession();
		TeacherCustom teacherCustom = (TeacherCustom) session.getAttribute(Constants.TEACHER_SESSION);
		List<Teacher> teachers = teacherService.getTeacherListByTrsectionId(teacherCustom.getTrsectionId(), null, null);
		model.addAttribute("teachers", teachers);
		return "/teacher/trDirector/thesistopicManage/listThesistopic";
	}

	// 用户查询页面的结果集
	// 最终DataGridResultInfo通过@ResponseBody将java对象转成json

	@RequestMapping("listThesistopic.do")
	public @ResponseBody DataGridResultInfo listThesistopic(ThesistopicCustom thesistopicCustom, int page, int rows,
			HttpServletRequest request) throws Exception {
		int total = 0;

		List<ThesistopicCustom> list = new ArrayList<ThesistopicCustom>();
		// 根据传来的条件查出教研室中的所有老师
		HttpSession session = request.getSession();
		TeacherCustom teacherCustom = (TeacherCustom) session.getAttribute(Constants.TEACHER_SESSION);
		thesistopicCustom.setTrsectionId(teacherCustom.getTeacher().getTrsectionId());
		total = thesistopicService.getThesistopicCustomCountForTrDirector(thesistopicCustom);
		list = thesistopicService.getThesistopicCustomListForTrDirector(thesistopicCustom, page, rows);

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
		return "/teacher/trDirector/thesistopicManage/detaileThesistopic";
	}

	// 跳转到教研室主任审核课题界面
	@RequestMapping("toReviewThesistopic.do")
	public String toReviewThesistopic(ModelMap model,Integer thesisTopicId) {
		model.addAttribute("thesisTopicId", thesisTopicId);
		return "/teacher/trDirector/thesistopicManage/reviewThesistopic";
	}

	// 教研室主任审核课题界面
	@RequestMapping("reviewThesistopic.do")
	public @ResponseBody SubmitResultInfo reviewThesistopic(Thesistopic thesistopic) {

		// 默认为成功
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setType(ResultInfo.TYPE_RESULT_SUCCESS);
		resultInfo.setMessage("审核成功！");
		
		//String TrsectionNotVerify = "教研室未审核"; // 0
		//String TrsectionNotPass = "教研室未通过"; // 1
		//String DepartmentNotVerify = "院系未审核"; // 2
		//String DepartmentNotPass = "院系审核未通过"; // 3
		//String WaitForChoice = "等待选择"; // 4
		//String WaitingForDistribution = "等待分配"; // 5
		//String DistributionSuccess = "分配完成"; // 6
		
		//只能审核的状态有  0 1 
		//查出当前课题的状态
		Thesistopic t = thesistopicService.getThesistopicById(thesistopic);
		Byte topicState = t.getTopicState();
		if(topicState == 0 || topicState == 1){
			thesistopicService.updateThesistopic(thesistopic);
		}else{
			//不能审核
			resultInfo.setType(ResultInfo.TYPE_RESULT_WARN);
			resultInfo.setMessage("课题当前状态不能审核！");
		}
		
		// 将执行结果返回页面
		SubmitResultInfo submitResultInfo = new SubmitResultInfo(resultInfo);
		return submitResultInfo;
	}

}
