package cn.bysj.core.service.teacher.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bysj.core.mapper.TopictypeMapper;
import cn.bysj.core.pojo.Topictype;
import cn.bysj.core.pojo.TopictypeExample;
import cn.bysj.core.service.teacher.TopictypeService;

@Service
public class TopictypeServiceImpl implements TopictypeService {
	@Autowired
	private TopictypeMapper topictypeMapper;

	@Override
	public List<Topictype> getTopictypes() {
		TopictypeExample example = new TopictypeExample();
		return topictypeMapper.selectByExample(example);
	}

}
