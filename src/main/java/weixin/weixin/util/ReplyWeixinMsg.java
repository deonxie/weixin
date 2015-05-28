package weixin.weixin.util;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import weixin.weixin.entity.WeixinAutoReplyMsg;
import weixin.weixin.service.WeixinAutoReplyMsgService;

public class ReplyWeixinMsg implements Runnable {
	private Map<String,String> receive;
	
	public ReplyWeixinMsg(Map<String,String> receive){
		this.receive = receive;
	}
	
	@Override
	public void run() {
		String msgType = receive.get(WeixinMsgType.MsgField.MSG_TYPE.getField());
		//处理事件类型
		if(WeixinMsgType.MsgType.EVENT.getType().equals(msgType)){
			String eventType = receive.get(WeixinMsgType.MsgField.EVENT.getField());
			//关注事件
			if(WeixinMsgType.EventType.SUBSCRIBE.getType().equals(eventType)){
				deailReply(WeixinMsgType.EventType.SUBSCRIBE.getType());
			}//点击按钮事件
			else if(WeixinMsgType.EventType.CLICK.getType().equals(eventType)){
				deailReply(receive.get(WeixinMsgType.MsgField.EVENT_KEY.getField()));
			}//点击超链接菜单事件
			else if(WeixinMsgType.EventType.VIEW.getType().equals(eventType)){
				deailReply(receive.get(WeixinMsgType.MsgField.EVENT_KEY.getField()));
			}//上传位置事件
			else if(WeixinMsgType.EventType.LOCATION.getType().equals(eventType)){
				deailReply(WeixinMsgType.EventType.LOCATION.getType());
			}//扫描带参数二维码事件
			else if(WeixinMsgType.EventType.SCAN.getType().equals(eventType)){
				deailReply(receive.get(WeixinMsgType.MsgField.EVENT_KEY.getField()));
			}//取消关注事件
			else if(WeixinMsgType.EventType.UNSUBSCRIBE.getType().equals(eventType)){
				
			}
		}//文本类型消息
		else if(WeixinMsgType.MsgType.TEXT.getType().equals(msgType)){
			deailReply(receive.get(WeixinMsgType.MsgField.CONTENT.getField()));
		}//图片类型消息
		else if(WeixinMsgType.MsgType.IMAGE.getType().equals(msgType)){
			deailReply(WeixinMsgType.MsgType.IMAGE.getType());
		}//语言类型消息
		else if(WeixinMsgType.MsgType.VOICE.getType().equals(msgType)){
			deailReply(WeixinMsgType.MsgType.VOICE.getType());
		}//视频类型消息
		else if(WeixinMsgType.MsgType.VIDEO.getType().equals(msgType)){
			deailReply(WeixinMsgType.MsgType.VIDEO.getType());
		}//上传位置消息
		else if(WeixinMsgType.MsgType.LOCATION.getType().equals(msgType)){
			deailReply(receive.get(WeixinMsgType.MsgField.LABEL.getField()));
		}//链接消息
		else if(WeixinMsgType.MsgType.LINK.getType().equals(msgType)){
			deailReply(receive.get(WeixinMsgType.MsgField.TITLE.getField()));
		}//小视频类型消息
		else if(WeixinMsgType.MsgType.SHORTVIDEO.getType().equals(msgType)){
			deailReply(WeixinMsgType.MsgType.SHORTVIDEO.getType());
		}
	}

	private void deailReply(String key){
		List<WeixinAutoReplyMsg> list = WeixinAutoReplyMsgService.findMacthReply(key);
		for(WeixinAutoReplyMsg reply : list){
			Map<String ,Object> json = Maps.newHashMap();
			json.put(WeixinMsgType.MsgField.TO_USER.getField(), 
					receive.get(WeixinMsgType.MsgField.FROM_USER.getField()));
			json.put(WeixinMsgType.MsgField.FROM_USER.toString(), 
					receive.get(WeixinMsgType.MsgField.TO_USER));
			json.put(WeixinMsgType.MsgField.CREATE_TIME.getField(),
					(int)(new Date().getTime()/1000));
			if(StringUtils.isBlank(reply.getPicUrl())){
				json.put(WeixinMsgType.MsgField.MSG_TYPE.getField(), 
						WeixinMsgType.MsgType.TEXT.getType());
				json.put(WeixinMsgType.MsgField.CONTENT.getField(), reply.getContent());
			}else{
				List<Map<String, String>> items = Lists.newArrayList();
				Map<String, String> item = Maps.newHashMap();;
				item.put("title", reply.getContent());
				item.put("picurl", reply.getPicUrl());
				item.put("url", reply.getUrl());
				items.add(item);
				Map<String, Object> articles = Maps.newHashMap();
				articles.put("articles", items);
				json.put("news", articles);
			}
			WeixinUtil.sendText(json);
		}
	}
}
