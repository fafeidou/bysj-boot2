package cn.bysj.core.service.teacher.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bysj.core.mapper.TechroleMapper;
import cn.bysj.core.pojo.TechroleExample;
import cn.bysj.core.pojo.TechroleExample.Criteria;
import cn.bysj.core.pojo.TechroleKey;
import cn.bysj.core.service.teacher.TechRoleService;

@Service
public class TechRoleServiceImpl implements TechRoleService {
	@Autowired
	private TechroleMapper techroleMappe;
	@Override
	public void updateRoleByTeacherId(Integer teacherId, Integer roleId) {
		TechroleKey record = new TechroleKey();
		record.setTeacherId(teacherId);
		record.setRoleId(roleId);
		TechroleExample example = new TechroleExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andTeacherIdEqualTo(teacherId);
		techroleMappe.updateByExample(record, example);
	}

}
