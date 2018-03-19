package cn.bysj.core.pojo;

import java.io.Serializable;
import java.util.List;

public class Teacher implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//教师ID   
    private Integer teacherId;
    //教研室ID    -- 
    private Integer trsectionId;
    //职工号     --   可以改
    private String employeeNum;
    //教师姓名  --    可以改
    private String teacherName;
    //密码                
    private String password;
    //手机           可以改
    private String phone;
    //qq号       可以改
    private String qq;
    //邮箱         可以改
    private String email;
    //专科论文数        
    private Integer juniorCollegeQuota;
     //本科论文数
    private Integer undergraduateQuota;
    //教师职称    --    可以改
    private String professionalRank;
    //教师在校状态            可以改
    private String teacherSchoolState;
    /***********前台需要显示*****************/
    //教研室名称
    private String trsectionName;
    //院系名称
    private String departmentName;
    //担任的角色
    private List<Role> roles;
    
    


	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getTrsectionName() {
		return trsectionName;
	}

	public void setTrsectionName(String trsectionName) {
		this.trsectionName = trsectionName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getTrsectionId() {
        return trsectionId;
    }

    public void setTrsectionId(Integer trsectionId) {
        this.trsectionId = trsectionId;
    }

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum == null ? null : employeeNum.trim();
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getJuniorCollegeQuota() {
        return juniorCollegeQuota;
    }

    public void setJuniorCollegeQuota(Integer juniorCollegeQuota) {
        this.juniorCollegeQuota = juniorCollegeQuota;
    }

    public Integer getUndergraduateQuota() {
        return undergraduateQuota;
    }

    public void setUndergraduateQuota(Integer undergraduateQuota) {
        this.undergraduateQuota = undergraduateQuota;
    }

    public String getProfessionalRank() {
        return professionalRank;
    }

    public void setProfessionalRank(String professionalRank) {
        this.professionalRank = professionalRank == null ? null : professionalRank.trim();
    }

    public String getTeacherSchoolState() {
        return teacherSchoolState;
    }

    public void setTeacherSchoolState(String teacherSchoolState) {
        this.teacherSchoolState = teacherSchoolState == null ? null : teacherSchoolState.trim();
    }
}