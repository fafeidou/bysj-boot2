package cn.bysj.core.service.teacher;

import java.util.List;

import cn.bysj.core.pojo.Trsection;

public interface TrsectionService {
	/**
	 * 根据教研室姓名去模糊查询教研室的数量
	 */
	int getTrsectionCountByTrSectionLikeName(String sectionName);

	/**
	 * 根据教研室姓名去模糊查询所有教研室
	 *
	 */
	List<Trsection> getTrsectionListByTrSectionLikeName(String sectionName,
			int page, int rows);

	/*
	 * 根据教师所在的院系id查询所有的教研室个数
	 */
	int getTrsectionCountByDeparmentId(Integer departmentId);

	/*
	 * 根据教师所在的院系id查询所有的教研室信息
	 */
	List<Trsection> getTrsectionListByDeparmentId(Integer departmentId,
			Integer page, Integer rows);

	/*
	 * 添加教研室
	 */
	void addTrsection(Trsection trsection) throws Exception;

	/**
	 * 通过教研室id去删除教研室
	 */
	void deleteTrsectionByTrsectionId(Integer trsectionId) throws Exception;
	/**
	 * 修改教研室信息   通过 教研室id
	 * @Description: TODO
	 * @param @param trsectionId   
	 * @return void  
	 * @throws
	 * @author it小祥
	 * @date 2016年11月26日
	 */
	void updateTrsectionByTrsectionId(Trsection trsection);
	/**
	 * 通过教研室id获取教研室信息
	 * @Description: TODO
	 * @param @param trsectionId   
	 * @return void  
	 * @throws
	 * @author it小祥
	 * @date 2016年11月26日
	 */
	Trsection getTrsectionByTrsectionId(Integer trsectionId);
	/**
	 * 根据教师所在的院系id查询所有的教研室信息
	 * @Description: TODO
	 * @param @param departmentId
	 * @param @return   
	 * @return List<Trsection>  
	 * @throws
	 * @author it小祥
	 * @date 2016年11月27日
	 */
	List<Trsection> getTrsectionListByDeparmentId(Integer departmentId);

}
