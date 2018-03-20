package cn.bysj.core.service.teacher.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.bysj.core.mapper.RoleoperationMapper;
import cn.bysj.core.mapper.TeacherMapper;
import cn.bysj.core.mapper.TeacherMapperCustom;
import cn.bysj.core.mapper.TechroleMapper;
import cn.bysj.core.mapper.TrsectionMapper;
import cn.bysj.core.pojo.Roleoperation;
import cn.bysj.core.pojo.RoleoperationExample;
import cn.bysj.core.pojo.Teacher;
import cn.bysj.core.pojo.TeacherExample;
import cn.bysj.core.pojo.TeacherExample.Criteria;
import cn.bysj.core.pojo.TechroleExample;
import cn.bysj.core.pojo.TechroleKey;
import cn.bysj.core.pojo.Trsection;
import cn.bysj.core.pojo.TrsectionExample;
import cn.bysj.core.pojo.vo.Menu;
import cn.bysj.core.pojo.vo.TeacherCustom;
import cn.bysj.core.process.result.ExceptionResultInfo;
import cn.bysj.core.process.result.ResultInfo;
import cn.bysj.core.service.teacher.TeacherService;
import cn.bysj.core.web.common.Constants;

@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private TechroleMapper techroleMapper;
	@Autowired
	private TrsectionMapper trsectionMapper;
	@Autowired
	private RoleoperationMapper roleoperationMapper;
	@Autowired
	private TeacherMapper teacherMapper;
	@Autowired
	private TeacherMapperCustom teacherMapperCustom;

	@Override
	public int getTeacherCount(Teacher teacher) {
		TeacherExample example = new TeacherExample();
		Criteria createCriteria = example.createCriteria();
		if (StringUtils.isNotBlank(teacher.getTeacherName())) {
			createCriteria.andTeacherNameEqualTo(teacher.getTeacherName());
		}
		return teacherMapper.countByExample(example);
	}

	@Override
	public List<Teacher> getTeacherList(Teacher teacher, int page, int rows) {
		TeacherExample example = new TeacherExample();
		PageHelper.startPage(page, rows);
		Criteria createCriteria = example.createCriteria();
		if (StringUtils.isNotBlank(teacher.getTeacherName())) {
			createCriteria.andTeacherNameEqualTo(teacher.getTeacherName());
		}

		return teacherMapper.selectByExample(example);
	}

	public Teacher getTeacherByTeacherEmployeeNum(Teacher teacher) {
		TeacherExample example = new TeacherExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEmployeeNumEqualTo(teacher.getEmployeeNum());
		List<Teacher> selectByExample = teacherMapper.selectByExample(example);
		if (selectByExample != null && selectByExample.size() > 0)
			return selectByExample.get(0);
		return null;
	}

	@Override
	public TeacherCustom getTeacherCustom(Teacher t) throws Exception {

		// 首先查出老师具有的techrole
		// 根据教师的id去查出对应的角色id
		TechroleExample techroleExample = new TechroleExample();
		cn.bysj.core.pojo.TechroleExample.Criteria techroleCriteria = techroleExample.createCriteria();
		techroleCriteria.andTeacherIdEqualTo(t.getTeacherId());
		Integer roleId = techroleMapper.selectByExample(techroleExample).get(0).getRoleId();
		TeacherCustom teacherCustom = new TeacherCustom();
		// 教师所在院系Id
		Integer departmentId = trsectionMapper.selectByPrimaryKey(t.getTrsectionId()).getDepartmentId();
		teacherCustom.setDepartmentId(departmentId);
		// 教师所在的教研室
		teacherCustom.setTrsectionId(t.getTrsectionId());
		// 查出普通教师对应的角色的所有权限
		List<Menu> menu_list = teacherMapperCustom.findMenuByroleid(roleId);
		Menu menu = new Menu();
		menu.setMenus(menu_list);
		teacherCustom.setMenu(menu);
		// 根据roleid去查所有的operation
		RoleoperationExample roleoperationExample = new RoleoperationExample();
		roleoperationExample.createCriteria().andRoleIdEqualTo(roleId);
		List<Roleoperation> operations = roleoperationMapper.selectByExample(roleoperationExample);
		teacherCustom.setOperationList(operations);
		return teacherCustom;
	}

	@Override
	public int getTeacherCountByDeparmentId(Integer departmentId) {
		// 先根据院系id查出所有教研室
		TrsectionExample trsectionExample = new TrsectionExample();
		trsectionExample.createCriteria().andDepartmentIdEqualTo(departmentId);
		List<Trsection> trsections = trsectionMapper.selectByExample(trsectionExample);
		List<Integer> values = new ArrayList<Integer>();
		for (Trsection trsection : trsections) {
			values.add(trsection.getTrsectionId());
		}
		TeacherExample teacherExample = new TeacherExample();
		teacherExample.createCriteria().andTrsectionIdIn(values);
		return teacherMapper.countByExample(teacherExample);
	}

	@Override
	public List<Teacher> getTeacherListByDeparmentId(Integer departmentId, Integer page, Integer rows) {
		// 先根据院系id查出所有教研室
		TrsectionExample trsectionExample = new TrsectionExample();
		trsectionExample.createCriteria().andDepartmentIdEqualTo(departmentId);
		List<Trsection> trsections = trsectionMapper.selectByExample(trsectionExample);
		List<Integer> values = new ArrayList<Integer>();
		for (Trsection trsection : trsections) {
			values.add(trsection.getTrsectionId());
		}
		TeacherExample teacherExample = new TeacherExample();
		if(page != null && rows != null){
			PageHelper.startPage(page, rows);
		}
		teacherExample.createCriteria().andTrsectionIdIn(values);
		return teacherMapper.selectByExample(teacherExample);
	}

	@Override
	public int getTeacherCountLikeTeacherName(String teacherName) {
		TeacherExample example = new TeacherExample();
		example.createCriteria().andTeacherNameLike("%" + teacherName + "%");
		return teacherMapper.countByExample(example);
	}

	@Override
	public List<Teacher> getTeacherListLikeTeacherName(String teacherName, Integer page, Integer rows) {
		TeacherExample example = new TeacherExample();
		if (page != null && rows != null) {
			PageHelper.startPage(page, rows);
		}
		example.createCriteria().andTeacherNameLike("%" + teacherName + "%");
		return teacherMapper.selectByExample(example);
	}

	@Override
	public int getTeacherCountByTrsectionId(Integer trsectionId) {
		TeacherExample example = new TeacherExample();
		example.createCriteria().andTrsectionIdEqualTo(trsectionId);
		return teacherMapper.countByExample(example);
	}

	@Override
	public List<Teacher> getTeacherListByTrsectionId(Integer trsectionId, Integer page, Integer rows) {
		TeacherExample example = new TeacherExample();
		if (page != null && rows != null) {
			PageHelper.startPage(page, rows);
		}
		if (trsectionId != null) {
			example.createCriteria().andTrsectionIdEqualTo(trsectionId);
		}
		return teacherMapper.selectByExample(example);
	}

	@Override
	public void addTeacher(Teacher teacher, Integer trsectionId, String initPassword) throws Exception {
		// 查看教师的职工号是否存在 ， 如果存在不能插入
		TeacherExample example = new TeacherExample();
		example.createCriteria().andEmployeeNumEqualTo(teacher.getEmployeeNum());
		List<Teacher> teachers = teacherMapper.selectByExample(example);
		if (teachers != null && teachers.size() > 0) {
			ResultInfo resultInfo = new ResultInfo();
			resultInfo.setType(ResultInfo.TYPE_RESULT_FAIL);
			resultInfo.setMessage("插入的教师职工号已存在!");
			throw new ExceptionResultInfo(resultInfo);
		}
		// 教师的默认在职状态为在职
		teacher.setTeacherSchoolState(Constants.TeacherSchoolStateDefault);
		// 设置教师所在的的教研室
		teacher.setTrsectionId(trsectionId);
		// 设置教师的初始密码
		teacher.setPassword(initPassword);
		teacherMapper.insert(teacher);
		// 插入教师的默认角色为普通教师
		TechroleKey techroleKey = new TechroleKey();
		techroleKey.setTeacherId(teacher.getTeacherId());
		techroleKey.setRoleId(Constants.OrdinaryTeacher);
		techroleMapper.insert(techroleKey);
	}

	@Override
	public Teacher getTeacherByTeacherId(Integer teacherId) {

		return teacherMapper.selectByPrimaryKey(teacherId);
	}

	@Override
	public void updateTeacherByTeacherId(Teacher teacher) {
		teacherMapper.updateByPrimaryKeySelective(teacher);
	}

}
