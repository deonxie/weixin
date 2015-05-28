package weixin.weixin.web;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;

import weixin.weixin.entity.WeixinConfig;
import weixin.weixin.service.WeixinConfigService;
import weixin.weixin.service.WeixinReceiveMsgService;
import weixin.weixin.util.SHA1;
import weixin.weixin.util.WeixinMsgType;

/**
 * 接收各种类型消息消息
 * @author jlusoft
 *
 */
@Controller
@RequestMapping("/receivemsg/")
public class WeixinReceiveMsgController extends GenericController{
	static Logger logger = LoggerFactory.getLogger(WeixinReceiveMsgController.class);
	@Autowired
	private WeixinReceiveMsgService ser;
	public static boolean check = false;
	
	@RequestMapping(value="",produces={MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public String receiveMsg(HttpServletRequest request){
		String echostr = request.getParameter("echostr");
		if(!check){
			String signature = request.getParameter("signature");
			String nonce = request.getParameter("nonce");
			String timestamp =	request.getParameter("timestamp");
			WeixinConfig config = WeixinConfigService.sysConfig();
			String token = config ==null?"":config.getToken();
					
			String digest = SHA1.getSHA1(token, timestamp, nonce);
			logger.info("signature="+signature+" echostr="+echostr+" nonce="+nonce
					+" timestamp="+timestamp+" digest="+digest);
			if (digest.equalsIgnoreCase(signature)){
				check =true;
				return echostr;
			}
			logger.error("weixin check fail");
		}
		return receiveMsg(request, echostr);
	}
	
	private final String receiveMsg(HttpServletRequest request,String echostr){
	    InputStream inputStream = null;
		try {
			inputStream = request.getInputStream();
			if(inputStream != null){
				StringBuffer xml = new StringBuffer();
				byte[] data = new byte[1024];
				int read = 0;
				while((read=inputStream.read(data))>-1)
					xml.append(new String(data,0,read,"UTF-8"));
				if(xml.length()>0){
					logger.info("msg:"+xml.toString());
					Map<String, String> mesage = Maps.newHashMap();
					Document doc = DocumentHelper.parseText(xml.toString()); // 将字符串转为XML
					Element root = doc.getRootElement(); // 获取根节点
					@SuppressWarnings("unchecked")
					List<Element> elementList = root.elements(); 
					for (Element e : elementList){
						mesage.put(e.getName(), e.getText());
					}
					WeixinReceiveMsgService.initTask(mesage);
					if(WeixinMsgType.MsgType.TEXT.getType().equals(
							mesage.get(WeixinMsgType.MsgField.MSG_TYPE.getField()))){
						xml = new StringBuffer("<xml><ToUserName><![CDATA[");
						xml.append(mesage.get("FromUserName")).append("]]></ToUserName><FromUserName><![CDATA[");
						xml.append(mesage.get("ToUserName")).append("]]></FromUserName><CreateTime>");
						xml.append(mesage.get("CreateTime")).
						append("</CreateTime><MsgType><![CDATA[transfer_customer_service]]></MsgType></xml>");
						return xml.toString();
					}
				}
			}
		} catch (IOException e) {
			logger.error("系统读取微信推送信息异常", e);
		} catch (DocumentException e) {
			logger.error("xml 解析异常", e);
		}  
		if(inputStream != null){
		    try {
				inputStream.close();
			} catch (IOException e) {
				logger.error("系统读取微信推送信息,关闭输入流异常", e);
			}  
		}
		return echostr;
	}
}
