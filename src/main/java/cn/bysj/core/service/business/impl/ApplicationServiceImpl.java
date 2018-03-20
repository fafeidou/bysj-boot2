package cn.bysj.core.service.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bysj.core.mapper.ApplicationMapper;
import cn.bysj.core.mapper.StudentMapper;
import cn.bysj.core.mapper.SystemcfgMapper;
import cn.bysj.core.mapper.ThesistopicMapper;
import cn.bysj.core.pojo.Application;
import cn.bysj.core.pojo.ApplicationExample;
import cn.bysj.core.pojo.ApplicationExample.Criteria;
import cn.bysj.core.pojo.Student;
import cn.bysj.core.pojo.Systemcfg;
import cn.bysj.core.pojo.SystemcfgExample;
import cn.bysj.core.pojo.Thesistopic;
import cn.bysj.core.pojo.vo.ThesistopicCustom;
import cn.bysj.core.service.business.ApplicationService;

@Service
public class ApplicationServiceImpl implements ApplicationService {
	@Autowired
	private ApplicationMapper applicationMapper;
	@Autowired
	private SystemcfgMapper systemcfgMapper;
	@Autowired
	private ThesistopicMapper thesistopicMapper;

	@Transactional
	@Override
	public void addThesistopicRecord(ThesistopicCustom custom) {
		// 查询该课题如果课题状态为等待选择4 改为 等待分配5
		Thesistopic thesistopic = thesistopicMapper.selectByPrimaryKey(custom.getThesisTopicId());
		if (thesistopic != null) {
			if (thesistopic.getTopicState() == 4) {
				thesistopic.setTopicState((byte) 5);
				thesistopicMapper.updateByPrimaryKey(thesistopic);
			}
		}
		// 如果学生状态为等待选择1则改为 等待分配2
		Student student = studentMapper.selectByPrimaryKey(custom.getStudentId());
		if (student != null) {
			if (student.getStudentState() == 1) {
				student.setStudentState(2);
				studentMapper.updateByPrimaryKeySelective(student);
			}
		}
		Application application = new Application();
		application.setStudentId(custom.getStudentId());
		application.setThesisTopicId(custom.getThesisTopicId());
		applicationMapper.insert(application);
	}

	@Override
	public boolean compareStudentCanSelect(Integer studentId, Integer departmentId) {
		ApplicationExample example = new ApplicationExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andStudentIdEqualTo(studentId);
		// 学生当前选的数量
		int select = applicationMapper.countByExample(example);

		SystemcfgExample example2 = new SystemcfgExample();
		cn.bysj.core.pojo.SystemcfgExample.Criteria createCriteria2 = example2.createCriteria();
		createCriteria2.andDepartmentIdEqualTo(departmentId);
		List<Systemcfg> systemcfgs = systemcfgMapper.selectByExample(example2);
		if (systemcfgs != null && systemcfgs.size() > 0) {
			// 获取允许选择的最大数量
			Integer count = systemcfgs.get(0).getThesisNumPerStudentCanSelect();
			if (count >= select + 1) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean compareThesistopicCanBeSelected(Integer thesisTopicId, Integer departmentId) {
		ApplicationExample example = new ApplicationExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andThesisTopicIdEqualTo(thesisTopicId);
		// 论文别选择数量
		int select = applicationMapper.countByExample(example);

		SystemcfgExample example2 = new SystemcfgExample();
		cn.bysj.core.pojo.SystemcfgExample.Criteria createCriteria2 = example2.createCriteria();
		createCriteria2.andDepartmentIdEqualTo(departmentId);
		List<Systemcfg> systemcfgs = systemcfgMapper.selectByExample(example2);
		if (systemcfgs != null && systemcfgs.size() > 0) {
			// 获取允许选择的最大数量
			Integer count = systemcfgs.get(0).getMaxStudentNumPerThesisCanBeSelected();
			if (count >= select + 1) {
				return true;
			}
		}
		return false;
	}

	@Transactional
	@Override
	public void teacherSelectStudent(ThesistopicCustom custom) {
		// 1、删除除了该学生id选择的记录其他的所有该课题的记录 没有修改其他学生的状态 因为 其他学生要么为等待选择或者为等待分配，都可以再选
		ApplicationExample applicationExample = new ApplicationExample();
		Criteria createCriteria = applicationExample.createCriteria();
		createCriteria.andThesisTopicIdEqualTo(custom.getThesisTopicId());
		createCriteria.andStudentIdNotEqualTo(custom.getStudentId());
		applicationMapper.deleteByExample(applicationExample);
		// 2、更新论文表该论文的状态为分配成功
		Thesistopic thesistopic = new Thesistopic();
		thesistopic.setThesisTopicId(custom.getThesisTopicId());
		// String DistributionSuccess = "分配完成"; // 6
		thesistopic.setTopicState((byte) 6);
		thesistopicMapper.updateByPrimaryKeySelective(thesistopic);
		// 3、更新该学生的状态为分配成功
		Student student = new Student();
		student.setStudentId(custom.getStudentId());
		// String DistributionSuccess = "分配完成"; // 3
		student.setStudentState(3);
		studentMapper.updateByPrimaryKeySelective(student);
	}

	@Autowired
	private StudentMapper studentMapper;

}
