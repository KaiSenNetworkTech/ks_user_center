package com.kaisen.usercenter.query;

import com.kaisen.usercenter.domain.UserInfoDO;
import com.kaisen.common.page.Page;

public class UserInfoQuery extends Page<UserInfoDO> {
	private static final long serialVersionUID = 3923785921722251025L;

	private Long id;

	private String userName;

	private String mobilePhoneNo;

	private String password;

	private Integer status = UserInfoDO.STATUS_NORMAL;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
}
