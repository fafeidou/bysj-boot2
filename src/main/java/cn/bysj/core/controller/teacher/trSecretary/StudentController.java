package cn.bysj.core.controller.teacher.trSecretary;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.bysj.core.service.teacher.ClassesService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.bysj.core.pojo.Classes;
import cn.bysj.core.pojo.Student;
import cn.bysj.core.pojo.Teacher;
import cn.bysj.core.pojo.vo.StudentCustom;
import cn.bysj.core.pojo.vo.StudentExcel;
import cn.bysj.core.pojo.vo.TeacherCustom;
import cn.bysj.core.process.result.DataGridResultInfo;
import cn.bysj.core.process.result.ResultInfo;
import cn.bysj.core.process.result.ResultUtil;
import cn.bysj.core.process.result.SubmitResultInfo;
import cn.bysj.core.service.BycjService;
import cn.bysj.core.service.teacher.StudentService;
import cn.bysj.core.service.teacher.TeacherService;
import cn.bysj.core.service.teacher.TechRoleService;
import cn.bysj.core.web.common.Constants;
import cn.bysj.core.web.encode.Md5Pwd;
import cn.bysj.core.web.excel.core.BingExcel;
import cn.bysj.core.web.excel.core.BingExcelBuilder;
import cn.bysj.core.web.excel.core.impl.BingExcelImpl.SheetVo;
import cn.bysj.core.web.utils.ExcelExportSXXSSF;

/**
 * 
 * ClassName: StudentController
 * 
 * @Description: 教研室主任管理学生
 * @author it小祥
 * @date 2017年1月15日
 */
@Controller
@RequestMapping("/teacher/trSecretary/studentManage/")
public class StudentController {
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private TechRoleService techRoleService;
	@Autowired
	private BycjService bycjService;
	@Autowired
	private Md5Pwd md5Pwd;
	@Value("${initPassword}")
	private String initPassword;
	@Autowired
	private ClassesService classesService;
	@Autowired
	private StudentService studentService;

	@RequestMapping("toListStudent.do")
	public String toListStudent(ModelMap model, HttpServletRequest request) {
		// 查出所有的班级
		HttpSession session = request.getSession();
		TeacherCustom teacherCustom = (TeacherCustom) session.getAttribute(Constants.TEACHER_SESSION);
		List<Classes> classes = classesService.getClassByDepartmentID(teacherCustom.getDepartmentId());
		model.addAttribute("classes", classes);
		return "/teacher/trSecretary/studentManage/listStudent";
	}

	// 用户查询页面的结果集
	// 最终DataGridResultInfo通过@ResponseBody将java对象转成json
	@RequestMapping("listStudent.do")
	public @ResponseBody DataGridResultInfo listStudent(Integer classisId, String studentName, int page, // 页码
			int rows, HttpServletRequest request) throws Exception {
		int total = 0;

		List<Student> list = new ArrayList<Student>();

		// 根据学生名字或者班级id查询学生信息
		if (StringUtils.isNotBlank(studentName) || classisId != null) {
			total = studentService.getStudentCountLikeStudentNameOrClassisId(studentName, classisId);
			list = studentService.getStudentListLikeStudentNameOrClassisId(studentName, classisId, page, rows);
		} else {
			// 根据session中教师所在的院系id去查询院系中所有的学生
			HttpSession session = request.getSession();
			TeacherCustom teacherCustom = (TeacherCustom) session.getAttribute(Constants.TEACHER_SESSION);
			total = studentService.getStudentCountByDeparmentId(teacherCustom.getDepartmentId());
			list = studentService.getStudentListByDeparmentId(teacherCustom.getDepartmentId(), page, rows);
		}
		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		// 填充 total
		dataGridResultInfo.setTotal(total);
		// 填充 rows
		dataGridResultInfo.setRows(list);
		return dataGridResultInfo;

	}

	// 去学生添加页面
	@RequestMapping("toAddStudent.do")
	public String toAddStudent(ModelMap model, HttpServletRequest request) {
		// 查出所有该院系的所有班级
		HttpSession session = request.getSession();
		TeacherCustom teacherCustom = (TeacherCustom) session.getAttribute(Constants.TEACHER_SESSION);
		List<Classes> classes = classesService.getClassByDepartmentID(teacherCustom.getDepartmentId());
		model.addAttribute("classes", classes);
		return "/teacher/trSecretary/studentManage/addStudent";
	}

