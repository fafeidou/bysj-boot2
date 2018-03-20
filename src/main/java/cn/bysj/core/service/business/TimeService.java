package cn.bysj.core.service.business;

public interface TimeService {
	
	/**
	 * 
	 * @param departmentId 
	 * @Description: 比较是否在学生的选题时间内
	 * @param @param now
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author it小祥
	 * @date 2017年1月23日
	 */
	boolean compareStudentTime(Long now, Integer departmentId);
	/**
	 * 
	 * @param departmentId 
	 * @Description: 比较是否在老师的选学生时间内
	 * @param @param now
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author it小祥
	 * @date 2017年1月23日
	 */
	boolean compareTeacherTime(Long now, Integer departmentId);


}
