package cn.bysj.core.controller.teacher.trSecretary;

import java.io.File;
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
import cn.bysj.core.pojo.vo.ThesistopicExcel;
import cn.bysj.core.process.result.DataGridResultInfo;
import cn.bysj.core.process.result.ResultInfo;
import cn.bysj.core.process.result.ResultUtil;
import cn.bysj.core.process.result.SubmitResultInfo;
import cn.bysj.core.service.teacher.TeacherService;
import cn.bysj.core.service.teacher.ThesistopicService;
import cn.bysj.core.service.teacher.TopicsourcetypeService;
import cn.bysj.core.service.teacher.TopictypeService;
import cn.bysj.core.web.common.Constants;
import cn.bysj.core.web.utils.ExcelExportSXXSSF;

/**
 * 
 * ClassName: ThesistopicController
 * 
 * @Description: 教研秘书管理课题
 * @author it小祥
 * @date 2017年1月17日
 */
@Controller
@RequestMapping("/teacher/trSecretary/thesistopicManage/")
public class ThesistopicController {
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
		List<Teacher> teachers = teacherService.getTeacherListByDeparmentId(teacherCustom.getDepartmentId(), null,
				null);
		model.addAttribute("teachers", teachers);
		return "/teacher/trSecretary/thesistopicManage/listThesistopic";
	}

	// 用户查询页面的结果集
	// 最终DataGridResultInfo通过@ResponseBody将java对象转成json

	@RequestMapping("listThesistopic.do")
	public @ResponseBody DataGridResultInfo listThesistopic(ThesistopicCustom thesistopicCustom, int page, int rows,
			HttpServletRequest request) throws Exception {
		int total = 0;

		List<ThesistopicCustom> list = new ArrayList<ThesistopicCustom>();
		// 根据传来的条件查出所有老师 , 查出该院系中的所有课题
		HttpSession session = request.getSession();
		TeacherCustom teacherCustom = (TeacherCustom) session.getAttribute(Constants.TEACHER_SESSION);
		thesistopicCustom.setDepartmentId(teacherCustom.getDepartmentId());

		total = thesistopicService.getThesistopicCustomCount(thesistopicCustom);
		list = thesistopicService.getThesistopicCustomList(thesistopicCustom, page, rows);

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
		return "/teacher/trSecretary/thesistopicManage/detaileThesistopic";
	}

	// 导出提交
	@RequestMapping("thesistopicExport.do")
	public @ResponseBody SubmitResultInfo thesistopicExport(Thesistopic thesistopic, HttpServletRequest request)
			throws Exception {
		// String realPath = path.toString().replaceAll("\\\\", "/") +
		// "/person1.xlsx";
		// 调用封装类执行导出
		// String path = request.getSession().getServletContext()
		// .getRealPath("upload");
		// 导出文件存放的路径，并且是虚拟目录指向的路径
		// String filePath = "d:/upload/linshi/";
		// 改为从系统参数配置表获取参数值
		String filePath = request.getSession().getServletContext().getRealPath("upload").replaceAll("\\\\", "/")
				+ "/thesistopic/";
		// String filePath =
		// "D://JAVA_TOMACT_WORK//bysj//_apache-tomcat-7.0.55-windows-x86//apache-tomcat-7.0.55//webapps//bysj//upload//";
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdir();
		}
		// System.out.println(filePath);
		// 导出文件的前缀
		String filePrefix = "thesistopic";
		// -1表示关闭自动刷新，手动控制写磁盘的时机，其它数据表示多少数据在内存保存，超过的则写入磁盘
		int flushRows = 100;

		// 定义导出数据的title
		List<String> fieldNames = new ArrayList<String>();
		fieldNames.add("论文标题");
		fieldNames.add("论文英文标题");
		fieldNames.add("论文来源类型名称");
		fieldNames.add("论文类型名称");
		fieldNames.add("论文目标和要求");
		fieldNames.add("主要研究内容");
		fieldNames.add("论文状态");
		fieldNames.add("论文添加时间");
		fieldNames.add("论文最后修改时间");
		fieldNames.add("论文简介");
		fieldNames.add("毕业年份");
		fieldNames.add("教师姓名");
		fieldNames.add("学生姓名");
		// 告诉导出类数据list中对象的属性，让ExcelExportSXXSSF通过反射获取对象的值
		List<String> fieldCodes = new ArrayList<String>();
		fieldCodes.add("thesisTitle");//
		fieldCodes.add("thesisEnglishTile");//
		fieldCodes.add("sourceTypeName");
		fieldCodes.add("typeName");
		fieldCodes.add("projectRequirement");
		fieldCodes.add("workloadReqirement");
		fieldCodes.add("topicStateString");
		fieldCodes.add("CreateDateString");
		fieldCodes.add("LastUseDateString");
		fieldCodes.add("note");
		fieldCodes.add("graduationYear");
		fieldCodes.add("teacherName");
		fieldCodes.add("studentName");
		// 上边的代码可以优化为，将title和title对应的 pojo的属性，使用map存储
		// ....
		// 注意：fieldCodes和fieldNames个数必须相同且属性和title顺序一一对应，这样title和内容才一一对应
		// 开始导出，执行一些workbook及sheet等对象的初始创建
		ExcelExportSXXSSF excelExportSXXSSF = ExcelExportSXXSSF.start(filePath, "/upload/thesistopic/", filePrefix,
				fieldNames, fieldCodes, flushRows);
		HttpSession session = request.getSession();
		TeacherCustom teacherCustom = (TeacherCustom) session.getAttribute(Constants.TEACHER_SESSION);
		// 导出的数据通过service取出
		List<ThesistopicExcel> list = thesistopicService.getThesistopicExcel(thesistopic,teacherCustom.getDepartmentId());
		// 执行导出
		excelExportSXXSSF.writeDatasByObject(list);
		// 输出文件，返回下载文件的http地址，已经包括虚拟目录
		String webpath = excelExportSXXSSF.exportFile();
		// System.out.println(webpath);
		return ResultUtil.createSubmitResult(
				ResultUtil.createSuccess("resources.messages", 101, new Object[] { list.size(), webpath// 下载地址
				}));
	}
	// 跳转到教研室主任审核课题界面
	@RequestMapping("toReviewThesistopic.do")
	public String toReviewThesistopic(ModelMap model,Integer thesisTopicId) {
		model.addAttribute("thesisTopicId", thesisTopicId);
		return "/teacher/trSecretary/thesistopicManage/reviewThesistopic";
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
		
		//只能审核的状态有  2 3 
		//查出当前课题的状态
		Thesistopic t = thesistopicService.getThesistopicById(thesistopic);
		Byte topicState = t.getTopicState();
		if(topicState == 2 || topicState == 3){
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
