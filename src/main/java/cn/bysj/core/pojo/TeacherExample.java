package cn.bysj.core.pojo;

import java.util.ArrayList;
import java.util.List;

import cn.bysj.core.pojo.query.BaseQuery;

public class TeacherExample extends BaseQuery{
	private static final long serialVersionUID = 1L;

	protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TeacherExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andTeacherIdIsNull() {
            addCriterion("teacher_ID is null");
            return (Criteria) this;
        }

        public Criteria andTeacherIdIsNotNull() {
            addCriterion("teacher_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherIdEqualTo(Integer value) {
            addCriterion("teacher_ID =", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdNotEqualTo(Integer value) {
            addCriterion("teacher_ID <>", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdGreaterThan(Integer value) {
            addCriterion("teacher_ID >", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("teacher_ID >=", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdLessThan(Integer value) {
            addCriterion("teacher_ID <", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdLessThanOrEqualTo(Integer value) {
            addCriterion("teacher_ID <=", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdIn(List<Integer> values) {
            addCriterion("teacher_ID in", values, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdNotIn(List<Integer> values) {
            addCriterion("teacher_ID not in", values, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdBetween(Integer value1, Integer value2) {
            addCriterion("teacher_ID between", value1, value2, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdNotBetween(Integer value1, Integer value2) {
            addCriterion("teacher_ID not between", value1, value2, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTrsectionIdIsNull() {
            addCriterion("TRSection_ID is null");
            return (Criteria) this;
        }

        public Criteria andTrsectionIdIsNotNull() {
            addCriterion("TRSection_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTrsectionIdEqualTo(Integer value) {
            addCriterion("TRSection_ID =", value, "trsectionId");
            return (Criteria) this;
        }

        public Criteria andTrsectionIdNotEqualTo(Integer value) {
            addCriterion("TRSection_ID <>", value, "trsectionId");
            return (Criteria) this;
        }

        public Criteria andTrsectionIdGreaterThan(Integer value) {
            addCriterion("TRSection_ID >", value, "trsectionId");
            return (Criteria) this;
        }

        public Criteria andTrsectionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("TRSection_ID >=", value, "trsectionId");
            return (Criteria) this;
        }

        public Criteria andTrsectionIdLessThan(Integer value) {
            addCriterion("TRSection_ID <", value, "trsectionId");
            return (Criteria) this;
        }

        public Criteria andTrsectionIdLessThanOrEqualTo(Integer value) {
            addCriterion("TRSection_ID <=", value, "trsectionId");
            return (Criteria) this;
        }

        public Criteria andTrsectionIdIn(List<Integer> values) {
            addCriterion("TRSection_ID in", values, "trsectionId");
            return (Criteria) this;
        }

        public Criteria andTrsectionIdNotIn(List<Integer> values) {
            addCriterion("TRSection_ID not in", values, "trsectionId");
            return (Criteria) this;
        }

        public Criteria andTrsectionIdBetween(Integer value1, Integer value2) {
            addCriterion("TRSection_ID between", value1, value2, "trsectionId");
            return (Criteria) this;
        }

        public Criteria andTrsectionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("TRSection_ID not between", value1, value2, "trsectionId");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumIsNull() {
            addCriterion("employee_Num is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumIsNotNull() {
            addCriterion("employee_Num is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumEqualTo(String value) {
            addCriterion("employee_Num =", value, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumNotEqualTo(String value) {
            addCriterion("employee_Num <>", value, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumGreaterThan(String value) {
            addCriterion("employee_Num >", value, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumGreaterThanOrEqualTo(String value) {
            addCriterion("employee_Num >=", value, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumLessThan(String value) {
            addCriterion("employee_Num <", value, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumLessThanOrEqualTo(String value) {
            addCriterion("employee_Num <=", value, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumLike(String value) {
            addCriterion("employee_Num like", value, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumNotLike(String value) {
            addCriterion("employee_Num not like", value, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumIn(List<String> values) {
            addCriterion("employee_Num in", values, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumNotIn(List<String> values) {
            addCriterion("employee_Num not in", values, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumBetween(String value1, String value2) {
            addCriterion("employee_Num between", value1, value2, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumNotBetween(String value1, String value2) {
            addCriterion("employee_Num not between", value1, value2, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andTeacherNameIsNull() {
            addCriterion("teacher_Name is null");
            return (Criteria) this;
        }

        public Criteria andTeacherNameIsNotNull() {
            addCriterion("teacher_Name is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherNameEqualTo(String value) {
            addCriterion("teacher_Name =", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameNotEqualTo(String value) {
            addCriterion("teacher_Name <>", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameGreaterThan(String value) {
            addCriterion("teacher_Name >", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameGreaterThanOrEqualTo(String value) {
            addCriterion("teacher_Name >=", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameLessThan(String value) {
            addCriterion("teacher_Name <", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameLessThanOrEqualTo(String value) {
            addCriterion("teacher_Name <=", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameLike(String value) {
            addCriterion("teacher_Name like", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameNotLike(String value) {
            addCriterion("teacher_Name not like", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameIn(List<String> values) {
            addCriterion("teacher_Name in", values, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameNotIn(List<String> values) {
            addCriterion("teacher_Name not in", values, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameBetween(String value1, String value2) {
            addCriterion("teacher_Name between", value1, value2, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameNotBetween(String value1, String value2) {
            addCriterion("teacher_Name not between", value1, value2, "teacherName");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andQqIsNull() {
            addCriterion("qq is null");
            return (Criteria) this;
        }

        public Criteria andQqIsNotNull() {
            addCriterion("qq is not null");
            return (Criteria) this;
        }

        public Criteria andQqEqualTo(String value) {
            addCriterion("qq =", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotEqualTo(String value) {
            addCriterion("qq <>", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqGreaterThan(String value) {
            addCriterion("qq >", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqGreaterThanOrEqualTo(String value) {
            addCriterion("qq >=", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLessThan(String value) {
            addCriterion("qq <", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLessThanOrEqualTo(String value) {
            addCriterion("qq <=", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLike(String value) {
            addCriterion("qq like", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotLike(String value) {
            addCriterion("qq not like", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqIn(List<String> values) {
            addCriterion("qq in", values, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotIn(List<String> values) {
            addCriterion("qq not in", values, "qq");
            return (Criteria) this;
        }

        public Criteria andQqBetween(String value1, String value2) {
            addCriterion("qq between", value1, value2, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotBetween(String value1, String value2) {
            addCriterion("qq not between", value1, value2, "qq");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andJuniorCollegeQuotaIsNull() {
            addCriterion("junior_College_Quota is null");
            return (Criteria) this;
        }

        public Criteria andJuniorCollegeQuotaIsNotNull() {
            addCriterion("junior_College_Quota is not null");
            return (Criteria) this;
        }

        public Criteria andJuniorCollegeQuotaEqualTo(Integer value) {
            addCriterion("junior_College_Quota =", value, "juniorCollegeQuota");
            return (Criteria) this;
        }

        public Criteria andJuniorCollegeQuotaNotEqualTo(Integer value) {
            addCriterion("junior_College_Quota <>", value, "juniorCollegeQuota");
            return (Criteria) this;
        }

        public Criteria andJuniorCollegeQuotaGreaterThan(Integer value) {
            addCriterion("junior_College_Quota >", value, "juniorCollegeQuota");
            return (Criteria) this;
        }

        public Criteria andJuniorCollegeQuotaGreaterThanOrEqualTo(Integer value) {
            addCriterion("junior_College_Quota >=", value, "juniorCollegeQuota");
            return (Criteria) this;
        }

        public Criteria andJuniorCollegeQuotaLessThan(Integer value) {
            addCriterion("junior_College_Quota <", value, "juniorCollegeQuota");
            return (Criteria) this;
        }

        public Criteria andJuniorCollegeQuotaLessThanOrEqualTo(Integer value) {
            addCriterion("junior_College_Quota <=", value, "juniorCollegeQuota");
            return (Criteria) this;
        }

        public Criteria andJuniorCollegeQuotaIn(List<Integer> values) {
            addCriterion("junior_College_Quota in", values, "juniorCollegeQuota");
            return (Criteria) this;
        }

        public Criteria andJuniorCollegeQuotaNotIn(List<Integer> values) {
            addCriterion("junior_College_Quota not in", values, "juniorCollegeQuota");
            return (Criteria) this;
        }

        public Criteria andJuniorCollegeQuotaBetween(Integer value1, Integer value2) {
            addCriterion("junior_College_Quota between", value1, value2, "juniorCollegeQuota");
            return (Criteria) this;
        }

        public Criteria andJuniorCollegeQuotaNotBetween(Integer value1, Integer value2) {
            addCriterion("junior_College_Quota not between", value1, value2, "juniorCollegeQuota");
            return (Criteria) this;
        }

        public Criteria andUndergraduateQuotaIsNull() {
            addCriterion("undergraduate_Quota is null");
            return (Criteria) this;
        }

        public Criteria andUndergraduateQuotaIsNotNull() {
            addCriterion("undergraduate_Quota is not null");
            return (Criteria) this;
        }

        public Criteria andUndergraduateQuotaEqualTo(Integer value) {
            addCriterion("undergraduate_Quota =", value, "undergraduateQuota");
            return (Criteria) this;
        }

        public Criteria andUndergraduateQuotaNotEqualTo(Integer value) {
            addCriterion("undergraduate_Quota <>", value, "undergraduateQuota");
            return (Criteria) this;
        }

        public Criteria andUndergraduateQuotaGreaterThan(Integer value) {
            addCriterion("undergraduate_Quota >", value, "undergraduateQuota");
            return (Criteria) this;
        }

        public Criteria andUndergraduateQuotaGreaterThanOrEqualTo(Integer value) {
            addCriterion("undergraduate_Quota >=", value, "undergraduateQuota");
            return (Criteria) this;
        }

        public Criteria andUndergraduateQuotaLessThan(Integer value) {
            addCriterion("undergraduate_Quota <", value, "undergraduateQuota");
            return (Criteria) this;
        }

        public Criteria andUndergraduateQuotaLessThanOrEqualTo(Integer value) {
            addCriterion("undergraduate_Quota <=", value, "undergraduateQuota");
            return (Criteria) this;
        }

        public Criteria andUndergraduateQuotaIn(List<Integer> values) {
            addCriterion("undergraduate_Quota in", values, "undergraduateQuota");
            return (Criteria) this;
        }

        public Criteria andUndergraduateQuotaNotIn(List<Integer> values) {
            addCriterion("undergraduate_Quota not in", values, "undergraduateQuota");
            return (Criteria) this;
        }

        public Criteria andUndergraduateQuotaBetween(Integer value1, Integer value2) {
            addCriterion("undergraduate_Quota between", value1, value2, "undergraduateQuota");
            return (Criteria) this;
        }

        public Criteria andUndergraduateQuotaNotBetween(Integer value1, Integer value2) {
            addCriterion("undergraduate_Quota not between", value1, value2, "undergraduateQuota");
            return (Criteria) this;
        }

        public Criteria andProfessionalRankIsNull() {
            addCriterion("professional_Rank is null");
            return (Criteria) this;
        }

        public Criteria andProfessionalRankIsNotNull() {
            addCriterion("professional_Rank is not null");
            return (Criteria) this;
        }

        public Criteria andProfessionalRankEqualTo(String value) {
            addCriterion("professional_Rank =", value, "professionalRank");
            return (Criteria) this;
        }

        public Criteria andProfessionalRankNotEqualTo(String value) {
            addCriterion("professional_Rank <>", value, "professionalRank");
            return (Criteria) this;
        }

        public Criteria andProfessionalRankGreaterThan(String value) {
            addCriterion("professional_Rank >", value, "professionalRank");
            return (Criteria) this;
        }

        public Criteria andProfessionalRankGreaterThanOrEqualTo(String value) {
            addCriterion("professional_Rank >=", value, "professionalRank");
            return (Criteria) this;
        }

        public Criteria andProfessionalRankLessThan(String value) {
            addCriterion("professional_Rank <", value, "professionalRank");
            return (Criteria) this;
        }

        public Criteria andProfessionalRankLessThanOrEqualTo(String value) {
            addCriterion("professional_Rank <=", value, "professionalRank");
            return (Criteria) this;
        }

        public Criteria andProfessionalRankLike(String value) {
            addCriterion("professional_Rank like", value, "professionalRank");
            return (Criteria) this;
        }

        public Criteria andProfessionalRankNotLike(String value) {
            addCriterion("professional_Rank not like", value, "professionalRank");
            return (Criteria) this;
        }

        public Criteria andProfessionalRankIn(List<String> values) {
            addCriterion("professional_Rank in", values, "professionalRank");
            return (Criteria) this;
        }

        public Criteria andProfessionalRankNotIn(List<String> values) {
            addCriterion("professional_Rank not in", values, "professionalRank");
            return (Criteria) this;
        }

        public Criteria andProfessionalRankBetween(String value1, String value2) {
            addCriterion("professional_Rank between", value1, value2, "professionalRank");
            return (Criteria) this;
        }

        public Criteria andProfessionalRankNotBetween(String value1, String value2) {
            addCriterion("professional_Rank not between", value1, value2, "professionalRank");
            return (Criteria) this;
        }

        public Criteria andTeacherSchoolStateIsNull() {
            addCriterion("teacher_school_state is null");
            return (Criteria) this;
        }

        public Criteria andTeacherSchoolStateIsNotNull() {
            addCriterion("teacher_school_state is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherSchoolStateEqualTo(String value) {
            addCriterion("teacher_school_state =", value, "teacherSchoolState");
            return (Criteria) this;
        }

        public Criteria andTeacherSchoolStateNotEqualTo(String value) {
            addCriterion("teacher_school_state <>", value, "teacherSchoolState");
            return (Criteria) this;
        }

        public Criteria andTeacherSchoolStateGreaterThan(String value) {
            addCriterion("teacher_school_state >", value, "teacherSchoolState");
            return (Criteria) this;
        }

        public Criteria andTeacherSchoolStateGreaterThanOrEqualTo(String value) {
            addCriterion("teacher_school_state >=", value, "teacherSchoolState");
            return (Criteria) this;
        }

        public Criteria andTeacherSchoolStateLessThan(String value) {
            addCriterion("teacher_school_state <", value, "teacherSchoolState");
            return (Criteria) this;
        }

        public Criteria andTeacherSchoolStateLessThanOrEqualTo(String value) {
            addCriterion("teacher_school_state <=", value, "teacherSchoolState");
            return (Criteria) this;
        }

        public Criteria andTeacherSchoolStateLike(String value) {
            addCriterion("teacher_school_state like", value, "teacherSchoolState");
            return (Criteria) this;
        }

        public Criteria andTeacherSchoolStateNotLike(String value) {
            addCriterion("teacher_school_state not like", value, "teacherSchoolState");
            return (Criteria) this;
        }

        public Criteria andTeacherSchoolStateIn(List<String> values) {
            addCriterion("teacher_school_state in", values, "teacherSchoolState");
            return (Criteria) this;
        }

        public Criteria andTeacherSchoolStateNotIn(List<String> values) {
            addCriterion("teacher_school_state not in", values, "teacherSchoolState");
            return (Criteria) this;
        }

        public Criteria andTeacherSchoolStateBetween(String value1, String value2) {
            addCriterion("teacher_school_state between", value1, value2, "teacherSchoolState");
            return (Criteria) this;
        }

        public Criteria andTeacherSchoolStateNotBetween(String value1, String value2) {
            addCriterion("teacher_school_state not between", value1, value2, "teacherSchoolState");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}