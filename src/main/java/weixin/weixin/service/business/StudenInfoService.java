package weixin.weixin.service.business;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import weixin.weixin.entity.business.StudenInfo;
import weixin.weixin.repository.business.StudenInfoDao;
import weixin.weixin.service.GenericService;
import weixin.weixin.util.PoiReadExcelUtil;

@Service
@Transactional(readOnly=true)
public class StudenInfoService extends GenericService<StudenInfo,StudenInfoDao>{
	@Autowired
	StudenInfoDao dao;

	@Transactional(readOnly=false)
	public void importExcel(String filepath) {
		Map<String, List<Map<String, Object>>> data =
				PoiReadExcelUtil.readExcelMap(filepath);
		if(data ==null)
			return;
		List<StudenInfo> students = Lists.newArrayList();
		StudenInfo stu;
		for(List<Map<String, Object>> list : data.values()){
			if(list !=null){
				for(Map<String, Object> map :list){
					stu = parseMap(map);
					if(stu != null)
						students.add(stu);
				}
			}
		}
		dao.save(students);
	}
	
	private StudenInfo parseMap(Map<String, Object> map){
		StudenInfo stu = null;
		if(map ==null)
			return stu;
		stu = new StudenInfo();
//		考生号	姓名	身份证号	录取院系	录取专业	收件人	联系电话	邮寄地址	邮编
		stu.setName(setDefault(map.get("考生姓名"),null));
		stu.setIcdNum(setDefault(map.get("身份证号"),null));
		stu.setInspectNum(setDefault(map.get("考生号"),null));
		stu.setType(setDefault(map.get("录取院系"),null));
		stu.setMajor(setDefault(map.get("录取专业"),null));
		stu.setReceiveName(setDefault(map.get("收件人"), null));
		stu.setTelNum(setDefault(map.get("联系电话"),null));
		stu.setAddress(setDefault(map.get("地址"),null));
		stu.setPostcode(setDefault(map.get("邮编"),null));
		stu.setStatus(StudenInfo.STATUS_PASS);
		stu.setCreateDate(new Date());
		return stu;
	}
	
	private String setDefault(Object obj,String def){
		if(obj == null)
			return def;
		return obj.toString();
	}

	public StudenInfo findInspectNumAndIcdNum(String inspectNum,String icdNum) {
		List<StudenInfo> list = dao.findInspectNumAndIcdNum(inspectNum, icdNum);
		if(list !=null && list.size()==1)
			return list.get(0);
		return null;
	}
}
