package cn.bysj.core.pojo.vo;

import java.io.Serializable;

import cn.bysj.core.pojo.Classes;
import cn.bysj.core.pojo.Departments;
import cn.bysj.core.pojo.Student;
import cn.bysj.core.pojo.Teacher;
import cn.bysj.core.pojo.Thesistopic;
import cn.bysj.core.pojo.Topicsourcetype;
import cn.bysj.core.pojo.Topictype;

public class StudentThesistopicCustom implements Serializable {
	private static final long serialVersionUID = 1L;
	private Student student;
	private Classes classes;
	private Departments departments;
	private Thesistopic thesistopic;
	private Teacher teacher;
	private Topictype topictype;
	private Topicsourcetype topicsourcetype;

	public Topictype getTopictype() {
		return topictype;
	}

	public void setTopictype(Topictype topictype) {
		this.topictype = topictype;
	}

	public Topicsourcetype getTopicsourcetype() {
		return topicsourcetype;
	}

	public void setTopicsourcetype(Topicsourcetype topicsourcetype) {
		this.topicsourcetype = topicsourcetype;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Thesistopic getThesistopic() {
		return thesistopic;
	}

	public void setThesistopic(Thesistopic thesistopic) {
		this.thesistopic = thesistopic;
	}

	public Departments getDepartments() {
		return departments;
	}

	public void setDepartments(Departments departments) {
		this.departments = departments;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Classes getClasses() {
		return classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

}
