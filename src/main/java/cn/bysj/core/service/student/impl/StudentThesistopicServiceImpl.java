package cn.bysj.core.service.student.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bysj.core.mapper.ApplicationMapper;
import cn.bysj.core.mapper.StudentMapper;
import cn.bysj.core.mapper.TeacherMapper;
import cn.bysj.core.mapper.ThesistopicMapper;
import cn.bysj.core.mapper.TopicsourcetypeMapper;
import cn.bysj.core.mapper.TopictypeMapper;
import cn.bysj.core.mapper.TrsectionMapper;
import cn.bysj.core.pojo.Application;
import cn.bysj.core.pojo.ApplicationExample;
import cn.bysj.core.pojo.Student;
import cn.bysj.core.pojo.StudentExample;
import cn.bysj.core.pojo.Teacher;
import cn.bysj.core.pojo.TeacherExample;
import cn.bysj.core.pojo.Thesistopic;
import cn.bysj.core.pojo.ThesistopicExample;
import cn.bysj.core.pojo.Trsection;
import cn.bysj.core.pojo.TrsectionExample;
import cn.bysj.core.pojo.ThesistopicExample.Criteria;
import cn.bysj.core.pojo.Topicsourcetype;
import cn.bysj.core.pojo.Topictype;
import cn.bysj.core.pojo.vo.ThesistopicCustom;
import cn.bysj.core.service.student.StudentThesistopicService;

@Service
public class StudentThesistopicServiceImpl implements StudentThesistopicService {
	@Autowired
	private ThesistopicMapper thesistopicMapper;
	@Autowired
	private TrsectionMapper trsectionMapper;
	@Autowired
	private TeacherMapper teacherMapper;

	@Override
	public int getThesistopicCustomCount(ThesistopicCustom thesistopicCustom) {
		ThesistopicExample example = new ThesistopicExample();
		Criteria createCriteria = example.createCriteria();
		// 设置论文来源类型
		if (thesistopicCustom.getTopicSourceTypeId() != null) {
			createCriteria.andTopicSourceTypeIdEqualTo(thesistopicCustom.getTopicSourceTypeId());
		}

		// 设置论文类型
		if (thesistopicCustom.getTopicTypeId() != null) {
			createCriteria.andTopicSourceTypeIdEqualTo(thesistopicCustom.getTopicTypeId());
		}

		// 设置教师课题
		// 默认情况查出院系的所有教研室的所有教师的所有课题
		// 设置论文的教师id , 如果不为空，查出该教师的所有论文，如果为空查出该院系的所有论文
		if (thesistopicCustom.getTeacherId() != null) {
			createCriteria.andTeacherIdEqualTo(thesistopicCustom.getTeacherId());
		} else {
			// 为空的时候要查出该院系的所有论文
			// 查出该院系中的所有教研室
			TrsectionExample example2 = new TrsectionExample();
			cn.bysj.core.pojo.TrsectionExample.Criteria createCriteria2 = example2.createCriteria();
			createCriteria2.andDepartmentIdEqualTo(thesistopicCustom.getDepartmentId());
			List<Trsection> trsections = trsectionMapper.selectByExample(example2);
			List<Integer> trsectionIds = new ArrayList<Integer>();
			for (int i = 0; i < trsections.size(); i++) {
				trsectionIds.add(i, trsections.get(i).getTrsectionId());
			}
			// 查出所有教师id
			TeacherExample example3 = new TeacherExample();
			cn.bysj.core.pojo.TeacherExample.Criteria createCriteria3 = example3.createCriteria();
			createCriteria3.andTrsectionIdIn(trsectionIds);
			List<Teacher> teachers = teacherMapper.selectByExample(example3);
			List<Integer> teacherIds = new ArrayList<Integer>();
			for (int i = 0; i < teachers.size(); i++) {
				teacherIds.add(i, teachers.get(i).getTeacherId());
			}
			// 根据id查出所有论文
			createCriteria.andTeacherIdIn(teacherIds);
		}
		// String WaitForChoice = "等待选择"; // 4
		// String WaitingForDistribution = "等待分配"; // 5
		// String DistributionSuccess = "分配完成"; // 6
		// 状态不为空设置状态
		if (thesistopicCustom.getTopicState() != null) {
			createCriteria.andTopicStateEqualTo(thesistopicCustom.getTopicState());
		} else {
			// 课题默认展示 4 5
			List<Byte> topicStates = new ArrayList<Byte>();
			topicStates.add((byte) 4);
			topicStates.add((byte) 5);
			createCriteria.andTopicStateIn(topicStates);
		}
		// 设置论文标题 , 进行模糊查询
		if (thesistopicCustom.getThesisTitle() != null && StringUtils.isNotBlank(thesistopicCustom.getThesisTitle())) {
			createCriteria.andThesisTitleLike("%" + thesistopicCustom.getThesisTitle() + "%");
		}

		// 设置论文的毕业年限 ,进行模糊查询
		if (thesistopicCustom.getGraduationYear() != null
				&& StringUtils.isNotBlank(thesistopicCustom.getGraduationYear())) {
			createCriteria.andGraduationYearLike("%" + thesistopicCustom.getGraduationYear() + "%");
		}

		return thesistopicMapper.countByExample(example);
	}

