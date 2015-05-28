package weixin.weixin.data;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.collect.Maps;

import weixin.weixin.util.CoreHttpClient;
import weixin.weixin.util.SystemProperties;
import weixin.weixin.util.WeixinToken;

public class WeixinTest {

	private static CoreHttpClient http;
	private static String token = "_BCjXKeRVqMUMcTO-gJuc6Qq1YA1jGCRSdArHs8FNK0Xjy5ySw_SwfsZFnOrOBmTiCWZhXBOyBeqbJa1Cq8OUsse4L9v3Ugy4UccLN06pws";

	public static String uploadImg(String imgpath) {
		try {
			org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
			PostMethod postMethod = new PostMethod("https://api.weixin.qq.com/cgi-bin/material/add_material?access_token="
					+ token);
			File file = new File(imgpath);
			
			postMethod.addRequestHeader("Connection", "Keep-Alive");
			postMethod.addRequestHeader("Cache-Control", "no-cache");
			postMethod.addRequestHeader("content-type", "multipart/form-data");
			postMethod.addRequestHeader("Content-Disposition", "form-data; name=\"media\"; filename=\""
					+file.getName()+"\"; filelength=\""+ file.getFreeSpace()+"\"");
//			String type = file.toURI().toURL().openConnection().getContentType();
//			System.out.println(type);
			FilePart filepart = new FilePart("media", file,
//					"content/video"
					"image/jpeg"
					, "UTF-8");
			
			Part[] parts = new Part[] { filepart };
			
			MultipartRequestEntity entity = new MultipartRequestEntity(parts,postMethod.getParams());
			postMethod.setRequestEntity(entity);
			
			int status = httpClient.executeMethod(postMethod); // 执行POST方法
			System.out.println("state:"+status);
			String responseContent = postMethod.getResponseBodyAsString();
			System.out.println("response:"+responseContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String sendIMgTextMsg(){
		  Map<String,Object> msg = Maps.newHashMap();
		  Map<String,Object> filter = Maps.newHashMap();
		  filter.put("is_to_all", false);
		  filter.put("group_id", 105);
		  msg.put("filter", filter);
		  Map<String,Object> mpnews = Maps.newHashMap();
		  mpnews.put("media_id", "yHSDPLrkJ0VaeZKOITA8XYdBF6VQJCMWaQmIXQFEtGc");
		  msg.put("mpnews", mpnews);
		  msg.put("msgtype", "mpnews");
		  if(http ==null)
				http = new CoreHttpClient(WeixinToken.baseurl);
			String url = "cgi-bin/message/mass/sendall?access_token="+token;
			String result= http.postJson(url, new JSONObject(msg).toString());
		return result;
	}
	public static void main(String[] args) {
//		uploadImg("/Users/jlusoft/Documents/workspace/weixin/src/main/webapp/tmp/4d81372d-9e44-460d-84a3-3c8129a7cdbf.jpeg");
//		uploadImg("/Users/jlusoft/Desktop/test.png");//yHSDPLrkJ0VaeZKOITA8XYjnwHDiUqPEOdrR4vHqkvE
//		uploadImg("/Users/jlusoft/Desktop/扇扇子.mp4");
//		uploadMedia(token,"video","/Users/jlusoft/Desktop/扇扇子.mp4");
		//{"type":"video","media_id":"rmyY83ES1dlqlCIZ2nwkth6Exg15PoQ7Cd23qVhuTECvIh1XmlWd0pAn1uRGLl2X","created_at":1432137158}
		//yong jiu
//		uploadMedia(token,"image","/Users/jlusoft/Desktop/test.png");
//		sendIMgTextMsg();
		try {
		JSONObject json = new JSONObject("{\"errcode\":0,\"errmsg\":\"invalid button name size\"}");
			System.out.println(json.getString("errcode"));
			System.out.println(json.getInt("errcode"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	   * 上传媒体文件
	   * @param accessToken 接口访问凭证
	   * @param type 媒体文件类型，分别有图片（image）、语音（voice）、视频（video），普通文件(file)
	   * @param media form-data中媒体文件标识，有filename、filelength、content-type等信息
	   * @param mediaFileUrl 媒体文件的url
	   * 上传的媒体文件限制
	  * 图片（image）:1MB，支持JPG格式
	  * 语音（voice）：2MB，播放长度不超过60s，支持AMR格式
	  * 视频（video）：10MB，支持MP4格式
	  * 普通文件（file）：10MB
	   * */
	  public static String uploadMedia(String accessToken, String type, String file) {
	    // 拼装请求地址 ling shi ;media/upload;
	    String uploadMediaUrl = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token="
	    		+accessToken+ "&type="+type;

	    // 定义数据分隔符
	    String boundary = "------------7da2e536604c8";
	    try {
	      URL uploadUrl = new URL(uploadMediaUrl);
	      HttpURLConnection uploadConn = (HttpURLConnection) uploadUrl.openConnection();
	      uploadConn.setDoOutput(true);
	      uploadConn.setDoInput(true);
	      uploadConn.setRequestMethod("POST");
	      // 设置请求头Content-Type
	      uploadConn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
	      // 获取媒体文件上传的输出流（往微信服务器写数据）
	      OutputStream outputStream = uploadConn.getOutputStream();

	      File f = new File(file);
	      f.toURI().toURL().openConnection().getContentType();
	      URLConnection meidaConn = f.toURI().toURL().openConnection();
	     

	      // 从请求头中获取内容类型
	      String contentType = meidaConn.getHeaderField("Content-Type");
	      // 根据内容类型判断文件扩展名
	      // 请求体开始
	      outputStream.write(("--" + boundary + "\r\n").getBytes());
	      outputStream.write(("Content-Disposition: form-data; name=\"media\"; filename=\""+f.getName()+"\"\r\n").getBytes());
	      outputStream.write(String.format("Content-Type: %s\r\n\r\n", contentType).getBytes());

	      // 获取媒体文件的输入流（读取文件）
	      BufferedInputStream bis = new BufferedInputStream(meidaConn.getInputStream());
	      byte[] buf = new byte[8096];
	      int size = 0;
	      outputStream.write("media:".getBytes());
	      while ((size = bis.read(buf)) != -1) {
	        // 将媒体文件写到输出流（往微信服务器写数据）
	        outputStream.write(buf, 0, size);
	      }
	      // 请求体结束
	      outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
	      outputStream.close();
	      bis.close();

	      // 获取媒体文件上传的输入流（从微信服务器读数据）
	      InputStream inputStream = uploadConn.getInputStream();
	      InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
	      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	      StringBuffer buffer = new StringBuffer();
	      String str = null;
	      while ((str = bufferedReader.readLine()) != null) {
	        buffer.append(str);
	      }
	      bufferedReader.close();
	      inputStreamReader.close();
	      // 释放资源
	      inputStream.close();
	      inputStream = null;
	      uploadConn.disconnect();

	      System.out.println(buffer.toString());
	  
	    } catch (Exception e) {
	      String error = String.format("上传媒体文件失败：%s", e);
	      System.out.println(error);
	    }
	    return null;
	  }
}
