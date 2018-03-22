package cn.bysj.core.service.teacher.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bysj.core.mapper.ApplicationMapper;
import cn.bysj.core.mapper.BycjMapper;
import cn.bysj.core.mapper.ClassesMapper;
import cn.bysj.core.mapper.StudentMapper;
import cn.bysj.core.mapper.TeacherMapper;
import cn.bysj.core.mapper.ThesistopicMapper;
import cn.bysj.core.mapper.TopicsourcetypeMapper;
import cn.bysj.core.mapper.TopictypeMapper;
import cn.bysj.core.mapper.TrsectionMapper;
import cn.bysj.core.pojo.Application;
import cn.bysj.core.pojo.ApplicationExample;
import cn.bysj.core.pojo.Classes;
import cn.bysj.core.pojo.ClassesExample;
import cn.bysj.core.pojo.Student;
import cn.bysj.core.pojo.StudentExample;
import cn.bysj.core.pojo.Teacher;
import cn.bysj.core.pojo.TeacherExample;
import cn.bysj.core.pojo.Thesistopic;
import cn.bysj.core.pojo.ThesistopicExample;
import cn.bysj.core.pojo.ThesistopicExample.Criteria;
import cn.bysj.core.pojo.Topicsourcetype;
import cn.bysj.core.pojo.Topictype;
import cn.bysj.core.pojo.Trsection;
import cn.bysj.core.pojo.TrsectionExample;
import cn.bysj.core.pojo.vo.StudentThesistopicCustom;
import cn.bysj.core.pojo.vo.ThesistopicCustom;
import cn.bysj.core.pojo.vo.ThesistopicExcel;
import cn.bysj.core.service.teacher.ThesistopicService;
import cn.bysj.core.web.common.ConstantsStudentTopicState;
import cn.bysj.core.web.common.ConstantsTopicState;

@Service
public class ThesistopicServiceImpl implements ThesistopicService {
	@Autowired
	private ThesistopicMapper thesistopicMapper;
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private TrsectionMapper trsectionMapper;
	@Autowired
	private TeacherMapper teacherMapper;
	@Autowired
	private BycjMapper bycjMapper;
	@Autowired
	private TopictypeMapper topictypeMapper;
	@Autowired
	private TopicsourcetypeMapper topicsourcetypeMapper;

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

		// 设置论文标题 , 进行模糊查询
		if (thesistopicCustom.getThesisTitle() != null && StringUtils.isNotBlank(thesistopicCustom.getThesisTitle())) {
			createCriteria.andThesisTitleLike("%" + thesistopicCustom.getThesisTitle() + "%");
		}

		// 设置论文的毕业年限 ,进行模糊查询
		if (thesistopicCustom.getGraduationYear() != null
				&& StringUtils.isNotBlank(thesistopicCustom.getGraduationYear())) {
			createCriteria.andGraduationYearLike("%" + thesistopicCustom.getGraduationYear() + "%");
		}

