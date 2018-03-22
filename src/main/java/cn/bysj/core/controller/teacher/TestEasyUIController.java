package cn.bysj.core.controller.teacher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bysj.core.pojo.Teacher;
import cn.bysj.core.process.result.DataGridResultInfo;
import cn.bysj.core.service.teacher.TeacherService;

/**
 * 测试EasyUI 教师展示 ClassName: TestEasyUIController
 * 
 * @Description: TODO
 * @author it小祥
 * @date 2016年11月18日
 */
@Controller
@RequestMapping("/test/")
public class TestEasyUIController {

	/**
	 * 
	 */
	@Autowired
	private TeacherService teacherService;

	@RequestMapping("testTeacher.do")
	public String toTestTeacher() throws Exception {
		return "/teacher/test/testTeacher";
	}

	@RequestMapping("first.do")
	public String first() throws Exception {
		return "/first";
	}

	// 用户查询页面的结果集
	// 最终DataGridResultInfo通过@ResponseBody将java对象转成json
	@RequestMapping("/listTeacher.do")
	public @ResponseBody DataGridResultInfo listTeacher(Teacher teacher,int page,//页码
			int rows)
			throws Exception {

		// 查询列表的总数
		int total = teacherService.getTeacherCount(teacher);

		// 分页查询，向sysuserQueryVo中传入pageQuery
		List<Teacher> list = teacherService.getTeacherList(teacher,page,rows);
		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		// 填充 total
		dataGridResultInfo.setTotal(total);
		// 填充 rows
		dataGridResultInfo.setRows(list);
		return dataGridResultInfo;

	}
	//去添加页面
	@RequestMapping("/toAddTeacher.do")
	public String toAddTeacher(){
		return "/teacher/test/addTeacher";
	}
}
