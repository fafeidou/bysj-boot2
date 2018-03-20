package cn.bysj.core.service.weixin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bysj.core.mapper.weixin.WeixinstudentstateMapper;
import cn.bysj.core.pojo.weixin.Weixinstudentstate;
import cn.bysj.core.pojo.weixin.WeixinstudentstateExample;
import cn.bysj.core.pojo.weixin.WeixinstudentstateExample.Criteria;
import cn.bysj.core.service.weixin.WeixinstudentstateService;

@Service
public class WeixinstudentstateServiceImpl implements WeixinstudentstateService {
	@Autowired
	private WeixinstudentstateMapper weixinstudentstateMapper;

	@Override
	public Weixinstudentstate getStateByFromUserName(String fromUserName) {
		if (fromUserName != null) {
			return weixinstudentstateMapper.selectByPrimaryKey(fromUserName);
		}
		return null;
	}

	@Override
	public void AddWeiXinStudentState(Weixinstudentstate weixinstudentstate) {
		if (weixinstudentstate != null) {
			weixinstudentstateMapper.insert(weixinstudentstate);
		}
	}

	@Override
	public void deleteWeiXinStudentStateByFromUserName(String fromUserName) {
		if (fromUserName != null) {
			weixinstudentstateMapper.deleteByPrimaryKey(fromUserName);
		}
	}

	@Override
	public Weixinstudentstate getStateByStudentId(Integer studentId) {
		WeixinstudentstateExample example = new WeixinstudentstateExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andStudentIdEqualTo(studentId);
		List<Weixinstudentstate> list = weixinstudentstateMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
