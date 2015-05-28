package weixin.weixin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 接收各种类型消息消息
 * @author jlusoft
 *
 */
@Entity
@Table(name="weixin_receivemsg")
public class WeixinReceiveMsg extends IdEntity{
	private String toUserName;//开发者微信号
	private String fromUserName;//发送方帐号（一个OpenID）
	private long createTime;//消息创建时间（整型）
	private String msgType;//text,image,voice,video,shortvideo,location,link,event
	private String content;//文本消息内容
	private String msgId;//消息id，64位整型 
	private String picUrl;//图片链接
	private String mediaId;//图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private String format;//语音格式，如amr，speex等
	private String thumbMediaId;//视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据
	private float latitude;//location_X,地理位置维度
	private float longitude;//location_Y,地理位置经度
	private int scale;//地图缩放大小
	private String label;//地理位置信息
	private String title;//消息标题
	private String description;//消息描述
	private String url;//消息链接
	private String event;//事件类型，VIEW,CLICK,LOCATION,subscribe,SCAN,unsubscribe
	private String eventKey;//CLICK事件KEY值，VIEW设置的跳转URL 
	private float precision;//地理位置精度 
	private String ticket;//二维码的ticket，可用来换取二维码图片
	private String recognition;//语音识别结果，UTF8编码
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
	 * @return the msgId
	 */
	public String getMsgId() {
		return msgId;
	}
	/**
	 * @param msgId the msgId to set
	 */
	public void setMsgId(String msgId) {
		this.msgId = msgId;
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
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}
	/**
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
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
	 * @return the latitude
	 */
	public float getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public float getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the scale
	 */
	public int getScale() {
		return scale;
	}
	/**
	 * @param scale the scale to set
	 */
	public void setScale(int scale) {
		this.scale = scale;
	}
	/**
	 * @return the label
	 */
	@Column(columnDefinition="text")
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
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
	/**
	 * @return the event
	 */
	public String getEvent() {
		return event;
	}
	/**
	 * @param event the event to set
	 */
	public void setEvent(String event) {
		this.event = event;
	}
	/**
	 * @return the eventKey
	 */
	public String getEventKey() {
		return eventKey;
	}
	/**
	 * @param eventKey the eventKey to set
	 */
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
	/**
	 * @return the precision
	 */
	public float getPrecision() {
		return precision;
	}
	/**
	 * @param precision the precision to set
	 */
	public void setPrecision(float precision) {
		this.precision = precision;
	}
	/**
	 * @return the ticket
	 */
	@Column(columnDefinition="text")
	public String getTicket() {
		return ticket;
	}
	/**
	 * @param ticket the ticket to set
	 */
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	/**
	 * @return the recognition
	 */
	@Column(columnDefinition="text")
	public String getRecognition() {
		return recognition;
	}
	/**
	 * @param recognition the recognition to set
	 */
	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}
	
}
