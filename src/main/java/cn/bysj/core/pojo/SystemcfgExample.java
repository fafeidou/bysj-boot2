package cn.bysj.core.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SystemcfgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SystemcfgExample() {
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

        public Criteria andSystemCfgIdIsNull() {
            addCriterion("system_Cfg_ID is null");
            return (Criteria) this;
        }

        public Criteria andSystemCfgIdIsNotNull() {
            addCriterion("system_Cfg_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSystemCfgIdEqualTo(Integer value) {
            addCriterion("system_Cfg_ID =", value, "systemCfgId");
            return (Criteria) this;
        }

        public Criteria andSystemCfgIdNotEqualTo(Integer value) {
            addCriterion("system_Cfg_ID <>", value, "systemCfgId");
            return (Criteria) this;
        }

        public Criteria andSystemCfgIdGreaterThan(Integer value) {
            addCriterion("system_Cfg_ID >", value, "systemCfgId");
            return (Criteria) this;
        }

        public Criteria andSystemCfgIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("system_Cfg_ID >=", value, "systemCfgId");
            return (Criteria) this;
        }

        public Criteria andSystemCfgIdLessThan(Integer value) {
            addCriterion("system_Cfg_ID <", value, "systemCfgId");
            return (Criteria) this;
        }

        public Criteria andSystemCfgIdLessThanOrEqualTo(Integer value) {
            addCriterion("system_Cfg_ID <=", value, "systemCfgId");
            return (Criteria) this;
        }

        public Criteria andSystemCfgIdIn(List<Integer> values) {
            addCriterion("system_Cfg_ID in", values, "systemCfgId");
            return (Criteria) this;
        }

        public Criteria andSystemCfgIdNotIn(List<Integer> values) {
            addCriterion("system_Cfg_ID not in", values, "systemCfgId");
            return (Criteria) this;
        }

        public Criteria andSystemCfgIdBetween(Integer value1, Integer value2) {
            addCriterion("system_Cfg_ID between", value1, value2, "systemCfgId");
            return (Criteria) this;
        }

        public Criteria andSystemCfgIdNotBetween(Integer value1, Integer value2) {
            addCriterion("system_Cfg_ID not between", value1, value2, "systemCfgId");
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

        public Criteria andSystemStateIsNull() {
            addCriterion("system_State is null");
            return (Criteria) this;
        }

        public Criteria andSystemStateIsNotNull() {
            addCriterion("system_State is not null");
            return (Criteria) this;
        }

        public Criteria andSystemStateEqualTo(String value) {
            addCriterion("system_State =", value, "systemState");
            return (Criteria) this;
        }

        public Criteria andSystemStateNotEqualTo(String value) {
            addCriterion("system_State <>", value, "systemState");
            return (Criteria) this;
        }

        public Criteria andSystemStateGreaterThan(String value) {
            addCriterion("system_State >", value, "systemState");
            return (Criteria) this;
        }

        public Criteria andSystemStateGreaterThanOrEqualTo(String value) {
            addCriterion("system_State >=", value, "systemState");
            return (Criteria) this;
        }

        public Criteria andSystemStateLessThan(String value) {
            addCriterion("system_State <", value, "systemState");
            return (Criteria) this;
        }

        public Criteria andSystemStateLessThanOrEqualTo(String value) {
            addCriterion("system_State <=", value, "systemState");
            return (Criteria) this;
        }

        public Criteria andSystemStateLike(String value) {
            addCriterion("system_State like", value, "systemState");
            return (Criteria) this;
        }

        public Criteria andSystemStateNotLike(String value) {
            addCriterion("system_State not like", value, "systemState");
            return (Criteria) this;
        }

        public Criteria andSystemStateIn(List<String> values) {
            addCriterion("system_State in", values, "systemState");
            return (Criteria) this;
        }

        public Criteria andSystemStateNotIn(List<String> values) {
            addCriterion("system_State not in", values, "systemState");
            return (Criteria) this;
        }

        public Criteria andSystemStateBetween(String value1, String value2) {
            addCriterion("system_State between", value1, value2, "systemState");
            return (Criteria) this;
        }

        public Criteria andSystemStateNotBetween(String value1, String value2) {
            addCriterion("system_State not between", value1, value2, "systemState");
            return (Criteria) this;
        }

        public Criteria andThesisNumPerStudentCanSelectIsNull() {
            addCriterion("Thesis_Num_Per_Student_Can_Select is null");
            return (Criteria) this;
        }

        public Criteria andThesisNumPerStudentCanSelectIsNotNull() {
            addCriterion("Thesis_Num_Per_Student_Can_Select is not null");
            return (Criteria) this;
        }

        public Criteria andThesisNumPerStudentCanSelectEqualTo(Integer value) {
            addCriterion("Thesis_Num_Per_Student_Can_Select =", value, "thesisNumPerStudentCanSelect");
            return (Criteria) this;
        }

        public Criteria andThesisNumPerStudentCanSelectNotEqualTo(Integer value) {
            addCriterion("Thesis_Num_Per_Student_Can_Select <>", value, "thesisNumPerStudentCanSelect");
            return (Criteria) this;
        }

        public Criteria andThesisNumPerStudentCanSelectGreaterThan(Integer value) {
            addCriterion("Thesis_Num_Per_Student_Can_Select >", value, "thesisNumPerStudentCanSelect");
            return (Criteria) this;
        }

        public Criteria andThesisNumPerStudentCanSelectGreaterThanOrEqualTo(Integer value) {
            addCriterion("Thesis_Num_Per_Student_Can_Select >=", value, "thesisNumPerStudentCanSelect");
            return (Criteria) this;
        }

        public Criteria andThesisNumPerStudentCanSelectLessThan(Integer value) {
            addCriterion("Thesis_Num_Per_Student_Can_Select <", value, "thesisNumPerStudentCanSelect");
            return (Criteria) this;
        }

        public Criteria andThesisNumPerStudentCanSelectLessThanOrEqualTo(Integer value) {
            addCriterion("Thesis_Num_Per_Student_Can_Select <=", value, "thesisNumPerStudentCanSelect");
            return (Criteria) this;
        }

        public Criteria andThesisNumPerStudentCanSelectIn(List<Integer> values) {
            addCriterion("Thesis_Num_Per_Student_Can_Select in", values, "thesisNumPerStudentCanSelect");
            return (Criteria) this;
        }

        public Criteria andThesisNumPerStudentCanSelectNotIn(List<Integer> values) {
            addCriterion("Thesis_Num_Per_Student_Can_Select not in", values, "thesisNumPerStudentCanSelect");
            return (Criteria) this;
        }

        public Criteria andThesisNumPerStudentCanSelectBetween(Integer value1, Integer value2) {
            addCriterion("Thesis_Num_Per_Student_Can_Select between", value1, value2, "thesisNumPerStudentCanSelect");
            return (Criteria) this;
        }

        public Criteria andThesisNumPerStudentCanSelectNotBetween(Integer value1, Integer value2) {
            addCriterion("Thesis_Num_Per_Student_Can_Select not between", value1, value2, "thesisNumPerStudentCanSelect");
            return (Criteria) this;
        }

        public Criteria andMaxStudentNumPerThesisCanBeSelectedIsNull() {
            addCriterion("Max_Student_Num_Per_Thesis_Can_Be_Selected is null");
            return (Criteria) this;
        }

        public Criteria andMaxStudentNumPerThesisCanBeSelectedIsNotNull() {
            addCriterion("Max_Student_Num_Per_Thesis_Can_Be_Selected is not null");
            return (Criteria) this;
        }

        public Criteria andMaxStudentNumPerThesisCanBeSelectedEqualTo(Integer value) {
            addCriterion("Max_Student_Num_Per_Thesis_Can_Be_Selected =", value, "maxStudentNumPerThesisCanBeSelected");
            return (Criteria) this;
        }

        public Criteria andMaxStudentNumPerThesisCanBeSelectedNotEqualTo(Integer value) {
            addCriterion("Max_Student_Num_Per_Thesis_Can_Be_Selected <>", value, "maxStudentNumPerThesisCanBeSelected");
            return (Criteria) this;
        }

        public Criteria andMaxStudentNumPerThesisCanBeSelectedGreaterThan(Integer value) {
            addCriterion("Max_Student_Num_Per_Thesis_Can_Be_Selected >", value, "maxStudentNumPerThesisCanBeSelected");
            return (Criteria) this;
        }

        public Criteria andMaxStudentNumPerThesisCanBeSelectedGreaterThanOrEqualTo(Integer value) {
            addCriterion("Max_Student_Num_Per_Thesis_Can_Be_Selected >=", value, "maxStudentNumPerThesisCanBeSelected");
            return (Criteria) this;
        }

        public Criteria andMaxStudentNumPerThesisCanBeSelectedLessThan(Integer value) {
            addCriterion("Max_Student_Num_Per_Thesis_Can_Be_Selected <", value, "maxStudentNumPerThesisCanBeSelected");
            return (Criteria) this;
        }

        public Criteria andMaxStudentNumPerThesisCanBeSelectedLessThanOrEqualTo(Integer value) {
            addCriterion("Max_Student_Num_Per_Thesis_Can_Be_Selected <=", value, "maxStudentNumPerThesisCanBeSelected");
            return (Criteria) this;
        }

        public Criteria andMaxStudentNumPerThesisCanBeSelectedIn(List<Integer> values) {
            addCriterion("Max_Student_Num_Per_Thesis_Can_Be_Selected in", values, "maxStudentNumPerThesisCanBeSelected");
            return (Criteria) this;
        }

        public Criteria andMaxStudentNumPerThesisCanBeSelectedNotIn(List<Integer> values) {
            addCriterion("Max_Student_Num_Per_Thesis_Can_Be_Selected not in", values, "maxStudentNumPerThesisCanBeSelected");
            return (Criteria) this;
        }

        public Criteria andMaxStudentNumPerThesisCanBeSelectedBetween(Integer value1, Integer value2) {
            addCriterion("Max_Student_Num_Per_Thesis_Can_Be_Selected between", value1, value2, "maxStudentNumPerThesisCanBeSelected");
            return (Criteria) this;
        }

        public Criteria andMaxStudentNumPerThesisCanBeSelectedNotBetween(Integer value1, Integer value2) {
            addCriterion("Max_Student_Num_Per_Thesis_Can_Be_Selected not between", value1, value2, "maxStudentNumPerThesisCanBeSelected");
            return (Criteria) this;
        }

        public Criteria andFirstRoundStudentSelectBeginTimeIsNull() {
            addCriterion("first_Round_Student_Select_Begin_Time is null");
            return (Criteria) this;
        }

        public Criteria andFirstRoundStudentSelectBeginTimeIsNotNull() {
            addCriterion("first_Round_Student_Select_Begin_Time is not null");
            return (Criteria) this;
        }

        public Criteria andFirstRoundStudentSelectBeginTimeEqualTo(Date value) {
            addCriterion("first_Round_Student_Select_Begin_Time =", value, "firstRoundStudentSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundStudentSelectBeginTimeNotEqualTo(Date value) {
            addCriterion("first_Round_Student_Select_Begin_Time <>", value, "firstRoundStudentSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundStudentSelectBeginTimeGreaterThan(Date value) {
            addCriterion("first_Round_Student_Select_Begin_Time >", value, "firstRoundStudentSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundStudentSelectBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("first_Round_Student_Select_Begin_Time >=", value, "firstRoundStudentSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundStudentSelectBeginTimeLessThan(Date value) {
            addCriterion("first_Round_Student_Select_Begin_Time <", value, "firstRoundStudentSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundStudentSelectBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("first_Round_Student_Select_Begin_Time <=", value, "firstRoundStudentSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundStudentSelectBeginTimeIn(List<Date> values) {
            addCriterion("first_Round_Student_Select_Begin_Time in", values, "firstRoundStudentSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundStudentSelectBeginTimeNotIn(List<Date> values) {
            addCriterion("first_Round_Student_Select_Begin_Time not in", values, "firstRoundStudentSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundStudentSelectBeginTimeBetween(Date value1, Date value2) {
            addCriterion("first_Round_Student_Select_Begin_Time between", value1, value2, "firstRoundStudentSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundStudentSelectBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("first_Round_Student_Select_Begin_Time not between", value1, value2, "firstRoundStudentSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundStudentSelectEndTimeIsNull() {
            addCriterion("first_Round_Student_Select_End_Time is null");
            return (Criteria) this;
        }

        public Criteria andFirstRoundStudentSelectEndTimeIsNotNull() {
            addCriterion("first_Round_Student_Select_End_Time is not null");
            return (Criteria) this;
        }

        public Criteria andFirstRoundStudentSelectEndTimeEqualTo(Date value) {
            addCriterion("first_Round_Student_Select_End_Time =", value, "firstRoundStudentSelectEndTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundStudentSelectEndTimeNotEqualTo(Date value) {
            addCriterion("first_Round_Student_Select_End_Time <>", value, "firstRoundStudentSelectEndTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundStudentSelectEndTimeGreaterThan(Date value) {
            addCriterion("first_Round_Student_Select_End_Time >", value, "firstRoundStudentSelectEndTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundStudentSelectEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("first_Round_Student_Select_End_Time >=", value, "firstRoundStudentSelectEndTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundStudentSelectEndTimeLessThan(Date value) {
            addCriterion("first_Round_Student_Select_End_Time <", value, "firstRoundStudentSelectEndTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundStudentSelectEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("first_Round_Student_Select_End_Time <=", value, "firstRoundStudentSelectEndTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundStudentSelectEndTimeIn(List<Date> values) {
            addCriterion("first_Round_Student_Select_End_Time in", values, "firstRoundStudentSelectEndTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundStudentSelectEndTimeNotIn(List<Date> values) {
            addCriterion("first_Round_Student_Select_End_Time not in", values, "firstRoundStudentSelectEndTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundStudentSelectEndTimeBetween(Date value1, Date value2) {
            addCriterion("first_Round_Student_Select_End_Time between", value1, value2, "firstRoundStudentSelectEndTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundStudentSelectEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("first_Round_Student_Select_End_Time not between", value1, value2, "firstRoundStudentSelectEndTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundTeacherSelectBeginTimeIsNull() {
            addCriterion("first_Round_Teacher_Select_Begin_Time is null");
            return (Criteria) this;
        }

        public Criteria andFirstRoundTeacherSelectBeginTimeIsNotNull() {
            addCriterion("first_Round_Teacher_Select_Begin_Time is not null");
            return (Criteria) this;
        }

        public Criteria andFirstRoundTeacherSelectBeginTimeEqualTo(Date value) {
            addCriterion("first_Round_Teacher_Select_Begin_Time =", value, "firstRoundTeacherSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundTeacherSelectBeginTimeNotEqualTo(Date value) {
            addCriterion("first_Round_Teacher_Select_Begin_Time <>", value, "firstRoundTeacherSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundTeacherSelectBeginTimeGreaterThan(Date value) {
            addCriterion("first_Round_Teacher_Select_Begin_Time >", value, "firstRoundTeacherSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundTeacherSelectBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("first_Round_Teacher_Select_Begin_Time >=", value, "firstRoundTeacherSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundTeacherSelectBeginTimeLessThan(Date value) {
            addCriterion("first_Round_Teacher_Select_Begin_Time <", value, "firstRoundTeacherSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundTeacherSelectBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("first_Round_Teacher_Select_Begin_Time <=", value, "firstRoundTeacherSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundTeacherSelectBeginTimeIn(List<Date> values) {
            addCriterion("first_Round_Teacher_Select_Begin_Time in", values, "firstRoundTeacherSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundTeacherSelectBeginTimeNotIn(List<Date> values) {
            addCriterion("first_Round_Teacher_Select_Begin_Time not in", values, "firstRoundTeacherSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundTeacherSelectBeginTimeBetween(Date value1, Date value2) {
            addCriterion("first_Round_Teacher_Select_Begin_Time between", value1, value2, "firstRoundTeacherSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundTeacherSelectBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("first_Round_Teacher_Select_Begin_Time not between", value1, value2, "firstRoundTeacherSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundTeacherSelectEndTimeIsNull() {
            addCriterion("first_Round_Teacher_Select_End_Time is null");
            return (Criteria) this;
        }

        public Criteria andFirstRoundTeacherSelectEndTimeIsNotNull() {
            addCriterion("first_Round_Teacher_Select_End_Time is not null");
            return (Criteria) this;
        }

        public Criteria andFirstRoundTeacherSelectEndTimeEqualTo(Date value) {
            addCriterion("first_Round_Teacher_Select_End_Time =", value, "firstRoundTeacherSelectEndTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundTeacherSelectEndTimeNotEqualTo(Date value) {
            addCriterion("first_Round_Teacher_Select_End_Time <>", value, "firstRoundTeacherSelectEndTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundTeacherSelectEndTimeGreaterThan(Date value) {
            addCriterion("first_Round_Teacher_Select_End_Time >", value, "firstRoundTeacherSelectEndTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundTeacherSelectEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("first_Round_Teacher_Select_End_Time >=", value, "firstRoundTeacherSelectEndTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundTeacherSelectEndTimeLessThan(Date value) {
            addCriterion("first_Round_Teacher_Select_End_Time <", value, "firstRoundTeacherSelectEndTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundTeacherSelectEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("first_Round_Teacher_Select_End_Time <=", value, "firstRoundTeacherSelectEndTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundTeacherSelectEndTimeIn(List<Date> values) {
            addCriterion("first_Round_Teacher_Select_End_Time in", values, "firstRoundTeacherSelectEndTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundTeacherSelectEndTimeNotIn(List<Date> values) {
            addCriterion("first_Round_Teacher_Select_End_Time not in", values, "firstRoundTeacherSelectEndTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundTeacherSelectEndTimeBetween(Date value1, Date value2) {
            addCriterion("first_Round_Teacher_Select_End_Time between", value1, value2, "firstRoundTeacherSelectEndTime");
            return (Criteria) this;
        }

        public Criteria andFirstRoundTeacherSelectEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("first_Round_Teacher_Select_End_Time not between", value1, value2, "firstRoundTeacherSelectEndTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundStudentSelectBeginTimeIsNull() {
            addCriterion("second_Round_Student_Select_Begin_Time is null");
            return (Criteria) this;
        }

        public Criteria andSecondRoundStudentSelectBeginTimeIsNotNull() {
            addCriterion("second_Round_Student_Select_Begin_Time is not null");
            return (Criteria) this;
        }

        public Criteria andSecondRoundStudentSelectBeginTimeEqualTo(Date value) {
            addCriterion("second_Round_Student_Select_Begin_Time =", value, "secondRoundStudentSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundStudentSelectBeginTimeNotEqualTo(Date value) {
            addCriterion("second_Round_Student_Select_Begin_Time <>", value, "secondRoundStudentSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundStudentSelectBeginTimeGreaterThan(Date value) {
            addCriterion("second_Round_Student_Select_Begin_Time >", value, "secondRoundStudentSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundStudentSelectBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("second_Round_Student_Select_Begin_Time >=", value, "secondRoundStudentSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundStudentSelectBeginTimeLessThan(Date value) {
            addCriterion("second_Round_Student_Select_Begin_Time <", value, "secondRoundStudentSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundStudentSelectBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("second_Round_Student_Select_Begin_Time <=", value, "secondRoundStudentSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundStudentSelectBeginTimeIn(List<Date> values) {
            addCriterion("second_Round_Student_Select_Begin_Time in", values, "secondRoundStudentSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundStudentSelectBeginTimeNotIn(List<Date> values) {
            addCriterion("second_Round_Student_Select_Begin_Time not in", values, "secondRoundStudentSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundStudentSelectBeginTimeBetween(Date value1, Date value2) {
            addCriterion("second_Round_Student_Select_Begin_Time between", value1, value2, "secondRoundStudentSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundStudentSelectBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("second_Round_Student_Select_Begin_Time not between", value1, value2, "secondRoundStudentSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundStudentSelecEndTimeIsNull() {
            addCriterion("second_Round_Student_Selec_End_Time is null");
            return (Criteria) this;
        }

        public Criteria andSecondRoundStudentSelecEndTimeIsNotNull() {
            addCriterion("second_Round_Student_Selec_End_Time is not null");
            return (Criteria) this;
        }

        public Criteria andSecondRoundStudentSelecEndTimeEqualTo(Date value) {
            addCriterion("second_Round_Student_Selec_End_Time =", value, "secondRoundStudentSelecEndTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundStudentSelecEndTimeNotEqualTo(Date value) {
            addCriterion("second_Round_Student_Selec_End_Time <>", value, "secondRoundStudentSelecEndTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundStudentSelecEndTimeGreaterThan(Date value) {
            addCriterion("second_Round_Student_Selec_End_Time >", value, "secondRoundStudentSelecEndTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundStudentSelecEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("second_Round_Student_Selec_End_Time >=", value, "secondRoundStudentSelecEndTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundStudentSelecEndTimeLessThan(Date value) {
            addCriterion("second_Round_Student_Selec_End_Time <", value, "secondRoundStudentSelecEndTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundStudentSelecEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("second_Round_Student_Selec_End_Time <=", value, "secondRoundStudentSelecEndTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundStudentSelecEndTimeIn(List<Date> values) {
            addCriterion("second_Round_Student_Selec_End_Time in", values, "secondRoundStudentSelecEndTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundStudentSelecEndTimeNotIn(List<Date> values) {
            addCriterion("second_Round_Student_Selec_End_Time not in", values, "secondRoundStudentSelecEndTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundStudentSelecEndTimeBetween(Date value1, Date value2) {
            addCriterion("second_Round_Student_Selec_End_Time between", value1, value2, "secondRoundStudentSelecEndTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundStudentSelecEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("second_Round_Student_Selec_End_Time not between", value1, value2, "secondRoundStudentSelecEndTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundTeacherSelectBeginTimeIsNull() {
            addCriterion("Second_Round_Teacher_Select_Begin_Time is null");
            return (Criteria) this;
        }

        public Criteria andSecondRoundTeacherSelectBeginTimeIsNotNull() {
            addCriterion("Second_Round_Teacher_Select_Begin_Time is not null");
            return (Criteria) this;
        }

        public Criteria andSecondRoundTeacherSelectBeginTimeEqualTo(Date value) {
            addCriterion("Second_Round_Teacher_Select_Begin_Time =", value, "secondRoundTeacherSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundTeacherSelectBeginTimeNotEqualTo(Date value) {
            addCriterion("Second_Round_Teacher_Select_Begin_Time <>", value, "secondRoundTeacherSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundTeacherSelectBeginTimeGreaterThan(Date value) {
            addCriterion("Second_Round_Teacher_Select_Begin_Time >", value, "secondRoundTeacherSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundTeacherSelectBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("Second_Round_Teacher_Select_Begin_Time >=", value, "secondRoundTeacherSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundTeacherSelectBeginTimeLessThan(Date value) {
            addCriterion("Second_Round_Teacher_Select_Begin_Time <", value, "secondRoundTeacherSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundTeacherSelectBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("Second_Round_Teacher_Select_Begin_Time <=", value, "secondRoundTeacherSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundTeacherSelectBeginTimeIn(List<Date> values) {
            addCriterion("Second_Round_Teacher_Select_Begin_Time in", values, "secondRoundTeacherSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundTeacherSelectBeginTimeNotIn(List<Date> values) {
            addCriterion("Second_Round_Teacher_Select_Begin_Time not in", values, "secondRoundTeacherSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundTeacherSelectBeginTimeBetween(Date value1, Date value2) {
            addCriterion("Second_Round_Teacher_Select_Begin_Time between", value1, value2, "secondRoundTeacherSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundTeacherSelectBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("Second_Round_Teacher_Select_Begin_Time not between", value1, value2, "secondRoundTeacherSelectBeginTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundTeacherSelectEndTimeIsNull() {
            addCriterion("Second_Round_Teacher_Select_End_Time is null");
            return (Criteria) this;
        }

        public Criteria andSecondRoundTeacherSelectEndTimeIsNotNull() {
            addCriterion("Second_Round_Teacher_Select_End_Time is not null");
            return (Criteria) this;
        }

        public Criteria andSecondRoundTeacherSelectEndTimeEqualTo(Date value) {
            addCriterion("Second_Round_Teacher_Select_End_Time =", value, "secondRoundTeacherSelectEndTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundTeacherSelectEndTimeNotEqualTo(Date value) {
            addCriterion("Second_Round_Teacher_Select_End_Time <>", value, "secondRoundTeacherSelectEndTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundTeacherSelectEndTimeGreaterThan(Date value) {
            addCriterion("Second_Round_Teacher_Select_End_Time >", value, "secondRoundTeacherSelectEndTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundTeacherSelectEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("Second_Round_Teacher_Select_End_Time >=", value, "secondRoundTeacherSelectEndTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundTeacherSelectEndTimeLessThan(Date value) {
            addCriterion("Second_Round_Teacher_Select_End_Time <", value, "secondRoundTeacherSelectEndTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundTeacherSelectEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("Second_Round_Teacher_Select_End_Time <=", value, "secondRoundTeacherSelectEndTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundTeacherSelectEndTimeIn(List<Date> values) {
            addCriterion("Second_Round_Teacher_Select_End_Time in", values, "secondRoundTeacherSelectEndTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundTeacherSelectEndTimeNotIn(List<Date> values) {
            addCriterion("Second_Round_Teacher_Select_End_Time not in", values, "secondRoundTeacherSelectEndTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundTeacherSelectEndTimeBetween(Date value1, Date value2) {
            addCriterion("Second_Round_Teacher_Select_End_Time between", value1, value2, "secondRoundTeacherSelectEndTime");
            return (Criteria) this;
        }

        public Criteria andSecondRoundTeacherSelectEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("Second_Round_Teacher_Select_End_Time not between", value1, value2, "secondRoundTeacherSelectEndTime");
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