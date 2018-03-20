package cn.bysj.core.service.teacher;

import cn.bysj.core.pojo.Systemcfg;
import cn.bysj.core.pojo.vo.SystemcfgCustom;

public interface SystemcfgService {
	/**
	 * 
	 * @Description: 根据院系id获取系统信息
	 * @param @param departmentId
	 * @param @return   
	 * @return Systemcfg  
	 * @throws
	 * @author it小祥
	 * @date 2017年1月19日
	 */
	SystemcfgCustom getSystemcfgByDapartmentId(Integer departmentId);
	/**
	 * 
	 * @Description: 更新
	 * @param @param systemcfg   
	 * @return void  
	 * @throws
	 * @author it小祥
	 * @date 2017年1月19日
	 */
	void updateSystemcfg(Systemcfg systemcfg);

}
