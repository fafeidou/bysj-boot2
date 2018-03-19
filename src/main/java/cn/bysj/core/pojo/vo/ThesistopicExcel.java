package cn.bysj.core.pojo.vo;

import java.io.Serializable;

public class ThesistopicExcel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String thesisTitle; // 展示论文标题
	private String thesisEnglishTile; // 展示论文英文标题
	private String sourceTypeName; // 展示来源名字
	private String typeName; // 展示论文类型
	private String projectRequirement; // 展示论文目标和要求
	private String workloadReqirement; // 展示主要研究内容
	private String topicStateString; //论文状态
	private String CreateDateString; //论文添加时间
	private String LastUseDateString;  //论文最后修改时间
	private String note; // 展示课题简介
	private String graduationYear; // 展示毕业年数
	private String teacherName; //教师姓名
	private String studentName; //学生姓名
	private String studentStateString; //学生论文状态
	
	
	public String getStudentStateString() {
		return studentStateString;
	}
	public void setStudentStateString(String studentStateString) {
		this.studentStateString = studentStateString;
	}
	public String getThesisTitle() {
		return thesisTitle;
	}
	public void setThesisTitle(String thesisTitle) {
		this.thesisTitle = thesisTitle;
	}
	public String getThesisEnglishTile() {
		return thesisEnglishTile;
	}
	public void setThesisEnglishTile(String thesisEnglishTile) {
		this.thesisEnglishTile = thesisEnglishTile;
	}
	public String getSourceTypeName() {
		return sourceTypeName;
	}
	public void setSourceTypeName(String sourceTypeName) {
		this.sourceTypeName = sourceTypeName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getProjectRequirement() {
		return projectRequirement;
	}
	public void setProjectRequirement(String projectRequirement) {
		this.projectRequirement = projectRequirement;
	}
	public String getWorkloadReqirement() {
		return workloadReqirement;
	}
	public void setWorkloadReqirement(String workloadReqirement) {
		this.workloadReqirement = workloadReqirement;
	}
	public String getTopicStateString() {
		return topicStateString;
	}
	public void setTopicStateString(String topicStateString) {
		this.topicStateString = topicStateString;
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getGraduationYear() {
		return graduationYear;
	}
	public void setGraduationYear(String graduationYear) {
		this.graduationYear = graduationYear;
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
