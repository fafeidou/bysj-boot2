package cn.bysj.core.controller.teacher.trSecretary;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.bysj.core.pojo.Teacher;
import cn.bysj.core.pojo.Trsection;
import cn.bysj.core.pojo.vo.TeacherCustom;
import cn.bysj.core.pojo.vo.TeacherExcel;
import cn.bysj.core.process.result.DataGridResultInfo;
import cn.bysj.core.process.result.ResultInfo;
import cn.bysj.core.process.result.ResultUtil;
import cn.bysj.core.process.result.SubmitResultInfo;
import cn.bysj.core.service.BycjService;
import cn.bysj.core.service.teacher.TeacherService;
import cn.bysj.core.service.teacher.TechRoleService;
import cn.bysj.core.service.teacher.TrsectionService;
import cn.bysj.core.web.common.Constants;
import cn.bysj.core.web.encode.Md5Pwd;
import cn.bysj.core.web.excel.core.BingExcel;
import cn.bysj.core.web.excel.core.BingExcelBuilder;
import cn.bysj.core.web.excel.core.impl.BingExcelImpl.SheetVo;
import cn.bysj.core.web.utils.ExcelExportSXXSSF;

/**
 * 教师管理 ----> 教研秘书专用 ClassName: TeacherController
 * 
 * @Description: TODO
 * @author it小祥
 * @date 2016年11月27日
 */
@Controller
@RequestMapping("/teacher/trSecretary/teacherManage/")
public class TeacherController {
	@Autowired
	private TrsectionService trsectionService;
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

	@RequestMapping("toListTeacher.do")
	public String toListTeacher(ModelMap model, HttpServletRequest request) {
		// 查出所有的教研室
		HttpSession session = request.getSession();
		TeacherCustom teacherCustom = (TeacherCustom) session.getAttribute(Constants.TEACHER_SESSION);
		List<Trsection> trsections = trsectionService.getTrsectionListByDeparmentId(teacherCustom.getDepartmentId());
		model.addAttribute("trsections", trsections);
		return "/teacher/trSecretary/teacherManage/listTeacher";
	}

	// 用户查询页面的结果集
	// 最终DataGridResultInfo通过@ResponseBody将java对象转成json
	@RequestMapping("listTeacher.do")
	public @ResponseBody DataGridResultInfo listTeacher(Integer trsectionId, String teacherName, int page, // 页码
			int rows, HttpServletRequest request) throws Exception {

		int total = 0;
		List<Teacher> list = new ArrayList<Teacher>();

		// 根据教研室名称进行模糊查询
		if (StringUtils.isNotBlank(teacherName)) {
			total = teacherService.getTeacherCountLikeTeacherName(teacherName.trim());
			list = teacherService.getTeacherListLikeTeacherName(teacherName.trim(), page, rows);
		} else if (trsectionId != null) {
			total = teacherService.getTeacherCountByTrsectionId(trsectionId);
			list = teacherService.getTeacherListByTrsectionId(trsectionId, page, rows);
		} else {

			// 根据session中教师所在的院系id去查询院系中所有教师
			HttpSession session = request.getSession();
			TeacherCustom teacherCustom = (TeacherCustom) session.getAttribute(Constants.TEACHER_SESSION);
			total = teacherService.getTeacherCountByDeparmentId(teacherCustom.getDepartmentId());
			list = teacherService.getTeacherListByDeparmentId(teacherCustom.getDepartmentId(), page, rows);
		}
		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		// 填充 total
		dataGridResultInfo.setTotal(total);
		// 填充 rows
		dataGridResultInfo.setRows(list);
		return dataGridResultInfo;

	}

	// 去教师添加页面
	@RequestMapping("toAddTeacher.do")
	public String toAddTeacher() {
		return "/teacher/trSecretary/teacherManage/addTeacher";
	}

