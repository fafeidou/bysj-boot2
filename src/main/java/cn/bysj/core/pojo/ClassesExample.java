package cn.bysj.core.pojo;

import java.util.ArrayList;
import java.util.List;

public class ClassesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ClassesExample() {
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

        public Criteria andDepartmentIdIsNull() {
            addCriterion("department_ID is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIsNotNull() {
            addCriterion("department_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdEqualTo(Integer value) {
            addCriterion("department_ID =", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotEqualTo(Integer value) {
            addCriterion("department_ID <>", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdGreaterThan(Integer value) {
            addCriterion("department_ID >", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("department_ID >=", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdLessThan(Integer value) {
            addCriterion("department_ID <", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdLessThanOrEqualTo(Integer value) {
            addCriterion("department_ID <=", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIn(List<Integer> values) {
            addCriterion("department_ID in", values, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotIn(List<Integer> values) {
            addCriterion("department_ID not in", values, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdBetween(Integer value1, Integer value2) {
            addCriterion("department_ID between", value1, value2, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("department_ID not between", value1, value2, "departmentId");
            return (Criteria) this;
        }

        public Criteria andClassNameIsNull() {
            addCriterion("class_Name is null");
            return (Criteria) this;
        }

        public Criteria andClassNameIsNotNull() {
            addCriterion("class_Name is not null");
            return (Criteria) this;
        }

        public Criteria andClassNameEqualTo(String value) {
            addCriterion("class_Name =", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotEqualTo(String value) {
            addCriterion("class_Name <>", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThan(String value) {
            addCriterion("class_Name >", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThanOrEqualTo(String value) {
            addCriterion("class_Name >=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThan(String value) {
            addCriterion("class_Name <", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThanOrEqualTo(String value) {
            addCriterion("class_Name <=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLike(String value) {
            addCriterion("class_Name like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotLike(String value) {
            addCriterion("class_Name not like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameIn(List<String> values) {
            addCriterion("class_Name in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotIn(List<String> values) {
            addCriterion("class_Name not in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameBetween(String value1, String value2) {
            addCriterion("class_Name between", value1, value2, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotBetween(String value1, String value2) {
            addCriterion("class_Name not between", value1, value2, "className");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeIsNull() {
            addCriterion("academic_Type is null");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeIsNotNull() {
            addCriterion("academic_Type is not null");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeEqualTo(String value) {
            addCriterion("academic_Type =", value, "academicType");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeNotEqualTo(String value) {
            addCriterion("academic_Type <>", value, "academicType");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeGreaterThan(String value) {
            addCriterion("academic_Type >", value, "academicType");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeGreaterThanOrEqualTo(String value) {
            addCriterion("academic_Type >=", value, "academicType");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeLessThan(String value) {
            addCriterion("academic_Type <", value, "academicType");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeLessThanOrEqualTo(String value) {
            addCriterion("academic_Type <=", value, "academicType");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeLike(String value) {
            addCriterion("academic_Type like", value, "academicType");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeNotLike(String value) {
            addCriterion("academic_Type not like", value, "academicType");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeIn(List<String> values) {
            addCriterion("academic_Type in", values, "academicType");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeNotIn(List<String> values) {
            addCriterion("academic_Type not in", values, "academicType");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeBetween(String value1, String value2) {
            addCriterion("academic_Type between", value1, value2, "academicType");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeNotBetween(String value1, String value2) {
            addCriterion("academic_Type not between", value1, value2, "academicType");
            return (Criteria) this;
        }

        public Criteria andGraduationYearIsNull() {
            addCriterion("Graduation_Year is null");
            return (Criteria) this;
        }

        public Criteria andGraduationYearIsNotNull() {
            addCriterion("Graduation_Year is not null");
            return (Criteria) this;
        }

        public Criteria andGraduationYearEqualTo(String value) {
            addCriterion("Graduation_Year =", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearNotEqualTo(String value) {
            addCriterion("Graduation_Year <>", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearGreaterThan(String value) {
            addCriterion("Graduation_Year >", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearGreaterThanOrEqualTo(String value) {
            addCriterion("Graduation_Year >=", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearLessThan(String value) {
            addCriterion("Graduation_Year <", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearLessThanOrEqualTo(String value) {
            addCriterion("Graduation_Year <=", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearLike(String value) {
            addCriterion("Graduation_Year like", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearNotLike(String value) {
            addCriterion("Graduation_Year not like", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearIn(List<String> values) {
            addCriterion("Graduation_Year in", values, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearNotIn(List<String> values) {
            addCriterion("Graduation_Year not in", values, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearBetween(String value1, String value2) {
            addCriterion("Graduation_Year between", value1, value2, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearNotBetween(String value1, String value2) {
            addCriterion("Graduation_Year not between", value1, value2, "graduationYear");
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