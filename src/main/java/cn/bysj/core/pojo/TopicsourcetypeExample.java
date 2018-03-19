package cn.bysj.core.pojo;

import java.util.ArrayList;
import java.util.List;

public class TopicsourcetypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TopicsourcetypeExample() {
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

        public Criteria andSourceTypeNameIsNull() {
            addCriterion("source_Type_Name is null");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNameIsNotNull() {
            addCriterion("source_Type_Name is not null");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNameEqualTo(String value) {
            addCriterion("source_Type_Name =", value, "sourceTypeName");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNameNotEqualTo(String value) {
            addCriterion("source_Type_Name <>", value, "sourceTypeName");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNameGreaterThan(String value) {
            addCriterion("source_Type_Name >", value, "sourceTypeName");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("source_Type_Name >=", value, "sourceTypeName");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNameLessThan(String value) {
            addCriterion("source_Type_Name <", value, "sourceTypeName");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNameLessThanOrEqualTo(String value) {
            addCriterion("source_Type_Name <=", value, "sourceTypeName");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNameLike(String value) {
            addCriterion("source_Type_Name like", value, "sourceTypeName");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNameNotLike(String value) {
            addCriterion("source_Type_Name not like", value, "sourceTypeName");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNameIn(List<String> values) {
            addCriterion("source_Type_Name in", values, "sourceTypeName");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNameNotIn(List<String> values) {
            addCriterion("source_Type_Name not in", values, "sourceTypeName");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNameBetween(String value1, String value2) {
            addCriterion("source_Type_Name between", value1, value2, "sourceTypeName");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNameNotBetween(String value1, String value2) {
            addCriterion("source_Type_Name not between", value1, value2, "sourceTypeName");
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