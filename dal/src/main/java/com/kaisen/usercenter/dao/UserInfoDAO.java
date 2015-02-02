package com.kaisen.usercenter.dao;

import java.util.List;

import com.kaisen.usercenter.domain.UserInfoDO;
import com.kaisen.usercenter.query.UserInfoQuery;

public interface UserInfoDAO {
	Integer insert(UserInfoDO userInfoDO);

	Integer update(UserInfoDO userInfoDO);

	List<UserInfoDO> query(UserInfoQuery userInfoQuery);

	Integer delete(UserInfoDO userInfoDO);
}
