package cn.bysj.core.service.teacher.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bysj.core.mapper.TopicsourcetypeMapper;
import cn.bysj.core.pojo.Topicsourcetype;
import cn.bysj.core.pojo.TopicsourcetypeExample;
import cn.bysj.core.service.teacher.TopicsourcetypeService;

@Service
public class TopicsourcetypeServiceImpl implements TopicsourcetypeService {
	@Autowired
	private TopicsourcetypeMapper topicsourcetypeMapper;

	@Override
	public List<Topicsourcetype> getTopicsourcetype() {
		TopicsourcetypeExample example = new TopicsourcetypeExample();
		return topicsourcetypeMapper.selectByExample(example);
	}

}
