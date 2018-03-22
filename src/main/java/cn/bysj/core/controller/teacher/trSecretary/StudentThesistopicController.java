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

import cn.bysj.core.pojo.Classes;
import cn.bysj.core.pojo.Departments;
import cn.bysj.core.pojo.Student;
import cn.bysj.core.pojo.vo.StudentThesistopicCustom;
import cn.bysj.core.pojo.vo.TeacherCustom;
import cn.bysj.core.pojo.vo.ThesistopicExcel;
import cn.bysj.core.process.result.DataGridResultInfo;
import cn.bysj.core.process.result.ResultUtil;
import cn.bysj.core.process.result.SubmitResultInfo;
import cn.bysj.core.service.teacher.ClassesService;
import cn.bysj.core.service.teacher.ThesistopicService;
import cn.bysj.core.web.common.Constants;
import cn.bysj.core.web.utils.ExcelExportSXXSSF;

/**
 * 
 * ClassName: StudentThesistopicController
 * 
 * @Description: 学生课题管理
 * @author it小祥
 * @date 2017年1月20日
 */
@Controller
@RequestMapping("/teacher/trSecretary/studentThesistopicManage/")
public class StudentThesistopicController {
	@Value("${initPassword}")
	private String initPassword;
	@Autowired
	private ThesistopicService thesistopicService;
	@Autowired
	private ClassesService classesService;

	@RequestMapping("toListThesistopic.do")
	public String toListThesistopic(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		TeacherCustom teacherCustom = (TeacherCustom) session.getAttribute(Constants.TEACHER_SESSION);
		// 查出该院系的所有班级
		List<Classes> classes = classesService.getClassesListByDeparmentId(teacherCustom.getDepartmentId(), null, null);
		model.addAttribute("classes", classes);
		return "/teacher/trSecretary/studentThesistopicManage/listThesistopic";
	}

	// 用户查询页面的结果集
	// 最终DataGridResultInfo通过@ResponseBody将java对象转成json

	@RequestMapping("listThesistopic.do")
	public @ResponseBody DataGridResultInfo listThesistopic(Integer classisId, Integer studentState, int page, int rows,
			HttpServletRequest request) throws Exception {
		int total = 0;

		List<StudentThesistopicCustom> list = new ArrayList<StudentThesistopicCustom>();
		// 根据传来的条件查出所有老师 , 查出该院系中的所有课题
		StudentThesistopicCustom custom = new StudentThesistopicCustom();
		HttpSession session = request.getSession();
		TeacherCustom teacherCustom = (TeacherCustom) session.getAttribute(Constants.TEACHER_SESSION);
		Departments departments = new Departments();
		departments.setDepartmentId(teacherCustom.getDepartmentId());
		custom.setDepartments(departments);
		;
		if (classisId != null) {
			Classes classes = new Classes();
			classes.setClassisId(classisId);
			custom.setClasses(classes);
		}
		if (studentState != null) {
			Student student = new Student();
			student.setStudentState(studentState);
			custom.setStudent(student);
		}

		total = thesistopicService.getThesistopicCustomCountFromStudent(custom);
		list = thesistopicService.getThesistopicCustomListFromStudent(custom, page, rows);

		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		// 填充 total
		dataGridResultInfo.setTotal(total);
		// 填充 rows
		dataGridResultInfo.setRows(list);
		return dataGridResultInfo;

	}

	// 导出提交
	@RequestMapping("studentThesistopicExport.do")
	public @ResponseBody SubmitResultInfo thesistopicExport(Integer classisId, Integer studentState,
			HttpServletRequest request) throws Exception {
		StudentThesistopicCustom custom = new StudentThesistopicCustom();
		HttpSession session = request.getSession();
		TeacherCustom teacherCustom = (TeacherCustom) session.getAttribute(Constants.TEACHER_SESSION);
		Departments departments = new Departments();
		departments.setDepartmentId(teacherCustom.getDepartmentId());
		custom.setDepartments(departments);
		if (classisId != null) {
			Classes classes = new Classes();
			classes.setClassisId(classisId);
			custom.setClasses(classes);
		}
		if (studentState != null) {
			Student student = new Student();
			student.setStudentState(studentState);
			custom.setStudent(student);
		}
		String filePath = request.getSession().getServletContext().getRealPath("upload").replaceAll("\\\\", "/")
				+ "/studentThesistopic/";
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdir();
		}
		// 导出文件的前缀
		String filePrefix = "studentThesistopic";
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
		fieldCodes.add("studentStateString");
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
		ExcelExportSXXSSF excelExportSXXSSF = ExcelExportSXXSSF.start(filePath, "/upload/studentThesistopic/",
				filePrefix, fieldNames, fieldCodes, flushRows);
		// 导出的数据通过service取出
		List<ThesistopicExcel> list = thesistopicService.getStudentThesistopicExcel(custom);
		// 执行导出
		excelExportSXXSSF.writeDatasByObject(list);
		// 输出文件，返回下载文件的http地址，已经包括虚拟目录
		String webpath = excelExportSXXSSF.exportFile();
		return ResultUtil.createSubmitResult(
				ResultUtil.createSuccess("resources.messages", 101, new Object[] { list.size(), webpath// 下载地址
				}));
	}

}
