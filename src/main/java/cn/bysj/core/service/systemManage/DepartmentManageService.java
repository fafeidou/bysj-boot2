package cn.bysj.core.service.systemManage;

import java.util.List;

import cn.bysj.core.pojo.Departments;
import cn.bysj.core.pojo.DepartmentsExample;
import cn.itcast.common.page.Pagination;

/**
 * 院系管理
 * @author Administrator
 *
 */
public interface DepartmentManageService {
	
	/**
	 * 添加院系
	 */
	Integer AddDepartment(Departments department);
	/*
	 * 通过院系名称查询
	 */
	List<Departments> findDepartmentByName(Departments department);
	
	/*
	 * 分页查询所有院系
	 */
	Pagination getDepartmentsListWithPage(DepartmentsExample example, String departmentName);
	/*
	 * 通过院系ID进行更新
	 */
	void updateDepartmentByKey(Departments department);
	/*
	 * 通过院系主键进行删除
	 */
	void deleteDepartmentByID(Departments department);
	/**
	 * 获取所有院系
	 * @Description: TODO
	 * @param @return   
	 * @return List<Departments>  
	 * @throws
	 * @author it小祥
	 * @date 2016年11月8日
	 */
	List<Departments> getAllDepartments();
	/**
	 * 根据院系名查询院系
	 * @param departmentName 
	 * @Description: TODO
	 * @param @return   
	 * @return List<Departments>  
	 * @throws
	 * @author it小祥
	 * @date 2016年11月8日
	 */
	List<Departments> getDepartmentsLikeName(String departmentName);
}
