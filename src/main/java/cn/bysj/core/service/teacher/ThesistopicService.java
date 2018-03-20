package cn.bysj.core.service.teacher;

import java.util.List;

import cn.bysj.core.pojo.Thesistopic;
import cn.bysj.core.pojo.vo.StudentThesistopicCustom;
import cn.bysj.core.pojo.vo.ThesistopicCustom;
import cn.bysj.core.pojo.vo.ThesistopicExcel;

public interface ThesistopicService {
	/**
	 * 
	 * @Description: 根据传来的条件查询数量
	 * @param @param
	 *            thesistopicCustom
	 * @param @return
	 * @return int
	 * @throws @author
	 *             it小祥
	 * @date 2017年1月17日
	 */
	int getThesistopicCustomCount(ThesistopicCustom thesistopicCustom);

	/**
	 * 
	 * @Description: 根据传来的条件查询
	 * @param @param
	 *            thesistopicCustom
	 * @param @param
	 *            page
	 * @param @param
	 *            rows
	 * @param @return
	 * @return List<ThesistopicCustom>
	 * @throws @author
	 *             it小祥
	 * @date 2017年1月17日
	 */
	List<ThesistopicCustom> getThesistopicCustomList(ThesistopicCustom thesistopicCustom, Integer page, Integer rows);

	/**
	 * 
	 * @Description: 通过论文id查询论文详细信息
	 * @param @param
	 *            thesisTopicId
	 * @param @return
	 * @return ThesistopicCustom
	 * @throws @author
	 *             it小祥
	 * @date 2017年1月18日
	 */
	ThesistopicCustom getThesistopicCustomById(Integer thesisTopicId);

	/**
	 * 
	 * @param integer
	 * @Description: 导出excel表格
	 * @param @param
	 *            thesistopic
	 * @param @return
	 * @return List<ThesistopicExcel>
	 * @throws @author
	 *             it小祥
	 * @date 2017年1月19日
	 */
	List<ThesistopicExcel> getThesistopicExcel(Thesistopic thesistopic, Integer departmentId);

	/**
	 * 
	 * @Description: 普通教师查看其他教师课题
	 * @param @param
	 *            thesistopicCustom
	 * @param @return
	 * @return int
	 * @throws @author
	 *             it小祥
	 * @date 2017年1月19日
	 */
	int getThesistopicCustomCountForOrteacher(ThesistopicCustom thesistopicCustom);

	/**
	 * 
	 * @Description: 普通教师查看其他教师课题
	 * @param @param
	 *            thesistopicCustom
	 * @param @param
	 *            page
	 * @param @param
	 *            rows
	 * @param @return
	 * @return List<ThesistopicCustom>
	 * @throws @author
	 *             it小祥
	 * @date 2017年1月19日
	 */
	List<ThesistopicCustom> getThesistopicCustomListForOrteacher(ThesistopicCustom thesistopicCustom, int page,
			int rows);

	/**
	 * 
	 * @Description: 普通教师查看自己的论文
	 * @param @param
	 *            thesistopicCustom
	 * @param @return
	 * @return int
	 * @throws @author
	 *             it小祥
	 * @date 2017年1月19日
	 */
	int getThesistopicCustomCountForOrteacherByTeacherId(ThesistopicCustom thesistopicCustom);

	/**
	 * 
	 * @Description: 普通教师查看自己的论文
	 * @param @param
	 *            thesistopicCustom
	 * @param @param
	 *            page
	 * @param @param
	 *            rows
	 * @param @return
	 * @return List<ThesistopicCustom>
	 * @throws @author
	 *             it小祥
	 * @date 2017年1月19日
	 */
	List<ThesistopicCustom> getThesistopicCustomListForOrteacherByTeacherId(ThesistopicCustom thesistopicCustom,
			int page, int rows);

	/**
	 * 
	 * @Description: 通过id查询论文信息
	 * @param @param
	 *            thesistopic
	 * @param @return
	 * @return Thesistopic
	 * @throws @author
	 *             it小祥
	 * @date 2017年1月19日
	 */
	Thesistopic getThesistopicById(Thesistopic thesistopic);

