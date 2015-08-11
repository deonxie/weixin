package weixin.weixin.entity.business;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import weixin.weixin.entity.IdEntity;

@Entity
@Table(name="weixin_inforecord")
public class InfoRecord extends IdEntity{
	private Long studentId;
	private String icdNum;//身份证号
	private String inspectNum;//考生号
//	考生号	姓名	身份证号	录取院系	录取专业	收件人	联系电话	邮寄地址	邮编
	private String receiveName;//收件人
	private String telNum;//联系电话
	private String address;//联系地址
	private String postcode;//邮编
	private String oldReceiveName;//收件人
	private String oldTelNum;//联系电话
	private String oldAddress;//联系地址
	private String oldPostcode;//邮编
	private Date createDate;
	
	
	
	/**
	 * @return the icdNum
	 */
	public String getIcdNum() {
		return icdNum;
	}
	/**
	 * @param icdNum the icdNum to set
	 */
	public void setIcdNum(String icdNum) {
		this.icdNum = icdNum;
	}
	/**
	 * @return the inspectNum
	 */
	public String getInspectNum() {
		return inspectNum;
	}
	/**
	 * @param inspectNum the inspectNum to set
	 */
	public void setInspectNum(String inspectNum) {
		this.inspectNum = inspectNum;
	}
	/**
	 * @return the receive
	 */
	public String getReceiveName() {
		return receiveName;
	}
	/**
	 * @param receive the receive to set
	 */
	public void setReceiveName(String receive) {
		this.receiveName = receive;
	}
	/**
	 * @return the postcode
	 */
	public String getPostcode() {
		return postcode;
	}
	/**
	 * @param postcode the postcode to set
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	/**
	 * @return the createDate
	 */
	@Column(updatable=false)
	@Temporal(TemporalType.DATE)
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
	 * @return the studentId
	 */
	public Long getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	/**
	 * @return the telNum
	 */
	public String getTelNum() {
		return telNum;
	}
	/**
	 * @param telNum the telNum to set
	 */
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}
	/**
	 * @return the address
	 */
	@Column(columnDefinition="text")
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the oldReceiveName
	 */
	public String getOldReceiveName() {
		return oldReceiveName;
	}
	/**
	 * @param oldReceiveName the oldReceiveName to set
	 */
	public void setOldReceiveName(String oldReceiveName) {
		this.oldReceiveName = oldReceiveName;
	}
	/**
	 * @return the oldTelNum
	 */
	public String getOldTelNum() {
		return oldTelNum;
	}
	/**
	 * @param oldTelNum the oldTelNum to set
	 */
	public void setOldTelNum(String oldTelNum) {
		this.oldTelNum = oldTelNum;
	}
	/**
	 * @return the oldeAddress
	 */
	@Column(columnDefinition="text")
	public String getOldAddress() {
		return oldAddress;
	}
	/**
	 * @param oldeAddress the oldeAddress to set
	 */
	public void setOldAddress(String oldAddress) {
		this.oldAddress = oldAddress;
	}
	/**
	 * @return the oldPostcode
	 */
	public String getOldPostcode() {
		return oldPostcode;
	}
	/**
	 * @param oldPostcode the oldPostcode to set
	 */
	public void setOldPostcode(String oldPostcode) {
		this.oldPostcode = oldPostcode;
	}
	
}
