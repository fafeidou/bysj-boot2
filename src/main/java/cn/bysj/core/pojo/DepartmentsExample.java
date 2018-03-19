package cn.bysj.core.pojo;

import java.util.ArrayList;
import java.util.List;

import cn.bysj.core.pojo.query.BaseQuery;

public class DepartmentsExample extends BaseQuery{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DepartmentsExample() {
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

        public Criteria andDepartmentNameIsNull() {
            addCriterion("department_Name is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameIsNotNull() {
            addCriterion("department_Name is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameEqualTo(String value) {
            addCriterion("department_Name =", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameNotEqualTo(String value) {
            addCriterion("department_Name <>", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameGreaterThan(String value) {
            addCriterion("department_Name >", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameGreaterThanOrEqualTo(String value) {
            addCriterion("department_Name >=", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameLessThan(String value) {
            addCriterion("department_Name <", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameLessThanOrEqualTo(String value) {
            addCriterion("department_Name <=", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameLike(String value) {
            addCriterion("department_Name like", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameNotLike(String value) {
            addCriterion("department_Name not like", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameIn(List<String> values) {
            addCriterion("department_Name in", values, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameNotIn(List<String> values) {
            addCriterion("department_Name not in", values, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameBetween(String value1, String value2) {
            addCriterion("department_Name between", value1, value2, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameNotBetween(String value1, String value2) {
            addCriterion("department_Name not between", value1, value2, "departmentName");
            return (Criteria) this;
        }

        public Criteria andTopicSourceTypeIdIsNull() {
            addCriterion("topic_Source_Type_ID is null");
            return (Criteria) this;
        }

        public Criteria andTopicSourceTypeIdIsNotNull() {
            addCriterion("topic_Source_Type_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTopicSourceTypeIdEqualTo(Integer value) {
            addCriterion("topic_Source_Type_ID =", value, "topicSourceTypeId");
            return (Criteria) this;
        }

        public Criteria andTopicSourceTypeIdNotEqualTo(Integer value) {
            addCriterion("topic_Source_Type_ID <>", value, "topicSourceTypeId");
            return (Criteria) this;
        }

        public Criteria andTopicSourceTypeIdGreaterThan(Integer value) {
            addCriterion("topic_Source_Type_ID >", value, "topicSourceTypeId");
            return (Criteria) this;
        }

        public Criteria andTopicSourceTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("topic_Source_Type_ID >=", value, "topicSourceTypeId");
            return (Criteria) this;
        }

        public Criteria andTopicSourceTypeIdLessThan(Integer value) {
            addCriterion("topic_Source_Type_ID <", value, "topicSourceTypeId");
            return (Criteria) this;
        }

        public Criteria andTopicSourceTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("topic_Source_Type_ID <=", value, "topicSourceTypeId");
            return (Criteria) this;
        }

        public Criteria andTopicSourceTypeIdIn(List<Integer> values) {
            addCriterion("topic_Source_Type_ID in", values, "topicSourceTypeId");
            return (Criteria) this;
        }

        public Criteria andTopicSourceTypeIdNotIn(List<Integer> values) {
            addCriterion("topic_Source_Type_ID not in", values, "topicSourceTypeId");
            return (Criteria) this;
        }

        public Criteria andTopicSourceTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("topic_Source_Type_ID between", value1, value2, "topicSourceTypeId");
            return (Criteria) this;
        }

        public Criteria andTopicSourceTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("topic_Source_Type_ID not between", value1, value2, "topicSourceTypeId");
            return (Criteria) this;
        }

        public Criteria andTopicTypeIdIsNull() {
            addCriterion("topic_Type_ID is null");
            return (Criteria) this;
        }

        public Criteria andTopicTypeIdIsNotNull() {
            addCriterion("topic_Type_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTopicTypeIdEqualTo(Integer value) {
            addCriterion("topic_Type_ID =", value, "topicTypeId");
            return (Criteria) this;
        }

        public Criteria andTopicTypeIdNotEqualTo(Integer value) {
            addCriterion("topic_Type_ID <>", value, "topicTypeId");
            return (Criteria) this;
        }

        public Criteria andTopicTypeIdGreaterThan(Integer value) {
            addCriterion("topic_Type_ID >", value, "topicTypeId");
            return (Criteria) this;
        }

        public Criteria andTopicTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("topic_Type_ID >=", value, "topicTypeId");
            return (Criteria) this;
        }

        public Criteria andTopicTypeIdLessThan(Integer value) {
            addCriterion("topic_Type_ID <", value, "topicTypeId");
            return (Criteria) this;
        }

        public Criteria andTopicTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("topic_Type_ID <=", value, "topicTypeId");
            return (Criteria) this;
        }

        public Criteria andTopicTypeIdIn(List<Integer> values) {
            addCriterion("topic_Type_ID in", values, "topicTypeId");
            return (Criteria) this;
        }

        public Criteria andTopicTypeIdNotIn(List<Integer> values) {
            addCriterion("topic_Type_ID not in", values, "topicTypeId");
            return (Criteria) this;
        }

        public Criteria andTopicTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("topic_Type_ID between", value1, value2, "topicTypeId");
            return (Criteria) this;
        }

        public Criteria andTopicTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("topic_Type_ID not between", value1, value2, "topicTypeId");
            return (Criteria) this;
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