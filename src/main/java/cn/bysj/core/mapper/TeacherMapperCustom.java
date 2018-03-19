package cn.bysj.core.mapper;

import java.util.List;

import cn.bysj.core.pojo.vo.Menu;

public interface TeacherMapperCustom {
	/**
	 * 根据用户角色获取菜单
	 */
	public List<Menu> findMenuByroleid(Integer roleid) throws Exception;
	
}
