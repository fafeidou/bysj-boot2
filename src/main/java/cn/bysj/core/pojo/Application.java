package cn.bysj.core.pojo;

public class Application extends ApplicationKey {
    private String messageToTeacher;

    public String getMessageToTeacher() {
        return messageToTeacher;
    }

    public void setMessageToTeacher(String messageToTeacher) {
        this.messageToTeacher = messageToTeacher == null ? null : messageToTeacher.trim();
    }
}