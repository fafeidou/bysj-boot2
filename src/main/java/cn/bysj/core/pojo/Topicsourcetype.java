package cn.bysj.core.pojo;

public class Topicsourcetype {
    private Integer topicSourceTypeId;

    private String sourceTypeName;

    public Integer getTopicSourceTypeId() {
        return topicSourceTypeId;
    }

    public void setTopicSourceTypeId(Integer topicSourceTypeId) {
        this.topicSourceTypeId = topicSourceTypeId;
    }

    public String getSourceTypeName() {
        return sourceTypeName;
    }

    public void setSourceTypeName(String sourceTypeName) {
        this.sourceTypeName = sourceTypeName == null ? null : sourceTypeName.trim();
    }
}