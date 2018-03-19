package cn.bysj.core.pojo.vo;

import java.io.Serializable;

import cn.bysj.core.pojo.Student;
import cn.bysj.core.pojo.Thesistopic;
import cn.bysj.core.pojo.Topicsourcetype;
import cn.bysj.core.pojo.Topictype;

/**
 * 
 * ClassName: ThesistopicCustom
 * 
 * @Description: 论文扩展类
 * @author it小祥
 * @date 2017年1月17日
 */
public class ThesistopicCustom extends Thesistopic implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer teacherId;
	private Integer departmentId;
	private String teacherName;
	private String studentName;
	private Integer trsectionId;
	private Integer studentId;

	private Thesistopic thesistopic;
	private Topictype topictype;
	private Topicsourcetype topicsourcetype;
	private String topicStateString;
	private String CreateDateString;
	private String LastUseDateString;
	private Student student;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Integer getTrsectionId() {
		return trsectionId;
	}

	public void setTrsectionId(Integer trsectionId) {
		this.trsectionId = trsectionId;
	}

	public String getCreateDateString() {
		return CreateDateString;
	}

	public void setCreateDateString(String createDateString) {
		CreateDateString = createDateString;
	}

	public String getLastUseDateString() {
		return LastUseDateString;
	}

	public void setLastUseDateString(String lastUseDateString) {
		LastUseDateString = lastUseDateString;
	}

	public Topictype getTopictype() {
		return topictype;
	}

	public void setTopictype(Topictype topictype) {
		this.topictype = topictype;
	}

	public Topicsourcetype getTopicsourcetype() {
		return topicsourcetype;
	}

	public void setTopicsourcetype(Topicsourcetype topicsourcetype) {
		this.topicsourcetype = topicsourcetype;
	}

	public String getTopicStateString() {
		return topicStateString;
	}

	public void setTopicStateString(String topicStateString) {
		this.topicStateString = topicStateString;
	}

	public Thesistopic getThesistopic() {
		return thesistopic;
	}

	public void setThesistopic(Thesistopic thesistopic) {
		this.thesistopic = thesistopic;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

}
