package cn.bysj.core.service.teacher;

import java.util.List;

import cn.bysj.core.pojo.Teacher;
import cn.bysj.core.pojo.vo.TeacherCustom;

public interface TeacherService {

	/**
	 * 查询教师的人数
	 * 
	 * @param teacher
	 * @Description: TODO
	 * @param @param
	 *            teacherQueryVo
	 * @param @return
	 * @return int
	 * @throws @author
	 *             it小祥
	 * @date 2016年11月19日
	 */
	int getTeacherCount(Teacher teacher);

	/**
	 * 获取教师信息
	 * 
	 * @param teacher
	 * @param rows
	 * @param page
	 * @Description: TODO
	 * @param @param
	 *            teacherQueryVo
	 * @param @return
	 * @return List<TeacherCustom>
	 * @throws @author
	 *             it小祥
	 * @date 2016年11月19日
	 */
	List<Teacher> getTeacherList(Teacher teacher, int page, int rows);

	/**
	 * 根据教师的工号去查询教师信息
	 * 
	 * @Description: TODO
	 * @param @param
	 *            teacher
	 * @param @return
	 * @return Teacher
	 * @throws @author
	 *             it小祥
	 * @date 2016年11月22日
	 */
	Teacher getTeacherByTeacherEmployeeNum(Teacher teacher);

	/*
	 * 查出用户对应角色信息
	 */
	TeacherCustom getTeacherCustom(Teacher t) throws Exception;

	/**
	 * 根据院系id去查询该院系的所有教师个数
	 * 
	 * @Description: TODO
	 * @param @param
	 *            departmentId
	 * @param @return
	 * @return int
	 * @throws @author
	 *             it小祥
	 * @date 2016年11月27日
	 */
	int getTeacherCountByDeparmentId(Integer departmentId);

	/**
	 * 根据院系id去查询该院系的所有教师信息
	 * 
	 * @Description: TODO
	 * @param @param
	 *            departmentId
	 * @param @param
	 *            page
	 * @param @param
	 *            rows
	 * @param @return
	 * @return List<Trsection>
	 * @throws @author
	 *             it小祥
	 * @date 2016年11月27日
	 */

	List<Teacher> getTeacherListByDeparmentId(Integer departmentId, Integer page, Integer rows);

	/**
	 * 通过教师名称去模糊查询教师个数
	 * 
	 * @Description: TODO
	 * @param @param
	 *            teacherName
	 * @param @return
	 * @return int
	 * @throws @author
	 *             it小祥
	 * @date 2016年11月27日
	 */
	int getTeacherCountLikeTeacherName(String teacherName);

	/**
	 * 通过教师名称去模糊查询教师信息
	 * 
	 * @Description: TODO
	 * @param @param
	 *            teacherName
	 * @param @param
	 *            page
	 * @param @param
	 *            rows
	 * @param @return
	 * @return List<Teacher>
	 * @throws @author
	 *             it小祥
	 * @date 2016年11月27日
	 */
	List<Teacher> getTeacherListLikeTeacherName(String teacherName, Integer page, Integer rows);

	/**
	 * 通过教研室id查询教师个数
	 * 
	 * @Description: TODO
	 * @param @param
	 *            trsectionId
	 * @param @return
	 * @return int
	 * @throws @author
	 *             it小祥
	 * @date 2016年11月27日
	 */
	int getTeacherCountByTrsectionId(Integer trsectionId);

	/**
	 * 通过教研室id查询教师信息
	 * 
	 * @Description: TODO
	 * @param @param
	 *            trsectionId
	 * @param @param
	 *            page
	 * @param @param
	 *            rows
	 * @param @return
	 * @return List<Teacher>
	 * @throws @author
	 *             it小祥
	 * @date 2016年11月27日
	 */
	List<Teacher> getTeacherListByTrsectionId(Integer trsectionId, Integer page, Integer rows);

	/**
	 * 
	 * @param trsectionId
	 * @param initPassword
	 * @Description: TODO
	 * @param @param
	 *            teahcer
	 * @return void
	 * @throws @author
	 *             it小祥
	 * @date 2016年11月28日
	 */
	void addTeacher(Teacher teacher, Integer trsectionId, String initPassword) throws Exception;

	/**
	 * 通过教师主键或去教师信息
	 * 
	 * @Description: TODO
	 * @param @param
	 *            teacherId
	 * @param @return
	 * @return Teacher
	 * @throws @author
	 *             it小祥
	 * @date 2016年11月29日
	 */
	Teacher getTeacherByTeacherId(Integer teacherId);

	/**
	 * 通过教师的id去修改教师的信息,教师有哪些信息，去修改那些信息
	 * 
	 * @Description: TODO
	 * @param @param
	 *            teacher
	 * @return void
	 * @throws @author
	 *             it小祥
	 * @date 2016年11月29日
	 */
	void updateTeacherByTeacherId(Teacher teacher);

}
