package weixin.weixin.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 微信配置参数
 * @author jlusoft
 *
 */
@Entity
@Table(name="weixin_config")
public class WeixinConfig extends IdEntity{
	private String token;//MJ8DUkfVDc3s167e
	private String appid;//= wxdb9710f998e23b63-test
	private String secret;// a196855bfa03aae6fceca55055152488
	private int jsapiToken;
	
	/**不启用微信jsapi token*/
	public static final int NO_JSAPI_TOKEN = 0;
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the appid
	 */
	public String getAppid() {
		return appid;
	}

	/**
	 * @param appid the appid to set
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}

	/**
	 * @return the secret
	 */
	public String getSecret() {
		return secret;
	}

	/**
	 * @param secret the secret to set
	 */
	public void setSecret(String secret) {
		this.secret = secret;
	}

	/**
	 * @return the jsapiToken
	 */
	public int getJsapiToken() {
		return jsapiToken;
	}

	/**
	 * @param jsapiToken the jsapiToken to set
	 */
	public void setJsapiToken(int jsapiToken) {
		this.jsapiToken = jsapiToken;
	}
	
}
