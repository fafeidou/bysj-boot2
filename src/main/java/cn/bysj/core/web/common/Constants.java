package cn.bysj.core.web.common;

public interface Constants {
	/*
	 * 教师Session
	 */
	String TEACHER_SESSION = "teacher_session";
	/**
	 * 学生session
	 */
	String STUDENT_SESSION = "student_session";
	/**
	 * 管理员session
	 */
	/*
	 * 微信用户名
	 */
	String FROM_USER_NAME = "from_user_name";
	String MANAGER_SESSION = "manager_session";
	/* 普通教师0，教研室主任1， 教研秘书* */
	Integer OrdinaryTeacher = 0;
	Integer TercherAndResearchDirecor = 1;
	Integer TercherAndResearchSecretary = 2;

	/* 教师默认在职状态 */
	String TeacherSchoolStateDefault = "在职";

	/* 学生选课状态 */

	Integer StudentInformationNotPerfect = 0;
	Integer StudentWaitForChoice = 1;
	Integer StudentWaitingForDistribution = 2;
	Integer StudentDistributionSuccess = 3;

	/* 学生在校默认状态 */
	String StudentAtSchool = "在校";

}
