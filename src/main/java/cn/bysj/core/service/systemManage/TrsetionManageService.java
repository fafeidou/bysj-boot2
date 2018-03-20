package cn.bysj.core.service.systemManage;

import java.util.List;

import cn.bysj.core.pojo.Departments;
import cn.bysj.core.pojo.Trsection;
import cn.bysj.core.pojo.TrsectionExample;
import cn.itcast.common.page.Pagination;


/**
 * 教研室管理
 * ClassName: TrsetionManagerService
 * @Description: TODO
 * @author it小祥
 * @date 2016年11月8日
 */
public interface TrsetionManageService {
	/**
	 * 根据教研室名称查询
	 * @param sectionName 
	 * @Description: TODO
	 * @param @return   
	 * @return List<Trsection>  
	 * @throws
	 * @author it小祥
	 * @date 2016年11月8日
	 */
	List<Trsection> getTrsectionsByName(String sectionName);
	/**
	 * 添加教研室
	 * @Description: TODO
	 * @param @param trsection   
	 * @return void  
	 * @throws
	 * @author it小祥
	 * @date 2016年11月8日
	 */
	Integer addTrsection(Trsection trsection);
	/**
	 * 查询所有教研室
	 * @Description: TODO
	 * @param @return   
	 * @return List<Trsection>  
	 * @throws
	 * @author it小祥
	 * @date 2016年11月11日
	 */
	List<Trsection> getAllTrsections();
	/**
	 * 根据院系id查询所有教研室
	 * @Description: TODO
	 * @param @param trsectionId
	 * @param @return   
	 * @return List<Trsection>  
	 * @throws
	 * @author it小祥
	 * @date 2016年11月11日
	 */
	List<Trsection> getTrsectionsByDepartmentId(Integer trsectionId);
	/**
	 * 教研室的查询分页
	 * @Description: TODO
	 * @param @param example
	 * @param @param department
	 * @param @return   
	 * @return Pagination  
	 * @throws
	 * @author it小祥
	 * @date 2016年11月14日
	 */
	Pagination getDepartmentsListWithPage(TrsectionExample example,
			Departments department);
	/**
	 * 通过教研室id去修改教研室的名字
	 * @param trsection 
	 * @Description: TODO
	 * @param    
	 * @return void  
	 * @throws
	 * @author it小祥
	 * @date 2016年11月14日
	 */
	int updateTrsectionNameById(Trsection trsection);
	/**
	 * 通过教研室Id删除教研室  
	 * @Description: TODO
	 * @param @param trsectionId   
	 * @return void  
	 * @throws
	 * @author it小祥
	 * @date 2016年11月18日
	 */
	Integer deleteTrsectionById(Integer trsectionId);
	

}
