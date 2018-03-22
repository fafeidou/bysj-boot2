package cn.bysj.core.controller.student;

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
import cn.bysj.core.pojo.Topicsourcetype;
import cn.bysj.core.pojo.Topictype;
import cn.bysj.core.pojo.vo.StudentCustom;
import cn.bysj.core.pojo.vo.ThesistopicCustom;
import cn.bysj.core.process.result.DataGridResultInfo;
import cn.bysj.core.process.result.ResultInfo;
import cn.bysj.core.process.result.SubmitResultInfo;
import cn.bysj.core.service.business.ApplicationService;
import cn.bysj.core.service.business.TimeService;
import cn.bysj.core.service.student.StudentThesistopicService;
import cn.bysj.core.service.teacher.StudentService;
import cn.bysj.core.service.teacher.TeacherService;
import cn.bysj.core.service.teacher.ThesistopicService;
import cn.bysj.core.service.teacher.TopicsourcetypeService;
import cn.bysj.core.service.teacher.TopictypeService;
import cn.bysj.core.web.common.Constants;

/**
 * 学生本人课题管理 ClassName: StudentMyselfThesistopicController
 * 
 * @Description: TODO
 * @author it小祥
 * @date 2017年1月23日
 */
@Controller
@RequestMapping("/student/thesistopicManage/")
public class StudentMyselfThesistopicController {
	@Autowired
	private StudentThesistopicService studentThesistopicService;
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
	@Autowired
	private TimeService timeService;
	@Autowired
	private ApplicationService applicationService;

	/**
	 * 跳转到论文展示界面
	 */
	@RequestMapping("toListAllThesistopic.do")
	public String toListMyselfInfo(ModelMap model, HttpServletRequest request) {
		// 查出所有的课题来源类型
		List<Topicsourcetype> topicsourcetypes = topicsourcetypeService.getTopicsourcetype();
		model.addAttribute("topicsourcetypes", topicsourcetypes);
		// 查出所有课题类型
		List<Topictype> topictypes = topictypeService.getTopictypes();
		model.addAttribute("topictypes", topictypes);
		// 查出该院系的所有老师
		HttpSession session = request.getSession();
		StudentCustom studentCustom = (StudentCustom) session.getAttribute(Constants.STUDENT_SESSION);
		List<Teacher> teachers = teacherService.getTeacherListByDeparmentId(studentCustom.getDepartmentId(), null,
				null);
		model.addAttribute("teachers", teachers);
		return "/student/thesistopicManage/listAllThesistopic";
	}

	@RequestMapping("listAllThesistopic.do")
	public @ResponseBody DataGridResultInfo listAllThesistopic(ThesistopicCustom thesistopicCustom, int page, int rows,
			HttpServletRequest request) throws Exception {
		int total = 0;
		List<ThesistopicCustom> list = new ArrayList<ThesistopicCustom>();
		// 根据传来的条件查出所有老师 , 查出该院系中的所有课题
		HttpSession session = request.getSession();
		StudentCustom studentCustom = (StudentCustom) session.getAttribute(Constants.STUDENT_SESSION);
		thesistopicCustom.setDepartmentId(studentCustom.getDepartmentId());

		total = studentThesistopicService.getThesistopicCustomCount(thesistopicCustom);
		list = studentThesistopicService.getThesistopicCustomList(thesistopicCustom, page, rows);

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
		return "/student/thesistopicManage/detaileThesistopic";
	}

	@RequestMapping("toListMyselfThesistopic.do")
	public String toListMyselfThesistopic(ModelMap model, HttpServletRequest request) {
		// 查出所有的课题来源类型
		List<Topicsourcetype> topicsourcetypes = topicsourcetypeService.getTopicsourcetype();
		model.addAttribute("topicsourcetypes", topicsourcetypes);
		// 查出所有课题类型
		List<Topictype> topictypes = topictypeService.getTopictypes();
		model.addAttribute("topictypes", topictypes);
		// 查出该院系的所有老师
		HttpSession session = request.getSession();
		StudentCustom studentCustom = (StudentCustom) session.getAttribute(Constants.STUDENT_SESSION);
		List<Teacher> teachers = teacherService.getTeacherListByDeparmentId(studentCustom.getDepartmentId(), null,
				null);
		model.addAttribute("teachers", teachers);
		return "/student/thesistopicManage/listMyselfThesistopic";
	}