	/**
	 * 
	 * @Description: 更新论文信息
	 * @param @param
	 *            thesistopic
	 * @return void
	 * @throws @author
	 *             it小祥
	 * @date 2017年1月19日
	 */
	void updateThesistopic(Thesistopic thesistopic);

	/**
	 * 
	 * @Description: 根据条件查询
	 * @param @param
	 *            thesistopic
	 * @param @return
	 * @return List<Thesistopic>
	 * @throws @author
	 *             it小祥
	 * @date 2017年1月20日
	 */
	List<Thesistopic> getThesistopic(Thesistopic thesistopic);

	/**
	 * 
	 * @Description: 添加论文
	 * @param @param
	 *            thesistopic
	 * @return void
	 * @throws @author
	 *             it小祥
	 * @date 2017年1月20日
	 */
	void addThesistopic(Thesistopic thesistopic);

	/**
	 * 
	 * @Description: 从学生表查看学生选题情况
	 * @param @param
	 *            teacherCustom
	 * @param @return
	 * @return int
	 * @throws @author
	 *             it小祥
	 * @date 2017年1月20日
	 */
	int getThesistopicCustomCountFromStudent(StudentThesistopicCustom custom);

	/**
	 * 
	 * @Description: 从学生表查看学生选题情况 查询条件 班级id 以及 学生状态，默认情况查出该院系的所有学生所选的课题
	 * @param @param
	 *            teacherCustom
	 * @param @param
	 *            page
	 * @param @param
	 *            rows
	 * @param @return
	 * @return List<StudentThesistopicCustom>
	 * @throws @author
	 *             it小祥
	 * @date 2017年1月20日
	 */
	List<StudentThesistopicCustom> getThesistopicCustomListFromStudent(StudentThesistopicCustom custom, Integer page,
			Integer rows);

	/**
	 * 
	 * @Description:导出Excel 从学生表查看学生选题情况 查询条件 班级id 以及 学生状态，默认情况查出该院系的所有学生所选的课题
	 * @param @param
	 *            custom
	 * @param @return
	 * @return List<ThesistopicExcel>
	 * @throws @author
	 *             it小祥
	 * @date 2017年1月20日
	 */
	List<ThesistopicExcel> getStudentThesistopicExcel(StudentThesistopicCustom custom);

	/**
	 * 
	 * @Description: 查出教师所在教研室的所有教师课题
	 * @param @param
	 *            thesistopicCustom
	 * @param @return
	 * @return int
	 * @throws @author
	 *             it小祥
	 * @date 2017年1月22日
	 */
	int getThesistopicCustomCountForTrDirector(ThesistopicCustom thesistopicCustom);

	/**
	 * 
	 * @Description: 查出教师所在教研室的所有教师课题
	 * @param @param
	 *            thesistopicCustom
	 * @param @param
	 *            page
	 * @param @param
	 *            rows
	 * @param @return
	 * @return List<ThesistopicCustom>
	 * @throws @author
	 *             it小祥
	 * @date 2017年1月22日
	 */
	List<ThesistopicCustom> getThesistopicCustomListForTrDirector(ThesistopicCustom thesistopicCustom, int page,
			int rows);

	/**
	 * 
	 * @Description: 查出学生选择教师的所有课题
	 * @param @param
	 *            thesistopicCustom
	 * @param @return
	 * @return int
	 * @throws @author
	 *             it小祥
	 * @date 2017年1月23日
	 */
	int getThesistopicBeSelectedCustomCount(ThesistopicCustom thesistopicCustom);

	/**
	 * 
	 * @Description: 查出学生选择教师的所有课题
	 * @param @param
	 *            thesistopicCustom
	 * @param @return
	 * @return int
	 * @throws @author
	 *             it小祥
	 * @date 2017年1月23日
	 */
	List<ThesistopicCustom> getThesistopicCustomBeSelectedList(ThesistopicCustom thesistopicCustom, int page, int rows);
}
