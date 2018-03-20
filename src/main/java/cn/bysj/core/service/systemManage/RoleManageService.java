package cn.bysj.core.service.systemManage;

import java.util.List;

import cn.bysj.core.pojo.Role;
import cn.bysj.core.pojo.TechroleKey;

/**
 * 角色管理
 * ClassName: RoleManageService
 * @Description: TODO
 * @author it小祥
 * @date 2016年11月12日
 */
public interface RoleManageService {
	/**
	 * 查询所有角色
	 * @Description: TODO
	 * @param @return   
	 * @return List<Role>  
	 * @throws
	 * @author it小祥
	 * @date 2016年11月12日
	 */
	List<Role> getAllRoles();
	/**
	 * 更新教师角色-----------过期  
	 * @Description: TODO
	 * @param @param parseTeacherId
	 * @param @param parseRoleIds   
	 * @return void  
	 * @throws
	 * @author it小祥
	 * @date 2016年11月13日
	 */
	void updateTeacherRole(Integer teacherId, List<Integer> toleIds);
	
	
	/**
	 * 更新教师角色----->每个教师只能有一个角色
	 * @Description: TODO
	 * @param @param techroleKey
	 * @param @return   
	 * @return Integer  
	 * @throws
	 * @author it小祥
	 * @date 2016年11月25日
	 */
	Integer updateTeacherRole(TechroleKey techroleKey);
	
}
