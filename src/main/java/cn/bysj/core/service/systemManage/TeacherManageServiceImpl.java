package cn.bysj.core.service.systemManage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bysj.core.mapper.DepartmentsMapper;
import cn.bysj.core.mapper.RoleMapper;
import cn.bysj.core.mapper.TeacherMapper;
import cn.bysj.core.mapper.TechroleMapper;
import cn.bysj.core.mapper.TrsectionMapper;
import cn.bysj.core.pojo.Departments;
import cn.bysj.core.pojo.DepartmentsExample;
import cn.bysj.core.pojo.Role;
import cn.bysj.core.pojo.RoleExample;
import cn.bysj.core.pojo.Teacher;
import cn.bysj.core.pojo.TeacherExample;
import cn.bysj.core.pojo.TeacherExample.Criteria;
import cn.bysj.core.pojo.TechroleExample;
import cn.bysj.core.pojo.TechroleKey;
import cn.bysj.core.pojo.Trsection;
import cn.bysj.core.pojo.TrsectionExample;
import cn.bysj.core.web.common.Constants;
import cn.itcast.common.page.Pagination;

/**
 * 教师管理 ClassName: TeacherManageService
 * 
 * @Description: TODO
 * @author it小祥
 * @date 2016年11月11日
 */
@Service
@Transactional
public class TeacherManageServiceImpl implements TeacherManageService {
	@Autowired
	private TeacherMapper teacherMapper;
	@Autowired
	private TrsectionMapper trsectionMapper;
	@Autowired
	private DepartmentsMapper departmentsMapper;
	@Autowired
	private TechroleMapper techroleMapper;
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public Integer addTeacher(Teacher teacher) {
		teacher.setTeacherSchoolState(Constants.TeacherSchoolStateDefault);
		int teacherId = teacherMapper.insert(teacher);
		// 添加老师时，老师的默认角色为普通教师
		TechroleKey key = new TechroleKey();
		// 0:表示的是默认角色为 普通教师
		key.setRoleId(Constants.OrdinaryTeacher);
		key.setTeacherId(teacher.getTeacherId());
		techroleMapper.insert(key);
		return teacherId;
	}

	@Override
	public List<Teacher> getTeacherById(Teacher teacher) {
		TeacherExample example = new TeacherExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andTeacherIdEqualTo(teacher.getTeacherId());
		return teacherMapper.selectByExample(example);
	}

	@Override
	public List<Teacher> getTeacherByEmployeeNum(Teacher teacher) {
		TeacherExample example = new TeacherExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEmployeeNumEqualTo(teacher.getEmployeeNum());
		return teacherMapper.selectByExample(example);
	}

	@Override
	public Pagination getTeachersListWithPage(TeacherExample example,
			String trsectionId) {
		// 要用limit
		example.setIsLimit(1);
		Criteria createCriteria = example.createCriteria();
		example.setOrderByClause("teacher_ID desc");
		if (trsectionId != null) {
			Integer parseTrsectionId = Integer.parseInt(trsectionId);
			createCriteria.andTrsectionIdEqualTo(parseTrsectionId);
		}
		Pagination p = new Pagination(example.getPageNo(),
				example.getPageSize(), teacherMapper.countByExample(example));
		List<Teacher> list = teacherMapper.selectByExample(example);
		// 查询 教研室 、 院系
		for (Teacher teacher : list) {
			// 先查询教师所在的教研室
			TrsectionExample trsectionExample = new TrsectionExample();
			cn.bysj.core.pojo.TrsectionExample.Criteria trsectionCriteria = trsectionExample
					.createCriteria();
			trsectionCriteria.andTrsectionIdEqualTo(teacher.getTrsectionId());
			List<Trsection> trsections = trsectionMapper
					.selectByExample(trsectionExample);
			teacher.setTrsectionName(trsections.get(0).getSectionName());
			// 查询老师所在的院系
			DepartmentsExample departmentsExample = new DepartmentsExample();
			cn.bysj.core.pojo.DepartmentsExample.Criteria departmentCriteria = departmentsExample
					.createCriteria();
			departmentCriteria.andDepartmentIdEqualTo(trsections.get(0)
					.getDepartmentId());
			List<Departments> departmentSelectByExample = departmentsMapper
					.selectByExample(departmentsExample);
			teacher.setDepartmentName(departmentSelectByExample.get(0)
					.getDepartmentName());
			// 查处老师担任的角色
			// 1，先查出所有老师的角色ID
			TechroleExample techroleExample = new TechroleExample();
			cn.bysj.core.pojo.TechroleExample.Criteria techroleExampleCriteria = techroleExample
					.createCriteria();
			techroleExampleCriteria.andTeacherIdEqualTo(teacher.getTeacherId());
			List<TechroleKey> techroleKeys = techroleMapper
					.selectByExample(techroleExample);

			List<Integer> roleIds = new ArrayList<Integer>();
			for (int i = 0; i < techroleKeys.size(); i++) {
				roleIds.add(i, techroleKeys.get(i).getRoleId());
			}
			// 2，再去查所有角色的名称
			RoleExample roleExample = new RoleExample();
			cn.bysj.core.pojo.RoleExample.Criteria roleExampleCriteria = roleExample
					.createCriteria();
			roleExampleCriteria.andRoleIdIn(roleIds);
			List<Role> roles = roleMapper.selectByExample(roleExample);
			// 把查出来的角色给老师
			teacher.setRoles(roles);
		}

		p.setList(list);
		return p;
	}

	@Override
	public List<Teacher> getTeachersByTrsectionId(Integer trsectionId) {
		TeacherExample example = new TeacherExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andTrsectionIdEqualTo(trsectionId);
		return teacherMapper.selectByExample(example);
	}

	@Override
	public int updateTeacherMessage(Teacher teacher) {
		return teacherMapper.updateByPrimaryKeySelective(teacher);
	}
	
	@Override
	public Pagination getTeachersLikeNameWithPage(TeacherExample example,
			String teacherName) {
		example.setIsLimit(1);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andTeacherNameLike("%" + teacherName + "%");
		Pagination p = new Pagination(example.getPageNo(), example.getPageSize(),teacherMapper.countByExample(example));
		
		List<Teacher> list = teacherMapper.selectByExample(example);
		p.setList(list);
		
		return p;
	}

}
