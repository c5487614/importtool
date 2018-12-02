package cch.importTool.disruptor.authication;

public class AuthEvent {
	private String id;
	private String type;
	private String personName;
	private String personIdCardNum;
	private String mobile;
	private String legalPersonName;
	private String legalPersonIdCardNum;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getPersonIdCardNum() {
		return personIdCardNum;
	}
	public void setPersonIdCardNum(String personIdCardNum) {
		this.personIdCardNum = personIdCardNum;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLegalPersonName() {
		return legalPersonName;
	}
	public void setLegalPersonName(String legalPersonName) {
		this.legalPersonName = legalPersonName;
	}
	public String getLegalPersonIdCardNum() {
		return legalPersonIdCardNum;
	}
	public void setLegalPersonIdCardNum(String legalPersonIdCardNum) {
		this.legalPersonIdCardNum = legalPersonIdCardNum;
	}

}
