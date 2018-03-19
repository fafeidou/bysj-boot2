package cn.bysj.core.pojo;

public class Classes {
    private Integer classisId;

    private Integer departmentId;

    private String className;

    private String academicType;

    private String graduationYear;

    public Integer getClassisId() {
        return classisId;
    }

    public void setClassisId(Integer classisId) {
        this.classisId = classisId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getAcademicType() {
        return academicType;
    }

    public void setAcademicType(String academicType) {
        this.academicType = academicType == null ? null : academicType.trim();
    }

    public String getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear == null ? null : graduationYear.trim();
    }
}