package cn.bysj.core.service.systemManage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bysj.core.mapper.DepartmentsMapper;
import cn.bysj.core.pojo.Departments;
import cn.bysj.core.pojo.DepartmentsExample;
import cn.bysj.core.pojo.DepartmentsExample.Criteria;
import cn.itcast.common.page.Pagination;

/**
 * 院系管理
 * 
 * @author Administrator
 *
 */
@Service
public class DepartmentManageServiceImpl implements DepartmentManageService {
	@Autowired
	private DepartmentsMapper departmentsMapper;

	@Override
	public Integer AddDepartment(Departments department) {
		return departmentsMapper.insertSelective(department);
	}

	@Override
	public List<Departments> findDepartmentByName(Departments department) {
		DepartmentsExample departmentsExample = new DepartmentsExample();
		Criteria createCriteria = departmentsExample.createCriteria();
		createCriteria.andDepartmentNameEqualTo(department.getDepartmentName());
		return departmentsMapper.selectByExample(departmentsExample);
	}


	@Override
	public Pagination getDepartmentsListWithPage(DepartmentsExample example,String departmentName) {
		//要是用limit
		example.setIsLimit(1);
		Criteria createCriteria = example.createCriteria();
		example.setOrderByClause("department_ID desc");
		if(departmentName != null){
			createCriteria.andDepartmentNameLike("%" + departmentName + "%");
		}
		
		Pagination p = new Pagination(example.getPageNo(), example.getPageSize(),departmentsMapper.countByExample(example));
		
		List<Departments> list = departmentsMapper.selectByExample(example);
		p.setList(list);
		return p;
	}

	@Override
	public void updateDepartmentByKey(Departments department) {
		departmentsMapper.updateByPrimaryKey(department);
	}

	@Override
	public void deleteDepartmentByID(Departments department) {
		departmentsMapper.deleteByPrimaryKey(department.getDepartmentId());
	}

	@Override
	public List<Departments> getAllDepartments() {
		DepartmentsExample example = new DepartmentsExample();
		return departmentsMapper.selectByExample(example);
	}

	@Override
	public List<Departments> getDepartmentsLikeName(String departmentName) {
		DepartmentsExample example = new DepartmentsExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andDepartmentNameLike("%" + departmentName + "%");
		return departmentsMapper.selectByExample(example);
	}

}
