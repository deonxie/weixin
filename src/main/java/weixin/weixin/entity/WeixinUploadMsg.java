package weixin.weixin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 上传图片文本消息到微信服务器
 * @author jlusoft
 *
 */
@Entity
@Table(name="weixin_uploadmsg")
public class WeixinUploadMsg extends IdEntity{
	 private String thumb_media_id;//图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得
	 private String author;//图文消息的作者
	 private String title;//图文消息的标题
	 private String content_source_url;//在图文消息页面点击“阅读原文”后的页面
	 private String content;//图文消息页面的内容，支持HTML标签
	 private String digest;//图文消息的描述
	 private int show_cover_pic;//是否显示封面，1为显示，0为不显示
	 private Date createTime;
	 private String picPath;//图片本地路径
	 
	/**
	 * @return the thumb_media_id
	 */
	public String getThumb_media_id() {
		return thumb_media_id;
	}
	/**
	 * @param thumb_media_id the thumb_media_id to set
	 */
	public void setThumb_media_id(String thumb_media_id) {
		this.thumb_media_id = thumb_media_id;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return the title
	 */
	@Column(columnDefinition="text")
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the content_source_url
	 */
	public String getContent_source_url() {
		return content_source_url;
	}
	/**
	 * @param content_source_url the content_source_url to set
	 */
	public void setContent_source_url(String content_source_url) {
		this.content_source_url = content_source_url;
	}
	/**
	 * @return the content
	 */
	@Column(columnDefinition="text")
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the digest
	 */
	@Column(columnDefinition="text")
	public String getDigest() {
		return digest;
	}
	/**
	 * @param digest the digest to set
	 */
	public void setDigest(String digest) {
		this.digest = digest;
	}
	/**
	 * @return the show_cover_pic
	 */
	public int getShow_cover_pic() {
		return show_cover_pic;
	}
	/**
	 * @param show_cover_pic the show_cover_pic to set
	 */
	public void setShow_cover_pic(int show_cover_pic) {
		this.show_cover_pic = show_cover_pic;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the picPath
	 */
	public String getPicPath() {
		return picPath;
	}
	/**
	 * @param picPath the picPath to set
	 */
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	
}
