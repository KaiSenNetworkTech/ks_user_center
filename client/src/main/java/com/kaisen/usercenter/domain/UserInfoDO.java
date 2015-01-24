package com.kaisen.usercenter.domain;

import java.util.Date;

public class UserInfoDO extends BaseDO {
	private static final long serialVersionUID = 3941285400739591760L;

	private String userName;
	private String mobilePhoneNo;
	private String password;

	private Integer status;
	public static Integer STATUS_NORMAL = Integer.valueOf(1); // 正常

	private String avatarsUrl;
	private Date dateOfBirth;
	private Integer sex;
	private String regionCode;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobilePhoneNo() {
		return mobilePhoneNo;
	}

	public void setMobilePhoneNo(String mobilePhoneNo) {
		this.mobilePhoneNo = mobilePhoneNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAvatarsUrl() {
		return avatarsUrl;
	}

	public void setAvatarsUrl(String avatarsUrl) {
		this.avatarsUrl = avatarsUrl;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
}
