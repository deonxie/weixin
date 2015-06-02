package weixin.weixin.web;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import weixin.weixin.entity.IndexItem;
import weixin.weixin.service.IndexItemService;
import cn.gd.thinkjoy.modules.web.Servlets;
import cn.gd.thinkjoy.modules.web.pojo.PageRequest;

@Controller
@RequestMapping("/weixin/indexitem/")
public class IndexItemController extends GenericController {
	@Autowired
	IndexItemService ser;
	
	@RequestMapping("")
	public String list(PageRequest pagerequest,HttpServletRequest request,
			Model model){
		Map<String, Object> params = Servlets.getParametersStartingWith(request, SEARCH);
		pagerequest.setOrderBy("type,index");
		pagerequest.setOrderDir("asc,asc");
		Page<IndexItem> page = ser.search(params, pagerequest);
		model.addAttribute(PAGE, page);
		model.addAttribute(PAGEREQUEST, pagerequest);
		return "weixin/indexitemList";
	}
	
	@RequestMapping("update/{id}")
	public String update(@PathVariable("id")long id,Model model ){
		model.addAttribute(ENTITY, ser.get(id));
		return "weixin/indexitemForm";
	}
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(@ModelAttribute("indexitem")IndexItem entity,Model model,
			HttpServletRequest request){
		
		if(StringUtils.isNotBlank(entity.getTempImg())){
			int index = StringUtils.indexOf(entity.getTempImg(), "/images/indexItem/example/");
			if(index>=0){
				entity.setImgPath(entity.getTempImg().substring(index));
			}else{
				String root = request.getServletContext().getRealPath("");
				try {
					FileUtils.moveFile(new File(root+"/tmp/"+entity.getTempImg()), 
							new File(root+"/static/images/indexItem/"+entity.getTempImg()));
					entity.setImgPath("/images/indexItem/"+entity.getTempImg());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		ser.save(entity);
		return REDIRECT+"/weixin/indexitem/";
	}
	
	@RequestMapping("delete/{id}")
	public String delete(@PathVariable("id")long id,Model model ){
		ser.delete(id);
		return REDIRECT+"/weixin/indexitem/";
	}
	
	@ModelAttribute("indexitem")
	public IndexItem init(@RequestParam(value="id",defaultValue="0")long id){
		return ser.get(id);
	}
}
