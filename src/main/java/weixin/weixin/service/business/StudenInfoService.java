package weixin.weixin.service.business;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

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
		Map<String, StudenInfo> mapStus = mapStudens();
		StudenInfo stu;
		for(List<Map<String, Object>> list : data.values()){
			if(list !=null){
				for(Map<String, Object> map :list){
					stu = parseMap(map,mapStus);
					if(stu != null)
						students.add(stu);
				}
			}
		}
		dao.save(students);
	}
	private Map<String, StudenInfo> mapStudens(){
		Map<String, StudenInfo> map = Maps.newHashMap();
		Iterable<StudenInfo> students = dao.findAll();
		if(students!=null){
			for(StudenInfo stu : students)
				map.put(stu.getIcdNum()+stu.getInspectNum(), stu);
		}
		return map;
	}
	
	private StudenInfo parseMap(Map<String, Object> map,Map<String,StudenInfo> mapStu){
		if(map ==null)
			return null;
		String icd = setDefault(map.get("身份证号"),"");
		String inspect = setDefault(map.get("考生号"),"");
		
		StudenInfo stu = mapStu.get(icd+inspect);
		if(stu!=null){//学生已经更新信息的不能修改
			if(stu.getIsupdate()== StudenInfo.IS_UPDATE){
				logger.info("身份证号:{}考生号:{},学员已修改不能覆盖",icd,inspect);
				return null;
			}
			logger.info("覆盖身份证号:{}考生号:{}",icd,inspect);
		}else{
			stu = new StudenInfo();
			stu.setCreateDate(new Date());
		}
//		考生号	姓名	身份证号	录取院系	录取专业	收件人	联系电话	邮寄地址	邮编
		stu.setName(setDefault(map.get("考生姓名"),null));
		stu.setIcdNum(icd);
		stu.setInspectNum(inspect);
		stu.setType(setDefault(map.get("录取院系"),null));
		stu.setMajor(setDefault(map.get("录取专业"),null));
		stu.setReceiveName(setDefault(map.get("收件人"), null));
		stu.setTelNum(setDefault(map.get("联系电话"),null));
		stu.setAddress(setDefault(map.get("地址"),null));
		stu.setPostcode(setDefault(map.get("邮编"),null));
		stu.setStatus(StudenInfo.STATUS_PASS);
		
		return stu;
	}
	
	private final String setDefault(Object obj,String def){
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
	
	@Transactional(readOnly=false)
	public void deleteIds(List<Long> ids) {
		dao.deleteIds(ids);
	}

	public List<Map<String, String>> exportExcelData(int status) {
		Iterable<StudenInfo> iter = dao.findAllByStatus(status);
		List<Map<String, String>> list = Lists.newArrayList();
		if(list != null){
			Map<String, String> map;
			for(StudenInfo stu : iter){
				map = Maps.newHashMap();
				map.put("name", stu.getName());//考生姓名
				map.put("icdNum", stu.getIcdNum());//身份证号
				map.put("inspectNum", stu.getInspectNum());//考生号
				map.put("type", stu.getType());//录取院系
				map.put("major", stu.getMajor());//录取专业
				map.put("receiveName", stu.getReceiveName());//收件人
				map.put("telNum", stu.getTelNum());//联系电话
				map.put("address", stu.getAddress());//地址
				map.put("postcode", stu.getPostcode());//邮编
				list.add(map);
			}
		}
		return list;
	}
	
	public String[][] exportExcelTitles() {
		String[][] fieldTitle = new String[][]{
			new String[]{"name", "考生姓名"},
			new String[]{"icdNum", "身份证号"},
			new String[]{"inspectNum", "考生号"},
			new String[]{"type", "录取院系"},
			new String[]{"major", "录取专业"},
			new String[]{"receiveName", "收件人"},
			new String[]{"telNum", "联系电话"},
			new String[]{"address", "地址"},
			new String[]{"postcode", "邮编"}
		};
		return fieldTitle;
	}

	@Transactional(readOnly=false)
	public void deleteAll(int isupdate) {
		dao.deleteAllPass(isupdate);
	}
}
