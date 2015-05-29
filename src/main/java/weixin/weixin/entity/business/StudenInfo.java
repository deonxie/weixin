package weixin.weixin.entity.business;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import weixin.weixin.entity.IdEntity;

@Entity
@Table(name="weixin_studentinfo")
public class StudenInfo extends IdEntity{
//	考生号	姓名	身份证号	录取院系	录取专业	收件人	联系电话	邮寄地址	邮编
	private String name;//考生姓名
	private String icdNum;//身份证号
	private String inspectNum;//考生号
	private String type;//录取院系
	private String major;//录取专业
	private String receiveName;//收件人
	private String telNum;//联系电话
	private String address;//联系地址
	private String postcode;//邮编
	private String schoolName;//报考学校
	private String schollCode;//报考学校代码
	private int status;//0待审核，1审核通过
	private int register;//0未预报的，1已预报到
	private Date registerDate;
	private String info;//操作记录
	private Date createDate;
	
	/**审核通过*/
	public static final int STATUS_PASS = 1;
	/**待审核*/
	public static final int STATUS_FAIL = 0;
	/**已预报到*/
	public static final int REGISTERED = 1;
	/**未预报的*/
	public static final int REGISTERING = 0;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}
	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
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
	 * @return the schoolName
	 */
	public String getSchoolName() {
		return schoolName;
	}
	/**
	 * @param schoolName the schoolName to set
	 */
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	/**
	 * @return the schollCode
	 */
	public String getSchollCode() {
		return schollCode;
	}
	/**
	 * @param schollCode the schollCode to set
	 */
	public void setSchollCode(String schollCode) {
		this.schollCode = schollCode;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the register
	 */
	public int getRegister() {
		return register;
	}
	/**
	 * @param register the register to set
	 */
	public void setRegister(int register) {
		this.register = register;
	}
	/**
	 * @return the registerDate
	 */
	public Date getRegisterDate() {
		return registerDate;
	}
	/**
	 * @param registerDate the registerDate to set
	 */
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	/**
	 * @return the major
	 */
	public String getMajor() {
		return major;
	}
	/**
	 * @param major the major to set
	 */
	public void setMajor(String major) {
		this.major = major;
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
	
}
