package cn.bysj.core.pojo;

import java.util.Date;

public class Systemcfg {
    private Integer systemCfgId;

    private Integer departmentId;

    private String systemState;

    private Integer thesisNumPerStudentCanSelect;

    private Integer maxStudentNumPerThesisCanBeSelected;

    private Date firstRoundStudentSelectBeginTime;

    private Date firstRoundStudentSelectEndTime;

    private Date firstRoundTeacherSelectBeginTime;

    private Date firstRoundTeacherSelectEndTime;

    private Date secondRoundStudentSelectBeginTime;

    private Date secondRoundStudentSelecEndTime;

    private Date secondRoundTeacherSelectBeginTime;

    private Date secondRoundTeacherSelectEndTime;

    public Integer getSystemCfgId() {
        return systemCfgId;
    }

    public void setSystemCfgId(Integer systemCfgId) {
        this.systemCfgId = systemCfgId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getSystemState() {
        return systemState;
    }

    public void setSystemState(String systemState) {
        this.systemState = systemState == null ? null : systemState.trim();
    }

    public Integer getThesisNumPerStudentCanSelect() {
        return thesisNumPerStudentCanSelect;
    }

    public void setThesisNumPerStudentCanSelect(Integer thesisNumPerStudentCanSelect) {
        this.thesisNumPerStudentCanSelect = thesisNumPerStudentCanSelect;
    }

    public Integer getMaxStudentNumPerThesisCanBeSelected() {
        return maxStudentNumPerThesisCanBeSelected;
    }

    public void setMaxStudentNumPerThesisCanBeSelected(Integer maxStudentNumPerThesisCanBeSelected) {
        this.maxStudentNumPerThesisCanBeSelected = maxStudentNumPerThesisCanBeSelected;
    }

    public Date getFirstRoundStudentSelectBeginTime() {
        return firstRoundStudentSelectBeginTime;
    }

    public void setFirstRoundStudentSelectBeginTime(Date firstRoundStudentSelectBeginTime) {
        this.firstRoundStudentSelectBeginTime = firstRoundStudentSelectBeginTime;
    }

    public Date getFirstRoundStudentSelectEndTime() {
        return firstRoundStudentSelectEndTime;
    }

    public void setFirstRoundStudentSelectEndTime(Date firstRoundStudentSelectEndTime) {
        this.firstRoundStudentSelectEndTime = firstRoundStudentSelectEndTime;
    }

    public Date getFirstRoundTeacherSelectBeginTime() {
        return firstRoundTeacherSelectBeginTime;
    }

    public void setFirstRoundTeacherSelectBeginTime(Date firstRoundTeacherSelectBeginTime) {
        this.firstRoundTeacherSelectBeginTime = firstRoundTeacherSelectBeginTime;
    }

    public Date getFirstRoundTeacherSelectEndTime() {
        return firstRoundTeacherSelectEndTime;
    }

    public void setFirstRoundTeacherSelectEndTime(Date firstRoundTeacherSelectEndTime) {
        this.firstRoundTeacherSelectEndTime = firstRoundTeacherSelectEndTime;
    }

    public Date getSecondRoundStudentSelectBeginTime() {
        return secondRoundStudentSelectBeginTime;
    }

    public void setSecondRoundStudentSelectBeginTime(Date secondRoundStudentSelectBeginTime) {
        this.secondRoundStudentSelectBeginTime = secondRoundStudentSelectBeginTime;
    }

    public Date getSecondRoundStudentSelecEndTime() {
        return secondRoundStudentSelecEndTime;
    }

    public void setSecondRoundStudentSelecEndTime(Date secondRoundStudentSelecEndTime) {
        this.secondRoundStudentSelecEndTime = secondRoundStudentSelecEndTime;
    }

    public Date getSecondRoundTeacherSelectBeginTime() {
        return secondRoundTeacherSelectBeginTime;
    }

    public void setSecondRoundTeacherSelectBeginTime(Date secondRoundTeacherSelectBeginTime) {
        this.secondRoundTeacherSelectBeginTime = secondRoundTeacherSelectBeginTime;
    }

    public Date getSecondRoundTeacherSelectEndTime() {
        return secondRoundTeacherSelectEndTime;
    }

    public void setSecondRoundTeacherSelectEndTime(Date secondRoundTeacherSelectEndTime) {
        this.secondRoundTeacherSelectEndTime = secondRoundTeacherSelectEndTime;
    }
}