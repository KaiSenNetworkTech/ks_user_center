package com.kaisen.usercenter.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.config.annotation.Service;
import com.kaisen.common.result.CallServiceResult;
import com.kaisen.common.result.ResultEnum;
import com.kaisen.usercenter.domain.UserInfoDO;
import com.kaisen.usercenter.manager.IUserInfoManager;
import com.kaisen.usercenter.query.UserInfoQuery;
import com.kaisen.usercenter.service.IUserService;

@Service(version = "1.0.0")
public class UserServiceImpl implements IUserService {
	private static Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Resource
	private IUserInfoManager userInfoManager;

	@Override
	public CallServiceResult<Void> register(UserInfoDO userInfoDO) {
		CallServiceResult<Void> result = new CallServiceResult<Void>();

		if (StringUtils.isBlank(userInfoDO.getUserName())
				|| StringUtils.isBlank(userInfoDO.getMobilePhoneNo())
				|| StringUtils.isBlank(userInfoDO.getPassword())) {
			result.setResultEnum(ResultEnum.REGISTER_PARAMS_ERROR);
			return result;
		}

		UserInfoDO userInfoDOFromDB = userInfoManager
				.queryUserInfoByMobilePhoneNo(userInfoDO.getMobilePhoneNo());
		if (this.isUserExists(userInfoDOFromDB)) {
			result.setResultEnum(ResultEnum.MOBILE_PHONE_NO_ALREADY_EXISTS);
			return result;
		}

		if (userInfoDOFromDB == null) {
			try {
				Date current = new Date();
				userInfoDO.setPassword(DigestUtils.md5Hex(userInfoDO
						.getPassword()));
				userInfoDO.setGmtCreate(current);
				userInfoDO.setGmtModify(current);
				userInfoManager.insertUserInfo(userInfoDO);
			} catch (Exception e) {
				logger.error("插入用户信息失败", e);
				result.setResultEnum(ResultEnum.DB_ERROR);
				return result;
			}
		}

		result.setResultEnum(ResultEnum.SUCCESS);
		return result;
	}

	@Override
	public CallServiceResult<UserInfoDO> authentication(UserInfoDO userInfoDO) {
		CallServiceResult<UserInfoDO> result = new CallServiceResult<UserInfoDO>();

		if (StringUtils.isBlank(userInfoDO.getMobilePhoneNo())
				|| StringUtils.isBlank(userInfoDO.getPassword())) {
			result.setResultEnum(ResultEnum.MOBILE_PHONE_NO_OR_PASSWORD_NULL);
			return result;
		}

		UserInfoDO userInfoDOFromDB = userInfoManager
				.queryUserInfoByMobilePhoneNo(userInfoDO.getMobilePhoneNo());
		if (!this.isUserExists(userInfoDOFromDB)) {
			result.setResultEnum(ResultEnum.MOBILE_PHONE_NO_NOT_EXISTS);
			return result;
		}

		if (userInfoDO.getPassword().equals(userInfoDOFromDB.getPassword())) {
			result.setReturnObject(userInfoDOFromDB);
			result.setResultEnum(ResultEnum.SUCCESS);
			return result;
		} else {
			result.setResultEnum(ResultEnum.MOBILE_PHONE_NO_OR_PASSWORD_ERROR);
			return result;
		}
	}

	@Override
	public CallServiceResult<Void> updateUserInfo(UserInfoDO userInfoDO) {
		CallServiceResult<Void> result = new CallServiceResult<Void>();
		if (userInfoDO.getId() == null || userInfoDO.getId() < 1) {
			result.setResultEnum(ResultEnum.PARAMS_ERROR);
			return result;
		}

		userInfoDO.setGmtModify(new Date());
		try {
			userInfoManager.updateUserInfo(userInfoDO);
		} catch (Exception e) {
			logger.error("更新用户信息失败", e);
			result.setResultEnum(ResultEnum.DB_ERROR);
			return result;
		}

		result.setResultEnum(ResultEnum.SUCCESS);
		return result;
	}

	@Override
	public CallServiceResult<List<UserInfoDO>> queryUserInfoByName(
			String userName) {
		CallServiceResult<List<UserInfoDO>> result = new CallServiceResult<List<UserInfoDO>>();

		UserInfoQuery userInfoQuery = new UserInfoQuery();
		userInfoQuery.setUserName(userName);
		userInfoQuery.setStatus(UserInfoDO.STATUS_NORMAL);
		List<UserInfoDO> userInfoDOs = userInfoManager
				.queryUserInfo(userInfoQuery);

		result.setReturnObject(userInfoDOs);
		result.setResultEnum(ResultEnum.SUCCESS);
		return result;
	}

	@Override
	public CallServiceResult<Void> deleteUserInfoById(Long id) {
		CallServiceResult<Void> result = new CallServiceResult<Void>();

		try {
			userInfoManager.deleteUserInfo(id);
		} catch (Exception e) {
			logger.error("删除用户失败,id=" + id, e);
			result.setResultEnum(ResultEnum.DB_ERROR);
			return result;
		}

		return result;
	}

	private Boolean isUserExists(UserInfoDO userInfoDOFromDB) {
		return userInfoDOFromDB != null
				&& userInfoDOFromDB.getStatus() == UserInfoDO.STATUS_NORMAL;
	}
}