	@Override
	public List<ThesistopicCustom> getThesistopicCustomList(ThesistopicCustom thesistopicCustom, int page, int rows) {
		ThesistopicExample example = new ThesistopicExample();
		Criteria createCriteria = example.createCriteria();
		// 设置论文来源类型
		if (thesistopicCustom.getTopicSourceTypeId() != null) {
			createCriteria.andTopicSourceTypeIdEqualTo(thesistopicCustom.getTopicSourceTypeId());
		}

		// 设置论文类型
		if (thesistopicCustom.getTopicTypeId() != null) {
			createCriteria.andTopicSourceTypeIdEqualTo(thesistopicCustom.getTopicTypeId());
		}

		// 设置教师课题
		// 默认情况查出院系的所有教研室的所有教师的所有课题
		// 设置论文的教师id , 如果不为空，查出该教师的所有论文，如果为空查出该院系的所有论文
		if (thesistopicCustom.getTeacherId() != null) {
			createCriteria.andTeacherIdEqualTo(thesistopicCustom.getTeacherId());
		} else {
			// 为空的时候要查出该院系的所有论文
			// 查出该院系中的所有教研室
			TrsectionExample example2 = new TrsectionExample();
			cn.bysj.core.pojo.TrsectionExample.Criteria createCriteria2 = example2.createCriteria();
			createCriteria2.andDepartmentIdEqualTo(thesistopicCustom.getDepartmentId());
			List<Trsection> trsections = trsectionMapper.selectByExample(example2);
			List<Integer> trsectionIds = new ArrayList<Integer>();
			for (int i = 0; i < trsections.size(); i++) {
				trsectionIds.add(i, trsections.get(i).getTrsectionId());
			}
			// 查出所有教师id
			TeacherExample example3 = new TeacherExample();
			cn.bysj.core.pojo.TeacherExample.Criteria createCriteria3 = example3.createCriteria();
			createCriteria3.andTrsectionIdIn(trsectionIds);
			List<Teacher> teachers = teacherMapper.selectByExample(example3);
			List<Integer> teacherIds = new ArrayList<Integer>();
			for (int i = 0; i < teachers.size(); i++) {
				teacherIds.add(i, teachers.get(i).getTeacherId());
			}
			// 根据id查出所有论文
			createCriteria.andTeacherIdIn(teacherIds);
		}
		// String WaitForChoice = "等待选择"; // 4
		// String WaitingForDistribution = "等待分配"; // 5
		// String DistributionSuccess = "分配完成"; // 6
		// 状态不为空设置状态
		if (thesistopicCustom.getTopicState() != null) {
			createCriteria.andTopicStateEqualTo(thesistopicCustom.getTopicState());
		} else {
			// 课题默认展示 4 5
			List<Byte> topicStates = new ArrayList<Byte>();
			topicStates.add((byte) 4);
			topicStates.add((byte) 5);
			createCriteria.andTopicStateIn(topicStates);
		}
		// 设置论文标题 , 进行模糊查询
		if (thesistopicCustom.getThesisTitle() != null && StringUtils.isNotBlank(thesistopicCustom.getThesisTitle())) {
			createCriteria.andThesisTitleLike("%" + thesistopicCustom.getThesisTitle() + "%");
		}

		// 设置论文的毕业年限 ,进行模糊查询
		if (thesistopicCustom.getGraduationYear() != null
				&& StringUtils.isNotBlank(thesistopicCustom.getGraduationYear())) {
			createCriteria.andGraduationYearLike("%" + thesistopicCustom.getGraduationYear() + "%");
		}
		int startRow = (page - 1) * rows;
		example.setIsLimit(1);
		example.setStartRow(startRow);
		example.setPageSize(rows);
		List<Thesistopic> thesistopics = thesistopicMapper.selectByExample(example);
		List<ThesistopicCustom> list = new ArrayList<ThesistopicCustom>();
		// 将每个老师的名字 以及 学生的姓名查出来
		for (int i = 0; i < thesistopics.size(); i++) {
			ThesistopicCustom custom = new ThesistopicCustom();
			// 查出所有课题对应老师的名字
			Teacher t = teacherMapper.selectByPrimaryKey(thesistopics.get(i).getTeacherId());
			if (t.getTeacherName() != null && StringUtils.isNotBlank(t.getTeacherName())) {
				custom.setTeacherName(t.getTeacherName());
			}
			// 查出所有选择论文的学生
			StudentExample studentExample = new StudentExample();
			cn.bysj.core.pojo.StudentExample.Criteria createCriteriaStudent = studentExample.createCriteria();
			createCriteriaStudent.andThesisTopicIdEqualTo(thesistopics.get(i).getThesisTopicId());
			// 如果有人选就有唯一性标识
			// ThesisTopicId具有唯一性标识
			List<Student> students = studentMapper.selectByExample(studentExample);
			if (students.size() > 0 && students != null) {
				custom.setStudentName(students.get(0).getStudentName());
			}
			custom.setThesistopic(thesistopics.get(i));
			// 查出论文来源类型
			Topicsourcetype topicsourcetype = topicsourcetypeMapper
					.selectByPrimaryKey(thesistopics.get(i).getTopicSourceTypeId());
			if (topicsourcetype != null) {
				custom.setTopicsourcetype(topicsourcetype);
			}
			Topictype topictype = topictypeMapper.selectByPrimaryKey(thesistopics.get(i).getTopicTypeId());
			if (topictype != null) {
				custom.setTopictype(topictype);
			}
			// 查出论文类型
			list.add(i, custom);
		}
		return list;
	}

