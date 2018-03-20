package cn.bysj.core.service.teacher.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bysj.core.mapper.TeacherMapper;
import cn.bysj.core.mapper.TrsectionMapper;
import cn.bysj.core.pojo.Teacher;
import cn.bysj.core.pojo.TeacherExample;
import cn.bysj.core.pojo.Trsection;
import cn.bysj.core.pojo.TrsectionExample;
import cn.bysj.core.pojo.TrsectionExample.Criteria;
import cn.bysj.core.process.result.ExceptionResultInfo;
import cn.bysj.core.process.result.ResultInfo;
import cn.bysj.core.service.teacher.TrsectionService;

import com.github.pagehelper.PageHelper;

@Service
public class TrsectionServiceImpl implements TrsectionService {
	@Autowired
	private TrsectionMapper trsectionMapper;
	@Autowired
	private TeacherMapper teacherMapper;

	@Override
	public int getTrsectionCountByTrSectionLikeName(String sectionName) {
		TrsectionExample example = new TrsectionExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andSectionNameLike("%" + sectionName + "%");
		return trsectionMapper.countByExample(example);
	}

	@Override
	public List<Trsection> getTrsectionListByTrSectionLikeName(
			String sectionName, int page, int rows) {
		TrsectionExample example = new TrsectionExample();
		PageHelper.startPage(page, rows);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andSectionNameLike("%" + sectionName + "%");
		return trsectionMapper.selectByExample(example);
	}

	@Override
	public int getTrsectionCountByDeparmentId(Integer departmentId) {
		TrsectionExample example = new TrsectionExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andDepartmentIdEqualTo(departmentId);
		return trsectionMapper.countByExample(example);
	}

	@Override
	public List<Trsection> getTrsectionListByDeparmentId(Integer departmentId,
			Integer page, Integer rows) {
		TrsectionExample example = new TrsectionExample();
		if(page != null && rows != null){
			PageHelper.startPage(page, rows);
		}
		Criteria createCriteria = example.createCriteria();
		createCriteria.andDepartmentIdEqualTo(departmentId);
		return trsectionMapper.selectByExample(example);
	}

	@Override
	public void addTrsection(Trsection trsection) throws Exception {
		// 教研室名字不能重复
		TrsectionExample example = new TrsectionExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andSectionNameEqualTo(trsection.getSectionName());
		List<Trsection> selectByExample = trsectionMapper
				.selectByExample(example);
		if (selectByExample.size() > 0) {
			// 使用系统自定义异常类
			ResultInfo resultInfo = new ResultInfo();
			resultInfo.setType(ResultInfo.TYPE_RESULT_FAIL);
			resultInfo.setMessage("教研室名重复");
			throw new ExceptionResultInfo(resultInfo);
		}
		trsectionMapper.insert(trsection);
	}

	@Override
	public void deleteTrsectionByTrsectionId(Integer trsectionId)
			throws Exception {
		// 查看此教研室是否存在
		Trsection trsection = trsectionMapper.selectByPrimaryKey(trsectionId);
		if (trsection == null) {
			ResultInfo resultInfo = new ResultInfo();
			resultInfo.setType(ResultInfo.TYPE_RESULT_FAIL);
			resultInfo.setMessage("所删除的教研室不存在!");
			throw new ExceptionResultInfo(resultInfo);
		}
		// 查看教研室中是否有教师 ， 如果有教师 是不能删除的
		TeacherExample example = new TeacherExample();
		cn.bysj.core.pojo.TeacherExample.Criteria createCriteria = example
				.createCriteria();
		createCriteria.andTrsectionIdEqualTo(trsectionId);
		List<Teacher> teachers = teacherMapper.selectByExample(example);
		if (teachers.size() > 0) {
			ResultInfo resultInfo = new ResultInfo();
			resultInfo.setType(ResultInfo.TYPE_RESULT_FAIL);
			resultInfo.setMessage("教研室中有教师不能删除!");
			throw new ExceptionResultInfo(resultInfo);
		}
		trsectionMapper.deleteByPrimaryKey(trsectionId);

	}


	@Override
	public Trsection getTrsectionByTrsectionId(Integer trsectionId) {
		return trsectionMapper.selectByPrimaryKey(trsectionId);
	}

	@Override
	public void updateTrsectionByTrsectionId(Trsection trsection) {
		trsectionMapper.updateByPrimaryKeySelective(trsection);
	}

	@Override
	public List<Trsection> getTrsectionListByDeparmentId(Integer departmentId) {
		TrsectionExample example = new TrsectionExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andDepartmentIdEqualTo(departmentId);
		return trsectionMapper.selectByExample(example);
	}

}
