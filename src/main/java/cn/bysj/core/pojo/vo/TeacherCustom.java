package cn.bysj.core.pojo.vo;

import java.io.Serializable;
import java.util.List;

import cn.bysj.core.pojo.Roleoperation;
import cn.bysj.core.pojo.Teacher;

/**
 * 教师扩展类 ClassName: TeacherCustom
 * 
 * @Description: TODO
 * @author it小祥
 * @date 2016年11月19日
 */
public class TeacherCustom extends Teacher implements Serializable{

	private static final long serialVersionUID = 1L;
	private Menu menu;// 操作菜单
	private List<Roleoperation> operationList;// 操作权限，包括用户点击菜单及操作菜单功能所有链接权限
	
	private Integer departmentId;
	private Teacher teacher;
	
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public List<Roleoperation> getOperationList() {
		return operationList;
	}

	public void setOperationList(List<Roleoperation> operationList) {
		this.operationList = operationList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
