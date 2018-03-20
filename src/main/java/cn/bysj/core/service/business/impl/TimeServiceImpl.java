package cn.bysj.core.service.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bysj.core.mapper.SystemcfgMapper;
import cn.bysj.core.pojo.Systemcfg;
import cn.bysj.core.pojo.SystemcfgExample;
import cn.bysj.core.pojo.SystemcfgExample.Criteria;
import cn.bysj.core.service.business.TimeService;

@Service
public class TimeServiceImpl implements TimeService {
	@Autowired
	private SystemcfgMapper systemcfgMapper;

	@Override
	public boolean compareStudentTime(Long now, Integer departmentId) {
		Long firstRoundStudentSelectBeginTime;
		Long firstRoundStudentSelectEndTime;
		Long secondRoundStudentSelectBeginTime;
		Long secondRoundStudentSelectEndTime;
		Systemcfg systemcfg;
		SystemcfgExample example = new SystemcfgExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andDepartmentIdEqualTo(departmentId);
		List<Systemcfg> systemcfgs = systemcfgMapper.selectByExample(example);
		if (systemcfgs != null && systemcfgs.size() > 0) {
			systemcfg = systemcfgs.get(0);
			firstRoundStudentSelectBeginTime = systemcfg.getFirstRoundStudentSelectBeginTime().getTime();
			firstRoundStudentSelectEndTime = systemcfg.getFirstRoundStudentSelectEndTime().getTime();
			secondRoundStudentSelectBeginTime = systemcfg.getSecondRoundStudentSelectBeginTime().getTime();
			secondRoundStudentSelectEndTime = systemcfg.getSecondRoundStudentSelecEndTime().getTime();

			if ((now.longValue() >= firstRoundStudentSelectBeginTime.longValue()
					&& now.longValue() <= firstRoundStudentSelectEndTime.longValue())
					|| (now.longValue() >= secondRoundStudentSelectBeginTime.longValue()
							&& now.longValue() <= secondRoundStudentSelectEndTime.longValue())) {
				return true;
			}

		}
		return false;
	}

	@Override
	public boolean compareTeacherTime(Long now, Integer departmentId) {
		Long firstRoundTeacherSelectBeginTime;
		Long firstRoundTeacherSelectEndTime;
		Long secondRoundTeacherSelectBeginTime;
		Long secondRoundTeacherSelectEndTime;
		Systemcfg systemcfg;
		SystemcfgExample example = new SystemcfgExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andDepartmentIdEqualTo(departmentId);
		List<Systemcfg> systemcfgs = systemcfgMapper.selectByExample(example);
		if (systemcfgs != null && systemcfgs.size() > 0) {
			systemcfg = systemcfgs.get(0);
			firstRoundTeacherSelectBeginTime = systemcfg.getFirstRoundTeacherSelectBeginTime().getTime();
			firstRoundTeacherSelectEndTime = systemcfg.getFirstRoundTeacherSelectEndTime().getTime();
			secondRoundTeacherSelectBeginTime = systemcfg.getSecondRoundTeacherSelectBeginTime().getTime();
			secondRoundTeacherSelectEndTime = systemcfg.getSecondRoundTeacherSelectEndTime().getTime();
			if ((now.longValue() >= firstRoundTeacherSelectBeginTime.longValue()
					&& now.longValue() <= firstRoundTeacherSelectEndTime.longValue())
					|| (now.longValue() >= secondRoundTeacherSelectBeginTime.longValue()
							&& now.longValue() <= secondRoundTeacherSelectEndTime.longValue())) {
				return true;
			}
		}
		return false;
	}

}
