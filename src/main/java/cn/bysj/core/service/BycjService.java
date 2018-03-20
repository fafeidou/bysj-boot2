package cn.bysj.core.service;

public interface BycjService {
	/**
	 * 根据教师的id和角色ID去查询符合条件的老师的id
	 * @Description: TODO
	 * @param @return   
	 * @return Integer  
	 * @throws
	 * @author it小祥
	 * @date 2017年1月13日
	 */
	Integer getTeacherIdByTrsectionIdAndRoleId(Integer trsectionId,Integer roleId); 
}
