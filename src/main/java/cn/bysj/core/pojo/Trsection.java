package cn.bysj.core.pojo;

public class Trsection {
    private Integer trsectionId;

    private Integer departmentId;

    private String sectionName;

    public Integer getTrsectionId() {
        return trsectionId;
    }

    public void setTrsectionId(Integer trsectionId) {
        this.trsectionId = trsectionId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName == null ? null : sectionName.trim();
    }
}