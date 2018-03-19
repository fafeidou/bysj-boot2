package cn.bysj.core.pojo.vo;

import com.google.common.base.MoreObjects;

import cn.bysj.core.web.excel.annotation.CellConfig;
import cn.bysj.core.web.excel.annotation.OutAlias;

@OutAlias("xiaoshou")
public class StudentExcel {
	@CellConfig(index = 0)
	private String studentNo;
	@CellConfig(index = 1)
	private String studentName;
	@CellConfig(index = 2)
	private String phone;
	@CellConfig(index = 3)
	private String qq;
	@CellConfig(index = 4)
	private String email;
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String toString() {
		return MoreObjects.toStringHelper(this.getClass()).omitNullValues().add("studentName", studentName)
				.add("studentNo", studentNo).add("phone", phone).add("qq", qq).add("email", email).toString();
	}
}
