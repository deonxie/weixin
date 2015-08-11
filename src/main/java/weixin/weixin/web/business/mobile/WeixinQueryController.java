package weixin.weixin.web.business.mobile;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import weixin.weixin.entity.business.InfoRecord;
import weixin.weixin.entity.business.StudenInfo;
import weixin.weixin.service.business.InfoRecordService;
import weixin.weixin.service.business.StudenInfoService;
import weixin.weixin.web.GenericController;

@Controller
@RequestMapping("/business/weixin/query/")
public class WeixinQueryController extends GenericController{
	@Autowired
	StudenInfoService ser;
	@Autowired
	InfoRecordService infoRecordser;
	
	@RequestMapping("")
	public String query(){
		return "weixin/mobile/queryForm";
	}
	
	@RequestMapping(value="passquery",method= RequestMethod.POST)
	public String passQuery(@RequestParam("key")String key,
			@RequestParam("name")String icdNum,Model model){
		StudenInfo stu =ser.findInspectNumAndIcdNum(key,icdNum);
		if(stu == null){
			model.addAttribute("message", "查询不到您的信息！");
			return "weixin/mobile/queryForm";
		}
		model.addAttribute("entity", stu);
		return "weixin/mobile/studentInfo";
	}
	
	@RequestMapping(value="updateicd",method= RequestMethod.POST)
	public String save(@RequestParam("key")String key,@RequestParam("name")String icdNum,
			@RequestParam("telNum")String telNum,@RequestParam("address")String address,
			@RequestParam("receive")String receive,Model model){
		StudenInfo stu = ser.findInspectNumAndIcdNum(key,icdNum);
		if(null != stu){//记录旧值
			InfoRecord record = new InfoRecord();
			record.setIcdNum(stu.getIcdNum());
			record.setInspectNum(stu.getInspectNum());
			record.setOldAddress(stu.getAddress());
			record.setOldReceiveName(stu.getReceiveName());
			record.setOldTelNum(stu.getTelNum());

			stu.setReceiveName(receive);
			stu.setTelNum(telNum);
			stu.setAddress(address);
			stu.setStatus(StudenInfo.STATUS_FAIL);
			stu.setIsupdate(StudenInfo.IS_UPDATE);
			ser.save(stu);
//			记录修改后的新值
			record.setAddress(address);
			record.setReceiveName(receive);
			record.setTelNum(telNum);
			record.setCreateDate(new Date());
			infoRecordser.save(record);
			
			model.addAttribute("message", "修改成功！");
		}
		model.addAttribute("entity", stu);
		return "weixin/mobile/studentInfo";
	}
	
	@RequestMapping(value="register",method=RequestMethod.GET)
	public String register(){
		return "weixin/mobile/registerForm";
	}
	
	@RequestMapping(value="register",method=RequestMethod.POST)
	public String register(@RequestParam("name")String icdNum,
			@RequestParam("key")String key,Model model){
		StudenInfo stu =ser.findInspectNumAndIcdNum(key,icdNum);
		String message = "";
		if(null != stu){
			if(StudenInfo.REGISTERED == stu.getRegister()){
				stu.setRegisterDate(new Date());
				stu.setRegister(StudenInfo.REGISTERED);
				ser.save(stu);
				message = "预报到成功";
			}else{
				message = "已报到";
			}
		}else
			message =  "请检查输入消息！";
		model.addAttribute("message",message);
		return "weixin/mobile/registerForm";
	}
}