	@RequestMapping("listMyselfThesistopic.do")
	public @ResponseBody DataGridResultInfo listMyselfThesistopic(ThesistopicCustom thesistopicCustom, Integer page,
			Integer rows, HttpServletRequest request) throws Exception {
		int total = 0;
		List<ThesistopicCustom> list = new ArrayList<ThesistopicCustom>();
		// 根据学生的id查询所有论文
		HttpSession session = request.getSession();
		StudentCustom studentCustom = (StudentCustom) session.getAttribute(Constants.STUDENT_SESSION);
		thesistopicCustom.setStudentId(studentCustom.getStudent().getStudentId());
		total = studentThesistopicService.getThesistopicCustomCountMyself(thesistopicCustom);
		list = studentThesistopicService.getThesistopicCustomListMyself(thesistopicCustom, page, rows);

		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		// 填充 total
		dataGridResultInfo.setTotal(total);
		// 填充 rows
		dataGridResultInfo.setRows(list);
		return dataGridResultInfo;

	}

	@RequestMapping("choiceThesistopic.do")
	public @ResponseBody SubmitResultInfo choiceThesistopic(ThesistopicCustom custom, HttpServletRequest request) {
		// 默认为失败
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setType(ResultInfo.TYPE_RESULT_WARN);
		HttpSession session = request.getSession();
		StudentCustom studentCustom = (StudentCustom) session.getAttribute(Constants.STUDENT_SESSION);
		custom.setStudentId(studentCustom.getStudent().getStudentId());
		Integer studentState = studentService.getstudentByStudentId(studentCustom.getStudent().getStudentId())
				.getStudentState();
		Integer departmentId = studentCustom.getDepartmentId();
		// String InfoNotVerify = "信息未完善"; // 0
		// String WaitForChoice = "等待选择"; // 1
		// String WaitingForDistribution = "等待分配"; // 2
		// String DistributionSuccess = "分配完成"; // 3
		// 看状态如果信息未完善不能选
		if (studentState != 0) {
			// 如果分配完成不能选
			if (studentState != 3) {
				// 如果不在学生第一次和第二次选题之间 ，不能选
				Long now = new Date().getTime();
				// 判断能不能选
				if (timeService.compareStudentTime(now, departmentId)) {
					// 如果超过学生所允许的最大数量，不能选
					if (applicationService.compareStudentCanSelect(studentCustom.getStudent().getStudentId(),
							departmentId)) {
						// 如果超过题目被选的的最大数量不能选
						if (applicationService.compareThesistopicCanBeSelected(custom.getThesisTopicId(),
								departmentId)) {
							// 在application中添加一条信息
							applicationService.addThesistopicRecord(custom);
							resultInfo.setType(ResultInfo.TYPE_RESULT_SUCCESS);
							resultInfo.setMessage("恭喜您，选择成功，等待分配");
						} else {
							resultInfo.setMessage("该论文已经超过选择最大被选择数量，请选择其他题目!");
						}
					} else {
						resultInfo.setMessage("你选择的论文数量已经超过选择最大选择数量，不能再选择了!");
					}
				} else {
					resultInfo.setMessage("当前不在选题时间，请耐心等待!");
				}
			} else {
				resultInfo.setMessage("你的论文分配已经完成，不能再选了!");
			}
		} else {
			resultInfo.setMessage("信息未完善，不能开始选题!");
		}

		SubmitResultInfo submitResultInfo = new SubmitResultInfo(resultInfo);
		return submitResultInfo;
	}

	@Autowired
	private StudentService studentService;
}