	// 添加学生
	@RequestMapping("addStudent.do")
	public @ResponseBody SubmitResultInfo addStudent(Student student, HttpServletRequest request) throws Exception {
		// 默认为成功
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setType(ResultInfo.TYPE_RESULT_SUCCESS);
		resultInfo.setMessage("操作成功！" + "学生初始化密码为:" + initPassword);
		// 判断传来的classisId是否为空
		if (student.getClassisId() != null) {
			// 判断学生学号是否为空
			if (StringUtils.isNotBlank(student.getStudentNo()) && student.getStudentNo() != null) {
				// 判断学生的姓名是否为空
				if (StringUtils.isNotBlank(student.getStudentName()) && student.getStudentName() != null) {
					// 判断该学生学号是否已经存在
					Student s = studentService.getStudentByStudentNo(student.getStudentNo());
					if (s == null) {
						// 课题号thesisTopicId初始化为空
						// 设置默认密码
						student.setPassword(md5Pwd.encode(initPassword));
						// 设置学生状态 默认为 信息未完善
						student.setStudentState(Constants.StudentInformationNotPerfect);
						// 设置学生在校状态 默认为 在校
						student.setStudentSchoolState(Constants.StudentAtSchool);
						// 添加该学生
						studentService.addStudent(student);
					} else {
						resultInfo.setType(ResultInfo.TYPE_RESULT_WARN);
						resultInfo.setMessage("该学生学号已经存在!!!");
					}

				} else {
					resultInfo.setType(ResultInfo.TYPE_RESULT_WARN);
					resultInfo.setMessage("学生姓名不能为空!!!");
				}
			} else {
				resultInfo.setType(ResultInfo.TYPE_RESULT_WARN);
				resultInfo.setMessage("学生学号不能为空!!!");
			}
		} else {
			resultInfo.setType(ResultInfo.TYPE_RESULT_WARN);
			resultInfo.setMessage("请选择班级!!!");
		}
		// 将执行结果返回页面
		SubmitResultInfo submitResultInfo = new SubmitResultInfo(resultInfo);
		return submitResultInfo;
	}

	// 去学生批量添加界面
	@RequestMapping("toAddStudentByExcel.do")
	public String toAddStudentByExcel(ModelMap model, HttpServletRequest request) {
		// 查出所有的班级
		HttpSession session = request.getSession();
		TeacherCustom teacherCustom = (TeacherCustom) session.getAttribute(Constants.TEACHER_SESSION);
		List<Classes> classes = classesService.getClassByDepartmentID(teacherCustom.getDepartmentId());
		model.addAttribute("classes", classes);
		return "/teacher/trSecretary/studentManage/addStudentExcel";
	}

