package cn.bysj.core.service.systemManage;

import java.util.List;

import cn.bysj.core.pojo.Teacher;
import cn.bysj.core.pojo.TeacherExample;
import cn.itcast.common.page.Pagination;

/**
 * 教师管理 ClassName: TeacherManageService
 * 
 * @Description: TODO
 * @author it小祥
 * @date 2016年11月11日
 */
public interface TeacherManageService {
	/**
	 * 添加一名教师
	 * 
	 * @Description: TODO
	 * @param @param teacher
	 * @param @return
	 * @return Integer
	 * @throws
	 * @author it小祥
	 * @date 2016年11月11日
	 */
	Integer addTeacher(Teacher teacher);

	/**
	 * 根据教师id查询教师
	 * 
	 * @Description: TODO
	 * @param @param teacher
	 * @param @return
	 * @return List<Teacher>
	 * @throws
	 * @author it小祥
	 * @date 2016年11月11日
	 */
	List<Teacher> getTeacherById(Teacher teacher);

	/**
	 * 根据教师职工号查询老师
	 * 
	 * @Description: TODO
	 * @param @param teacher
	 * @param @return
	 * @return List<Teacher>
	 * @throws
	 * @author it小祥
	 * @date 2016年11月11日
	 */
	List<Teacher> getTeacherByEmployeeNum(Teacher teacher);

	/**
	 * 查询教师分页
	 * 
	 * @Description: TODO
	 * @param @param example
	 * @param @param trsectionId
	 * @param @return
	 * @return Pagination
	 * @throws
	 * @author it小祥
	 * @date 2016年11月12日
	 */
	Pagination getTeachersListWithPage(TeacherExample example,
			String trsectionId);

	/**
	 * 通过教研室Id去查老师
	 * 
	 * @Description: TODO
	 * @param @param trsectionId
	 * @param @return
	 * @return List<Teacher>
	 * @throws
	 * @author it小祥
	 * @date 2016年11月18日
	 */
	List<Teacher> getTeachersByTrsectionId(Integer trsectionId);

	/**
	 * 根据所选择的老师的字段修改
	 * 
	 * @Description: TODO
	 * @param @param teacher
	 * @param @return
	 * @return int
	 * @throws
	 * @author it小祥
	 * @date 2016年11月18日
	 */
	int updateTeacherMessage(Teacher teacher);

	/**
	 * 根据教师姓名进行模糊查询分页
	 * 
	 * @Description: TODO
	 * @param @param example
	 * @param @param teacherName
	 * @param @return
	 * @return Pagination
	 * @throws
	 * @author it小祥
	 * @date 2016年11月18日
	 */
	Pagination getTeachersLikeNameWithPage(TeacherExample example,
			String teacherName);

}
