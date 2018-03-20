package cn.bysj.core.service.systemManage;

import cn.bysj.core.pojo.Systemmanager;

public interface SystemmanagerService {
	/**
	 * 根据用户名查询管理者信息
	 * @Description: TODO
	 * @param @param name
	 * @param @return   
	 * @return Systemmanager  
	 * @throws
	 * @author it小祥
	 * @date 2017年2月1日
	 */
	Systemmanager getSystemmanagerByName(String name);

}
