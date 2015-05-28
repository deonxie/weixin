package weixin.weixin.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weixin.weixin.entity.WeixinSendMsg;
import weixin.weixin.repository.WeixinSendMsgDao;

/**
 * 微信发送消息
 * @author jlusoft
 *
 */
@Service
@Transactional(readOnly=true)
public class WeixinSendMsgService extends GenericService<WeixinSendMsg,WeixinSendMsgDao>{
	@Autowired
	private WeixinSendMsgDao dao;
	
	
	
	
}
