package cn.bysj.core.service.systemManage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bysj.core.mapper.RoleMapper;
import cn.bysj.core.mapper.TechroleMapper;
import cn.bysj.core.pojo.Role;
import cn.bysj.core.pojo.RoleExample;
import cn.bysj.core.pojo.TechroleExample;
import cn.bysj.core.pojo.TechroleExample.Criteria;
import cn.bysj.core.pojo.TechroleKey;

/**
 * 角色管理 ClassName: RoleManageService
 * 
 * @Description: TODO
 * @author it小祥
 * @date 2016年11月12日
 */
@Service
public class RoleManageServiceImpl implements RoleManageService {
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private TechroleMapper techroleMapper;

	@Override
	public List<Role> getAllRoles() {
		RoleExample example = new RoleExample();
		return roleMapper.selectByExample(example);
	}

	@Override
	public void updateTeacherRole(Integer teacherId, List<Integer> roleIds) {
		// 先查询出该老师所拥有的所有角色
		TechroleExample techroleExample = new TechroleExample();
		cn.bysj.core.pojo.TechroleExample.Criteria techroleExampleCriteria = techroleExample
				.createCriteria();
		techroleExampleCriteria.andTeacherIdEqualTo(teacherId);
		List<TechroleKey> techRoles = techroleMapper
				.selectByExample(techroleExample);
		List<Integer> previousRoleIds = new ArrayList<Integer>();
		for (int i = 0; i < techRoles.size(); i++) {
			previousRoleIds.add(i, techRoles.get(i).getRoleId());
		}

		for (int i = 0; i < roleIds.size(); i++) {
			// 如果本次修改的角色老师以前角色没有，那就插入一个角色
			if (!previousRoleIds.contains(roleIds.get(i))) {
				TechroleKey techroleKey = new TechroleKey();
				techroleKey.setRoleId(roleIds.get(i));
				techroleKey.setTeacherId(teacherId);
				techroleMapper.insert(techroleKey);
			}
			// 如果本次修改包含了老师以前有的角色，那就不做修改
		}
		// 如果老师以前有的角色，这次没有选，那就删除
		for (int i = 0; i < previousRoleIds.size(); i++) {
			if (!roleIds.contains(previousRoleIds.get(i))) {
				TechroleKey techroleKey = new TechroleKey();
				techroleKey.setRoleId(previousRoleIds.get(i));
				techroleKey.setTeacherId(teacherId);
				techroleMapper.deleteByPrimaryKey(techroleKey);
			}
		}
	}

	@Override
	public Integer updateTeacherRole(TechroleKey techroleKey) {
		
		/*
		 
		TechroleExample example = new TechroleExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andRoleIdEqualTo(techroleKey.getRoleId());
		createCriteria.andTeacherIdEqualTo(techroleKey.getTeacherId());
		 */
		TechroleExample example2 = new TechroleExample();
		Criteria createCriteria2 = example2.createCriteria();
		createCriteria2.andTeacherIdEqualTo(techroleKey.getTeacherId());
		TechroleKey techroleKey2 = techroleMapper.selectByExample(example2).get(0);
		
		techroleMapper.deleteByPrimaryKey(techroleKey2);
		return techroleMapper.insert(techroleKey);
	}
}
