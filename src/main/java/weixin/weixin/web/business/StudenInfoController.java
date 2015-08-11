package weixin.weixin.web.business;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.google.common.collect.Lists;

import cn.gd.thinkjoy.modules.web.Servlets;
import cn.gd.thinkjoy.modules.web.pojo.PageRequest;
import weixin.weixin.entity.business.StudenInfo;
import weixin.weixin.service.business.StudenInfoService;
import weixin.weixin.util.PoiWriteExcelUtil;
import weixin.weixin.web.GenericController;

@Controller
@RequestMapping("/business/stu/")
public class StudenInfoController extends GenericController{
	@Autowired
	StudenInfoService ser;
	
	@RequiresPermissions("studen:view")
	@RequestMapping("")
	public String list(PageRequest pageRequest, Model model,
            ServletRequest request){
		Map<String, Object> param = Servlets.getParametersStartingWith(request, "search_");
		param.put("EQ_status", StudenInfo.STATUS_PASS);
		Page<StudenInfo> page = ser.search(param, pageRequest);
		model.addAttribute("page", page);
        model.addAttribute("pageRequest", pageRequest);
        return "business/studentList";
	}
	
	@RequiresPermissions("studen:view")
	@RequestMapping("update/{id}")
	public String update(@PathVariable("id")long id,Model model){
		model.addAttribute("entity", ser.get(id));
		return "business/studentForm";
	}
	
	@RequiresPermissions("studen:edit")
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(@ModelAttribute("student")StudenInfo entity,Model model){
		entity.setStatus(StudenInfo.STATUS_PASS);
		ser.save(entity);
		return REDIRECT+"/business/stu/";
	}
	
	@RequiresPermissions("studen:edit")
	@RequestMapping("delete/{id}")
	public String delete(@PathVariable("id")long id,Model model){
		ser.delete(id);
		return REDIRECT+"/business/stu/";
	}
	
	@RequiresPermissions("studen:edit")
	@RequestMapping("import")
	public String importExcel(@RequestParam("filepath")String filepath,Model model,
			HttpServletRequest request){
		String root = request.getServletContext().getRealPath("/tmp/")+File.separator;
		ser.importExcel(root+filepath);
		return REDIRECT+"/business/stu/";
	}
	
	@RequiresPermissions("studen:edit")
	@RequestMapping("passlist")
	public String passList(PageRequest pageRequest, Model model,
            ServletRequest request){
		Map<String, Object> param = Servlets.getParametersStartingWith(request, "search_");
		param.put("EQ_status", StudenInfo.STATUS_FAIL);
		Page<StudenInfo> page = ser.search(param, pageRequest);
		model.addAttribute("page", page);
        model.addAttribute("pageRequest", pageRequest);
        return "business/studentVerifyList";
	}
	
	@RequiresPermissions("studen:edit")
	@RequestMapping("pass")
	public String passStatus(@RequestParam("id")long id){
		StudenInfo stu = ser.get(id);
		if(stu != null){
			stu.setStatus(StudenInfo.STATUS_PASS);
			ser.save(stu);
		}
		return REDIRECT+"/business/stu/passlist";
	}
	
	@RequiresPermissions("studen:edit")
	@RequestMapping("deleteIds")
	public String deleteIds(@RequestParam("ids")Long[] ids){
		if(ids!=null && ids.length>0){
			List<Long> list = Lists.newArrayList();
			for(Long id : ids)
				list.add(id);
			ser.deleteIds(list);
		}
		return REDIRECT+"/business/stu/";
	}
	
	@RequiresPermissions("studen:edit")
	@RequestMapping("deleteall")
	public String deleteall(){
		ser.deleteAll(StudenInfo.NOT_UPDATE);
		return REDIRECT+"/business/stu/";
	}
	
	@RequiresPermissions("studen:edit")
    @RequestMapping("export")
    public void exportDetail(HttpServletResponse response) {
		PoiWriteExcelUtil.exportExcel(response, "学生录取信息列表", 
				ser.exportExcelTitles(), ser.exportExcelData(StudenInfo.STATUS_PASS));
	}
    
	@RequiresPermissions("studen:edit")
    @RequestMapping("validExport")
    public void exportValidateDetail(HttpServletResponse response) {
		PoiWriteExcelUtil.exportExcel(response, "学生审核信息列表", 
				ser.exportExcelTitles(), ser.exportExcelData(StudenInfo.STATUS_FAIL));
	}
	
	@ModelAttribute("student")
	public StudenInfo initdata(@RequestParam(value="id",defaultValue="0")long id){
		if(id>0)
			return ser.get(id);
		StudenInfo stu = new StudenInfo();
		stu.setCreateDate(new Date());
		return stu;
	}
	
}
