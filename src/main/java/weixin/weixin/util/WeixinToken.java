package weixin.weixin.util;

import java.io.IOException;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import weixin.weixin.entity.WeixinConfig;
import weixin.weixin.service.WeixinConfigService;

public class WeixinToken {
	static Logger logger = LoggerFactory.getLogger(WeixinToken.class);
	public final static String baseurl = "https://api.weixin.qq.com/";
	private final static String access_token_key = "access_token_key";
	private final static String access_jsapi_key = "access_jsapi_key";
	private final static String AccessToken = "AccessToken";
	private final static String JsapiToken = "JsapiToken";
	private static CacheManager cacheManager;
	
	static{
		try{
			cacheManager = CacheManager.create(new ClassPathResource("/ehcache/ehcache-weixin.xml").getInputStream());
			cacheManager.addCache(new Cache(access_token_key, 1, false, false, 7100, 7100));
			cacheManager.addCache(new Cache(access_jsapi_key, 1, false, false, 7100, 7100));
		} catch (CacheException e) {
			logger.error("微信网络访问缓存管理器 加载失败",e);
		} catch (IOException e) {
			logger.error("微信网络访问缓存管理器 配置文件加载失败",e);
		}
	}
	
	/**
	 * 获取微信的访问码,使用缓存对象保存，微信会7200秒更新一次验证码，验证码过期需要重新联网获取
	 * @return token
	 */
	public static String getAccessToken(){
		String token = null;
		Cache cache = cacheManager.getCache(access_token_key);
		Element element = cache.get(AccessToken);
		if(null == element){
			token = accessToken();
			cache.put(new Element(AccessToken, token));
			logger.info("微信：AccessToken:{}",token);
			return token;
		}
		if(null != element.getObjectValue()){
			token = element.getObjectValue().toString();
		}
		return token;
	}
	/**
	 * 获取jsapi token ，7100秒更新一次
	 * @return
	 */
	public static String getJsapiToken(){
		String jsapiToken = null;
		Cache cache = cacheManager.getCache(access_jsapi_key);
		Element element = cache.get(JsapiToken);
		if(null == element){
			String token = getAccessToken();
			jsapiToken = jsapiToken(token);
			cache.put(new Element(JsapiToken, jsapiToken));
			logger.info("微信：AccessToken:{}，jsapiToken:{}",token,jsapiToken);
			return jsapiToken;
		}
		if(null != element.getObjectValue()){
			jsapiToken = element.getObjectValue().toString();
		}
		return jsapiToken;
	}
	
	private static String accessToken(){
		WeixinConfig config = WeixinConfigService.sysConfig();
		String token = "";
		if(config == null)
			return token;
		CoreHttpClient http = new CoreHttpClient(baseurl);
		String result = http.get("cgi-bin/token?grant_type=client_credential&appid="+config.getAppid()
				+"&secret="+config.getSecret(),null);
		try {
			JSONObject json = new JSONObject(result);
			token = json.getString("access_token");
		} catch (JSONException e) {
			logger.error("获取access token异常,{}",e);
		}
		return token;		
	}
	
	private static String jsapiToken(String token){
		WeixinConfig config = WeixinConfigService.sysConfig();
		String jsapiToken = "";
		if(config == null || WeixinConfig.NO_JSAPI_TOKEN == config.getJsapiToken())
			return jsapiToken;
		CoreHttpClient http = new CoreHttpClient(baseurl);
		String result = http.get("cgi-bin/ticket/getticket?access_token="+token+ "&type=jsapi",null);
		try {
			JSONObject json = new JSONObject(result);
			jsapiToken = json.getString("ticket");
		} catch (JSONException e) {
			logger.error("获取jsapi token异常,{}",e);
		}
		return jsapiToken;
	}
}
