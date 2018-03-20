package cn.bysj.core.service.teacher.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bysj.core.mapper.SystemcfgMapper;
import cn.bysj.core.pojo.Systemcfg;
import cn.bysj.core.pojo.SystemcfgExample;
import cn.bysj.core.pojo.SystemcfgExample.Criteria;
import cn.bysj.core.pojo.vo.SystemcfgCustom;
import cn.bysj.core.service.teacher.SystemcfgService;

@Service
public class SystemcfgServiceImpl implements SystemcfgService {
	@Autowired
	private SystemcfgMapper systemcfgMapper;

	@Override
	public SystemcfgCustom getSystemcfgByDapartmentId(Integer departmentId) {
		SystemcfgExample example = new SystemcfgExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andDepartmentIdEqualTo(departmentId);
		List<Systemcfg> list = systemcfgMapper.selectByExample(example);
		if (list.size() > 0 && list != null) {
			Systemcfg systemcfg = list.get(0);
			SystemcfgCustom custom = new SystemcfgCustom();
			if (systemcfg.getSystemCfgId() != null) {
				custom.setSystemCfgId(systemcfg.getSystemCfgId());
			}
			if (systemcfg.getSystemState() != null) {
				custom.setSystemState(systemcfg.getSystemState());
			}
			if (systemcfg.getThesisNumPerStudentCanSelect() != null) {
				custom.setThesisNumPerStudentCanSelect(systemcfg.getThesisNumPerStudentCanSelect());
			}
			if (systemcfg.getMaxStudentNumPerThesisCanBeSelected() != null) {
				custom.setMaxStudentNumPerThesisCanBeSelected(systemcfg.getMaxStudentNumPerThesisCanBeSelected());
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (systemcfg.getFirstRoundStudentSelectBeginTime() != null) {
				custom.setFirstRoundStudentSelectBeginTime(
						dateFormat.format(systemcfg.getFirstRoundStudentSelectBeginTime()));
			}
			if (systemcfg.getFirstRoundStudentSelectEndTime() != null) {
				custom.setFirstRoundStudentSelectEndTime(
						dateFormat.format(systemcfg.getFirstRoundStudentSelectEndTime()));
			}
			if (systemcfg.getFirstRoundTeacherSelectBeginTime() != null) {
				custom.setFirstRoundTeacherSelectBeginTime(
						dateFormat.format(systemcfg.getFirstRoundTeacherSelectBeginTime()));
			}
			if (systemcfg.getFirstRoundTeacherSelectEndTime() != null) {
				custom.setFirstRoundTeacherSelectEndTime(
						dateFormat.format(systemcfg.getFirstRoundTeacherSelectEndTime()));
			}
			if (systemcfg.getSecondRoundStudentSelectBeginTime() != null) {
				custom.setSecondRoundStudentSelectBeginTime(
						dateFormat.format(systemcfg.getSecondRoundStudentSelectBeginTime()));
			}
			if (systemcfg.getSecondRoundStudentSelecEndTime() != null) {
				custom.setSecondRoundStudentSelecEndTime(
						dateFormat.format(systemcfg.getSecondRoundStudentSelecEndTime()));
			}
			if (systemcfg.getSecondRoundTeacherSelectBeginTime() != null) {
				custom.setSecondRoundTeacherSelectBeginTime(
						dateFormat.format(systemcfg.getSecondRoundTeacherSelectBeginTime()));
			}
			if (systemcfg.getSecondRoundTeacherSelectEndTime() != null) {
				custom.setSecondRoundTeacherSelectEndTime(
						dateFormat.format(systemcfg.getSecondRoundTeacherSelectEndTime()));
			}
			return custom;
		}
		return null;
	}

	@Override
	public void updateSystemcfg(Systemcfg systemcfg) {
		systemcfgMapper.updateByPrimaryKeySelective(systemcfg);
	}

}
