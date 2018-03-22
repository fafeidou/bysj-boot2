package cn.bysj.core.controller.teacher.orTeacher;

import java.util.ArrayList;
import java.util.Date;
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
import cn.bysj.core.web.common.ConstantsTopicState;

/**
 * 
 * ClassName: ThesistopicController
 * 
 * @Description: 教研秘书管理课题
 * @author it小祥
 * @date 2017年1月17日
 */
@Controller
@RequestMapping("/teacher/orTeacher/myselfThesistopic/")
public class OrMyselfThesistopicController {
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

	@RequestMapping("toListMyselfThesistopic.do")
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
		return "/teacher/orTeacher/myselfThesistopic/listMyselfThesistopic";
	}

	// 用户查询页面的结果集
	// 最终DataGridResultInfo通过@ResponseBody将java对象转成json

	@RequestMapping("listMyselfThesistopic.do")
	public @ResponseBody DataGridResultInfo listMyselfThesistopic(ThesistopicCustom thesistopicCustom, int page,
			int rows, HttpServletRequest request) throws Exception {
		int total = 0;
		List<ThesistopicCustom> list = new ArrayList<ThesistopicCustom>();
		// 根据传来的条件查出所有老师 , 查出该院系中的所有课题
		HttpSession session = request.getSession();
		TeacherCustom teacherCustom = (TeacherCustom) session.getAttribute(Constants.TEACHER_SESSION);
		thesistopicCustom.setTeacherId(teacherCustom.getTeacher().getTeacherId());
		total = thesistopicService.getThesistopicCustomCountForOrteacherByTeacherId(thesistopicCustom);
		list = thesistopicService.getThesistopicCustomListForOrteacherByTeacherId(thesistopicCustom, page, rows);

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
		return "/teacher/orTeacher/myselfThesistopic/detaileThesistopic";
	}

	// 去论文修改界面
	@RequestMapping("toEditThesisTopic.do")
	public String toEditThesisTopic(Integer thesisTopicId, ModelMap model, HttpServletRequest request) {
		String message = "";
		ThesistopicCustom thesistopicCustom = thesistopicService.getThesistopicCustomById(thesisTopicId);
		Byte topicState = thesistopicCustom.getThesistopic().getTopicState();

		// 只能修改0、1、3、 不能修改 2 4 5 6
		if (topicState == 2 || topicState == 4 || topicState == 5 || topicState == 6) {
			if (topicState == 2) {
				message = ConstantsTopicState.DepartmentNotVerify + "不能修改";
			} else if (topicState == 4) {
				message = ConstantsTopicState.WaitForChoice + "不能修改";
			} else if (topicState == 5) {
				message = ConstantsTopicState.WaitingForDistribution + "不能修改";
			} else if (topicState == 6) {
				message = ConstantsTopicState.DistributionSuccess + "不能修改";
			}
			model.addAttribute("message", message);
			return "/teacher/orTeacher/myselfThesistopic/error";
		}
		// 查出所有的课题来源类型
		List<Topicsourcetype> topicsourcetypes = topicsourcetypeService.getTopicsourcetype();
		model.addAttribute("topicsourcetypes", topicsourcetypes);
		// 查出所有课题类型
		List<Topictype> topictypes = topictypeService.getTopictypes();
		model.addAttribute("topictypes", topictypes);
		model.addAttribute("thesistopicCustom", thesistopicCustom);
		return "/teacher/orTeacher/myselfThesistopic/editThesisTopic";
	}

	@RequestMapping("editThesisTopic.do")
	public @ResponseBody SubmitResultInfo editThesisTopic(Thesistopic thesistopic) throws Exception {
		// 默认为成功
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setType(ResultInfo.TYPE_RESULT_SUCCESS);
		resultInfo.setMessage("修改成功！");

		// String TrsectionNotVerify = "教研室未审核"; // 0
		// String TrsectionNotPass = "教研室未通过"; // 1
		// String DepartmentNotVerify = "院系未审核"; // 2
		// String DepartmentNotPass = "院系审核未通过"; // 3
		// String WaitForChoice = "等待选择"; // 4
		// String WaitingForDistribution = "等待分配"; // 5
		// String DistributionSuccess = "分配完成"; // 6
		// 只能修改0、1、3、 不能修改 2 4 5 6
		// 查询一下状态
		Thesistopic t = thesistopicService.getThesistopicById(thesistopic);
		Byte topicState = t.getTopicState();
		// 如果状态是0 --> 修改状态还是0
		// 如果状态是1 --> 修改状态是0
		if (topicState == 0 || topicState == 1) {
			thesistopic.setTopicState((byte) 0);
		} else if (topicState == 3) {
			// 如果状态是3 --> 修改状态就是2
			thesistopic.setTopicState((byte) 2);
		}
		// 设置最后修改时间
		thesistopic.setCreateDate(t.getCreateDate());
		thesistopic.setLastUseDate(new Date());
		thesistopicService.updateThesistopic(thesistopic);
		// 将执行结果返回页面
		SubmitResultInfo submitResultInfo = new SubmitResultInfo(resultInfo);
		return submitResultInfo;
	}

	@RequestMapping("toAddThesisTopic.do")
	public String toAddThesisTopic(Thesistopic thesistopic, ModelMap model) throws Exception {
		// 查出所有的课题来源类型
		List<Topicsourcetype> topicsourcetypes = topicsourcetypeService.getTopicsourcetype();
		model.addAttribute("topicsourcetypes", topicsourcetypes);
		// 查出所有课题类型
		List<Topictype> topictypes = topictypeService.getTopictypes();
		model.addAttribute("topictypes", topictypes);
		return "/teacher/orTeacher/myselfThesistopic/addThesisTopic";
	}

	@RequestMapping("addThesisTopic.do")
	public @ResponseBody SubmitResultInfo addThesisTopic(Thesistopic thesistopic, ModelMap model,
			HttpServletRequest request) throws Exception {
		// 默认为成功
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setType(ResultInfo.TYPE_RESULT_SUCCESS);
		resultInfo.setMessage("添加成功！");
		HttpSession session = request.getSession();
		TeacherCustom teacherCustom = (TeacherCustom) session.getAttribute(Constants.TEACHER_SESSION);
		// 设置教师id
		thesistopic.setTeacherId(teacherCustom.getTeacher().getTeacherId());
		// 查询上传类型数量是否超过要求
		// 本科 专科
		// 查询数量
		Teacher teacher = teacherService.getTeacherByTeacherId(teacherCustom.getTeacher().getTeacherId());
		// 查询类型上传数量
		List<Thesistopic> thesistopics = thesistopicService.getThesistopic(thesistopic);
		// 有问题 数据库中论文类型表 本科 为 0 专科为1
		if (thesistopics != null && thesistopics.size() > 0) {
			if (thesistopic.getTopicTypeId() == 0) {
				if (teacher.getUndergraduateQuota() < thesistopics.size() + 1) {
					resultInfo.setType(ResultInfo.TYPE_RESULT_WARN);
					resultInfo.setMessage("超过本科上传允许数量，上传失败!");
				}
			} else if (thesistopic.getTopicTypeId() == 1) {
				if (teacher.getJuniorCollegeQuota() < thesistopics.size() + 1) {
					resultInfo.setType(ResultInfo.TYPE_RESULT_WARN);
					resultInfo.setMessage("超过专科上传允许数量，上传失败!");
				}
			}
		} else if (thesistopics == null) {
			if (thesistopic.getTopicTypeId() == 0) {
				if (teacher.getUndergraduateQuota() < 1) {
					resultInfo.setType(ResultInfo.TYPE_RESULT_WARN);
					resultInfo.setMessage("超过本科上传允许数量，上传失败!");
				}
			} else if (thesistopic.getTopicTypeId() == 1) {
				if (teacher.getJuniorCollegeQuota() < 1) {
					resultInfo.setType(ResultInfo.TYPE_RESULT_WARN);
					resultInfo.setMessage("超过专科上传允许数量，上传失败!");
				}
			}
		}

		// 设置状态
		thesistopic.setTopicState((byte) 0);
		// 设置创建 和 最后修改时间
		Date now = new Date();
		thesistopic.setCreateDate(now);
		thesistopic.setLastUseDate(now);
		thesistopicService.addThesistopic(thesistopic);
		// 将执行结果返回页面
		SubmitResultInfo submitResultInfo = new SubmitResultInfo(resultInfo);
		return submitResultInfo;
	}
}
