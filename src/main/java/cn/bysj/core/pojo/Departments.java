package cn.bysj.core.pojo;

public class Departments {
    private Integer departmentId;

    private String departmentName;

    private Integer topicSourceTypeId;

    private Integer topicTypeId;

    private Integer systemCfgId;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    public Integer getTopicSourceTypeId() {
        return topicSourceTypeId;
    }

    public void setTopicSourceTypeId(Integer topicSourceTypeId) {
        this.topicSourceTypeId = topicSourceTypeId;
    }

    public Integer getTopicTypeId() {
        return topicTypeId;
    }

    public void setTopicTypeId(Integer topicTypeId) {
        this.topicTypeId = topicTypeId;
    }

    public Integer getSystemCfgId() {
        return systemCfgId;
    }

    public void setSystemCfgId(Integer systemCfgId) {
        this.systemCfgId = systemCfgId;
    }
}