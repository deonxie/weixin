package weixin.weixin.service;


import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import weixin.weixin.entity.WeixinMenu;
import weixin.weixin.repository.WeixinMenuDao;
import weixin.weixin.util.WeixinUtil;

/**
 * 微信自定义按钮
 * @author jlusoft
 *
 */
@Service
@Transactional(readOnly=true)
public class WeixinMenuService extends GenericService<WeixinMenu,WeixinMenuDao>{
	@Autowired
	private WeixinMenuDao dao;

	public List<WeixinMenu> findFirstLevelMenu() {
		return dao.findFirstLevelMenu();
	}
	
	@Transactional(readOnly=false)
	public boolean saveMenu(WeixinMenu entity) {
		if(passValidate(entity)){
			super.save(entity);
			return true;
		}
		return false;
	}
	
	private boolean passValidate(WeixinMenu entity){
		List<WeixinMenu> first = dao.findFirstLevelMenu();
		if(first == null)
			return true;
		if(entity.getParent()==null || entity.getParent().getId()==null 
				|| entity.getParent().getId()<=0){
			boolean exist = false;
			for(WeixinMenu f: first){
				if(f.getId()==entity.getId())
					exist = true;
			}
			if(exist)
				return first.size()<4;
			else
				return first.size()<3;
		}
		for(WeixinMenu wm: first){
			if(wm.getId() == entity.getParent().getId()){
				if(wm.getChild() == null)
					return true;
				boolean exist = false;
				for(WeixinMenu second: wm.getChild()){
					if(second.getId()==entity.getId())
						exist = true;
				}
				if(exist)
					return first.size()<6;
				else
					return first.size()<5;
			}
		}
		return false;
	}

	public boolean asyncWeixin() {
		List<WeixinMenu> first = dao.findFirstLevelMenu();
		if(first ==null)
			return false;
		Map<String,Object> json = Maps.newHashMap();
		json.put("button", parseMenu(first));
		try {
			return 0 == WeixinUtil.createMenu(json);
		} catch (JSONException e) {
			logger.error("创建自定义菜单失败，",e);
		}
		return false;
	}
	
	private List<Map<String, Object>> parseMenu(List<WeixinMenu> list){
		if(list ==null)
			return null;
		List<Map<String, Object>> menus = Lists.newArrayList();
		Map<String, Object> items;
		for(WeixinMenu wm : list){
			if("click".equals(wm.getType())){
				items = Maps.newHashMap();
				items.put("name", wm.getName());
				items.put("type", "click");
				items.put("key", StringUtils.trim(wm.getKey()));
				List<Map<String, Object>> sublist = parseMenu(wm.getChild());
				if(sublist!=null && sublist.size()>0)
					items.put("sub_button", sublist);
				menus.add(items);
				continue;
			}
			if("view".equals(wm.getType())){
				items = Maps.newHashMap();
				items.put("name", wm.getName());
				items.put("type", "view");
				items.put("url", wm.getKey());
				List<Map<String, Object>> sublist = parseMenu(wm.getChild());
				if(sublist !=null && sublist.size()>0)
					items.put("sub_button", sublist);
				menus.add(items);
				continue;
			}
		}
		return menus;
	}
}