	@Override
	public int getThesistopicCustomCountMyself(ThesistopicCustom thesistopicCustom) {
		List<Integer> thesistopicIds = new ArrayList<Integer>();
		ThesistopicExample example = new ThesistopicExample();
		Criteria createCriteria = example.createCriteria();
		// 根据学生id查询所有论文id
		ApplicationExample applicationExample = new ApplicationExample();
		cn.bysj.core.pojo.ApplicationExample.Criteria createCriteria2 = applicationExample.createCriteria();
		createCriteria2.andStudentIdEqualTo(thesistopicCustom.getStudentId());
		List<Application> applications = applicationMapper.selectByExample(applicationExample);
		if (applications != null && applications.size() > 0) {
			for (int i = 0; i < applications.size(); i++) {
				thesistopicIds.add(i, applications.get(i).getThesisTopicId());
			}
		}

		if (applications != null && applications.size() > 0) {
			createCriteria.andThesisTopicIdIn(thesistopicIds);
			List<Thesistopic> thesistopics = thesistopicMapper.selectByExample(example);
			if (thesistopics.size() > 0 && thesistopics != null) {
				return thesistopics.size();
			}
		}
		return 0;
	}

	@Override
	public List<ThesistopicCustom> getThesistopicCustomListMyself(ThesistopicCustom thesistopicCustom, Integer page,
			Integer rows) {
		List<Integer> thesistopicIds = new ArrayList<Integer>();
		List<ThesistopicCustom> list = new ArrayList<ThesistopicCustom>();
		ThesistopicExample example = new ThesistopicExample();
		Criteria createCriteria = example.createCriteria();
		// 根据学生id查询所有论文id
		ApplicationExample applicationExample = new ApplicationExample();
		cn.bysj.core.pojo.ApplicationExample.Criteria createCriteria2 = applicationExample.createCriteria();
		createCriteria2.andStudentIdEqualTo(thesistopicCustom.getStudentId());
		List<Application> applications = applicationMapper.selectByExample(applicationExample);
		if (applications != null && applications.size() > 0) {
			for (int i = 0; i < applications.size(); i++) {
				thesistopicIds.add(i, applications.get(i).getThesisTopicId());
			}
		}
		if (applications != null && applications.size() > 0) {
			createCriteria.andThesisTopicIdIn(thesistopicIds);
			if (page != null && rows != null) {
				int startRow = (page - 1) * rows;
				example.setIsLimit(1);
				example.setStartRow(startRow);
				example.setPageSize(rows);
			}
			List<Thesistopic> thesistopics = thesistopicMapper.selectByExample(example);

			// 将每个老师的名字 以及 学生的姓名查出来
			for (int i = 0; i < thesistopics.size(); i++) {
				ThesistopicCustom custom = new ThesistopicCustom();
				// 查出所有课题对应老师的名字
				Teacher t = teacherMapper.selectByPrimaryKey(thesistopics.get(i).getTeacherId());
				if (t.getTeacherName() != null && StringUtils.isNotBlank(t.getTeacherName())) {
					custom.setTeacherName(t.getTeacherName());
				}
				// 查出所有选择论文的学生
				StudentExample studentExample = new StudentExample();
				cn.bysj.core.pojo.StudentExample.Criteria createCriteriaStudent = studentExample.createCriteria();
				createCriteriaStudent.andThesisTopicIdEqualTo(thesistopics.get(i).getThesisTopicId());
				// 如果有人选就有唯一性标识
				// ThesisTopicId具有唯一性标识
				List<Student> students = studentMapper.selectByExample(studentExample);
				if (students.size() > 0 && students != null) {
					custom.setStudentName(students.get(0).getStudentName());
				}
				custom.setThesistopic(thesistopics.get(i));
				// 查出论文来源类型
				Topicsourcetype topicsourcetype = topicsourcetypeMapper
						.selectByPrimaryKey(thesistopics.get(i).getTopicSourceTypeId());
				if (topicsourcetype != null) {
					custom.setTopicsourcetype(topicsourcetype);
				}
				Topictype topictype = topictypeMapper.selectByPrimaryKey(thesistopics.get(i).getTopicTypeId());
				if (topictype != null) {
					custom.setTopictype(topictype);
				}
				// 查出论文类型
				list.add(i, custom);
			}
		}
		return list;
	}

	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private TopictypeMapper topictypeMapper;
	@Autowired
	private TopicsourcetypeMapper topicsourcetypeMapper;
	@Autowired
	private ApplicationMapper applicationMapper;
}
