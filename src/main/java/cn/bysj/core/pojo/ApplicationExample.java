package cn.bysj.core.pojo;

import java.util.ArrayList;
import java.util.List;

public class ApplicationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;
	// 每页数
	protected int pageSize;
	// 起始行
	protected int startRow;// 起始行
	// 是否使用limit 0 ： 不用 1 ：用
	protected int isLimit;
	
    public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getIsLimit() {
		return isLimit;
	}

	public void setIsLimit(int isLimit) {
		this.isLimit = isLimit;
	}

	public ApplicationExample() {
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

        public Criteria andMessageToTeacherIsNull() {
            addCriterion("message_To_Teacher is null");
            return (Criteria) this;
        }

        public Criteria andMessageToTeacherIsNotNull() {
            addCriterion("message_To_Teacher is not null");
            return (Criteria) this;
        }

        public Criteria andMessageToTeacherEqualTo(String value) {
            addCriterion("message_To_Teacher =", value, "messageToTeacher");
            return (Criteria) this;
        }

        public Criteria andMessageToTeacherNotEqualTo(String value) {
            addCriterion("message_To_Teacher <>", value, "messageToTeacher");
            return (Criteria) this;
        }

        public Criteria andMessageToTeacherGreaterThan(String value) {
            addCriterion("message_To_Teacher >", value, "messageToTeacher");
            return (Criteria) this;
        }

        public Criteria andMessageToTeacherGreaterThanOrEqualTo(String value) {
            addCriterion("message_To_Teacher >=", value, "messageToTeacher");
            return (Criteria) this;
        }

        public Criteria andMessageToTeacherLessThan(String value) {
            addCriterion("message_To_Teacher <", value, "messageToTeacher");
            return (Criteria) this;
        }

        public Criteria andMessageToTeacherLessThanOrEqualTo(String value) {
            addCriterion("message_To_Teacher <=", value, "messageToTeacher");
            return (Criteria) this;
        }

        public Criteria andMessageToTeacherLike(String value) {
            addCriterion("message_To_Teacher like", value, "messageToTeacher");
            return (Criteria) this;
        }

        public Criteria andMessageToTeacherNotLike(String value) {
            addCriterion("message_To_Teacher not like", value, "messageToTeacher");
            return (Criteria) this;
        }

        public Criteria andMessageToTeacherIn(List<String> values) {
            addCriterion("message_To_Teacher in", values, "messageToTeacher");
            return (Criteria) this;
        }

        public Criteria andMessageToTeacherNotIn(List<String> values) {
            addCriterion("message_To_Teacher not in", values, "messageToTeacher");
            return (Criteria) this;
        }

        public Criteria andMessageToTeacherBetween(String value1, String value2) {
            addCriterion("message_To_Teacher between", value1, value2, "messageToTeacher");
            return (Criteria) this;
        }

        public Criteria andMessageToTeacherNotBetween(String value1, String value2) {
            addCriterion("message_To_Teacher not between", value1, value2, "messageToTeacher");
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