	// 教师添加
	@RequestMapping("addTeacher.do")
	public @ResponseBody SubmitResultInfo addTeacher(Teacher teacher, HttpServletRequest request) throws Exception {

		// 默认为成功
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setType(ResultInfo.TYPE_RESULT_SUCCESS);
		resultInfo.setMessage("操作成功！" + "教师初始化密码为:" + initPassword);
		// 查出操作角色所在的教研室
		TeacherCustom teacherCustom = (TeacherCustom) request.getSession().getAttribute(Constants.TEACHER_SESSION);
		teacherService.addTeacher(teacher, teacherCustom.getTrsectionId(), md5Pwd.encode(initPassword));
		// 将执行结果返回页面
		SubmitResultInfo submitResultInfo = new SubmitResultInfo(resultInfo);
		return submitResultInfo;
	}

	// 去教师添加页面
	@RequestMapping("toAddTeacherByExcel.do")
	public String toAddTeacherByExcel(ModelMap model, HttpServletRequest request) {
		// 查出所有教研室

		// 查出操作角色所在的教研室
		TeacherCustom teacherCustom = (TeacherCustom) request.getSession().getAttribute(Constants.TEACHER_SESSION);
		List<Trsection> trsections = trsectionService.getTrsectionListByDeparmentId(teacherCustom.getDepartmentId(),
				null, null);
		model.addAttribute("trsections", trsections);
		return "/teacher/trSecretary/teacherManage/addTeacherExcel";
	}

