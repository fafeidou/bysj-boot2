package cn.bysj.core.web.session;

import org.springframework.stereotype.Component;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@Component
public class HttpSessionProvider implements SessionProvider{

	public void setAttribute(HttpServletRequest request, String name,
			Serializable value) {
		HttpSession session = request.getSession();  //true   Cookie JESSIONID  
		session.setAttribute(name, value);
	}

	public Serializable getAttribute(String name, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session != null){
			return (Serializable) session.getAttribute(name);
		}
		return null;
	}

	public void logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session != null){
			session.invalidate();
		}
		//Cookie JESSIONID
	}

	public String getSessionId(HttpServletRequest request) {
		return request.getSession().getId(); 
	}

}
