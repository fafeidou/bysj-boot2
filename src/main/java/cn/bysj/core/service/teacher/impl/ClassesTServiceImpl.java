package cn.bysj.core.service.teacher.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.bysj.core.mapper.ClassesMapper;
import cn.bysj.core.mapper.StudentMapper;
import cn.bysj.core.pojo.Classes;
import cn.bysj.core.pojo.ClassesExample;
import cn.bysj.core.pojo.ClassesExample.Criteria;
import cn.bysj.core.pojo.Student;
import cn.bysj.core.pojo.StudentExample;
import cn.bysj.core.process.result.ExceptionResultInfo;
import cn.bysj.core.process.result.ResultInfo;
import cn.bysj.core.service.teacher.ClassesService;

@Service
public class ClassesTServiceImpl implements ClassesService {
	@Autowired
	private ClassesMapper classesMapper;
	@Autowired
	private StudentMapper studentMapper;

	@Override
	public List<Classes> getClassByDepartmentID(Integer departmentId) {
		ClassesExample example = new ClassesExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andDepartmentIdEqualTo(departmentId);
		return classesMapper.selectByExample(example);
	}
	@Override
	public int getClassesCountByClassesLikeName(String className) {
		ClassesExample example = new ClassesExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andClassNameLike("%" + className + "%");
		return classesMapper.countByExample(example);
	}

	@Override
	public List<Classes> getClassesListByClassesLikeName(String className, int page, int rows) {
		ClassesExample example = new ClassesExample();
		PageHelper.startPage(page, rows);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andClassNameLike("%" + className + "%");
		return classesMapper.selectByExample(example);
	}

	@Override
	public int getClassesCountByDeparmentId(Integer departmentId) {
		ClassesExample example = new ClassesExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andDepartmentIdEqualTo(departmentId);
		return classesMapper.countByExample(example);
	}

	@Override
	public List<Classes> getClassesListByDeparmentId(Integer departmentId, Integer page, Integer rows) {
		ClassesExample example = new ClassesExample();
		if(page != null && rows != null){
			PageHelper.startPage(page, rows);
		}
		Criteria createCriteria = example.createCriteria();
		createCriteria.andDepartmentIdEqualTo(departmentId);
		return classesMapper.selectByExample(example);
	}

	@Override
	public void addClasses(Classes classes) {
		classesMapper.insert(classes);
	}

	@Override
	public Classes getClassesByClassesName(String className) {
		ClassesExample example = new ClassesExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andClassNameEqualTo(className);
		List<Classes> list = classesMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void deleteClassesByClassisId(Integer classisId) throws Exception {
		// 查看此班级是否存在
		Classes classes = classesMapper.selectByPrimaryKey(classisId);
		if (classes == null) {
			ResultInfo resultInfo = new ResultInfo();
			resultInfo.setType(ResultInfo.TYPE_RESULT_FAIL);
			resultInfo.setMessage("所删除的班级不存在!");
			throw new ExceptionResultInfo(resultInfo);
		}
		// 查看班级中是否学生 ， 如果有学生 是不能删除的
		StudentExample example2 = new StudentExample();
		cn.bysj.core.pojo.StudentExample.Criteria createCriteria2 = example2.createCriteria();
		createCriteria2.andClassisIdEqualTo(classisId);
		List<Student> students = studentMapper.selectByExample(example2);
		if (students != null && students.size() > 0) {
			ResultInfo resultInfo = new ResultInfo();
			resultInfo.setType(ResultInfo.TYPE_RESULT_FAIL);
			resultInfo.setMessage("班级中有教师不能删除!");
			throw new ExceptionResultInfo(resultInfo);
		}

		classesMapper.deleteByPrimaryKey(classisId);
	}

	@Override
	public Classes getClassesByClassesId(Integer classisId) {
		return classesMapper.selectByPrimaryKey(classisId);
	}

	@Override
	public void updateClassesByClassisId(Classes classes) {
		classesMapper.updateByPrimaryKeySelective(classes);
	}

}
