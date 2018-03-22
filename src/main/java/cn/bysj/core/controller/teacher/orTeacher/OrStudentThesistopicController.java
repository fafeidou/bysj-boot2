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

import cn.bysj.core.pojo.Student;
import cn.bysj.core.pojo.Thesistopic;
import cn.bysj.core.pojo.vo.TeacherCustom;
import cn.bysj.core.pojo.vo.ThesistopicCustom;
import cn.bysj.core.process.result.DataGridResultInfo;
import cn.bysj.core.process.result.ResultInfo;
import cn.bysj.core.process.result.SubmitResultInfo;
import cn.bysj.core.service.business.ApplicationService;
import cn.bysj.core.service.business.TimeService;
import cn.bysj.core.service.teacher.StudentService;
import cn.bysj.core.service.teacher.ThesistopicService;
import cn.bysj.core.web.common.Constants;

/**
 * 
 * ClassName: OrStudentThesistopicController
 * 
 * @Description: 普通教师管理选择课题
 * @author it小祥
 * @date 2017年1月23日
 */
@Controller
@RequestMapping("/teacher/orTeacher/studentThesistopic/")
public class OrStudentThesistopicController {
	@Value("${initPassword}")
	private String initPassword;
	@Autowired
	private ThesistopicService thesistopicService;
	/**
	 * 
	 * @Description: 查询所有选择自己课题的学生
	 * @param @param model
	 * @param @param request
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author it小祥
	 * @date 2017年1月24日
	 */
	@RequestMapping("toListSelectThesistopic.do")
	public String toListSelectThesistopic(ModelMap model, HttpServletRequest request) {
		return "/teacher/orTeacher/studentThesistopic/listSelectThesistopic";
	}
	
	// 用户查询页面的结果集
	// 最终DataGridResultInfo通过@ResponseBody将java对象转成json
	@RequestMapping("listSelectThesistopic.do")
	public @ResponseBody DataGridResultInfo listSelectThesistopic(ThesistopicCustom thesistopicCustom, int page,
			int rows, HttpServletRequest request) throws Exception {
		int total = 0;
		List<ThesistopicCustom> list = new ArrayList<ThesistopicCustom>();
		// 根据传来的条件查出所有老师 , 查出该院系中的所有课题
		HttpSession session = request.getSession();
		TeacherCustom teacherCustom = (TeacherCustom) session.getAttribute(Constants.TEACHER_SESSION);
		thesistopicCustom.setTeacherId(teacherCustom.getTeacher().getTeacherId());
		total = thesistopicService.getThesistopicBeSelectedCustomCount(thesistopicCustom);
		list = thesistopicService.getThesistopicCustomBeSelectedList(thesistopicCustom, page, rows);

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
		return "/teacher/orTeacher/studentThesistopic/detaileThesistopic";
	}


	/**
	 * 
	 * @Description: 指定选择自己课题的学生
	 * @param @param
	 *            thesistopic
	 * @param @param
	 *            model
	 * @param @param
	 *            request
	 * @param @return
	 * @param @throws
	 *            Exception
	 * @return SubmitResultInfo
	 * @throws @author
	 *             it小祥
	 * @date 2017年1月23日
	 */
	@RequestMapping("specifyStudent.do")
	public @ResponseBody SubmitResultInfo specifyStudent(ThesistopicCustom custom, ModelMap model,
			HttpServletRequest request) throws Exception {
		// 默认为成功
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setType(ResultInfo.TYPE_RESULT_WARN);
		
		HttpSession session = request.getSession();
		TeacherCustom teacherCustom = (TeacherCustom) session.getAttribute(Constants.TEACHER_SESSION);
		Integer departmentId = teacherCustom.getDepartmentId();
		custom.setDepartmentId(departmentId);
		Long now = new Date().getTime();
		 //必须在指定时间内指定
		if(timeService.compareTeacherTime(now, departmentId)){
			//看学生的状态是否为等待分配
			//String WaitingForDistribution = "等待分配"; // 2
			//String DistributionSuccess = "分配完成"; // 3
			Student student = studentService.getstudentByStudentId(custom.getStudentId());
			Integer studentState = student.getStudentState();
			if(studentState == 2){
				//String WaitingForDistribution = "等待分配"; // 5
				//看课题当前状态是否为等待分配  
				Thesistopic thesistopic = new Thesistopic();
				thesistopic.setThesisTopicId(custom.getThesisTopicId());
				Thesistopic t = thesistopicService.getThesistopicById(thesistopic);
				Byte topicState = t.getTopicState();
				if(topicState == 5){
					//1、删除除了该学生id选择的记录其他的所有该课题的记录   没有修改其他学生的状态  因为 其他学生要么为等待选择或者为等待分配，都可以再选
					//2、更新论文表该论文的状态为分配成功
					//3、更新该学生的状态为分配成功
					//1、2、3为同一事务
					applicationService.teacherSelectStudent(custom);
					resultInfo.setType(ResultInfo.TYPE_RESULT_WARN);
					resultInfo.setMessage("恭喜您，指定成功！");
					
				}else{
					resultInfo.setMessage("论文状态不能选择！");
				}
			}else{
				resultInfo.setMessage("学生现在状态不能选择！");
			}
		}else{
			resultInfo.setMessage("现在不在教师选择学生时间，请耐心等待！");
		}
		
		
		// 将执行结果返回页面
		SubmitResultInfo submitResultInfo = new SubmitResultInfo(resultInfo);
		return submitResultInfo;
	}
	@Autowired
	private TimeService timeService;
	@Autowired
	private ApplicationService applicationService;
	@Autowired
	private StudentService studentService;
}
