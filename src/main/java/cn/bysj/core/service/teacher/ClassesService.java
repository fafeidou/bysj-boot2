package cn.bysj.core.service.teacher;

import java.util.List;

import cn.bysj.core.pojo.Classes;

public interface ClassesService {

	/*
	 * 通过院系id去查询班级
	 */
	List<Classes> getClassByDepartmentID(Integer departmentId);
	/**
	 * 根据班级名称去模糊查班级的数量
	 */
	int getClassesCountByClassesLikeName(String className);
	/**
	 * 根据班级名称去模糊查询所有班级
	 *
	 */
	List<Classes> getClassesListByClassesLikeName(String className, int page, int rows);
	/**
	 * 根据教师所在的院系id查询所有的班级个数
	 */
	int getClassesCountByDeparmentId(Integer departmentId);
	/**
	 根据教师所在的院系id查询所有的班级信息
	 */
	List<Classes> getClassesListByDeparmentId(Integer departmentId, Integer page, Integer rows);
	/**
	 添加班级 
	 */
	void addClasses(Classes classes);
	/**
	 * 
	 * @Description: 通过班级名称查询
	 * @param @param className   
	 * @return void  
	 * @throws
	 * @author it小祥
	 * @date 2017年1月15日
	 */
	Classes getClassesByClassesName(String className);
	/**
	 * 
	 * @Description: 通过班级id删除班级，有学生则不能删除
	 * @param @param classisId   
	 * @return void  
	 * @throws
	 * @author it小祥
	 * @date 2017年1月15日
	 */
	void deleteClassesByClassisId(Integer classisId) throws Exception;
	/**
	 * 
	 * @Description: 通过主键获取班级
	 * @param @param classisId
	 * @param @return   
	 * @return Classes  
	 * @throws
	 * @author it小祥
	 * @date 2017年1月15日
	 */
	Classes getClassesByClassesId(Integer classisId);
	/**
	 * 
	 * @Description: 修改班级信息 
	 * @param @param classes   
	 * @return void  
	 * @throws
	 * @author it小祥
	 * @date 2017年1月15日
	 */
	void updateClassesByClassisId(Classes classes);

	

}
