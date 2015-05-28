package weixin.weixin.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import weixin.weixin.entity.WeixinSendMsg;
import weixin.weixin.service.WeixinSendMsgService;

/**
 * 微信发送消息
 * @author jlusoft
 *
 */
@Controller
@RequestMapping("/sendmsg/")
public class WeixinSendMsgController extends GenericController{
	@Autowired
	private WeixinSendMsgService ser;
}
