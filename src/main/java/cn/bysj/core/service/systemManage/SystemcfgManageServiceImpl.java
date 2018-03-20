package cn.bysj.core.service.systemManage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bysj.core.mapper.SystemcfgMapper;
import cn.bysj.core.pojo.Systemcfg;
import cn.bysj.core.pojo.SystemcfgExample;

/**
 * 系统管理
 * 
 * @author Administrator
 *
 */
@Service
public class SystemcfgManageServiceImpl implements SystemcfgManageService {
	@Autowired
	SystemcfgMapper systemcfgMapper;

	@Override
	public List<Systemcfg> getSystemConfig() {
		SystemcfgExample example = new SystemcfgExample();
		return systemcfgMapper.selectByExample(example);
	}

}
