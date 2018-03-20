package cn.bysj.core.service.student;

import java.util.List;

import cn.bysj.core.pojo.vo.ThesistopicCustom;

public interface StudentThesistopicService {
	/**
	 * 
	 * @Description: 学生选题  列表
	 * @param @param thesistopicCustom
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author it小祥
	 * @date 2017年1月23日
	 */
	int getThesistopicCustomCount(ThesistopicCustom thesistopicCustom);
	/**
	 * 
	 * @Description: 学生选题  列表
	 * @param @param thesistopicCustom
	 * @param @param page
	 * @param @param rows
	 * @param @return   
	 * @return List<ThesistopicCustom>  
	 * @throws
	 * @author it小祥
	 * @date 2017年1月23日
	 */
	List<ThesistopicCustom> getThesistopicCustomList(ThesistopicCustom thesistopicCustom, int page, int rows);
	/**
	 * 
	 * @Description: 根据学生id查询学生的所有论文
	 * @param @param thesistopicCustom
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author it小祥
	 * @date 2017年1月23日
	 */
	int getThesistopicCustomCountMyself(ThesistopicCustom thesistopicCustom);
	/**
	 * 
	 * @Description: 根据学生id查询学生的所有论文
	 * @param @param thesistopicCustom
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author it小祥
	 * @date 2017年1月23日
	 */
	List<ThesistopicCustom> getThesistopicCustomListMyself(ThesistopicCustom thesistopicCustom, Integer page, Integer rows);

}
