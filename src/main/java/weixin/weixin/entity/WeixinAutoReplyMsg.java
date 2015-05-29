package weixin.weixin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 微信自动回复消息
 * @author jlusoft
 *
 */
@Entity
@Table(name="weixin_autoreplymsg")
public class WeixinAutoReplyMsg extends IdEntity{
	private String key;//关键字
	private int option;
	private String regular;
	private String content;
	private String picUrl;
	private String url;
	private Date createDate;
	private String tmpPic;
	
	public static final int STARIT_WITH = 1;
	public static final int END_WITH = 2;
	public static final int CONTAIN = 3;
	public static final int EQUALS = 4;
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
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
	 * @return the regular
	 */
	public String getRegular() {
		return regular;
	}
	/**
	 * @param regular the regular to set
	 */
	public void setRegular(String regular) {
		this.regular = regular;
	}
	/**
	 * @return the option
	 */
	public int getOption() {
		return option;
	}
	/**
	 * @param option the option to set
	 */
	public void setOption(int option) {
		this.option = option;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
		
	public boolean checkMsg(String str){
		if(str==null)
			return false;
		switch (option) {
		case STARIT_WITH:
			return str.startsWith(key);
		case END_WITH:
			return str.endsWith(key);
		case CONTAIN:
			return str.contains(key);
		default:
			return str.equals(key);
		}
	}
	/**
	 * @return the tmpPic
	 */
	@Transient
	public String getTmpPic() {
		return tmpPic;
	}
	/**
	 * @param tmpPic the tmpPic to set
	 */
	public void setTmpPic(String tmpPic) {
		this.tmpPic = tmpPic;
	}
	
}
