package cn.bysj.core.pojo;

import java.util.ArrayList;
import java.util.List;

public class StudentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StudentExample() {
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

        public Criteria andStudentIdIsNull() {
            addCriterion("student_ID is null");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNotNull() {
            addCriterion("student_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStudentIdEqualTo(Integer value) {
            addCriterion("student_ID =", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotEqualTo(Integer value) {
            addCriterion("student_ID <>", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThan(Integer value) {
            addCriterion("student_ID >", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("student_ID >=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThan(Integer value) {
            addCriterion("student_ID <", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThanOrEqualTo(Integer value) {
            addCriterion("student_ID <=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdIn(List<Integer> values) {
            addCriterion("student_ID in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotIn(List<Integer> values) {
            addCriterion("student_ID not in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdBetween(Integer value1, Integer value2) {
            addCriterion("student_ID between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("student_ID not between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andThesisTopicIdIsNull() {
            addCriterion("thesis_Topic_ID is null");
            return (Criteria) this;
        }

        public Criteria andThesisTopicIdIsNotNull() {
            addCriterion("thesis_Topic_ID is not null");
            return (Criteria) this;
        }

        public Criteria andThesisTopicIdEqualTo(Integer value) {
            addCriterion("thesis_Topic_ID =", value, "thesisTopicId");
            return (Criteria) this;
        }

        public Criteria andThesisTopicIdNotEqualTo(Integer value) {
            addCriterion("thesis_Topic_ID <>", value, "thesisTopicId");
            return (Criteria) this;
        }

        public Criteria andThesisTopicIdGreaterThan(Integer value) {
            addCriterion("thesis_Topic_ID >", value, "thesisTopicId");
            return (Criteria) this;
        }

        public Criteria andThesisTopicIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("thesis_Topic_ID >=", value, "thesisTopicId");
            return (Criteria) this;
        }

        public Criteria andThesisTopicIdLessThan(Integer value) {
            addCriterion("thesis_Topic_ID <", value, "thesisTopicId");
            return (Criteria) this;
        }

        public Criteria andThesisTopicIdLessThanOrEqualTo(Integer value) {
            addCriterion("thesis_Topic_ID <=", value, "thesisTopicId");
            return (Criteria) this;
        }

        public Criteria andThesisTopicIdIn(List<Integer> values) {
            addCriterion("thesis_Topic_ID in", values, "thesisTopicId");
            return (Criteria) this;
        }

        public Criteria andThesisTopicIdNotIn(List<Integer> values) {
            addCriterion("thesis_Topic_ID not in", values, "thesisTopicId");
            return (Criteria) this;
        }

        public Criteria andThesisTopicIdBetween(Integer value1, Integer value2) {
            addCriterion("thesis_Topic_ID between", value1, value2, "thesisTopicId");
            return (Criteria) this;
        }

        public Criteria andThesisTopicIdNotBetween(Integer value1, Integer value2) {
            addCriterion("thesis_Topic_ID not between", value1, value2, "thesisTopicId");
            return (Criteria) this;
        }

        public Criteria andClassisIdIsNull() {
            addCriterion("classis_ID is null");
            return (Criteria) this;
        }

        public Criteria andClassisIdIsNotNull() {
            addCriterion("classis_ID is not null");
            return (Criteria) this;
        }

        public Criteria andClassisIdEqualTo(Integer value) {
            addCriterion("classis_ID =", value, "classisId");
            return (Criteria) this;
        }

        public Criteria andClassisIdNotEqualTo(Integer value) {
            addCriterion("classis_ID <>", value, "classisId");
            return (Criteria) this;
        }

        public Criteria andClassisIdGreaterThan(Integer value) {
            addCriterion("classis_ID >", value, "classisId");
            return (Criteria) this;
        }

        public Criteria andClassisIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("classis_ID >=", value, "classisId");
            return (Criteria) this;
        }

        public Criteria andClassisIdLessThan(Integer value) {
            addCriterion("classis_ID <", value, "classisId");
            return (Criteria) this;
        }

        public Criteria andClassisIdLessThanOrEqualTo(Integer value) {
            addCriterion("classis_ID <=", value, "classisId");
            return (Criteria) this;
        }

        public Criteria andClassisIdIn(List<Integer> values) {
            addCriterion("classis_ID in", values, "classisId");
            return (Criteria) this;
        }

        public Criteria andClassisIdNotIn(List<Integer> values) {
            addCriterion("classis_ID not in", values, "classisId");
            return (Criteria) this;
        }

        public Criteria andClassisIdBetween(Integer value1, Integer value2) {
            addCriterion("classis_ID between", value1, value2, "classisId");
            return (Criteria) this;
        }

        public Criteria andClassisIdNotBetween(Integer value1, Integer value2) {
            addCriterion("classis_ID not between", value1, value2, "classisId");
            return (Criteria) this;
        }

        public Criteria andStudentNameIsNull() {
            addCriterion("student_Name is null");
            return (Criteria) this;
        }

        public Criteria andStudentNameIsNotNull() {
            addCriterion("student_Name is not null");
            return (Criteria) this;
        }

        public Criteria andStudentNameEqualTo(String value) {
            addCriterion("student_Name =", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameNotEqualTo(String value) {
            addCriterion("student_Name <>", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameGreaterThan(String value) {
            addCriterion("student_Name >", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameGreaterThanOrEqualTo(String value) {
            addCriterion("student_Name >=", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameLessThan(String value) {
            addCriterion("student_Name <", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameLessThanOrEqualTo(String value) {
            addCriterion("student_Name <=", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameLike(String value) {
            addCriterion("student_Name like", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameNotLike(String value) {
            addCriterion("student_Name not like", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameIn(List<String> values) {
            addCriterion("student_Name in", values, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameNotIn(List<String> values) {
            addCriterion("student_Name not in", values, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameBetween(String value1, String value2) {
            addCriterion("student_Name between", value1, value2, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameNotBetween(String value1, String value2) {
            addCriterion("student_Name not between", value1, value2, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNoIsNull() {
            addCriterion("student_No is null");
            return (Criteria) this;
        }

        public Criteria andStudentNoIsNotNull() {
            addCriterion("student_No is not null");
            return (Criteria) this;
        }

        public Criteria andStudentNoEqualTo(String value) {
            addCriterion("student_No =", value, "studentNo");
            return (Criteria) this;
        }

        public Criteria andStudentNoNotEqualTo(String value) {
            addCriterion("student_No <>", value, "studentNo");
            return (Criteria) this;
        }

        public Criteria andStudentNoGreaterThan(String value) {
            addCriterion("student_No >", value, "studentNo");
            return (Criteria) this;
        }

        public Criteria andStudentNoGreaterThanOrEqualTo(String value) {
            addCriterion("student_No >=", value, "studentNo");
            return (Criteria) this;
        }

        public Criteria andStudentNoLessThan(String value) {
            addCriterion("student_No <", value, "studentNo");
            return (Criteria) this;
        }

        public Criteria andStudentNoLessThanOrEqualTo(String value) {
            addCriterion("student_No <=", value, "studentNo");
            return (Criteria) this;
        }

        public Criteria andStudentNoLike(String value) {
            addCriterion("student_No like", value, "studentNo");
            return (Criteria) this;
        }

        public Criteria andStudentNoNotLike(String value) {
            addCriterion("student_No not like", value, "studentNo");
            return (Criteria) this;
        }

        public Criteria andStudentNoIn(List<String> values) {
            addCriterion("student_No in", values, "studentNo");
            return (Criteria) this;
        }

        public Criteria andStudentNoNotIn(List<String> values) {
            addCriterion("student_No not in", values, "studentNo");
            return (Criteria) this;
        }

        public Criteria andStudentNoBetween(String value1, String value2) {
            addCriterion("student_No between", value1, value2, "studentNo");
            return (Criteria) this;
        }

        public Criteria andStudentNoNotBetween(String value1, String value2) {
            addCriterion("student_No not between", value1, value2, "studentNo");
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

        public Criteria andStudentStateIsNull() {
            addCriterion("student_State is null");
            return (Criteria) this;
        }

        public Criteria andStudentStateIsNotNull() {
            addCriterion("student_State is not null");
            return (Criteria) this;
        }

        public Criteria andStudentStateEqualTo(Integer value) {
            addCriterion("student_State =", value, "studentState");
            return (Criteria) this;
        }

        public Criteria andStudentStateNotEqualTo(Integer value) {
            addCriterion("student_State <>", value, "studentState");
            return (Criteria) this;
        }

        public Criteria andStudentStateGreaterThan(Integer value) {
            addCriterion("student_State >", value, "studentState");
            return (Criteria) this;
        }

        public Criteria andStudentStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("student_State >=", value, "studentState");
            return (Criteria) this;
        }

        public Criteria andStudentStateLessThan(Integer value) {
            addCriterion("student_State <", value, "studentState");
            return (Criteria) this;
        }

        public Criteria andStudentStateLessThanOrEqualTo(Integer value) {
            addCriterion("student_State <=", value, "studentState");
            return (Criteria) this;
        }

        public Criteria andStudentStateIn(List<Integer> values) {
            addCriterion("student_State in", values, "studentState");
            return (Criteria) this;
        }

        public Criteria andStudentStateNotIn(List<Integer> values) {
            addCriterion("student_State not in", values, "studentState");
            return (Criteria) this;
        }

        public Criteria andStudentStateBetween(Integer value1, Integer value2) {
            addCriterion("student_State between", value1, value2, "studentState");
            return (Criteria) this;
        }

        public Criteria andStudentStateNotBetween(Integer value1, Integer value2) {
            addCriterion("student_State not between", value1, value2, "studentState");
            return (Criteria) this;
        }

        public Criteria andStudentSchoolStateIsNull() {
            addCriterion("student_school_state is null");
            return (Criteria) this;
        }

        public Criteria andStudentSchoolStateIsNotNull() {
            addCriterion("student_school_state is not null");
            return (Criteria) this;
        }

        public Criteria andStudentSchoolStateEqualTo(String value) {
            addCriterion("student_school_state =", value, "studentSchoolState");
            return (Criteria) this;
        }

        public Criteria andStudentSchoolStateNotEqualTo(String value) {
            addCriterion("student_school_state <>", value, "studentSchoolState");
            return (Criteria) this;
        }

        public Criteria andStudentSchoolStateGreaterThan(String value) {
            addCriterion("student_school_state >", value, "studentSchoolState");
            return (Criteria) this;
        }

        public Criteria andStudentSchoolStateGreaterThanOrEqualTo(String value) {
            addCriterion("student_school_state >=", value, "studentSchoolState");
            return (Criteria) this;
        }

        public Criteria andStudentSchoolStateLessThan(String value) {
            addCriterion("student_school_state <", value, "studentSchoolState");
            return (Criteria) this;
        }

        public Criteria andStudentSchoolStateLessThanOrEqualTo(String value) {
            addCriterion("student_school_state <=", value, "studentSchoolState");
            return (Criteria) this;
        }

        public Criteria andStudentSchoolStateLike(String value) {
            addCriterion("student_school_state like", value, "studentSchoolState");
            return (Criteria) this;
        }

        public Criteria andStudentSchoolStateNotLike(String value) {
            addCriterion("student_school_state not like", value, "studentSchoolState");
            return (Criteria) this;
        }

        public Criteria andStudentSchoolStateIn(List<String> values) {
            addCriterion("student_school_state in", values, "studentSchoolState");
            return (Criteria) this;
        }

        public Criteria andStudentSchoolStateNotIn(List<String> values) {
            addCriterion("student_school_state not in", values, "studentSchoolState");
            return (Criteria) this;
        }

        public Criteria andStudentSchoolStateBetween(String value1, String value2) {
            addCriterion("student_school_state between", value1, value2, "studentSchoolState");
            return (Criteria) this;
        }

        public Criteria andStudentSchoolStateNotBetween(String value1, String value2) {
            addCriterion("student_school_state not between", value1, value2, "studentSchoolState");
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