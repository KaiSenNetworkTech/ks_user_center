package com.kaisen.usercenter.manager;

import java.util.List;

import com.kaisen.usercenter.domain.UserInfoDO;
import com.kaisen.usercenter.query.UserInfoQuery;

public interface IUserInfoManager {
	void insert(UserInfoDO userInfoDO);

	void update(UserInfoDO userInfoDO);

	List<UserInfoDO> query(UserInfoQuery userInfoQuery);

	void delete(UserInfoDO userInfoDO);

	UserInfoDO getUserInfo(String mobilePhoneNo);
}
