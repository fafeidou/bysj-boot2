package cn.bysj.core.service.systemManage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bysj.core.mapper.TrsectionMapper;
import cn.bysj.core.pojo.Departments;
import cn.bysj.core.pojo.Trsection;
import cn.bysj.core.pojo.TrsectionExample;
import cn.bysj.core.pojo.TrsectionExample.Criteria;
import cn.itcast.common.page.Pagination;

/**
 * 教研室管理 ClassName: TrsetionManagerService
 * 
 * @Description: TODO
 * @author it小祥
 * @date 2016年11月8日
 */
@Service
@Transactional
@Primary
public class TrsetionManageServiceImpl implements TrsetionManageService {
	@Autowired
	private TrsectionMapper trsectionMapper;

	@Override
	@Transactional(readOnly = true)
	public List<Trsection> getTrsectionsByName(String sectionName) {
		TrsectionExample example = new TrsectionExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andSectionNameEqualTo(sectionName);
		return trsectionMapper.selectByExample(example);
	}

	@Override
	public Integer addTrsection(Trsection trsection) {
		return trsectionMapper.insert(trsection);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Trsection> getAllTrsections() {
		TrsectionExample example = new TrsectionExample();
		return trsectionMapper.selectByExample(example);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Trsection> getTrsectionsByDepartmentId(Integer trsectionId) {
		TrsectionExample example = new TrsectionExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andDepartmentIdEqualTo(trsectionId);
		return trsectionMapper.selectByExample(example);
	}

	@Override
	@Transactional(readOnly = true)
	public Pagination getDepartmentsListWithPage(TrsectionExample example,
			Departments department) {
		// 要是用limit
		example.setIsLimit(1);
		Criteria createCriteria = example.createCriteria();
		example.setOrderByClause("department_ID desc");
		if (department.getDepartmentId() != null) {
			createCriteria.andDepartmentIdEqualTo(department.getDepartmentId());
		}
		Pagination p = new Pagination(example.getPageNo(),
				example.getPageSize(), trsectionMapper.countByExample(example));
		List<Trsection> list = trsectionMapper.selectByExample(example);
		p.setList(list);
		return p;
	}

	@Override
	public int updateTrsectionNameById(Trsection trsection) {
		int key = trsectionMapper.updateByPrimaryKeySelective(trsection);
		return key;
	}

	@Override
	public Integer deleteTrsectionById(Integer trsectionId) {
		return trsectionMapper.deleteByPrimaryKey(trsectionId);
	}


}