	// 教师添加
	@RequestMapping("addTeacherByExcel.do")
	public @ResponseBody SubmitResultInfo addTeacherByExcel(Integer trsectionId,@RequestParam(required = false) MultipartFile excel,
			HttpServletRequest request) throws Exception {
		// 默认为成功
		ResultInfo resultInfo = new ResultInfo();
		// 记录一下导入数据成功的个数
		Integer count = 1;
		// 查出操作角色所在的教研室
		TeacherCustom teacherCustom = (TeacherCustom) request.getSession().getAttribute(Constants.TEACHER_SESSION);
		
		if(trsectionId != null){
		
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
			SheetVo<TeacherExcel> vo = bing.readFile(targetFile, TeacherExcel.class, 1);
			for (TeacherExcel teacherExcel : vo.getObjectList()) {
				Teacher teacher = new Teacher();
				teacher.setEmployeeNum(teacherExcel.getEmployeeNum());

				if (!StringUtils.isNumeric(teacherExcel.getJuniorCollegeQuota())
						|| !StringUtils.isNumeric(teacherExcel.getUndergraduateQuota())) {
					// 专科论文数或本科论文数必须为数字
					resultInfo.setType(ResultInfo.TYPE_RESULT_WARN);
					resultInfo.setMessage("第" + count + "条专科论文数或本科论文数必须为数字,之前条数导入成功");
					break;
				}
				teacher.setJuniorCollegeQuota(Integer.parseInt(teacherExcel.getJuniorCollegeQuota()));
				teacher.setUndergraduateQuota(Integer.parseInt(teacherExcel.getUndergraduateQuota()));
				teacher.setTeacherName(teacherExcel.getTeacherName());
				teacher.setProfessionalRank(teacherExcel.getProfessionalRank());
				teacher.setQq(teacherExcel.getQq());
				teacher.setPhone(teacherExcel.getPhone());
				teacher.setEmail(teacherExcel.getEmail());
				teacher.setTeacherSchoolState(teacherExcel.getTeacherSchoolState());
				teacher.setTrsectionId(trsectionId);
				// 查询职工号是否重复如果重复则更新数据
				Teacher t = teacherService.getTeacherByTeacherEmployeeNum(teacher);
				if (t != null) {
					teacher.setTeacherId(t.getTeacherId());
					teacherService.updateTeacherByTeacherId(teacher);
				} else {
					// 开始插入数据
					teacherService.addTeacher(teacher, teacherCustom.getTrsectionId(), md5Pwd.encode(initPassword));
				}
				resultInfo.setType(ResultInfo.TYPE_RESULT_SUCCESS);
				resultInfo.setMessage("导入成功!" + count++ + "条记录," + "初始化密码为:" + initPassword);
			}
		} catch (Exception e) {
		}
		}else{
			resultInfo.setType(ResultInfo.TYPE_RESULT_WARN);
			resultInfo.setMessage("请选择教研室");
		}
		// 将执行结果返回页面
		SubmitResultInfo submitResultInfo = new SubmitResultInfo(resultInfo);
		return submitResultInfo;
	}

	@RequestMapping("toEditTeacher.do")
	public String toEditTeacher(Integer teacherId, ModelMap model) {
		Teacher teacher = teacherService.getTeacherByTeacherId(teacherId);
		model.addAttribute("teacher", teacher);
		return "/teacher/trSecretary/teacherManage/editTeacher";
	}

	/*
	 * 修改教研室信息
	 */
	@RequestMapping("editTeacher.do")
	public @ResponseBody SubmitResultInfo editTeacher(Teacher teacher) {
		// 默认为成功
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setType(ResultInfo.TYPE_RESULT_SUCCESS);
		resultInfo.setMessage("修改成功！");
		teacherService.updateTeacherByTeacherId(teacher);
		SubmitResultInfo submitResultInfo = new SubmitResultInfo(resultInfo);
		return submitResultInfo;
	}

	@RequestMapping("initTeacherPassword.do")
	public @ResponseBody SubmitResultInfo initTeacherPassword(Teacher teacher) {
		// 默认为成功
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setType(ResultInfo.TYPE_RESULT_SUCCESS);
		resultInfo.setMessage("恭喜您，初始化密码成功！" + "初始密码为：" + initPassword);
		teacher.setPassword(md5Pwd.encode(initPassword));
		teacherService.updateTeacherByTeacherId(teacher);
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
	@RequestMapping("teacherExport.do")
	public @ResponseBody SubmitResultInfo teacherExport(Integer trsectionId, HttpServletRequest request)
			throws Exception {
		// String realPath = path.toString().replaceAll("\\\\", "/") +
		// "/person1.xlsx";
		// 调用封装类执行导出
		// String path = request.getSession().getServletContext()
		// .getRealPath("upload");
		// 导出文件存放的路径，并且是虚拟目录指向的路径
		// String filePath = "d:/upload/linshi/";
		// 改为从系统参数配置表获取参数值
		String filePath = request.getSession().getServletContext().getRealPath("upload").replaceAll("\\\\", "/") + "/";
		// String filePath =
		// "D://JAVA_TOMACT_WORK//bysj//_apache-tomcat-7.0.55-windows-x86//apache-tomcat-7.0.55//webapps//bysj//upload//";
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdir();
		}
		// System.out.println(filePath);
		// 导出文件的前缀
		String filePrefix = "teacher";
		// -1表示关闭自动刷新，手动控制写磁盘的时机，其它数据表示多少数据在内存保存，超过的则写入磁盘
		int flushRows = 100;

		// 定义导出数据的title
		List<String> fieldNames = new ArrayList<String>();
		fieldNames.add("工号");
		fieldNames.add("姓名");
		fieldNames.add("职称");
		fieldNames.add("专科人数");
		fieldNames.add("本科人数");
		fieldNames.add("QQ号");
		fieldNames.add("手机号");
		fieldNames.add("邮箱");
		fieldNames.add("在职状态");

		// 告诉导出类数据list中对象的属性，让ExcelExportSXXSSF通过反射获取对象的值
		List<String> fieldCodes = new ArrayList<String>();
		fieldCodes.add("employeeNum");// 药品流水号
		fieldCodes.add("teacherName");// 通用名
		fieldCodes.add("professionalRank");
		fieldCodes.add("juniorCollegeQuota");
		fieldCodes.add("undergraduateQuota");
		fieldCodes.add("qq");
		fieldCodes.add("phone");
		fieldCodes.add("email");
		fieldCodes.add("teacherSchoolState");

		// 上边的代码可以优化为，将title和title对应的 pojo的属性，使用map存储
		// ....
		// 注意：fieldCodes和fieldNames个数必须相同且属性和title顺序一一对应，这样title和内容才一一对应
		// 开始导出，执行一些workbook及sheet等对象的初始创建
		ExcelExportSXXSSF excelExportSXXSSF = ExcelExportSXXSSF.start(filePath, "/upload/", filePrefix, fieldNames,
				fieldCodes, flushRows);

		// 导出的数据通过service取出
		List<Teacher> list = teacherService.getTeacherListByTrsectionId(trsectionId, null, null);
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
