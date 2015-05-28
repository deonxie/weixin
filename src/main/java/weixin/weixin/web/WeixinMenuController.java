package weixin.weixin.web;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import weixin.weixin.entity.WeixinMenu;
import weixin.weixin.service.WeixinMenuService;
import weixin.weixin.util.WeixinUtil;

/**
 * 微信自定义按钮
 * @author jlusoft
 *
 */
@Controller
@RequestMapping("/weixin/menu/")
public class WeixinMenuController extends GenericController{
	@Autowired
	private WeixinMenuService ser;
	
	@RequiresPermissions("wxmenu:view")
	@RequestMapping("")
	public String list(Model model){
		model.addAttribute("entitys", ser.findFirstLevelMenu());
		return "weixin/menus";
	}
	
	@RequiresPermissions("wxmenu:edit")
	@RequestMapping(value="save",method= RequestMethod.POST)
	public String save(@ModelAttribute("initmenu")WeixinMenu menu, Model model){
		if(menu.getParent()!=null && (menu.getParent().getId()==null ||menu.getParent().getId()<=0))
			menu.setParent(null);
		if(ser.saveMenu(menu))
			model.addAttribute("message", "操作成功");
		else
			model.addAttribute("message", "操作失败，一级菜单不能超过3个，二级菜单不能超过5个");
		model.addAttribute("entitys", ser.findFirstLevelMenu());
		return "redirect:/weixin/menu/";
	}
	
	@RequiresPermissions("wxmenu:edit")
	@RequestMapping("delete/{id}")
	public String delete(@PathVariable("id")long id){
		ser.delete(id);
		return "redirect:/weixin/menu/";
	}
	
	@RequiresPermissions("wxmenu:edit")
	@RequestMapping("async")
	@ResponseBody
	public boolean asyncWeixin(){
		return ser.asyncWeixin();
	}
	
	@RequiresPermissions("wxmenu:edit")
	@RequestMapping("asyncdelete")
	@ResponseBody
	public boolean asyncDeleteWeixin(){
		try {
			return 0==WeixinUtil.deleteMenu();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@ModelAttribute("initmenu")
	public WeixinMenu initBinid(@RequestParam(value="id",defaultValue="0")long id){
		if(id>0)
			return ser.get(id);
		return new WeixinMenu();
	}
}
