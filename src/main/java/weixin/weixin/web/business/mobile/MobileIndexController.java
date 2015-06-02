package weixin.weixin.web.business.mobile;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import weixin.weixin.entity.IndexItem;
import weixin.weixin.service.IndexItemService;
import weixin.weixin.web.GenericController;

@Controller
@RequestMapping("/mobile")
public class MobileIndexController extends GenericController {
	@Autowired
	IndexItemService itemSer;
	
	@RequestMapping("")
	public String index(HttpServletRequest request,Model model){
		List<IndexItem> topImgs = itemSer.findByType(IndexItem.type_TopImg);
		List<IndexItem> itemImgs = itemSer.findByType(IndexItem.type_ItemImg);
		model.addAttribute("topImgs", topImgs);
		model.addAttribute("itemImgs",itemImgs);
		return "weixin/mobile/index";
	}
}
