package cn.bysj.core.service.systemManage;

import java.util.List;

import cn.bysj.core.pojo.Systemcfg;

/**
 * 系统管理
 * @author Administrator
 *
 */
public interface SystemcfgManageService {
	
	/**
	 * 获取当前系统信息
	 * @return
	 */
	List<Systemcfg> getSystemConfig();
}
