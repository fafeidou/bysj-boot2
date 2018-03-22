package cn.bysj.core.pojo;

import java.io.Serializable;
import java.util.Date;

public class Thesistopic implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer thesisTopicId = 88888888;  //---

    private Integer topicSourceTypeId;   //展示来源名字
    
    private Integer teacherId; //展示教师姓名---

    private Integer topicTypeId; //展示论文类型

    private String thesisTitle;   //展示论文标题

    private String thesisEnglishTile;  // 展示论文英文标题
 
    private String projectRequirement;  //展示论文目标和要求

    private String workloadReqirement;  //展示主要研究内容

    private Byte otherTeacherCanSee;  //其他老师是否能否看见---

    private Byte topicState;   //展示论文状态----- 课题的状态（0:教案室未审核1，教研室未通过2，院系未审核3，院系审核未通过4，等待选择5，等待分配6，完成）

    private Date createDate;   //展示课程添加时间----

    private Date lastUseDate;   //展示课程最后修改时间-----

    private String note;  //展示课题简介

    private String graduationYear;  //展示毕业年数

    public Integer getThesisTopicId() {
        return thesisTopicId;
    }

    public void setThesisTopicId(Integer thesisTopicId) {
        this.thesisTopicId = thesisTopicId;
    }

    public Integer getTopicSourceTypeId() {
        return topicSourceTypeId;
    }

    public void setTopicSourceTypeId(Integer topicSourceTypeId) {
        this.topicSourceTypeId = topicSourceTypeId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getTopicTypeId() {
        return topicTypeId;
    }

    public void setTopicTypeId(Integer topicTypeId) {
        this.topicTypeId = topicTypeId;
    }

    public String getThesisTitle() {
        return thesisTitle;
    }

    public void setThesisTitle(String thesisTitle) {
        this.thesisTitle = thesisTitle == null ? null : thesisTitle.trim();
    }

    public String getThesisEnglishTile() {
        return thesisEnglishTile;
    }

    public void setThesisEnglishTile(String thesisEnglishTile) {
        this.thesisEnglishTile = thesisEnglishTile == null ? null : thesisEnglishTile.trim();
    }

    public String getProjectRequirement() {
        return projectRequirement;
    }

    public void setProjectRequirement(String projectRequirement) {
        this.projectRequirement = projectRequirement == null ? null : projectRequirement.trim();
    }

    public String getWorkloadReqirement() {
        return workloadReqirement;
    }

    public void setWorkloadReqirement(String workloadReqirement) {
        this.workloadReqirement = workloadReqirement == null ? null : workloadReqirement.trim();
    }

    public Byte getOtherTeacherCanSee() {
        return otherTeacherCanSee;
    }

    public void setOtherTeacherCanSee(Byte otherTeacherCanSee) {
        this.otherTeacherCanSee = otherTeacherCanSee;
    }

    public Byte getTopicState() {
        return topicState;
    }

    public void setTopicState(Byte topicState) {
        this.topicState = topicState;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUseDate() {
        return lastUseDate;
    }

    public void setLastUseDate(Date lastUseDate) {
        this.lastUseDate = lastUseDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear == null ? null : graduationYear.trim();
    }
}