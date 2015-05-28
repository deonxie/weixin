package weixin.weixin.service;


import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weixin.weixin.entity.WeixinReceiveMsg;
import weixin.weixin.repository.WeixinReceiveMsgDao;
import weixin.weixin.util.ReplyWeixinMsg;

/**
 * 接收各种类型消息消息
 * @author jlusoft
 *
 */
@Service
@Transactional(readOnly=true)
public class WeixinReceiveMsgService extends GenericService<WeixinReceiveMsg,WeixinReceiveMsgDao>{
	@Autowired
	private WeixinReceiveMsgDao dao;
	/**接收并处理微信端传入的消息*/
	private static ExecutorService service = Executors.newFixedThreadPool(5);
	
	
	public static void initTask(Map<String, String> msg){
		service.execute(new ReplyWeixinMsg(msg));
	}
	
	public void start(){
		service.shutdown();
		service = Executors.newFixedThreadPool(5);
	}
	public void stop(){
		service.shutdown();
	}
}
