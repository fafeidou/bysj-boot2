package cn.bysj.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bysj.core.mapper.BycjMapper;

@Service
public class BycjServiceImpl implements BycjService {
	@Autowired
	private BycjMapper bycjMapper;
	@Override
	public Integer getTeacherIdByTrsectionIdAndRoleId(Integer trsectionId, Integer roleId) {
		return bycjMapper.getTeacherIdByTrsectionIdAndRoleId(trsectionId, roleId);
	}

}
