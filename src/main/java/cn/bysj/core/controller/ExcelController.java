package cn.bysj.core.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.bysj.core.excel.Person;
import cn.bysj.core.pojo.Teacher;
import cn.bysj.core.pojo.vo.TeacherExcel;
import cn.bysj.core.service.teacher.TeacherService;
import cn.bysj.core.web.excel.core.BingExcel;
import cn.bysj.core.web.excel.core.BingExcelBuilder;
import cn.bysj.core.web.excel.core.BingExcelEvent;
import cn.bysj.core.web.excel.core.BingExcelEventBuilder;
import cn.bysj.core.web.excel.core.BingWriterHandler;
import cn.bysj.core.web.excel.core.impl.BingExcelImpl.SheetVo;

/**
 * @author Administrator
 *
 */
@Controller
public class ExcelController {
	@Autowired
	private TeacherService teacherService;

	/**
	 * 跳转excel
	 */
	@RequestMapping(value = "/test/excel.do")
	public String testExcel() {
		return "/excel";
	}

	/**
	 * 读取excel
	 */
	@RequestMapping(value = "/uploadExcel.do")
	public void uploadPic(@RequestParam(required = false) MultipartFile excel, HttpServletRequest request) {
		// 获取excel的文件名以及后缀
		String excelName = excel.getOriginalFilename();
		String path = request.getSession().getServletContext().getRealPath("upload");
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		excelName = dateFormat.format(date) + excelName;
		File targetFile = new File(path, excelName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			excel.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		BingExcel bing = BingExcelBuilder.toBuilder().builder();

		try {
			SheetVo<TeacherExcel> vo = bing.readFile(targetFile, TeacherExcel.class, 1);
			for (TeacherExcel teacherExcel : vo.getObjectList()) {
				Teacher teacher = new Teacher();
				teacher.setEmployeeNum(teacherExcel.getEmployeeNum());
				// 查询职工号是否重复如果重复则停止
				if (teacherService.getTeacherByTeacherEmployeeNum(teacher) != null) {
					// 工号已存在
					break;
				}
				try {
					teacher.setJuniorCollegeQuota(Integer.parseInt(teacherExcel.getJuniorCollegeQuota()));
					teacher.setUndergraduateQuota(Integer.parseInt(teacherExcel.getUndergraduateQuota()));
				} catch (Exception e) {
					// 专科论文数或本科论文数必须为数字
					break;
				}
				teacher.setTeacherName(teacherExcel.getTeacherName());
				teacher.setProfessionalRank(teacherExcel.getProfessionalRank());
				teacher.setQq(teacherExcel.getQq());
				teacher.setPhone(teacherExcel.getPhone());
				teacher.setEmail(teacher.getEmail());
				teacher.setTeacherSchoolState(teacherExcel.getTeacherSchoolState());
				//开始插入数据
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/downloadExcel.do")
	public void downloadExcel(HttpServletResponse response, HttpServletRequest request) throws Exception {
		String path = request.getSession().getServletContext().getRealPath("upload");

		BingExcelEvent bing = BingExcelEventBuilder.toBuilder().builder();
		/**
		 * 对于数据量非常大时候，注意一点就是数据绝对不能放入到内存， 你如果想初始化一个长多为百万级的list，劝你趁早放弃
		 */
		// 测试百万级的写出,
		// BingWriterHandler writerHandler =
		// bing.writeFile("D:/JAVA_TOMACT_WORK/bysj/apache-tomcat-7.0.67/me-webapps/bysj/upload/person1.xlsx");
		String realPath = path.toString().replaceAll("\\\\", "/") + "/person1.xlsx";
		System.out.println("realPath" + realPath);
		BingWriterHandler writerHandler = bing.writeFile(realPath);

		for (int i = 0; i < 1000000; i++) {
			writerHandler.writeLine(new Person(23, RandomStringUtils.randomAlphanumeric(4), Math.random() * 1000));
		}

		writerHandler.close();

		response.sendRedirect("/upload/person1.xlsx");
	}

	@RequestMapping(value = "/test/manage.do")
	public String manage() {
		return "/manage";
	}

	@RequestMapping(value = "/text/include.do")
	public String include() {
		return "/login";
	}

	@RequestMapping(value = "/test/default.do")
	public String testDefault() {
		return "/default";
	}

	/*
	 * @InitBinder public void initBinder(WebDataBinder binder, WebRequest
	 * request) { SimpleDateFormat df = new
	 * SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	 * binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
	 * }
	 */
}
