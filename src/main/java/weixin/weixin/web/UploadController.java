package weixin.weixin.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import weixin.weixin.util.SystemProperties;



@Controller
@RequestMapping("/file/updload/")
public class UploadController {
	private static Logger logger = LoggerFactory.getLogger(UploadController.class);
	public static final String MAX_SIZE_KEY = "system.max.file.upload.size";
	public static final String ACCEPT_TYPE = "system.file.type.accepts";
	
	@RequestMapping(value="",method = RequestMethod.POST,produces={"application/json; charset=UTF-8"})
	@ResponseBody
	public String[] batchNodeTask(@RequestParam(value="file",required=true) CommonsMultipartFile file,
			HttpServletRequest request){
		InputStream input = null;
		FileOutputStream output = null;
		if(null == file ||file.getSize()<=0)
			return new String[]{Boolean.FALSE.toString(),"无上传文件"};
		
		String fileType = file.getFileItem().getName().substring(file.getFileItem().getName().lastIndexOf("."));
		String acceptType = SystemProperties.getInstance().getValue(ACCEPT_TYPE);
		if(!acceptType.toUpperCase().contains(fileType.toUpperCase()+";")){
			return new String[]{Boolean.FALSE.toString(),"上传文件类型不支持."};
		}
		
		long fileSize = file.getSize();
		String size = SystemProperties.getInstance().getValue(MAX_SIZE_KEY);
		int maxSize = null==size?5:Integer.parseInt(size);
		if((fileSize>>20)>maxSize){
			return new String[]{Boolean.FALSE.toString(),"上传文件太大，超过"+maxSize+"M."};
		}
		
		String path = UUID.randomUUID().toString()+fileType;
		try {
			output = new FileOutputStream(getRootPath(request)+path);
			input = file.getInputStream();
			byte[] data = new byte[10240];
			int length = 0;
			while((length = input.read(data))>-1){
				output.write(data, 0, length);
			}
			return new String[]{Boolean.TRUE.toString(),path};
		} catch (IOException e) {
			logger.error("文件保存失败", e);
		}finally{
			if(null != input){
				try {
					input.close();
				} catch (IOException e) {
					logger.error("input文件关闭异常", e);
				}
			}if(null != output){
				try {
					output.close();
				} catch (IOException e) {
					logger.error("output文件关闭异常", e);
				}
			}
		}
		return new String[]{Boolean.FALSE.toString(),"上传文件失败"};
	}
	
	private String getRootPath(HttpServletRequest request){
		return request.getServletContext().getRealPath("/tmp/")+File.separator;
	}
	
	@RequestMapping(value="ckeditor",method = RequestMethod.POST,produces={MediaType.TEXT_HTML_VALUE})
	@ResponseBody
	public String ckeditor(@RequestParam(value="upload",required=true) CommonsMultipartFile file,
			HttpServletRequest request){
		InputStream input = null;
		FileOutputStream output = null;
		if(null == file ||file.getSize()<=0)
			return "无上传文件";
		
		String fileType = file.getFileItem().getName().substring(file.getFileItem().getName().lastIndexOf("."));
		String acceptType = SystemProperties.getInstance().getValue(ACCEPT_TYPE);
		if(!acceptType.toUpperCase().contains(fileType.toUpperCase()+";")){
			return "上传文件类型不支持.只支持"+acceptType;
		}
		
		long fileSize = file.getSize();
		String size = SystemProperties.getInstance().getValue(MAX_SIZE_KEY);
		int maxSize = null==size?5:Integer.parseInt(size);
		if((fileSize>>20)>maxSize){
			return "上传文件太大，超过"+maxSize+"M.";
		}
		
		String path = UUID.randomUUID().toString()+fileType;
		String root = request.getServletContext().getRealPath("/ckeditor/")+File.separator;
		try {
			output = new FileOutputStream(root+path);
			input = file.getInputStream();
			byte[] data = new byte[10240];
			int length = 0;
			while((length = input.read(data))>-1){
				output.write(data, 0, length);
			}
			String callback = request.getParameter("CKEditorFuncNum");
			return "<script type=\"text/javascript\"> window.parent.CKEDITOR.tools.callFunction("
					+ callback + ",'"+request.getContextPath()+"/ckeditor"+File.separator+ path + "','');</script>";
		} catch (IOException e) {
			logger.error("文件保存失败", e);
		}finally{
			if(null != input){
				try {
					input.close();
				} catch (IOException e) {
					logger.error("input文件关闭异常", e);
				}
			}if(null != output){
				try {
					output.close();
				} catch (IOException e) {
					logger.error("output文件关闭异常", e);
				}
			}
		}
		return "上传文件失败";
	}
}
