package cn.bysj.core.pojo.vo;

import java.io.Serializable;

public class SystemcfgCustom implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer systemCfgId;

	private String systemState;

	private Integer thesisNumPerStudentCanSelect;

	private Integer maxStudentNumPerThesisCanBeSelected;

	private String firstRoundStudentSelectBeginTime;

	private String firstRoundStudentSelectEndTime;

	private String firstRoundTeacherSelectBeginTime;

	private String firstRoundTeacherSelectEndTime;

	private String secondRoundStudentSelectBeginTime;

	private String secondRoundStudentSelecEndTime;

	private String secondRoundTeacherSelectBeginTime;

	private String secondRoundTeacherSelectEndTime;
	
	public Integer getSystemCfgId() {
		return systemCfgId;
	}

	public void setSystemCfgId(Integer systemCfgId) {
		this.systemCfgId = systemCfgId;
	}

	public String getSystemState() {
		return systemState;
	}

	public void setSystemState(String systemState) {
		this.systemState = systemState;
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

	public String getFirstRoundStudentSelectBeginTime() {
		return firstRoundStudentSelectBeginTime;
	}

	public void setFirstRoundStudentSelectBeginTime(String firstRoundStudentSelectBeginTime) {
		this.firstRoundStudentSelectBeginTime = firstRoundStudentSelectBeginTime;
	}

	public String getFirstRoundStudentSelectEndTime() {
		return firstRoundStudentSelectEndTime;
	}

	public void setFirstRoundStudentSelectEndTime(String firstRoundStudentSelectEndTime) {
		this.firstRoundStudentSelectEndTime = firstRoundStudentSelectEndTime;
	}

	public String getFirstRoundTeacherSelectBeginTime() {
		return firstRoundTeacherSelectBeginTime;
	}

	public void setFirstRoundTeacherSelectBeginTime(String firstRoundTeacherSelectBeginTime) {
		this.firstRoundTeacherSelectBeginTime = firstRoundTeacherSelectBeginTime;
	}

	public String getFirstRoundTeacherSelectEndTime() {
		return firstRoundTeacherSelectEndTime;
	}

	public void setFirstRoundTeacherSelectEndTime(String firstRoundTeacherSelectEndTime) {
		this.firstRoundTeacherSelectEndTime = firstRoundTeacherSelectEndTime;
	}

	public String getSecondRoundStudentSelectBeginTime() {
		return secondRoundStudentSelectBeginTime;
	}

	public void setSecondRoundStudentSelectBeginTime(String secondRoundStudentSelectBeginTime) {
		this.secondRoundStudentSelectBeginTime = secondRoundStudentSelectBeginTime;
	}

	public String getSecondRoundStudentSelecEndTime() {
		return secondRoundStudentSelecEndTime;
	}

	public void setSecondRoundStudentSelecEndTime(String secondRoundStudentSelecEndTime) {
		this.secondRoundStudentSelecEndTime = secondRoundStudentSelecEndTime;
	}

	public String getSecondRoundTeacherSelectBeginTime() {
		return secondRoundTeacherSelectBeginTime;
	}

	public void setSecondRoundTeacherSelectBeginTime(String secondRoundTeacherSelectBeginTime) {
		this.secondRoundTeacherSelectBeginTime = secondRoundTeacherSelectBeginTime;
	}

	public String getSecondRoundTeacherSelectEndTime() {
		return secondRoundTeacherSelectEndTime;
	}

	public void setSecondRoundTeacherSelectEndTime(String secondRoundTeacherSelectEndTime) {
		this.secondRoundTeacherSelectEndTime = secondRoundTeacherSelectEndTime;
	}

}
