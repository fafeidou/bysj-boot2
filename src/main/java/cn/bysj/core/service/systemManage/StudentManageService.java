package cn.bysj.core.service.systemManage;

import java.util.List;

import cn.bysj.core.pojo.Student;

/**
 * 学生管理
 * ClassName: StudentManageService
 * @Description: TODO
 * @author it小祥
 * @date 2016年11月8日
 */
public interface StudentManageService {

	List<Student> getAllStudent();
	/**
	 * 查询所有学生
	 */
}
