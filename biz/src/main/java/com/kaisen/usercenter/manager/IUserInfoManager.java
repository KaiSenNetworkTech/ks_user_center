package com.kaisen.usercenter.manager;

import java.util.List;

import com.kaisen.usercenter.domain.UserInfoDO;
import com.kaisen.usercenter.query.UserInfoQuery;

public interface IUserInfoManager {
	void insertUserInfo(UserInfoDO userInfoDO);

	void updateUserInfo(UserInfoDO userInfoDO);

	List<UserInfoDO> queryUserInfo(UserInfoQuery userInfoQuery);

	void deleteUserInfo(Long id);

	UserInfoDO queryUserInfoByMobilePhoneNo(String mobilePhoneNo);
}
