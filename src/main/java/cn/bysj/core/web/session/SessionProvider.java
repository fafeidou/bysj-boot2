package cn.bysj.core.web.session;


import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

public interface SessionProvider {
	/**
	 * 往session设置值 name Constants buyer_sessin value 用户对象
	 */
	public void setAttribute(HttpServletRequest request, String name,
			Serializable value);

	/**
	 * 从session中取值
	 */
	public Serializable getAttribute(String name, HttpServletRequest request);

	/**
	 * 退出登录
	 */
	public void logout(HttpServletRequest request);

	/**
	 * 获取SessionID
	 */
	public String getSessionId(HttpServletRequest request);
}
