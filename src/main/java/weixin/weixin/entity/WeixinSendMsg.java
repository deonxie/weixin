package weixin.weixin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 微信发送消息
 * @author jlusoft
 *
 */
@Entity
@Table(name="weixin_sendmsg")
public class WeixinSendMsg extends IdEntity{
	private String toUserName;//接收方帐号（收到的OpenID）
	private String fromUserName;//开发者微信号
	private long createTime;//消息创建时间 （整型）
	private String msgType;//text,image,voice,video,news
	private String content;//文本内容
	private String mediaId;//通过上传多媒体文件，得到的id。 
	private String title;//视频消息的标题
	private String description;//视频消息的描述 
	private String musicURL;//音乐链接
	private String HQMusicUrl;//高质量音乐链接，WIFI环境优先使用该链接播放音乐
	private String thumbMediaId;//缩略图的媒体id，通过上传多媒体文件，得到的id 
	private String picUrl;//图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
	private String url;//点击图文消息跳转链接 
	/**
	 * @return the toUserName
	 */
	public String getToUserName() {
		return toUserName;
	}
	/**
	 * @param toUserName the toUserName to set
	 */
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	/**
	 * @return the fromUserName
	 */
	public String getFromUserName() {
		return fromUserName;
	}
	/**
	 * @param fromUserName the fromUserName to set
	 */
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	/**
	 * @return the createTime
	 */
	public long getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the msgType
	 */
	public String getMsgType() {
		return msgType;
	}
	/**
	 * @param msgType the msgType to set
	 */
	public void setMsgType(String msgType) {
		this.msgType = msgType;
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
	 * @return the mediaId
	 */
	public String getMediaId() {
		return mediaId;
	}
	/**
	 * @param mediaId the mediaId to set
	 */
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
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
	 * @return the description
	 */
	@Column(columnDefinition="text")
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the musicURL
	 */
	public String getMusicURL() {
		return musicURL;
	}
	/**
	 * @param musicURL the musicURL to set
	 */
	public void setMusicURL(String musicURL) {
		this.musicURL = musicURL;
	}
	/**
	 * @return the hQMusicUrl
	 */
	public String getHQMusicUrl() {
		return HQMusicUrl;
	}
	/**
	 * @param hQMusicUrl the hQMusicUrl to set
	 */
	public void setHQMusicUrl(String hQMusicUrl) {
		HQMusicUrl = hQMusicUrl;
	}
	/**
	 * @return the thumbMediaId
	 */
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	/**
	 * @param thumbMediaId the thumbMediaId to set
	 */
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	/**
	 * @return the picUrl
	 */
	public String getPicUrl() {
		return picUrl;
	}
	/**
	 * @param picUrl the picUrl to set
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
}
