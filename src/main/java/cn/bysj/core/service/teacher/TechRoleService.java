package cn.bysj.core.service.teacher;

public interface TechRoleService {
	/**
	 * 更改教师的角色
	 * @Description: TODO
	 * @param @param teacherId 教师id
	 * @param @param roleId    角色id
	 * @return void  
	 * @throws
	 * @author it小祥
	 * @date 2017年1月13日
	 */

	void updateRoleByTeacherId(Integer teacherId, Integer roleId);
	
}