		// 设置状态
		if (thesistopicCustom.getTopicState() != null) {
			createCriteria.andTopicStateEqualTo(thesistopicCustom.getTopicState());
		}
		return thesistopicMapper.countByExample(example);
	}

	@Override
	public List<ThesistopicCustom> getThesistopicCustomList(ThesistopicCustom thesistopicCustom, Integer page,
			Integer rows) {
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

		// 设置论文的教师id , 如果不为空，查出该教师的所有论文，如果为空查出该院系的所有论文
		if (thesistopicCustom.getTeacherId() != null) {
			createCriteria.andTeacherIdEqualTo(thesistopicCustom.getTeacherId());
		} else {
			// 为空的时候要查出该院系的所有论文
			// 查出该院系中的所有教研室
			// TrsectionExample example2 = new TrsectionExample();
			// cn.bysj.core.pojo.TrsectionExample.Criteria createCriteria2 =
			// example2.createCriteria();
			// 根据院系id查询所有的的教研室id
			// createCriteria2.andDepartmentIdEqualTo(thesistopicCustom.getDepartmentId());
			// List<Trsection> trsections =
			// trsectionMapper.selectByExample(example2);
			// for (int i = 0; i < trsections.size(); i++) {
			// trsectionIds.add(i, trsections.get(i).getTrsectionId());
			// }
			// 查出所有教师id

			// TeacherExample example3 = new TeacherExample();
			// cn.bysj.core.pojo.TeacherExample.Criteria createCriteria3 =
			// example3.createCriteria();
			// createCriteria3.andTrsectionIdIn(trsectionIds);
			// 根据教研室id查询所有的教师的id
			// List<Teacher> teachers = teacherMapper.selectByExample(example3);
			// List<Integer> teacherIds = new ArrayList<Integer>();
			// for (int i = 0; i < teachers.size(); i++) {
			// teacherIds.add(i, teachers.get(i).getTeacherId());
			// }

			List<Integer> trsectionIds = bycjMapper.getTrsectionIdsByDepartmentId(thesistopicCustom.getDepartmentId());

			List<Integer> teacherIds = new ArrayList<Integer>();
			for (Integer trsectionId : trsectionIds) {
				List<Integer> teacherIdsByTrsectionId = bycjMapper.getTeacherIdsByTrsectionId(trsectionId);
				teacherIds.addAll(teacherIdsByTrsectionId);
			}
			// 根据id查出所有论文
			createCriteria.andTeacherIdIn(teacherIds);
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
		// 设置状态
		if (thesistopicCustom.getTopicState() != null) {
			createCriteria.andTopicStateEqualTo(thesistopicCustom.getTopicState());
		}
		// 查出所有符合条件的论文
		// 分页
		example.setOrderByClause("thesis_Topic_ID");
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
			list.add(i, custom);
		}
		return list;
	}

	@Override
	public ThesistopicCustom getThesistopicCustomById(Integer thesisTopicId) {
		ThesistopicCustom thesistopicCustom = new ThesistopicCustom();
		// 根据id查询论文
		Thesistopic thesistopic = thesistopicMapper.selectByPrimaryKey(thesisTopicId);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		thesistopicCustom.setCreateDateString(dateFormat.format(thesistopic.getCreateDate()));
		thesistopicCustom.setLastUseDateString(dateFormat.format(thesistopic.getLastUseDate()));
		thesistopicCustom.setThesistopic(thesistopic);
		// 查询类型来源
		Topicsourcetype topicsourcetype = topicsourcetypeMapper.selectByPrimaryKey(thesistopic.getTopicSourceTypeId());
		if (topicsourcetype != null) {
			thesistopicCustom.setTopicsourcetype(topicsourcetype);
		}
		// 查询类型
		Topictype topictype = topictypeMapper.selectByPrimaryKey(thesistopic.getTopicTypeId());
		if (topictype != null) {
			thesistopicCustom.setTopictype(topictype);
		}
		// 查询教师
		Teacher teacher = teacherMapper.selectByPrimaryKey(thesistopic.getTeacherId());
		if (teacher != null) {
			thesistopicCustom.setTeacherName(teacher.getTeacherName());
		}
		// 查询学生
		StudentExample example = new StudentExample();
		cn.bysj.core.pojo.StudentExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andThesisTopicIdEqualTo(thesistopic.getThesisTopicId());
		List<Student> students = studentMapper.selectByExample(example);
		if (students.size() > 0 && students != null) {
			thesistopicCustom.setStudentName(students.get(0).getStudentName());
		}
		// 格式化状态
		Byte topicState = thesistopic.getTopicState();
		if (topicState == 0) {
			thesistopicCustom.setTopicStateString(ConstantsTopicState.TrsectionNotVerify);
		} else if (topicState == 1) {
			thesistopicCustom.setTopicStateString(ConstantsTopicState.TrsectionNotPass);
		} else if (topicState == 2) {
			thesistopicCustom.setTopicStateString(ConstantsTopicState.DepartmentNotVerify);
		} else if (topicState == 3) {
			thesistopicCustom.setTopicStateString(ConstantsTopicState.DepartmentNotPass);
		} else if (topicState == 4) {
			thesistopicCustom.setTopicStateString(ConstantsTopicState.WaitForChoice);
		} else if (topicState == 5) {
			thesistopicCustom.setTopicStateString(ConstantsTopicState.WaitingForDistribution);
		} else if (topicState == 6) {
			thesistopicCustom.setTopicStateString(ConstantsTopicState.DistributionSuccess);
		}

		return thesistopicCustom;
	}

	@Override
	public List<ThesistopicExcel> getThesistopicExcel(Thesistopic thesistopic, Integer departmentId) {
		// 创建查询对象
		ThesistopicExample example = new ThesistopicExample();
		Criteria createCriteria = example.createCriteria();
		// 论文来源类型
		if (thesistopic.getTopicSourceTypeId() != null) {
			createCriteria.andTopicSourceTypeIdEqualTo(thesistopic.getTopicSourceTypeId());
		}
		// 论文类型
		if (thesistopic.getTopicTypeId() != null) {
			createCriteria.andTopicTypeIdEqualTo(thesistopic.getTopicTypeId());
		}
		// 教师id 先看一下教师id是否为空 不为空直接赋值 为空查出院系所有的教师id
		if (thesistopic.getTeacherId() != null) {
			createCriteria.andTeacherIdEqualTo(thesistopic.getTeacherId());
		} else {
			// 查出院系中的教研室
			TrsectionExample example2 = new TrsectionExample();
			cn.bysj.core.pojo.TrsectionExample.Criteria createCriteria2 = example2.createCriteria();
			createCriteria2.andDepartmentIdEqualTo(departmentId);
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
			// 根据条件查询
			// 根据所有教师id查询
			if (teacherIds != null && teacherIds.size() > 0) {
				createCriteria.andTeacherIdIn(teacherIds);
			}
		}
		// 状态
		if (thesistopic.getTopicState() != null) {
			createCriteria.andTopicStateEqualTo(thesistopic.getTopicState());
		}
		// 论文标题模糊查询
		if (thesistopic.getThesisTitle() != null) {
			createCriteria.andThesisTitleLike("%" + thesistopic.getThesisTitle() + "%");
		}
		// 毕业年数模糊查询
		if (thesistopic.getGraduationYear() != null) {
			createCriteria.andGraduationYearLike("%" + thesistopic.getGraduationYear() + "%");
		}
		List<Thesistopic> thesistopics = thesistopicMapper.selectByExample(example);
		// 封装数据
		List<ThesistopicExcel> list = new ArrayList<ThesistopicExcel>();
		for (Thesistopic thesis : thesistopics) {
			ThesistopicExcel excel = new ThesistopicExcel();
			// private String thesisTitle; // 展示论文标题
			if (thesis.getThesisTitle() != null) {
				excel.setThesisTitle(thesis.getThesisTitle());
			}
			// private String thesisEnglishTile; // 展示论文英文标题
			if (thesis.getThesisEnglishTile() != null) {
				excel.setThesisEnglishTile(thesis.getThesisEnglishTile());
			}
			// private String sourceTypeName; // 展示来源名字
			if (thesis.getTopicSourceTypeId() != null) {
				// 将名字查询出来
				Topicsourcetype topicsourcetype = topicsourcetypeMapper
						.selectByPrimaryKey(thesis.getTopicSourceTypeId());
				if (topicsourcetype.getSourceTypeName() != null) {
					excel.setSourceTypeName(topicsourcetype.getSourceTypeName());
				}
			}
			// private String typeName; // 展示论文类型
			if (thesis.getTopicTypeId() != null) {
				Topictype topictype = topictypeMapper.selectByPrimaryKey(thesis.getTopicTypeId());
				if (topictype.getTypeName() != null) {
					excel.setTypeName(topictype.getTypeName());
				}
			}
			// private String projectRequirement; // 展示论文目标和要求
			if (thesis.getProjectRequirement() != null) {
				excel.setProjectRequirement(thesis.getProjectRequirement());
			}
			// private String workloadReqirement; // 展示主要研究内容
			if (thesis.getWorkloadReqirement() != null) {
				excel.setWorkloadReqirement(thesis.getWorkloadReqirement());
			}
			// private String topicStateString; //论文状态
			if (thesis.getTopicState() != null) {
				Byte topicState = thesis.getTopicState();
				if (topicState == 0) {
					excel.setTopicStateString(ConstantsTopicState.TrsectionNotVerify);
				} else if (topicState == 1) {
					excel.setTopicStateString(ConstantsTopicState.TrsectionNotPass);
				} else if (topicState == 2) {
					excel.setTopicStateString(ConstantsTopicState.DepartmentNotVerify);
				} else if (topicState == 3) {
					excel.setTopicStateString(ConstantsTopicState.DepartmentNotPass);
				} else if (topicState == 4) {
					excel.setTopicStateString(ConstantsTopicState.WaitForChoice);
				} else if (topicState == 5) {
					excel.setTopicStateString(ConstantsTopicState.WaitingForDistribution);
				} else if (topicState == 6) {
					excel.setTopicStateString(ConstantsTopicState.DistributionSuccess);
				}
			}
			// private String CreateDateString; //论文添加时间
			// private String LastUseDateString; //论文最后修改时间
			if (thesis.getCreateDate() != null || thesis.getLastUseDate() != null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if (thesis.getCreateDate() != null) {
					excel.setCreateDateString(dateFormat.format(thesis.getCreateDate()));
				}
				if (thesis.getLastUseDate() != null) {
					excel.setLastUseDateString(dateFormat.format(thesis.getLastUseDate()));
				}
			}
			// private String note; // 展示课题简介
			if (thesis.getNote() != null) {
				excel.setNote(thesis.getNote());
			}
			// private String graduationYear; // 展示毕业年数
			if (thesis.getGraduationYear() != null) {
				excel.setGraduationYear(thesis.getGraduationYear());
			}
			// private String teacherName; //教师姓名
			if (thesis.getTeacherId() != null) {
				Teacher teacher = teacherMapper.selectByPrimaryKey(thesis.getTeacherId());
				if (teacher.getTeacherName() != null) {
					excel.setTeacherName(teacher.getTeacherName());
				}
			}
			// private String studentName; //学生姓名

			// 根据topicid查询有没有学生选
			StudentExample studentExample = new StudentExample();
			cn.bysj.core.pojo.StudentExample.Criteria studentCreateCriteria = studentExample.createCriteria();
			studentCreateCriteria.andThesisTopicIdEqualTo(thesis.getThesisTopicId());
			List<Student> students = studentMapper.selectByExample(studentExample);
			if (students.size() > 0 && students != null) {
				excel.setStudentName(students.get(0).getStudentName());
			}
			list.add(excel);
		}

		return list;
	}

	@Override
	public int getThesistopicCustomCountForOrteacher(ThesistopicCustom thesistopicCustom) {
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

		// 设置论文标题 , 进行模糊查询
		if (thesistopicCustom.getThesisTitle() != null && StringUtils.isNotBlank(thesistopicCustom.getThesisTitle())) {
			createCriteria.andThesisTitleLike("%" + thesistopicCustom.getThesisTitle() + "%");
		}

		// 设置论文的毕业年限 ,进行模糊查询
		if (thesistopicCustom.getGraduationYear() != null
				&& StringUtils.isNotBlank(thesistopicCustom.getGraduationYear())) {
			createCriteria.andGraduationYearLike("%" + thesistopicCustom.getGraduationYear() + "%");
		}

		// 设置状态
		if (thesistopicCustom.getTopicState() != null) {
			createCriteria.andTopicStateEqualTo(thesistopicCustom.getTopicState());
		}
		createCriteria.andOtherTeacherCanSeeEqualTo((byte) 1);
		return thesistopicMapper.countByExample(example);
	}

	@Override
	public List<ThesistopicCustom> getThesistopicCustomListForOrteacher(ThesistopicCustom thesistopicCustom, int page,
			int rows) {
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

		// 设置论文的教师id , 如果不为空，查出该教师的所有论文，如果为空查出该院系的所有论文
		if (thesistopicCustom.getTeacherId() != null) {
			createCriteria.andTeacherIdEqualTo(thesistopicCustom.getTeacherId());
		} else {
			// 为空的时候要查出该院系的所有论文
			// 查出该院系中的所有教研室
			// TrsectionExample example2 = new TrsectionExample();
			// cn.bysj.core.pojo.TrsectionExample.Criteria createCriteria2 =
			// example2.createCriteria();
			// 根据院系id查询所有的的教研室id
			// createCriteria2.andDepartmentIdEqualTo(thesistopicCustom.getDepartmentId());
			// List<Trsection> trsections =
			// trsectionMapper.selectByExample(example2);
			// for (int i = 0; i < trsections.size(); i++) {
			// trsectionIds.add(i, trsections.get(i).getTrsectionId());
			// }
			// 查出所有教师id

			// TeacherExample example3 = new TeacherExample();
			// cn.bysj.core.pojo.TeacherExample.Criteria createCriteria3 =
			// example3.createCriteria();
			// createCriteria3.andTrsectionIdIn(trsectionIds);
			// 根据教研室id查询所有的教师的id
			// List<Teacher> teachers = teacherMapper.selectByExample(example3);
			// List<Integer> teacherIds = new ArrayList<Integer>();
			// for (int i = 0; i < teachers.size(); i++) {
			// teacherIds.add(i, teachers.get(i).getTeacherId());
			// }

			List<Integer> trsectionIds = bycjMapper.getTrsectionIdsByDepartmentId(thesistopicCustom.getDepartmentId());

			List<Integer> teacherIds = new ArrayList<Integer>();
			for (Integer trsectionId : trsectionIds) {
				List<Integer> teacherIdsByTrsectionId = bycjMapper.getTeacherIdsByTrsectionId(trsectionId);
				teacherIds.addAll(teacherIdsByTrsectionId);
			}
			// 根据id查出所有论文
			createCriteria.andTeacherIdIn(teacherIds);
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
		// 设置状态
		if (thesistopicCustom.getTopicState() != null) {
			createCriteria.andTopicStateEqualTo(thesistopicCustom.getTopicState());
		}
		createCriteria.andOtherTeacherCanSeeEqualTo((byte) 1);
		// 查出所有符合条件的论文

		// 分页
		example.setOrderByClause("thesis_Topic_ID");
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
			list.add(i, custom);
		}
		return list;
	}

	@Override
	public int getThesistopicCustomCountForOrteacherByTeacherId(ThesistopicCustom thesistopicCustom) {
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

		// 设置论文的教师id , 如果不为空，查出该教师的所有论文，如果为空查出该院系的所有论文
		if (thesistopicCustom.getTeacherId() != null) {
			createCriteria.andTeacherIdEqualTo(thesistopicCustom.getTeacherId());
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
		// 设置状态
		if (thesistopicCustom.getTopicState() != null) {
			createCriteria.andTopicStateEqualTo(thesistopicCustom.getTopicState());
		}
		return thesistopicMapper.countByExample(example);
	}

	@Override
	public List<ThesistopicCustom> getThesistopicCustomListForOrteacherByTeacherId(ThesistopicCustom thesistopicCustom,
			int page, int rows) {
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

		// 设置论文的教师id , 如果不为空，查出该教师的所有论文，如果为空查出该院系的所有论文
		if (thesistopicCustom.getTeacherId() != null) {
			createCriteria.andTeacherIdEqualTo(thesistopicCustom.getTeacherId());
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
		// 设置状态
		if (thesistopicCustom.getTopicState() != null) {
			createCriteria.andTopicStateEqualTo(thesistopicCustom.getTopicState());
		}
		// 查出所有符合条件的论文
		// 分页
		example.setOrderByClause("thesis_Topic_ID");
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
			list.add(i, custom);
		}
		return list;
	}

	@Override
	public Thesistopic getThesistopicById(Thesistopic thesistopic) {
		return thesistopicMapper.selectByPrimaryKey(thesistopic.getThesisTopicId());
	}

	@Override
	public void updateThesistopic(Thesistopic thesistopic) {
		thesistopicMapper.updateByPrimaryKeySelective(thesistopic);
	}

	@Override
	public List<Thesistopic> getThesistopic(Thesistopic thesistopic) {
		ThesistopicExample example = new ThesistopicExample();
		Criteria createCriteria = example.createCriteria();
		// 设置论文类型
		if (thesistopic.getTopicTypeId() != null) {
			createCriteria.andTopicTypeIdEqualTo(thesistopic.getTopicTypeId());
		}
		// 设置教师id
		if (thesistopic.getTeacherId() != null) {
			createCriteria.andTeacherIdEqualTo(thesistopic.getTeacherId());
		}
		List<Thesistopic> list = thesistopicMapper.selectByExample(example);
		if (list.size() > 0 && list != null) {
			return list;
		}
		return null;
	}

	@Override
	public void addThesistopic(Thesistopic thesistopic) {
		thesistopicMapper.insertSelective(thesistopic);
	}

	@Override
	public int getThesistopicCustomCountFromStudent(StudentThesistopicCustom custom) {
		List<Integer> classesIds = new ArrayList<Integer>();
		List<Integer> studentIds = new ArrayList<Integer>();
		// 看classid是否为空
		if (custom.getClasses() != null) {
			// 查出班级中的所有学生
			StudentExample studentExample = new StudentExample();
			cn.bysj.core.pojo.StudentExample.Criteria createCriteria = studentExample.createCriteria();
			createCriteria.andClassisIdEqualTo(custom.getClasses().getClassisId());
			// 设置学生状态
			if (custom.getStudent() != null) {
				createCriteria.andStudentStateEqualTo(custom.getStudent().getStudentState());
			}
			List<Student> students = studentMapper.selectByExample(studentExample);
			if (students != null && students.size() > 0) {
				for (int i = 0; i < students.size(); i++) {
					studentIds.add(i, students.get(i).getStudentId());
				}
			}
		} else {
			// 根据院系查出所有班级 在查出所有学生 学生查出所有论文id
			ClassesExample classesExample = new ClassesExample();
			cn.bysj.core.pojo.ClassesExample.Criteria createCriteria = classesExample.createCriteria();
			createCriteria.andDepartmentIdEqualTo(custom.getDepartments().getDepartmentId());
			List<Classes> classes = classesMapper.selectByExample(classesExample);
			if (classes != null && classes.size() > 0) {
				for (int i = 0; i < classes.size(); i++) {
					classesIds.add(i, classes.get(i).getClassisId());
				}
			}
			StudentExample studentExample = new StudentExample();
			cn.bysj.core.pojo.StudentExample.Criteria createCriteria2 = studentExample.createCriteria();
			createCriteria2.andClassisIdIn(classesIds);
			// 设置学生状态
			if (custom.getStudent() != null) {
				createCriteria2.andStudentStateEqualTo(custom.getStudent().getStudentState());
			}
			List<Student> students = studentMapper.selectByExample(studentExample);
			if (students != null && students.size() > 0) {
				for (int i = 0; i < students.size(); i++) {
					studentIds.add(i, students.get(i).getStudentId());
				}
			}
		}
		ApplicationExample applicationExample = new ApplicationExample();
		cn.bysj.core.pojo.ApplicationExample.Criteria createCriteria3 = applicationExample.createCriteria();
		createCriteria3.andStudentIdIn(studentIds);
		List<Application> applications = applicationMapper.selectByExample(applicationExample);
		if (applications != null && applications.size() > 0) {
			return applications.size();
		}
		return 0;
	}

	@Override
	public List<StudentThesistopicCustom> getThesistopicCustomListFromStudent(StudentThesistopicCustom custom,
			Integer page, Integer rows) {
		List<Integer> classesIds = new ArrayList<Integer>();
		List<Integer> studentIds = new ArrayList<Integer>();
		List<Application> applications = new ArrayList<Application>();
		// 看classid是否为空
		if (custom.getClasses() != null) {
			// 查出班级中的所有学生
			StudentExample studentExample = new StudentExample();
			cn.bysj.core.pojo.StudentExample.Criteria createCriteria = studentExample.createCriteria();
			createCriteria.andClassisIdEqualTo(custom.getClasses().getClassisId());
			// 设置学生状态
			if (custom.getStudent() != null) {
				createCriteria.andStudentStateEqualTo(custom.getStudent().getStudentState());
			}
			List<Student> students = studentMapper.selectByExample(studentExample);
			if (students != null && students.size() > 0) {
				for (int i = 0; i < students.size(); i++) {
					studentIds.add(i, students.get(i).getStudentId());
				}
			}
		} else {
			// 根据院系查出所有班级 在查出所有学生 学生查出所有论文id
			ClassesExample classesExample = new ClassesExample();
			cn.bysj.core.pojo.ClassesExample.Criteria createCriteria = classesExample.createCriteria();
			createCriteria.andDepartmentIdEqualTo(custom.getDepartments().getDepartmentId());
			List<Classes> classes = classesMapper.selectByExample(classesExample);
			if (classes != null && classes.size() > 0) {
				for (int i = 0; i < classes.size(); i++) {
					classesIds.add(i, classes.get(i).getClassisId());
				}
			}
			StudentExample studentExample = new StudentExample();
			cn.bysj.core.pojo.StudentExample.Criteria createCriteria2 = studentExample.createCriteria();
			createCriteria2.andClassisIdIn(classesIds);
			// 设置学生状态
			if (custom.getStudent() != null) {
				createCriteria2.andStudentStateEqualTo(custom.getStudent().getStudentState());
			}
			List<Student> students = studentMapper.selectByExample(studentExample);
			if (students != null && students.size() > 0) {
				for (int i = 0; i < students.size(); i++) {
					studentIds.add(i, students.get(i).getStudentId());
				}
			}
		}
		// 查出所有论文id
		ApplicationExample applicationExample = new ApplicationExample();
		cn.bysj.core.pojo.ApplicationExample.Criteria createCriteria3 = applicationExample.createCriteria();
		createCriteria3.andStudentIdIn(studentIds);
		int startRow = (page - 1) * rows;
		applicationExample.setIsLimit(1);
		applicationExample.setStartRow(startRow);
		applicationExample.setPageSize(rows);
		applications = applicationMapper.selectByExample(applicationExample);

		List<StudentThesistopicCustom> studentThesistopicCustoms = new ArrayList<StudentThesistopicCustom>();
		// 根据所有的thesistopicId 查出所有学生姓名 查出所有对应的论文
		if (applications != null && applications.size() > 0) {
			for (int i = 0; i < applications.size(); i++) {
				// 查出所有学生
				StudentThesistopicCustom studentThesistopicCustom = new StudentThesistopicCustom();
				Student student = studentMapper.selectByPrimaryKey(applications.get(i).getStudentId());
				if (student != null) {
					studentThesistopicCustom.setStudent(student);
				}
				// 查出对应的论文
				Thesistopic thesistopic = thesistopicMapper.selectByPrimaryKey(applications.get(i).getThesisTopicId());
				if (thesistopic != null) {
					studentThesistopicCustom.setThesistopic(thesistopic);
					// 查出对应的教师
					Teacher teacher = teacherMapper.selectByPrimaryKey(thesistopic.getTeacherId());
					if (teacher != null) {
						studentThesistopicCustom.setTeacher(teacher);
					}
				}

				studentThesistopicCustoms.add(i, studentThesistopicCustom);
			}
		}
		return studentThesistopicCustoms;
	}

	@Override
	public List<ThesistopicExcel> getStudentThesistopicExcel(StudentThesistopicCustom custom) {
		List<Integer> classesIds = new ArrayList<Integer>();
		List<Integer> studentIds = new ArrayList<Integer>();
		List<Application> applications = new ArrayList<Application>();
		// 看classid是否为空
		if (custom.getClasses() != null) {
			// 查出班级中的所有学生
			StudentExample studentExample = new StudentExample();
			cn.bysj.core.pojo.StudentExample.Criteria createCriteria = studentExample.createCriteria();
			createCriteria.andClassisIdEqualTo(custom.getClasses().getClassisId());
			// 设置学生状态
			if (custom.getStudent() != null) {
				createCriteria.andStudentStateEqualTo(custom.getStudent().getStudentState());
			}
			List<Student> students = studentMapper.selectByExample(studentExample);
			if (students != null && students.size() > 0) {
				for (int i = 0; i < students.size(); i++) {
					studentIds.add(i, students.get(i).getStudentId());
				}
			}
		} else {
			// 根据院系查出所有班级 在查出所有学生 学生查出所有论文id
			ClassesExample classesExample = new ClassesExample();
			cn.bysj.core.pojo.ClassesExample.Criteria createCriteria = classesExample.createCriteria();
			createCriteria.andDepartmentIdEqualTo(custom.getDepartments().getDepartmentId());
			List<Classes> classes = classesMapper.selectByExample(classesExample);
			if (classes != null && classes.size() > 0) {
				for (int i = 0; i < classes.size(); i++) {
					classesIds.add(i, classes.get(i).getClassisId());
				}
			}
			StudentExample studentExample = new StudentExample();
			cn.bysj.core.pojo.StudentExample.Criteria createCriteria2 = studentExample.createCriteria();
			createCriteria2.andClassisIdIn(classesIds);
			// 设置学生状态
			if (custom.getStudent() != null) {
				createCriteria2.andStudentStateEqualTo(custom.getStudent().getStudentState());
			}
			List<Student> students = studentMapper.selectByExample(studentExample);
			if (students != null && students.size() > 0) {
				for (int i = 0; i < students.size(); i++) {
					studentIds.add(i, students.get(i).getStudentId());
				}
			}
		}
		// 查出所有论文id
		ApplicationExample applicationExample = new ApplicationExample();
		cn.bysj.core.pojo.ApplicationExample.Criteria createCriteria3 = applicationExample.createCriteria();
		createCriteria3.andStudentIdIn(studentIds);
		applications = applicationMapper.selectByExample(applicationExample);

		List<StudentThesistopicCustom> studentThesistopicCustoms = new ArrayList<StudentThesistopicCustom>();
		// 根据所有的thesistopicId 查出所有学生姓名 查出所有对应的论文
		if (applications != null && applications.size() > 0) {
			for (int i = 0; i < applications.size(); i++) {
				// 查出所有学生
				StudentThesistopicCustom studentThesistopicCustom = new StudentThesistopicCustom();
				Student student = studentMapper.selectByPrimaryKey(applications.get(i).getStudentId());
				if (student != null) {
					studentThesistopicCustom.setStudent(student);
				}
				// 查出对应的论文
				Thesistopic thesistopic = thesistopicMapper.selectByPrimaryKey(applications.get(i).getThesisTopicId());
				if (thesistopic != null) {
					studentThesistopicCustom.setThesistopic(thesistopic);
				}
				// 查出对应的教师
				Teacher teacher = teacherMapper.selectByPrimaryKey(thesistopic.getTeacherId());
				if (teacher != null) {
					studentThesistopicCustom.setTeacher(teacher);
				}
				// 查出对应的类型
				Topictype topictype = topictypeMapper.selectByPrimaryKey(thesistopic.getTopicTypeId());
				if (topictype != null) {
					studentThesistopicCustom.setTopictype(topictype);
				}
				// 查出对应的来源的类型
				Topicsourcetype topicsourcetype = topicsourcetypeMapper
						.selectByPrimaryKey(thesistopic.getTopicSourceTypeId());
				if (topicsourcetype != null) {
					studentThesistopicCustom.setTopicsourcetype(topicsourcetype);
				}
				studentThesistopicCustoms.add(i, studentThesistopicCustom);
			}
		}
		List<ThesistopicExcel> excels = new ArrayList<ThesistopicExcel>();
		// 封装数据
		if (studentThesistopicCustoms.size() > 0 && studentThesistopicCustoms != null) {
			for (int i = 0; i < studentThesistopicCustoms.size(); i++) {
				ThesistopicExcel excel = new ThesistopicExcel();
				// private String thesisTitle; // 展示论文标题
				if (studentThesistopicCustoms.get(i).getThesistopic().getThesisTitle() != null) {
					excel.setThesisTitle(studentThesistopicCustoms.get(i).getThesistopic().getThesisTitle());
				}
				// private String thesisEnglishTile; // 展示论文英文标题
				if (studentThesistopicCustoms.get(i).getThesistopic().getThesisEnglishTile() != null) {
					excel.setThesisEnglishTile(
							studentThesistopicCustoms.get(i).getThesistopic().getThesisEnglishTile());
				}

				// private String typeName; // 展示来源名字
				if (studentThesistopicCustoms.get(i).getTopictype() != null) {
					excel.setTypeName(studentThesistopicCustoms.get(i).getTopictype().getTypeName());
				}
				// private String sourceTypeName; // 展示论文类型
				if (studentThesistopicCustoms.get(i).getTopicsourcetype() != null) {
					excel.setSourceTypeName(studentThesistopicCustoms.get(i).getTopicsourcetype().getSourceTypeName());
				}
				// private String projectRequirement; // 展示论文目标和要求
				if (studentThesistopicCustoms.get(i).getThesistopic().getProjectRequirement() != null) {
					excel.setProjectRequirement(
							studentThesistopicCustoms.get(i).getThesistopic().getProjectRequirement());
				}
				// private String workloadReqirement; // 展示主要研究内容
				if (studentThesistopicCustoms.get(i).getThesistopic().getWorkloadReqirement() != null) {
					excel.setWorkloadReqirement(
							studentThesistopicCustoms.get(i).getThesistopic().getWorkloadReqirement());
				}
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				// private String CreateDateString; //论文添加时间
				if (studentThesistopicCustoms.get(i).getThesistopic().getCreateDate() != null) {
					excel.setCreateDateString(
							dateFormat.format(studentThesistopicCustoms.get(i).getThesistopic().getCreateDate()));
				}
				// private String LastUseDateString; //论文最后修改时间
				if (studentThesistopicCustoms.get(i).getThesistopic().getLastUseDate() != null) {
					excel.setLastUseDateString(
							dateFormat.format(studentThesistopicCustoms.get(i).getThesistopic().getLastUseDate()));
				}
				// private String note; // 展示课题简介
				if (studentThesistopicCustoms.get(i).getThesistopic().getNote() != null) {
					excel.setNote(studentThesistopicCustoms.get(i).getThesistopic().getNote());
				}
				// private String graduationYear; // 展示毕业年数
				if (studentThesistopicCustoms.get(i).getThesistopic().getGraduationYear() != null) {
					excel.setGraduationYear(studentThesistopicCustoms.get(i).getThesistopic().getGraduationYear());
				}
				// private String teacherName; //教师姓名
				if (studentThesistopicCustoms.get(i).getTeacher() != null) {
					excel.setTeacherName(studentThesistopicCustoms.get(i).getTeacher().getTeacherName());
				}
				// private String studentName; //学生姓名
				if (studentThesistopicCustoms.get(i).getStudent() != null) {
					excel.setStudentName(studentThesistopicCustoms.get(i).getStudent().getStudentName());
				}
				// private String studentStateString; //学生论文状态
				// String InfoNotVerify = "信息未完善"; // 0
				// String WaitForChoice = "等待选择"; // 1
				// String WaitingForDistribution = "等待分配"; // 2
				// String DistributionSuccess = "分配完成"; // 3
				Integer studentState = studentThesistopicCustoms.get(i).getStudent().getStudentState();
				if (studentState != null) {
					if (studentState == 0) {
						excel.setStudentStateString(ConstantsStudentTopicState.InfoNotVerify);
					} else if (studentState == 1) {
						excel.setStudentStateString(ConstantsStudentTopicState.WaitForChoice);
					} else if (studentState == 2) {
						excel.setStudentStateString(ConstantsStudentTopicState.WaitingForDistribution);
					} else if (studentState == 3) {
						excel.setStudentStateString(ConstantsStudentTopicState.DistributionSuccess);
					}
				}
				excels.add(i, excel);
			}
		}
		return excels;
	}

	@Autowired
	private ApplicationMapper applicationMapper;
	@Autowired
	private ClassesMapper classesMapper;

	@Override
	public int getThesistopicCustomCountForTrDirector(ThesistopicCustom thesistopicCustom) {
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

		// 为空的时候要查出该教研室的所有论文
		// 查出院系中的所有教师id
		if (thesistopicCustom.getTeacherId() == null) {
			TeacherExample example2 = new TeacherExample();
			cn.bysj.core.pojo.TeacherExample.Criteria createCriteria2 = example2.createCriteria();
			createCriteria2.andTrsectionIdEqualTo(thesistopicCustom.getTrsectionId());
			List<Teacher> teachers = teacherMapper.selectByExample(example2);

			List<Integer> teacherIds = new ArrayList<Integer>();
			for (int i = 0; i < teachers.size(); i++) {
				teacherIds.add(i, teachers.get(i).getTeacherId());
			}
			// 根据id查出所有论文
			createCriteria.andTeacherIdIn(teacherIds);
		} else {
			createCriteria.andTeacherIdEqualTo(thesistopicCustom.getTeacherId());
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
		// 设置状态
		if (thesistopicCustom.getTopicState() != null) {
			createCriteria.andTopicStateEqualTo(thesistopicCustom.getTopicState());
		}
		return thesistopicMapper.countByExample(example);
	}

	@Override
	public List<ThesistopicCustom> getThesistopicCustomListForTrDirector(ThesistopicCustom thesistopicCustom, int page,
			int rows) {
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

		// 为空的时候要查出该教研室的所有论文
		// 查出院系中的所有教师id
		if (thesistopicCustom.getTeacherId() == null) {
			TeacherExample example2 = new TeacherExample();
			cn.bysj.core.pojo.TeacherExample.Criteria createCriteria2 = example2.createCriteria();
			createCriteria2.andTrsectionIdEqualTo(thesistopicCustom.getTrsectionId());
			List<Teacher> teachers = teacherMapper.selectByExample(example2);

			List<Integer> teacherIds = new ArrayList<Integer>();
			for (int i = 0; i < teachers.size(); i++) {
				teacherIds.add(i, teachers.get(i).getTeacherId());
			}
			// 根据id查出所有论文
			createCriteria.andTeacherIdIn(teacherIds);
		} else {
			createCriteria.andTeacherIdEqualTo(thesistopicCustom.getTeacherId());
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
		// 设置状态
		if (thesistopicCustom.getTopicState() != null) {
			createCriteria.andTopicStateEqualTo(thesistopicCustom.getTopicState());
		}
		// 查出所有符合条件的论文
		// 分页
		example.setOrderByClause("thesis_Topic_ID");
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
			list.add(i, custom);
		}
		return list;
	}

	@Override
	public int getThesistopicBeSelectedCustomCount(ThesistopicCustom thesistopicCustom) {
		List<Integer> thesistopicIds = new ArrayList<Integer>();
		ApplicationExample example = new ApplicationExample();
		cn.bysj.core.pojo.ApplicationExample.Criteria createCriteria = example.createCriteria();
		// 查出所有课题
		ThesistopicExample example2 = new ThesistopicExample();
		Criteria createCriteria2 = example2.createCriteria();
		createCriteria2.andTeacherIdEqualTo(thesistopicCustom.getTeacherId());
		List<Thesistopic> thesistopics = thesistopicMapper.selectByExample(example2);
		for (int i = 0; i < thesistopics.size(); i++) {
			thesistopicIds.add(i, thesistopics.get(i).getThesisTopicId());
		}
		// 查出所有被选的课题
		if (thesistopicIds != null && thesistopicIds.size() > 0) {
			createCriteria.andThesisTopicIdIn(thesistopicIds);
		} else {
			return 0;
		}
		return applicationMapper.countByExample(example);
	}

	@Override
	public List<ThesistopicCustom> getThesistopicCustomBeSelectedList(ThesistopicCustom thesistopicCustom, int page,
			int rows) {
		List<Integer> thesistopicIds = new ArrayList<Integer>();
		List<ThesistopicCustom> list = new ArrayList<ThesistopicCustom>();
		ApplicationExample example = new ApplicationExample();
		cn.bysj.core.pojo.ApplicationExample.Criteria createCriteria = example.createCriteria();
		// 查出所有课题
		ThesistopicExample example2 = new ThesistopicExample();
		Criteria createCriteria2 = example2.createCriteria();
		createCriteria2.andTeacherIdEqualTo(thesistopicCustom.getTeacherId());
		List<Thesistopic> thesistopics = thesistopicMapper.selectByExample(example2);
		for (int i = 0; i < thesistopics.size(); i++) {
			thesistopicIds.add(i, thesistopics.get(i).getThesisTopicId());
		}
		// 查出所有被选的课题
		if (thesistopicIds != null && thesistopicIds.size() > 0) {
			createCriteria.andThesisTopicIdIn(thesistopicIds);
		} else {
			return list;
		}
		// 查出所有符合条件的论文
		// 分页
		int startRow = (page - 1) * rows;
		example.setIsLimit(1);
		example.setStartRow(startRow);
		example.setPageSize(rows);
		List<Application> applications = applicationMapper.selectByExample(example);
		// 封装数据
		if (applications != null && applications.size() > 0) {
			for (int i = 0; i < applications.size(); i++) {
				ThesistopicCustom custom = new ThesistopicCustom();
				// 查出课题
				Thesistopic thesistopic = thesistopicMapper.selectByPrimaryKey(applications.get(i).getThesisTopicId());
				if (thesistopic != null) {
					custom.setThesistopic(thesistopic);
				}
				// 查出所有课题对应老师的名字
				Teacher teacher = teacherMapper.selectByPrimaryKey(thesistopic.getTeacherId());
				if (teacher != null) {
					custom.setTeacherName(teacher.getTeacherName());
				}
				// 查出所有选择论文的学生
				Student student = studentMapper.selectByPrimaryKey(applications.get(i).getStudentId());
				if (student != null) {
					custom.setStudent(student);
					custom.setStudentName(student.getStudentName());
				}
				list.add(i, custom);
			}
		}
		return list;
	}

}
