package cn.bysj.core.pojo.vo;

import java.io.Serializable;

import cn.bysj.core.pojo.Student;

/**
 * 
 * ClassName: StudentCustom
 * 
 * @Description: TODO
 * @author it小祥
 * @date 2017年1月16日
 */
public class StudentCustom extends Student implements Serializable {

	private static final long serialVersionUID = 1L;

	/* 导出excel 将integer 转换成 String */
	private String studentStateString;
	// 院系
	private Student student;

	private Integer departmentId;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getStudentStateString() {
		return studentStateString;
	}

	public void setStudentStateString(String studentStateString) {
		this.studentStateString = studentStateString;
	}

}
