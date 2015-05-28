package weixin.weixin.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import weixin.weixin.entity.WeixinConfig;
import weixin.weixin.service.WeixinConfigService;

/**
 * 微信配置参数
 * @author jlusoft
 *
 */
@Controller
@RequestMapping("/weixin/config/")
public class WeixinConfigController extends GenericController{
	@Autowired
	private WeixinConfigService ser;
	
	@RequiresPermissions("wxconfig:view")
	@RequestMapping("")
	public String cofing(Model model){
		model.addAttribute("entity", WeixinConfigService.sysConfig());
		return "weixin/config";
	}
	
	@RequiresPermissions("wxconfig:edit")
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(@ModelAttribute WeixinConfig entity,Model model){
		WeixinConfig old = WeixinConfigService.sysConfig();
		if(old != null)
			entity.setId(old.getId());
		ser.save(entity);
		model.addAttribute("entity", entity);
		return "weixin/config";
	}
	
	
}
