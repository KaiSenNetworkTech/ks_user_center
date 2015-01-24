package com.kaisen.usercenter.service;

import java.util.List;

import com.kaisen.usercenter.domain.UserInfoDO;
import com.kaisen.common.result.CallServiceResult;

public interface IUserService {
	CallServiceResult<Void> register(UserInfoDO userInfoDO);

	CallServiceResult<UserInfoDO> authentication(UserInfoDO userInfoDO);

	CallServiceResult<Void> updateUserInfo(UserInfoDO userInfoDO);

	CallServiceResult<List<UserInfoDO>> queryUserInfoByName(String userName);

	CallServiceResult<Void> deleteUserInfoById(Long id);
}
