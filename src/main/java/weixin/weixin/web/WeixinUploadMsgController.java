package weixin.weixin.web;


import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.gd.thinkjoy.modules.web.Servlets;
import cn.gd.thinkjoy.modules.web.pojo.PageRequest;
import weixin.weixin.entity.WeixinUploadMsg;
import weixin.weixin.service.WeixinUploadMsgService;

/**
 * 上传图片文本消息到微信服务器
 * @author jlusoft
 *
 */
@Controller
@RequestMapping("/uploadmsg/")
public class WeixinUploadMsgController extends GenericController{
	@Autowired
	private WeixinUploadMsgService ser;
	 
	@RequiresPermissions("wxmsg:view")
    @RequestMapping(value = "")
    public String list(PageRequest pageRequest, Model model,
                       ServletRequest request) {
        Page<WeixinUploadMsg> msgs = ser.search(Servlets.getParametersStartingWith(request, "search_"), pageRequest);
        model.addAttribute("page", msgs);
        model.addAttribute("pageRequest", pageRequest);
        return "weixin/uploadmsgList";
	}
    
	@RequiresPermissions("wxmsg:edit")
    @RequestMapping(value="update/{id}")
    public String update(@PathVariable("id")long id,Model model){
    	model.addAttribute("entity", ser.get(id));
    	return "weixin/uploadmsgForm";
    }
    
	@RequiresPermissions("wxmsg:edit")
    @RequestMapping(value="save",method={RequestMethod.POST})
    public String save(@ModelAttribute("bindData")WeixinUploadMsg msg){
    	ser.save(msg);
    	return "redirect:/uploadmsg/";
    }
    
	@RequiresPermissions("wxmsg:edit")
    @RequestMapping(value="delete/{id}")
    public String delete(@PathVariable("id")long id){
    	ser.delete(id);
    	return "redirect:/uploadmsg/";
    }
    
	@RequiresPermissions("wxmsg:view")
    @RequestMapping(value = "msgItemList")
    public String sendMsgToUser(PageRequest pageRequest, Model model,
                       ServletRequest request) {
        Page<WeixinUploadMsg> msgs = ser.search(Servlets.getParametersStartingWith(request, "search_"), pageRequest);
        model.addAttribute("page", msgs);
        model.addAttribute("pageRequest", pageRequest);
        return "weixin/msgItemList";
	}
    
	@RequiresPermissions("wxmsg:view")
    @RequestMapping("chooseMsgItem")
    public String chooseMsgItems(){
    	return "weixin/chooseMsgItem";
    }
    
    @RequiresPermissions("wxmsg:edit")
    @RequestMapping("sendMsgItem")
    public String sendMsgItems(@RequestParam("cover")long cover,
    		@RequestParam("items")String items,Model model,HttpServletRequest request){
    	String[] idsStr = StringUtils.split(items, ",");
    	Long[] ids = new Long[idsStr.length];
    	int index = 0;
    	ids[index++] = cover;
    	for(int i=0;i<idsStr.length;i++){
    		Long id = Long.parseLong(idsStr[i]);
    		if(cover == id) 
    			continue;
    		ids[index++] = id;
    	}
    	String root = request.getServletContext().getRealPath("/");
    	if(!ser.pushMsgToWeixin(root,ids))
    		model.addAttribute("message","消息发送失败");
    	else
    		model.addAttribute("message","发送成功");
    	return "weixin/chooseMsgItem";
    }
    
    @ModelAttribute("bindData")
	public WeixinUploadMsg initBind(@RequestParam(value="id",defaultValue="0")long id){
		if(id>0)
			return ser.get(id);
		WeixinUploadMsg tmp = new WeixinUploadMsg();
		tmp.setCreateTime(new Date());
		return tmp;
	}
}