	// 学生批量添加
	@RequestMapping("addStudentByExcel.do")
	public @ResponseBody SubmitResultInfo addStudentByExcel(Integer classisId,
			@RequestParam(required = false) MultipartFile excel, HttpServletRequest request) throws Exception {
		// 默认为成功
		ResultInfo resultInfo = new ResultInfo();
		// 记录一下导入数据成功的个数
		Integer count = 1;
		// 判断班级id是否为空
		if (classisId != null) {
			// 获取excel的文件名以及后缀
			String excelName = excel.getOriginalFilename();
			String path = request.getSession().getServletContext().getRealPath("upload");
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			excelName = dateFormat.format(date) + excelName;
			File targetFile = new File(path, excelName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			// 保存
			excel.transferTo(targetFile);
			BingExcel bing = BingExcelBuilder.toBuilder().builder();
			try {
				SheetVo<StudentExcel> vo = bing.readFile(targetFile, StudentExcel.class, 1);
				if (vo.getObjectList() != null && vo.getObjectList().size() > 0) {

					for (StudentExcel studentExcel : vo.getObjectList()) {
						Student student = new Student();
						// 判断学号是否为空
						if (StringUtils.isNotBlank(studentExcel.getStudentNo())
								&& studentExcel.getStudentNo() != null) {
							// 判断学号是否位数字
							if (StringUtils.isNumeric(studentExcel.getStudentNo())) {
								// 判断姓名是否为空
								if (StringUtils.isNotBlank(studentExcel.getStudentName())
										&& studentExcel.getStudentName() != null) {
									student.setStudentName(studentExcel.getStudentName());
									// 判断学号是否存在
									Student s = studentService.getStudentByStudentNo(studentExcel.getStudentNo());
									if (s == null) {
										student.setStudentNo(studentExcel.getStudentNo());
										// 课题号thesisTopicId初始化为空
										// 设置班级id
										student.setClassisId(classisId);
										// 设置默认密码
										student.setPassword(md5Pwd.encode(initPassword));
										// 设置学生状态 默认为 信息未完善
										student.setStudentState(Constants.StudentInformationNotPerfect);
										// 设置学生在校状态 默认为 在校
										student.setStudentSchoolState(Constants.StudentAtSchool);
										if (StringUtils.isNotBlank(studentExcel.getPhone())
												&& studentExcel.getPhone() != null) {
											student.setPhone(studentExcel.getPhone());
										}
										if (StringUtils.isNotBlank(studentExcel.getQq())
												&& studentExcel.getQq() != null) {
											student.setQq(studentExcel.getQq());
										}
										if (StringUtils.isNotBlank(studentExcel.getEmail())
												&& studentExcel.getEmail() != null) {
											student.setEmail(studentExcel.getEmail());
										}
										// 添加该学生
										studentService.addStudent(student);
										resultInfo.setType(ResultInfo.TYPE_RESULT_SUCCESS);
										resultInfo.setMessage("导入成功!" + count++ + "条记录," + "初始化密码为:" + initPassword);
									} else {
										resultInfo.setType(ResultInfo.TYPE_RESULT_WARN);
										resultInfo.setMessage("第" + count + "条学生学号已经存在,之前条数导入成功");
									}
								} else {
									resultInfo.setType(ResultInfo.TYPE_RESULT_WARN);
									resultInfo.setMessage("第" + count + "条学生姓名不能为空,之前条数导入成功");
								}
							} else {
								resultInfo.setType(ResultInfo.TYPE_RESULT_WARN);
								resultInfo.setMessage("第" + count + "条学号必须有数字组成,之前条数导入成功");
							}
						} else {
							resultInfo.setType(ResultInfo.TYPE_RESULT_WARN);
							resultInfo.setMessage("第" + count + "条学号不能为空,之前条数导入成功");
						}
					}
				} else {
					resultInfo.setType(ResultInfo.TYPE_RESULT_WARN);
					resultInfo.setMessage("excel表中没有数据 ");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			resultInfo.setType(ResultInfo.TYPE_RESULT_WARN);
			resultInfo.setMessage("请选择班级!!!");
		}
		// 将执行结果返回页面
		SubmitResultInfo submitResultInfo = new SubmitResultInfo(resultInfo);
		return submitResultInfo;
	}

	@RequestMapping("toEditStudent.do")
	public String toEditStudent(Integer studentId, ModelMap model, HttpServletRequest request) {
		Student student = studentService.getstudentByStudentId(studentId);
		model.addAttribute("student", student);
		HttpSession session = request.getSession();
		TeacherCustom teacherCustom = (TeacherCustom) session.getAttribute(Constants.TEACHER_SESSION);
		List<Classes> classes = classesService.getClassByDepartmentID(teacherCustom.getDepartmentId());
		model.addAttribute("classes", classes);
		return "/teacher/trSecretary/studentManage/editStudent";
	}

	/*
	 * 修改教研室信息
	 */
	@RequestMapping("editStudent.do")
	public @ResponseBody SubmitResultInfo editStudent(Student student) {
		// 默认为成功
		ResultInfo resultInfo = new ResultInfo();

		// 学号是否为空
		if (StringUtils.isNotBlank(student.getStudentNo()) && student.getStudentNo() != null) {
			// 学生姓名是否为空
			if (StringUtils.isNotBlank(student.getStudentName()) && student.getStudentName() != null) {
				// 学生学号必须是数字
				if (StringUtils.isNumeric(student.getStudentNo())) {
					// 更新数据
					studentService.updateStudent(student);
					resultInfo.setType(ResultInfo.TYPE_RESULT_SUCCESS);
					resultInfo.setMessage("修改成功！");
				} else {
					resultInfo.setType(ResultInfo.TYPE_RESULT_WARN);
					resultInfo.setMessage("学生学号必须是数字！");
				}
			} else {
				resultInfo.setType(ResultInfo.TYPE_RESULT_WARN);
				resultInfo.setMessage("学生的姓名不能空！");
			}
		} else {
			resultInfo.setType(ResultInfo.TYPE_RESULT_WARN);
			resultInfo.setMessage("学生的学号不能空！");
		}

		SubmitResultInfo submitResultInfo = new SubmitResultInfo(resultInfo);
		return submitResultInfo;
	}

	@RequestMapping("initStudentPassword.do")
	public @ResponseBody SubmitResultInfo initStudentPassword(Student student) {
		// 默认为成功
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setType(ResultInfo.TYPE_RESULT_SUCCESS);
		resultInfo.setMessage("恭喜您，初始化密码成功！" + "初始密码为：" + initPassword);
		student.setPassword(md5Pwd.encode(initPassword));
		studentService.updateStudent(student);
		SubmitResultInfo submitResultInfo = new SubmitResultInfo(resultInfo);
		return submitResultInfo;
	}

	/*
	 * 赋予教研室主人角色
	 */
	@RequestMapping("specifieTrDirector.do")
	public @ResponseBody SubmitResultInfo specifieTrDirector(Teacher teacher) {
		// 默认为成功
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setType(ResultInfo.TYPE_RESULT_SUCCESS);
		resultInfo.setMessage("指定成功！");
		// 根据传来的教师id去去查教师的信息
		Teacher t = teacherService.getTeacherByTeacherId(teacher.getTeacherId());
		// 查出教研室之前的教研室主任是否存在
		// 根据教师的id,角色id去查询
		Integer returnTeacherId = bycjService.getTeacherIdByTrsectionIdAndRoleId(t.getTrsectionId(),
				Constants.TercherAndResearchDirecor);
		// 比较returnTeacherId 和 传过来的 teacherid 是否相同
		if (returnTeacherId == null) {
			// 设置界面传来的teacherId 设置为 教研室主任
			techRoleService.updateRoleByTeacherId(teacher.getTeacherId(), Constants.TercherAndResearchDirecor);
		} else if (returnTeacherId != null && !teacher.getTeacherId().equals(returnTeacherId)) {
			// 设置界面传来的teacherId 设置为 教研室主任
			techRoleService.updateRoleByTeacherId(teacher.getTeacherId(), Constants.TercherAndResearchDirecor);
			// 将以前的那个教研室主任改为普通教师
			techRoleService.updateRoleByTeacherId(returnTeacherId, Constants.OrdinaryTeacher);
		}
		SubmitResultInfo submitResultInfo = new SubmitResultInfo(resultInfo);
		return submitResultInfo;
	}

	// 导出提交
	@RequestMapping("studentExport.do")
	public @ResponseBody SubmitResultInfo studentExport(Integer classisId, HttpServletRequest request)
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
				+ "/student/";
		// String filePath =
		// "D://JAVA_TOMACT_WORK//bysj//_apache-tomcat-7.0.55-windows-x86//apache-tomcat-7.0.55//webapps//bysj//upload//";
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdir();
		}
		// System.out.println(filePath);
		// 导出文件的前缀
		String filePrefix = "student";
		// -1表示关闭自动刷新，手动控制写磁盘的时机，其它数据表示多少数据在内存保存，超过的则写入磁盘
		int flushRows = 100;

		// 定义导出数据的title
		List<String> fieldNames = new ArrayList<String>();
		fieldNames.add("学号");
		fieldNames.add("姓名");
		fieldNames.add("手机号");
		fieldNames.add("QQ号");
		fieldNames.add("邮箱");
		fieldNames.add("学生状态");
		fieldNames.add("学生在校状态");
		// 告诉导出类数据list中对象的属性，让ExcelExportSXXSSF通过反射获取对象的值
		List<String> fieldCodes = new ArrayList<String>();
		fieldCodes.add("studentNo");//
		fieldCodes.add("studentName");//
		fieldCodes.add("phone");
		fieldCodes.add("qq");
		fieldCodes.add("email");
		fieldCodes.add("qq");
		fieldCodes.add("studentStateString");
		fieldCodes.add("studentSchoolState");

		// 上边的代码可以优化为，将title和title对应的 pojo的属性，使用map存储
		// ....
		// 注意：fieldCodes和fieldNames个数必须相同且属性和title顺序一一对应，这样title和内容才一一对应
		// 开始导出，执行一些workbook及sheet等对象的初始创建
		ExcelExportSXXSSF excelExportSXXSSF = ExcelExportSXXSSF.start(filePath, "/upload/student/", filePrefix,
				fieldNames, fieldCodes, flushRows);

		// 导出的数据通过service取出
		List<StudentCustom> list = studentService.getStudentCustomListByClassisId(classisId);
		// 执行导出
		excelExportSXXSSF.writeDatasByObject(list);
		// 输出文件，返回下载文件的http地址，已经包括虚拟目录
		String webpath = excelExportSXXSSF.exportFile();
		// System.out.println(webpath);
		return ResultUtil.createSubmitResult(
				ResultUtil.createSuccess("resources.messages", 101, new Object[] { list.size(), webpath// 下载地址
				}));
	}

}
