package cn.bysj.core.web.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**
 * 异步返回各种样式 json xml text
 * 
 * @author Administrator
 *
 */
public class ResponseUtils {
	// 发送内容
	public static void render(HttpServletResponse response, String contentType,
			String text) {
		response.setContentType(contentType);
		try {
			response.getWriter().write(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 发送的是Json
	public static void renderJson(HttpServletResponse response, String text) {
		render(response, "application/json;charset=UTF-8", text);
	}

	// 发送的是xml
	public static void renderXml(HttpServletResponse response, String text) {
		render(response, "text/html;charset=UTF-8", text);
	}

	// 发送的是text
	public static void renderText(HttpServletResponse response, String text) {
		render(response, "text/plain;charset=UTF-8", text);
	}
}
