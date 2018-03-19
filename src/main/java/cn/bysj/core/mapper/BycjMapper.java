package cn.bysj.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BycjMapper {
	/**
	 * 根据教师的id和角色ID去查询符合条件的老师的id
	 * @Description: TODO
	 * @param @return   
	 * @return Integer  
	 * @throws
	 * @author it小祥
	 * @date 2017年1月13日
	 */
	Integer getTeacherIdByTrsectionIdAndRoleId(@Param("trsectionId") Integer trsectionId, @Param("roleId") Integer roleId);
	
	/**
	 * //根据院系id查询所有的的教研室id
	 */
	
	List<Integer> getTrsectionIdsByDepartmentId(@Param("departmentId") Integer departmentId);
	
	/**
	 * //根据教研室id查询所有的教师的id
	 */
	List<Integer> getTeacherIdsByTrsectionId(@Param("trsectionId") Integer trsectionId);
	
	
	
	
	
	
	
}