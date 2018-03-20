package cn.bysj.core.service.teacher.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.bysj.core.mapper.ClassesMapper;
import cn.bysj.core.mapper.StudentMapper;
import cn.bysj.core.pojo.Classes;
import cn.bysj.core.pojo.ClassesExample;
import cn.bysj.core.pojo.Student;
import cn.bysj.core.pojo.StudentExample;
import cn.bysj.core.pojo.StudentExample.Criteria;
import cn.bysj.core.pojo.vo.StudentCustom;
import cn.bysj.core.service.teacher.StudentService;
import cn.bysj.core.web.common.Constants;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private ClassesMapper classesMapper;

	@Override
	public int getStudentCountLikeStudentNameOrClassisId(String studentName, Integer classisId) {
		StudentExample example = new StudentExample();
		Criteria createCriteria = example.createCriteria();
		if (studentName != null) {
			createCriteria.andStudentNameLike("%" + studentName.trim() + "%");
		}
		if (classisId != null) {
			createCriteria.andClassisIdEqualTo(classisId);
		}
		return studentMapper.countByExample(example);
	}

	@Override
	public List<Student> getStudentListLikeStudentNameOrClassisId(String studentName, Integer classisId, int page,
			int rows) {
		StudentExample example = new StudentExample();
		PageHelper.startPage(page, rows);
		Criteria createCriteria = example.createCriteria();
		if (studentName != null) {
			createCriteria.andStudentNameLike("%" + studentName.trim() + "%");
		}
		if (classisId != null) {
			createCriteria.andClassisIdEqualTo(classisId);
		}
		return studentMapper.selectByExample(example);
	}

	@Override
	public int getStudentCountByDeparmentId(Integer departmentId) {
		// 根据院系id查询所有班级
		ClassesExample classesExample = new ClassesExample();
		cn.bysj.core.pojo.ClassesExample.Criteria classesCreateCriteria = classesExample.createCriteria();
		classesCreateCriteria.andDepartmentIdEqualTo(departmentId);
		List<Classes> classess = classesMapper.selectByExample(classesExample);
		List<Integer> values = new ArrayList<Integer>();
		for (Classes classes : classess) {
			values.add(classes.getClassisId());
		}
		StudentExample studentExample = new StudentExample();
		Criteria studentCreateCriteria = studentExample.createCriteria();
		studentCreateCriteria.andClassisIdIn(values);
		return studentMapper.countByExample(studentExample);
	}

	@Override
	public List<Student> getStudentListByDeparmentId(Integer departmentId, int page, int rows) {
		ClassesExample classesExample = new ClassesExample();
		cn.bysj.core.pojo.ClassesExample.Criteria classesCreateCriteria = classesExample.createCriteria();
		classesCreateCriteria.andDepartmentIdEqualTo(departmentId);
		List<Classes> classess = classesMapper.selectByExample(classesExample);
		List<Integer> values = new ArrayList<Integer>();
		for (Classes classes : classess) {
			values.add(classes.getClassisId());
		}
		StudentExample studentExample = new StudentExample();
		PageHelper.startPage(page, rows);
		Criteria studentCreateCriteria = studentExample.createCriteria();
		studentCreateCriteria.andClassisIdIn(values);
		return studentMapper.selectByExample(studentExample);
	}

	@Override
	public Student getStudentByStudentNo(String studentNo) {
		StudentExample example = new StudentExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andStudentNoEqualTo(studentNo);
		List<Student> list = studentMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void addStudent(Student student) {
		studentMapper.insert(student);
	}

	@Override
	public List<StudentCustom> getStudentCustomListByClassisId(Integer classisId) {
		StudentExample example = new StudentExample();
		Criteria createCriteria = example.createCriteria();
		if (classisId != null) {
			createCriteria.andClassisIdEqualTo(classisId);
		}
		List<Student> students = studentMapper.selectByExample(example);
		List<StudentCustom> studentCustoms = new ArrayList<StudentCustom>();
		for (Student student : students) {
			StudentCustom custom = new StudentCustom();
			custom.setStudentNo(student.getStudentNo());
			custom.setStudentName(student.getStudentName());
			custom.setPhone(student.getPhone());
			custom.setQq(student.getQq());
			custom.setEmail(student.getEmail());
			custom.setStudentSchoolState(student.getStudentSchoolState());
			Integer studentState = student.getStudentState();
			if (studentState != null && studentState.equals(Constants.StudentInformationNotPerfect)) {
				custom.setStudentStateString("信息未完善");
			} else if (studentState != null && studentState.equals(Constants.StudentWaitForChoice)) {
				custom.setStudentStateString("等待选择");
			} else if (studentState != null && studentState.equals(Constants.StudentWaitingForDistribution)) {
				custom.setStudentStateString("等待分配");
			} else if (studentState != null && studentState.equals(Constants.StudentDistributionSuccess)) {
				custom.setStudentStateString("分配成功");
			}
			studentCustoms.add(custom);
		}
		return studentCustoms;
	}

	@Override
	public Student getstudentByStudentId(Integer studentId) {
		return studentMapper.selectByPrimaryKey(studentId);
	}

	@Override
	public void updateStudent(Student student) {
		studentMapper.updateByPrimaryKeySelective(student);
	}

	@Override
	public StudentCustom getStudentCustom(Student s) {
		StudentCustom custom = new StudentCustom();
		// 查出院系
		ClassesExample example = new ClassesExample();
		cn.bysj.core.pojo.ClassesExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andClassisIdEqualTo(s.getClassisId());
		List<Classes> classes = classesMapper.selectByExample(example);
		if (classes != null && classes.size() > 0) {
			custom.setDepartmentId(classes.get(0).getDepartmentId());
		}
		custom.setStudent(s);
		return custom;
	}

}
