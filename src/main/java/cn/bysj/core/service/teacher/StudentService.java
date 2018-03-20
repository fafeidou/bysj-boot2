package cn.bysj.core.service.teacher;

import java.util.List;

import cn.bysj.core.pojo.Student;
import cn.bysj.core.pojo.vo.StudentCustom;

public interface StudentService {
	/**
	 * 
	 * @Description: 根据学生姓名或者班级id计算count
	 * @param @param studentName
	 * @param @param classisId
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author it小祥
	 * @date 2017年1月15日
	 */
	int getStudentCountLikeStudentNameOrClassisId(String studentName, Integer classisId);
	/**
	 * 
	 * @Description: 根据学生姓名或者班级id查询所有学生 
	 * @param @param studentName
	 * @param @param classisId
	 * @param @param page
	 * @param @param rows
	 * @param @return   
	 * @return List<Student>  
	 * @throws
	 * @author it小祥
	 * @date 2017年1月15日
	 */
	List<Student> getStudentListLikeStudentNameOrClassisId(String studentName, Integer classisId, int page, int rows);
	/**
	 * 
	 * @Description: 根据院系id查询所有学生数量
	 * @param @param departmentId
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author it小祥
	 * @date 2017年1月15日
	 */
	int getStudentCountByDeparmentId(Integer departmentId);
	/**
	 * 
	 * @Description: 根据院系id查询所有学生信息
	 * @param @param departmentId
	 * @param @param page
	 * @param @param rows
	 * @param @return   
	 * @return List<Student>  
	 * @throws
	 * @author it小祥
	 * @date 2017年1月15日
	 */
	List<Student> getStudentListByDeparmentId(Integer departmentId, int page, int rows);
	/**
	 * 
	 * @Description: 根据学生的学号查询学生信息
	 * @param @param studentNo
	 * @param @return   
	 * @return Student  
	 * @throws
	 * @author it小祥
	 * @date 2017年1月16日
	 */
	Student getStudentByStudentNo(String studentNo);
	/**
	 * 
	 * @Description: 添加一条学生记录 
	 * @param @param student   
	 * @return void  
	 * @throws
	 * @author it小祥
	 * @date 2017年1月16日
	 */
	void addStudent(Student student);
	/**
	 * 
	 * @Description: 通过班级获取学生信息
	 * @param @param classisId
	 * @param @return   
	 * @return List<StudentCustom>  
	 * @throws
	 * @author it小祥
	 * @date 2017年1月16日
	 */
	List<StudentCustom> getStudentCustomListByClassisId(Integer classisId);
	/**
	 * 
	 * @Description: 通过学生id去查寻学生信息
	 * @param @param studentId
	 * @param @return   
	 * @return Student  
	 * @throws
	 * @author it小祥
	 * @date 2017年1月16日
	 */
	Student getstudentByStudentId(Integer studentId);
	/**
	 * 
	 * @Description: 修改学生信息
	 * @param @param student   
	 * @return void  
	 * @throws
	 * @author it小祥
	 * @date 2017年1月16日
	 */
	void updateStudent(Student student);
	/**
	 * 
	 * @Description: 登录成功获取studentCustom
	 * @param @param s
	 * @param @return   
	 * @return StudentCustom  
	 * @throws
	 * @author it小祥
	 * @date 2017年1月22日
	 */
	StudentCustom getStudentCustom(Student s);

}
