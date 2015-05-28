package weixin.weixin.util;

import java.io.File;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class WeixinUtil {
	static Logger logger = LoggerFactory.getLogger(WeixinUtil.class);
	private static CoreHttpClient http;
	/***
	 * 上传图片到永久素材
	 * @param imgpath
	 * @return
	 */
	public static String uploadImg(String imgpath){
			HttpClient httpClient = new HttpClient();
			PostMethod postMethod = new PostMethod("https://api.weixin.qq.com/cgi-bin/material/add_material?access_token="
					+ WeixinToken.getAccessToken());
			postMethod.addRequestHeader("Connection", "Keep-Alive");
			postMethod.addRequestHeader("Cache-Control", "no-cache");
			postMethod.addRequestHeader("content-type", "multipart/form-data");
			
		try {
			File file = new File(imgpath);
			postMethod.addRequestHeader("Content-Disposition", "form-data; name=\"media\"; filename=\""
					+file.getName()+"\"; filelength=\""+ file.getFreeSpace()+"\"");
			FilePart filepart = new FilePart("media", file,"image/jpeg", "UTF-8");
			Part[] parts = new Part[] { filepart };
			MultipartRequestEntity entity = new MultipartRequestEntity(parts,postMethod.getParams());
			postMethod.setRequestEntity(entity);
			
			int status = httpClient.executeMethod(postMethod); 
			if(status==200){
				String responseContent = postMethod.getResponseBodyAsString();
				JSONObject json = new JSONObject(responseContent);
				return json.getString("media_id");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 上传图文消息到微信端
	 * @param params
	 * @return
	 * @throws JSONException
	 */
	public static String uploadPictureMsg(Map<String ,Object> msgs) throws JSONException{
		if(http ==null)
			http = new CoreHttpClient(WeixinToken.baseurl);

		String url = "cgi-bin/material/add_news?access_token="+WeixinToken.getAccessToken();
		String param = null;
		if(msgs!=null)
			param = new JSONObject(msgs).toString();
		
		JSONObject json = new JSONObject(http.postJson(url, param));
		return json.getString("media_id");
	}
	
	/**
	 * 推送公众消息到用户
	 * @param map
	 * @return 返回推送成功的msgid
	 * @throws JSONException 
	 */
	public static String pushPublicMsg(Map<String ,Object> param) throws JSONException{
		if(http ==null)
			http = new CoreHttpClient(WeixinToken.baseurl);
		String url = "cgi-bin/message/mass/sendall?access_token="+WeixinToken.getAccessToken();
		JSONObject json = new JSONObject(http.postJson(url, new JSONObject(param).toString()));
		return json.getString("msg_id");
	}
	
	/**
	 * 删除所有自定义菜单
	 * @throws JSONException 
	 */
	public static int deleteMenu() throws JSONException{
		if(http ==null)
			http = new CoreHttpClient(WeixinToken.baseurl);
		String result = http.get("cgi-bin/menu/delete?access_token="+WeixinToken.getAccessToken(), null);
		return new JSONObject(result).getInt("errcode");
	}
	/**
	 * 创建自定义菜单
	 * @param param
	 * @return
	 * @throws JSONException 
	 */
	public static int createMenu(Map<String,Object> param) throws JSONException{
		deleteMenu();
		System.out.println(new JSONObject(param).toString());
		String result = http.postJson("cgi-bin/menu/create?access_token="+
				WeixinToken.getAccessToken(), new JSONObject(param).toString());
		return new JSONObject(result).getInt("errcode");
	}

	public static void sendText(Map<String, Object> json) {
		if(http ==null)
			http = new CoreHttpClient(WeixinToken.baseurl);
		String url = "cgi-bin/message/custom/send?access_token="+WeixinToken.getAccessToken();
		String result = http.postJson(url, new JSONObject(json).toString());
		logger.info("发送消息：{}",result);
	}
}
