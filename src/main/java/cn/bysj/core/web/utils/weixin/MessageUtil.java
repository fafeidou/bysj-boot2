package cn.bysj.core.web.utils.weixin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;

import cn.bysj.core.pojo.weixin.TextMessage;

public class MessageUtil {
	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_EVENT = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLICK = "CLICK";
	public static final String MESSAGE_VIEW = "VIEW";

	/**
	 * xml转换成map集合
	 * 
	 * @Description: TODO
	 * @param @param
	 *            request
	 * @param @return
	 * @param @throws
	 *            IOException
	 * @return Map<String,String>
	 * @throws DocumentException
	 * @throws @author
	 *             it小祥
	 * @date 2017年2月4日
	 */
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		InputStream ins = request.getInputStream();

		Document doc = reader.read(ins);

		Element root = doc.getRootElement();

		List<Element> list = root.elements();
		for (Element e : list) {
			map.put(e.getName(), e.getText());
		}
		ins.close();
		return map;
	}

	public static String textMessageToXml(TextMessage textMessage) {
		XStream xStream = new XStream();
		xStream.alias("xml", textMessage.getClass());
		return xStream.toXML(textMessage);

	}

	public static String ininText(String toUserName, String fromUserName, String content) {
		TextMessage text = new TextMessage();
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		text.setMsgType(MessageUtil.MESSAGE_TEXT);
		text.setContent("你发送的内容是:" + content);
		text.setCreateTime(new Date().getTime() + "");
		return textMessageToXml(text);
	}

	/**
	 * 主菜单
	 * 
	 * @Description: TODO
	 * @param @return
	 * @return String
	 * @throws @author
	 *             it小祥
	 * @date 2017年2月4日
	 */
	public static String menuText() {
		StringBuffer sb = new StringBuffer();
		sb.append("亲爱的，我在这里等你好久了~\n");
		sb.append("--------------------------------------\n");
		sb.append("使用查看论文状态请回复:'查看'");
		return sb.toString();
	}
}
