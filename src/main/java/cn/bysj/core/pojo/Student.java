package cn.bysj.core.pojo;

import java.io.Serializable;

public class Student implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer studentId;

    private Integer thesisTopicId;

    private Integer classisId;

    private String studentName;

    private String studentNo;

    private String password;

    private String phone;

    private String qq;

    private String email;

    private Integer studentState;

    private String studentSchoolState;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getThesisTopicId() {
        return thesisTopicId;
    }

    public void setThesisTopicId(Integer thesisTopicId) {
        this.thesisTopicId = thesisTopicId;
    }

    public Integer getClassisId() {
        return classisId;
    }

    public void setClassisId(Integer classisId) {
        this.classisId = classisId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo == null ? null : studentNo.trim();
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

    public Integer getStudentState() {
        return studentState;
    }

    public void setStudentState(Integer studentState) {
        this.studentState = studentState;
    }

    public String getStudentSchoolState() {
        return studentSchoolState;
    }

    public void setStudentSchoolState(String studentSchoolState) {
        this.studentSchoolState = studentSchoolState == null ? null : studentSchoolState.trim();
    }
}