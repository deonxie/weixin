package weixin.weixin.web;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.gd.thinkjoy.modules.web.Servlets;
import cn.gd.thinkjoy.modules.web.pojo.PageRequest;
import weixin.weixin.entity.WeixinAutoReplyMsg;
import weixin.weixin.service.WeixinAutoReplyMsgService;

/**
 * 微信自动回复消息
 * @author jlusoft
 *
 */
@Controller
@RequestMapping("/autoreplymsg/")
public class WeixinAutoReplyMsgController extends GenericController{
	@Autowired
	private WeixinAutoReplyMsgService ser;
	
	@RequestMapping("")
	public String list(PageRequest pagerequest,HttpServletRequest request,
			Model model){
		Map<String, Object> params = Servlets.getParametersStartingWith(request, SEARCH);
		Page<WeixinAutoReplyMsg> page = ser.search(params, pagerequest);
		model.addAttribute(PAGE, page);
		model.addAttribute(PAGEREQUEST, pagerequest);
		return "weixin/autoreplymsgList";
	}
	
	@RequestMapping("update/{id}")
	public String form(@PathVariable("id")long id,Model model){
		model.addAttribute(ENTITY, ser.get(id));
		return "weixin/autoreplymsgForm";
	}
	
	@RequestMapping("save")
	public String save(@ModelAttribute("replaymsg")WeixinAutoReplyMsg msg,Model model){
		
		ser.save(msg);
		return REDIRECT+"/autoreplymsg/";
	}
	
	@RequestMapping("delete/{id}")
	public String delete(@PathVariable("id")long id){
		ser.delete(id);
		return REDIRECT+"/autoreplymsg/";
	}
	
	@ModelAttribute("replaymsg")
	public WeixinAutoReplyMsg init(@RequestParam(value="id",defaultValue="0")long id){
		if(id>0)
			return ser.get(id);
		WeixinAutoReplyMsg msg = new WeixinAutoReplyMsg();
		msg.setCreateDate(new Date());
		return msg;
	}
}
