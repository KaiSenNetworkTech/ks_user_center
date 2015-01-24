package com.kaisen.usercenter.dao;

import java.util.List;

import com.kaisen.usercenter.domain.UserInfoDO;
import com.kaisen.usercenter.query.UserInfoQuery;

public interface UserInfoDAO {
	Integer insertUserInfo(UserInfoDO userInfoDO);

	Integer updateUserInfo(UserInfoDO userInfoDO);

	List<UserInfoDO> queryUserInfo(UserInfoQuery userInfoQuery);

	Integer deleteUserInfo(UserInfoDO userInfoDO);
}
