package cn.bysj.core.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ThesistopicExample {

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

	public ThesistopicExample() {
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

		public Criteria andThesisTitleIsNull() {
			addCriterion("thesis_Title is null");
			return (Criteria) this;
		}

		public Criteria andThesisTitleIsNotNull() {
			addCriterion("thesis_Title is not null");
			return (Criteria) this;
		}

		public Criteria andThesisTitleEqualTo(String value) {
			addCriterion("thesis_Title =", value, "thesisTitle");
			return (Criteria) this;
		}

		public Criteria andThesisTitleNotEqualTo(String value) {
			addCriterion("thesis_Title <>", value, "thesisTitle");
			return (Criteria) this;
		}

		public Criteria andThesisTitleGreaterThan(String value) {
			addCriterion("thesis_Title >", value, "thesisTitle");
			return (Criteria) this;
		}

		public Criteria andThesisTitleGreaterThanOrEqualTo(String value) {
			addCriterion("thesis_Title >=", value, "thesisTitle");
			return (Criteria) this;
		}

		public Criteria andThesisTitleLessThan(String value) {
			addCriterion("thesis_Title <", value, "thesisTitle");
			return (Criteria) this;
		}

		public Criteria andThesisTitleLessThanOrEqualTo(String value) {
			addCriterion("thesis_Title <=", value, "thesisTitle");
			return (Criteria) this;
		}

		public Criteria andThesisTitleLike(String value) {
			addCriterion("thesis_Title like", value, "thesisTitle");
			return (Criteria) this;
		}

		public Criteria andThesisTitleNotLike(String value) {
			addCriterion("thesis_Title not like", value, "thesisTitle");
			return (Criteria) this;
		}

		public Criteria andThesisTitleIn(List<String> values) {
			addCriterion("thesis_Title in", values, "thesisTitle");
			return (Criteria) this;
		}

		public Criteria andThesisTitleNotIn(List<String> values) {
			addCriterion("thesis_Title not in", values, "thesisTitle");
			return (Criteria) this;
		}

		public Criteria andThesisTitleBetween(String value1, String value2) {
			addCriterion("thesis_Title between", value1, value2, "thesisTitle");
			return (Criteria) this;
		}

		public Criteria andThesisTitleNotBetween(String value1, String value2) {
			addCriterion("thesis_Title not between", value1, value2, "thesisTitle");
			return (Criteria) this;
		}

		public Criteria andThesisEnglishTileIsNull() {
			addCriterion("thesis_English_Tile is null");
			return (Criteria) this;
		}

		public Criteria andThesisEnglishTileIsNotNull() {
			addCriterion("thesis_English_Tile is not null");
			return (Criteria) this;
		}

		public Criteria andThesisEnglishTileEqualTo(String value) {
			addCriterion("thesis_English_Tile =", value, "thesisEnglishTile");
			return (Criteria) this;
		}

		public Criteria andThesisEnglishTileNotEqualTo(String value) {
			addCriterion("thesis_English_Tile <>", value, "thesisEnglishTile");
			return (Criteria) this;
		}

		public Criteria andThesisEnglishTileGreaterThan(String value) {
			addCriterion("thesis_English_Tile >", value, "thesisEnglishTile");
			return (Criteria) this;
		}

		public Criteria andThesisEnglishTileGreaterThanOrEqualTo(String value) {
			addCriterion("thesis_English_Tile >=", value, "thesisEnglishTile");
			return (Criteria) this;
		}

		public Criteria andThesisEnglishTileLessThan(String value) {
			addCriterion("thesis_English_Tile <", value, "thesisEnglishTile");
			return (Criteria) this;
		}

		public Criteria andThesisEnglishTileLessThanOrEqualTo(String value) {
			addCriterion("thesis_English_Tile <=", value, "thesisEnglishTile");
			return (Criteria) this;
		}

		public Criteria andThesisEnglishTileLike(String value) {
			addCriterion("thesis_English_Tile like", value, "thesisEnglishTile");
			return (Criteria) this;
		}

		public Criteria andThesisEnglishTileNotLike(String value) {
			addCriterion("thesis_English_Tile not like", value, "thesisEnglishTile");
			return (Criteria) this;
		}

		public Criteria andThesisEnglishTileIn(List<String> values) {
			addCriterion("thesis_English_Tile in", values, "thesisEnglishTile");
			return (Criteria) this;
		}

		public Criteria andThesisEnglishTileNotIn(List<String> values) {
			addCriterion("thesis_English_Tile not in", values, "thesisEnglishTile");
			return (Criteria) this;
		}

		public Criteria andThesisEnglishTileBetween(String value1, String value2) {
			addCriterion("thesis_English_Tile between", value1, value2, "thesisEnglishTile");
			return (Criteria) this;
		}

		public Criteria andThesisEnglishTileNotBetween(String value1, String value2) {
			addCriterion("thesis_English_Tile not between", value1, value2, "thesisEnglishTile");
			return (Criteria) this;
		}

		public Criteria andProjectRequirementIsNull() {
			addCriterion("project_Requirement is null");
			return (Criteria) this;
		}

		public Criteria andProjectRequirementIsNotNull() {
			addCriterion("project_Requirement is not null");
			return (Criteria) this;
		}

		public Criteria andProjectRequirementEqualTo(String value) {
			addCriterion("project_Requirement =", value, "projectRequirement");
			return (Criteria) this;
		}

		public Criteria andProjectRequirementNotEqualTo(String value) {
			addCriterion("project_Requirement <>", value, "projectRequirement");
			return (Criteria) this;
		}

		public Criteria andProjectRequirementGreaterThan(String value) {
			addCriterion("project_Requirement >", value, "projectRequirement");
			return (Criteria) this;
		}

		public Criteria andProjectRequirementGreaterThanOrEqualTo(String value) {
			addCriterion("project_Requirement >=", value, "projectRequirement");
			return (Criteria) this;
		}

		public Criteria andProjectRequirementLessThan(String value) {
			addCriterion("project_Requirement <", value, "projectRequirement");
			return (Criteria) this;
		}

		public Criteria andProjectRequirementLessThanOrEqualTo(String value) {
			addCriterion("project_Requirement <=", value, "projectRequirement");
			return (Criteria) this;
		}

		public Criteria andProjectRequirementLike(String value) {
			addCriterion("project_Requirement like", value, "projectRequirement");
			return (Criteria) this;
		}

		public Criteria andProjectRequirementNotLike(String value) {
			addCriterion("project_Requirement not like", value, "projectRequirement");
			return (Criteria) this;
		}

		public Criteria andProjectRequirementIn(List<String> values) {
			addCriterion("project_Requirement in", values, "projectRequirement");
			return (Criteria) this;
		}

		public Criteria andProjectRequirementNotIn(List<String> values) {
			addCriterion("project_Requirement not in", values, "projectRequirement");
			return (Criteria) this;
		}

		public Criteria andProjectRequirementBetween(String value1, String value2) {
			addCriterion("project_Requirement between", value1, value2, "projectRequirement");
			return (Criteria) this;
		}

		public Criteria andProjectRequirementNotBetween(String value1, String value2) {
			addCriterion("project_Requirement not between", value1, value2, "projectRequirement");
			return (Criteria) this;
		}

		public Criteria andWorkloadReqirementIsNull() {
			addCriterion("workload_Reqirement is null");
			return (Criteria) this;
		}

		public Criteria andWorkloadReqirementIsNotNull() {
			addCriterion("workload_Reqirement is not null");
			return (Criteria) this;
		}

		public Criteria andWorkloadReqirementEqualTo(String value) {
			addCriterion("workload_Reqirement =", value, "workloadReqirement");
			return (Criteria) this;
		}

		public Criteria andWorkloadReqirementNotEqualTo(String value) {
			addCriterion("workload_Reqirement <>", value, "workloadReqirement");
			return (Criteria) this;
		}

		public Criteria andWorkloadReqirementGreaterThan(String value) {
			addCriterion("workload_Reqirement >", value, "workloadReqirement");
			return (Criteria) this;
		}

		public Criteria andWorkloadReqirementGreaterThanOrEqualTo(String value) {
			addCriterion("workload_Reqirement >=", value, "workloadReqirement");
			return (Criteria) this;
		}

		public Criteria andWorkloadReqirementLessThan(String value) {
			addCriterion("workload_Reqirement <", value, "workloadReqirement");
			return (Criteria) this;
		}

		public Criteria andWorkloadReqirementLessThanOrEqualTo(String value) {
			addCriterion("workload_Reqirement <=", value, "workloadReqirement");
			return (Criteria) this;
		}

		public Criteria andWorkloadReqirementLike(String value) {
			addCriterion("workload_Reqirement like", value, "workloadReqirement");
			return (Criteria) this;
		}

		public Criteria andWorkloadReqirementNotLike(String value) {
			addCriterion("workload_Reqirement not like", value, "workloadReqirement");
			return (Criteria) this;
		}

		public Criteria andWorkloadReqirementIn(List<String> values) {
			addCriterion("workload_Reqirement in", values, "workloadReqirement");
			return (Criteria) this;
		}

		public Criteria andWorkloadReqirementNotIn(List<String> values) {
			addCriterion("workload_Reqirement not in", values, "workloadReqirement");
			return (Criteria) this;
		}

		public Criteria andWorkloadReqirementBetween(String value1, String value2) {
			addCriterion("workload_Reqirement between", value1, value2, "workloadReqirement");
			return (Criteria) this;
		}

		public Criteria andWorkloadReqirementNotBetween(String value1, String value2) {
			addCriterion("workload_Reqirement not between", value1, value2, "workloadReqirement");
			return (Criteria) this;
		}

		public Criteria andOtherTeacherCanSeeIsNull() {
			addCriterion("other_Teacher_Can_See is null");
			return (Criteria) this;
		}

		public Criteria andOtherTeacherCanSeeIsNotNull() {
			addCriterion("other_Teacher_Can_See is not null");
			return (Criteria) this;
		}

		public Criteria andOtherTeacherCanSeeEqualTo(Byte value) {
			addCriterion("other_Teacher_Can_See =", value, "otherTeacherCanSee");
			return (Criteria) this;
		}

		public Criteria andOtherTeacherCanSeeNotEqualTo(Byte value) {
			addCriterion("other_Teacher_Can_See <>", value, "otherTeacherCanSee");
			return (Criteria) this;
		}

		public Criteria andOtherTeacherCanSeeGreaterThan(Byte value) {
			addCriterion("other_Teacher_Can_See >", value, "otherTeacherCanSee");
			return (Criteria) this;
		}

		public Criteria andOtherTeacherCanSeeGreaterThanOrEqualTo(Byte value) {
			addCriterion("other_Teacher_Can_See >=", value, "otherTeacherCanSee");
			return (Criteria) this;
		}

		public Criteria andOtherTeacherCanSeeLessThan(Byte value) {
			addCriterion("other_Teacher_Can_See <", value, "otherTeacherCanSee");
			return (Criteria) this;
		}

		public Criteria andOtherTeacherCanSeeLessThanOrEqualTo(Byte value) {
			addCriterion("other_Teacher_Can_See <=", value, "otherTeacherCanSee");
			return (Criteria) this;
		}

		public Criteria andOtherTeacherCanSeeIn(List<Byte> values) {
			addCriterion("other_Teacher_Can_See in", values, "otherTeacherCanSee");
			return (Criteria) this;
		}

		public Criteria andOtherTeacherCanSeeNotIn(List<Byte> values) {
			addCriterion("other_Teacher_Can_See not in", values, "otherTeacherCanSee");
			return (Criteria) this;
		}

		public Criteria andOtherTeacherCanSeeBetween(Byte value1, Byte value2) {
			addCriterion("other_Teacher_Can_See between", value1, value2, "otherTeacherCanSee");
			return (Criteria) this;
		}

		public Criteria andOtherTeacherCanSeeNotBetween(Byte value1, Byte value2) {
			addCriterion("other_Teacher_Can_See not between", value1, value2, "otherTeacherCanSee");
			return (Criteria) this;
		}

		public Criteria andTopicStateIsNull() {
			addCriterion("topic_State is null");
			return (Criteria) this;
		}

		public Criteria andTopicStateIsNotNull() {
			addCriterion("topic_State is not null");
			return (Criteria) this;
		}

		public Criteria andTopicStateEqualTo(Byte value) {
			addCriterion("topic_State =", value, "topicState");
			return (Criteria) this;
		}

		public Criteria andTopicStateNotEqualTo(Byte value) {
			addCriterion("topic_State <>", value, "topicState");
			return (Criteria) this;
		}

		public Criteria andTopicStateGreaterThan(Byte value) {
			addCriterion("topic_State >", value, "topicState");
			return (Criteria) this;
		}

		public Criteria andTopicStateGreaterThanOrEqualTo(Byte value) {
			addCriterion("topic_State >=", value, "topicState");
			return (Criteria) this;
		}

		public Criteria andTopicStateLessThan(Byte value) {
			addCriterion("topic_State <", value, "topicState");
			return (Criteria) this;
		}

		public Criteria andTopicStateLessThanOrEqualTo(Byte value) {
			addCriterion("topic_State <=", value, "topicState");
			return (Criteria) this;
		}

		public Criteria andTopicStateIn(List<Byte> values) {
			addCriterion("topic_State in", values, "topicState");
			return (Criteria) this;
		}

		public Criteria andTopicStateNotIn(List<Byte> values) {
			addCriterion("topic_State not in", values, "topicState");
			return (Criteria) this;
		}

		public Criteria andTopicStateBetween(Byte value1, Byte value2) {
			addCriterion("topic_State between", value1, value2, "topicState");
			return (Criteria) this;
		}

		public Criteria andTopicStateNotBetween(Byte value1, Byte value2) {
			addCriterion("topic_State not between", value1, value2, "topicState");
			return (Criteria) this;
		}

		public Criteria andCreateDateIsNull() {
			addCriterion("create_Date is null");
			return (Criteria) this;
		}

		public Criteria andCreateDateIsNotNull() {
			addCriterion("create_Date is not null");
			return (Criteria) this;
		}

		public Criteria andCreateDateEqualTo(Date value) {
			addCriterion("create_Date =", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateNotEqualTo(Date value) {
			addCriterion("create_Date <>", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateGreaterThan(Date value) {
			addCriterion("create_Date >", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
			addCriterion("create_Date >=", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateLessThan(Date value) {
			addCriterion("create_Date <", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateLessThanOrEqualTo(Date value) {
			addCriterion("create_Date <=", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateIn(List<Date> values) {
			addCriterion("create_Date in", values, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateNotIn(List<Date> values) {
			addCriterion("create_Date not in", values, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateBetween(Date value1, Date value2) {
			addCriterion("create_Date between", value1, value2, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateNotBetween(Date value1, Date value2) {
			addCriterion("create_Date not between", value1, value2, "createDate");
			return (Criteria) this;
		}

		public Criteria andLastUseDateIsNull() {
			addCriterion("last_Use_Date is null");
			return (Criteria) this;
		}

		public Criteria andLastUseDateIsNotNull() {
			addCriterion("last_Use_Date is not null");
			return (Criteria) this;
		}

		public Criteria andLastUseDateEqualTo(Date value) {
			addCriterion("last_Use_Date =", value, "lastUseDate");
			return (Criteria) this;
		}

		public Criteria andLastUseDateNotEqualTo(Date value) {
			addCriterion("last_Use_Date <>", value, "lastUseDate");
			return (Criteria) this;
		}

		public Criteria andLastUseDateGreaterThan(Date value) {
			addCriterion("last_Use_Date >", value, "lastUseDate");
			return (Criteria) this;
		}

		public Criteria andLastUseDateGreaterThanOrEqualTo(Date value) {
			addCriterion("last_Use_Date >=", value, "lastUseDate");
			return (Criteria) this;
		}

		public Criteria andLastUseDateLessThan(Date value) {
			addCriterion("last_Use_Date <", value, "lastUseDate");
			return (Criteria) this;
		}

		public Criteria andLastUseDateLessThanOrEqualTo(Date value) {
			addCriterion("last_Use_Date <=", value, "lastUseDate");
			return (Criteria) this;
		}

		public Criteria andLastUseDateIn(List<Date> values) {
			addCriterion("last_Use_Date in", values, "lastUseDate");
			return (Criteria) this;
		}

		public Criteria andLastUseDateNotIn(List<Date> values) {
			addCriterion("last_Use_Date not in", values, "lastUseDate");
			return (Criteria) this;
		}

		public Criteria andLastUseDateBetween(Date value1, Date value2) {
			addCriterion("last_Use_Date between", value1, value2, "lastUseDate");
			return (Criteria) this;
		}

		public Criteria andLastUseDateNotBetween(Date value1, Date value2) {
			addCriterion("last_Use_Date not between", value1, value2, "lastUseDate");
			return (Criteria) this;
		}

		public Criteria andNoteIsNull() {
			addCriterion("note is null");
			return (Criteria) this;
		}

		public Criteria andNoteIsNotNull() {
			addCriterion("note is not null");
			return (Criteria) this;
		}

		public Criteria andNoteEqualTo(String value) {
			addCriterion("note =", value, "note");
			return (Criteria) this;
		}

		public Criteria andNoteNotEqualTo(String value) {
			addCriterion("note <>", value, "note");
			return (Criteria) this;
		}

		public Criteria andNoteGreaterThan(String value) {
			addCriterion("note >", value, "note");
			return (Criteria) this;
		}

		public Criteria andNoteGreaterThanOrEqualTo(String value) {
			addCriterion("note >=", value, "note");
			return (Criteria) this;
		}

		public Criteria andNoteLessThan(String value) {
			addCriterion("note <", value, "note");
			return (Criteria) this;
		}

		public Criteria andNoteLessThanOrEqualTo(String value) {
			addCriterion("note <=", value, "note");
			return (Criteria) this;
		}

		public Criteria andNoteLike(String value) {
			addCriterion("note like", value, "note");
			return (Criteria) this;
		}

		public Criteria andNoteNotLike(String value) {
			addCriterion("note not like", value, "note");
			return (Criteria) this;
		}

		public Criteria andNoteIn(List<String> values) {
			addCriterion("note in", values, "note");
			return (Criteria) this;
		}

		public Criteria andNoteNotIn(List<String> values) {
			addCriterion("note not in", values, "note");
			return (Criteria) this;
		}

		public Criteria andNoteBetween(String value1, String value2) {
			addCriterion("note between", value1, value2, "note");
			return (Criteria) this;
		}

		public Criteria andNoteNotBetween(String value1, String value2) {
			addCriterion("note not between", value1, value2, "note");
			return (Criteria) this;
		}

		public Criteria andGraduationYearIsNull() {
			addCriterion("graduation_Year is null");
			return (Criteria) this;
		}

		public Criteria andGraduationYearIsNotNull() {
			addCriterion("graduation_Year is not null");
			return (Criteria) this;
		}

		public Criteria andGraduationYearEqualTo(String value) {
			addCriterion("graduation_Year =", value, "graduationYear");
			return (Criteria) this;
		}

		public Criteria andGraduationYearNotEqualTo(String value) {
			addCriterion("graduation_Year <>", value, "graduationYear");
			return (Criteria) this;
		}

		public Criteria andGraduationYearGreaterThan(String value) {
			addCriterion("graduation_Year >", value, "graduationYear");
			return (Criteria) this;
		}

		public Criteria andGraduationYearGreaterThanOrEqualTo(String value) {
			addCriterion("graduation_Year >=", value, "graduationYear");
			return (Criteria) this;
		}

		public Criteria andGraduationYearLessThan(String value) {
			addCriterion("graduation_Year <", value, "graduationYear");
			return (Criteria) this;
		}

		public Criteria andGraduationYearLessThanOrEqualTo(String value) {
			addCriterion("graduation_Year <=", value, "graduationYear");
			return (Criteria) this;
		}

		public Criteria andGraduationYearLike(String value) {
			addCriterion("graduation_Year like", value, "graduationYear");
			return (Criteria) this;
		}

		public Criteria andGraduationYearNotLike(String value) {
			addCriterion("graduation_Year not like", value, "graduationYear");
			return (Criteria) this;
		}

		public Criteria andGraduationYearIn(List<String> values) {
			addCriterion("graduation_Year in", values, "graduationYear");
			return (Criteria) this;
		}

		public Criteria andGraduationYearNotIn(List<String> values) {
			addCriterion("graduation_Year not in", values, "graduationYear");
			return (Criteria) this;
		}

		public Criteria andGraduationYearBetween(String value1, String value2) {
			addCriterion("graduation_Year between", value1, value2, "graduationYear");
			return (Criteria) this;
		}

		public Criteria andGraduationYearNotBetween(String value1, String value2) {
			addCriterion("graduation_Year not between", value1, value2, "graduationYear");
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