package cn.bysj.core.pojo;

public class Systemmanager {
    private Integer systemManagerId;

    private String name;

    private String password;

    public Integer getSystemManagerId() {
        return systemManagerId;
    }

    public void setSystemManagerId(Integer systemManagerId) {
        this.systemManagerId = systemManagerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}