package cn.bysj.core.service.business;

import cn.bysj.core.pojo.vo.ThesistopicCustom;

public interface ApplicationService {
	/**
	 * 
	 * @param departmentId
	 * @Description: 比较学生是否超过最大允许选择课题的数量，能否选择
	 * @param @param
	 *            studentId
	 * @param @return
	 * @return boolean true 能选 false 不能选
	 * @throws @author
	 *             it小祥
	 * @date 2017年1月23日
	 */
	boolean compareStudentCanSelect(Integer studentId, Integer departmentId);

	/**
	 * 
	 * @param departmentId
	 * @Description: 比较该课题是否超过允许选择的最大数量，能否选择
	 * @param @param
	 *            thesisTopicId
	 * @param @return
	 * @return boolean true 能选 false 不能选
	 * @throws @author
	 *             it小祥
	 * @date 2017年1月23日
	 */
	boolean compareThesistopicCanBeSelected(Integer thesisTopicId, Integer departmentId);

	/**
	 * 
	 * @Description: 添加一条选课信息
	 * @param @param
	 *            custom
	 * @return void
	 * @throws @author
	 *             it小祥
	 * @date 2017年1月23日
	 */
	void addThesistopicRecord(ThesistopicCustom custom);

	/**
	 * 
	 * @Description: //1、删除除了该学生id选择的记录其他的所有该课题的记录 没有修改其他学生的状态 因为
	 *               其他学生要么为等待选择或者为等待分配，都可以再选 //2、更新论文表该论文的状态为分配成功
	 *               //3、更新该学生的状态为分配成功 //1、2、3为同一事务
	 * @param @param
	 *            custom
	 * @return void
	 * @throws @author
	 *             it小祥
	 * @date 2017年1月24日
	 */
	void teacherSelectStudent(ThesistopicCustom custom);

}
