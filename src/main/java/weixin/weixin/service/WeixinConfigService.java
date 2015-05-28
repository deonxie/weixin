package weixin.weixin.service;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weixin.weixin.entity.WeixinConfig;
import weixin.weixin.repository.WeixinConfigDao;

/**
 * 微信配置参数
 * @author jlusoft
 *
 */
@Service
@Transactional(readOnly=true)
public class WeixinConfigService extends GenericService<WeixinConfig,WeixinConfigDao>{
	@Autowired
	private WeixinConfigDao dao;
	private static WeixinConfig config;
	
	@PostConstruct
	public void init(){
		Iterable<WeixinConfig> list = dao.findAll();
		if(list !=null && list.iterator().hasNext()){
			config = list.iterator().next();
		}
	}
	
	public static WeixinConfig sysConfig(){
		return config;
	}
	
	@Override
	@Transactional(readOnly=false)
	public void save(WeixinConfig entity) {
		Iterable<WeixinConfig> list = dao.findAll();
		if(list !=null && list.iterator().hasNext()){
			WeixinConfig con = list.iterator().next();
			con.setAppid(entity.getAppid());
			con.setSecret(entity.getSecret());
			con.setToken(entity.getToken());
			super.save(con);
			entity = con;
			config = con;
		}else{
			super.save(entity);
			config = entity;
		}
	}
}
