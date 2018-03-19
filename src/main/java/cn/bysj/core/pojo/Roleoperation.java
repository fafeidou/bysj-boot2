package cn.bysj.core.pojo;

import java.io.Serializable;

public class Roleoperation extends RoleoperationKey implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String actionUrl;

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl == null ? null : actionUrl.trim();
    }
}