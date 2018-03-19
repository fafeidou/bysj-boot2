package cn.bysj.core.pojo.vo;

import com.google.common.base.MoreObjects;

import cn.bysj.core.web.excel.annotation.CellConfig;
import cn.bysj.core.web.excel.annotation.OutAlias;
@OutAlias("xiaoshou")
public class TeacherExcel {

	// 职工号 -- 可以改
	@CellConfig(index = 0)
	private String employeeNum;
	// 教师姓名 -- 可以改
	@CellConfig(index = 1)
	private String teacherName;
	// 教师职称 -- 可以改
	@CellConfig(index = 2)
	private String professionalRank;
	// 专科论文数
	@CellConfig(index = 3)
	private String juniorCollegeQuota;
	// 本科论文数
	@CellConfig(index = 4)
	private String undergraduateQuota;
	// qq号 可以改
	@CellConfig(index = 5)
	private String qq;
	// 手机 可以改
	@CellConfig(index = 6)
	private String phone;
	// 邮箱 可以改
	@CellConfig(index = 7)
	private String email;
	// 教师在校状态 可以改
	@CellConfig(index = 8)
	private String teacherSchoolState;

	public String getEmployeeNum() {
		return employeeNum;
	}

	public void setEmployeeNum(String employeeNum) {
		this.employeeNum = employeeNum;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getProfessionalRank() {
		return professionalRank;
	}

	public void setProfessionalRank(String professionalRank) {
		this.professionalRank = professionalRank;
	}

	public String getJuniorCollegeQuota() {
		return juniorCollegeQuota;
	}

	public void setJuniorCollegeQuota(String juniorCollegeQuota) {
		this.juniorCollegeQuota = juniorCollegeQuota;
	}

	public String getUndergraduateQuota() {
		return undergraduateQuota;
	}

	public void setUndergraduateQuota(String undergraduateQuota) {
		this.undergraduateQuota = undergraduateQuota;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTeacherSchoolState() {
		return teacherSchoolState;
	}

	public void setTeacherSchoolState(String teacherSchoolState) {
		this.teacherSchoolState = teacherSchoolState;
	}

	public String toString() {
		return MoreObjects.toStringHelper(this.getClass()).omitNullValues().add("employeeNum", employeeNum)
				.add("teacherName", teacherName).add("professionalRank", professionalRank)
				.add("juniorCollegeQuota", juniorCollegeQuota).add("undergraduateQuota", undergraduateQuota)
				.add("qq", qq).add("phone", phone).add("email", email).add("teacherSchoolState", teacherSchoolState)
				.toString();
	}
}
