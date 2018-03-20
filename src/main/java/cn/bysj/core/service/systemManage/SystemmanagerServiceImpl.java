package cn.bysj.core.service.systemManage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bysj.core.mapper.SystemmanagerMapper;
import cn.bysj.core.pojo.Systemmanager;
import cn.bysj.core.pojo.SystemmanagerExample;
import cn.bysj.core.pojo.SystemmanagerExample.Criteria;

@Service
public class SystemmanagerServiceImpl implements SystemmanagerService {
	@Autowired
	private SystemmanagerMapper systemmanagerMapper;

	@Override
	public Systemmanager getSystemmanagerByName(String name) {
		SystemmanagerExample example = new SystemmanagerExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andNameEqualTo(name);
		List<Systemmanager> list = systemmanagerMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
