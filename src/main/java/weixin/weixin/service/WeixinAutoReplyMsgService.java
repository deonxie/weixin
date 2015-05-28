package weixin.weixin.service;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import weixin.weixin.entity.WeixinAutoReplyMsg;
import weixin.weixin.repository.WeixinAutoReplyMsgDao;

/**
 * 微信自动回复消息
 * @author jlusoft
 *
 */
@Service
@Transactional(readOnly=true)
public class WeixinAutoReplyMsgService extends GenericService<WeixinAutoReplyMsg,WeixinAutoReplyMsgDao>{
	@Autowired
	private WeixinAutoReplyMsgDao dao;
	private final static Map<Long,WeixinAutoReplyMsg> maps = Maps.newHashMap();
	
	@PostConstruct
	public void init(){
		List<WeixinAutoReplyMsg> list = getAll();
		if(list == null) return;
		for(WeixinAutoReplyMsg msg : list){
			maps.put(msg.getId(), msg);
		}
	}
	
	@Override
	@Transactional(readOnly=false)
	public void save(WeixinAutoReplyMsg entity){
		super.save(entity);
		maps.put(entity.getId(), entity);
	}
	
	public static List<WeixinAutoReplyMsg> findMacthReply(String value){
		List<WeixinAutoReplyMsg> list = Lists.newArrayList();
		for(WeixinAutoReplyMsg reply : maps.values()){
			if(reply.checkMsg(value))
				list.add(reply);
		}
		return list;
	}
}
