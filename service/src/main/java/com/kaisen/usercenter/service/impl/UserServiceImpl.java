package com.kaisen.usercenter.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
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

		if (userInfoDO == null
				|| StringUtils
						.isAnyBlank(userInfoDO.getUserName(),
								userInfoDO.getMobilePhoneNo(),
								userInfoDO.getPassword())) {
			result.setResultEnum(ResultEnum.REGISTER_PARAMS_ERROR);
			return result;
		}

		UserInfoQuery userInfoQuery = new UserInfoQuery();
		userInfoQuery.setMobilePhoneNo(userInfoDO.getMobilePhoneNo());
		List<UserInfoDO> userInfos = userInfoManager.query(userInfoQuery);
		if (CollectionUtils.isNotEmpty(userInfos)) {
			result.setResultEnum(ResultEnum.MOBILE_PHONE_NO_ALREADY_EXISTS);
			return result;
		} else {
			Date current = new Date();
			userInfoDO
					.setPassword(DigestUtils.md5Hex(userInfoDO.getPassword()));
			userInfoDO.setGmtCreate(current);
			userInfoDO.setGmtModify(current);

			try {
				userInfoManager.insert(userInfoDO);
			} catch (Exception e) {
				logger.error("插入用户信息失败", e);
				result.setResultEnum(ResultEnum.DB_ERROR);
				return result;
			}
		}

		return result;
	}

	@Override
	public CallServiceResult<UserInfoDO> authentication(UserInfoDO userInfoDO) {
		CallServiceResult<UserInfoDO> result = new CallServiceResult<UserInfoDO>();

		if (userInfoDO == null
				|| StringUtils.isAnyBlank(userInfoDO.getMobilePhoneNo(),
						userInfoDO.getPassword())) {
			result.setResultEnum(ResultEnum.MOBILE_PHONE_NO_OR_PASSWORD_NULL);
			return result;
		}

		UserInfoQuery userInfoQuery = new UserInfoQuery();
		userInfoQuery.setMobilePhoneNo(userInfoDO.getMobilePhoneNo());
		List<UserInfoDO> userInfos = userInfoManager.query(userInfoQuery);
		if (CollectionUtils.isEmpty(userInfos)) {
			result.setResultEnum(ResultEnum.MOBILE_PHONE_NO_NOT_EXISTS);
			return result;
		}

		UserInfoDO userInfoDOFromDB = userInfos.get(0);
		if (userInfoDO.getPassword().equals(userInfoDOFromDB.getPassword())) {
			result.setReturnObject(userInfoDOFromDB);
			return result;
		} else {
			result.setResultEnum(ResultEnum.MOBILE_PHONE_NO_OR_PASSWORD_ERROR);
			return result;
		}
	}

	@Override
	public CallServiceResult<Void> updateUserInfo(UserInfoDO userInfoDO) {
		CallServiceResult<Void> result = new CallServiceResult<Void>();
		if (userInfoDO == null || userInfoDO.getId() == null
				|| userInfoDO.getId() < 1) {
			result.setResultEnum(ResultEnum.PARAMS_ERROR);
			return result;
		}

		try {
			userInfoManager.update(userInfoDO);
		} catch (Exception e) {
			logger.error("更新用户信息失败", e);
			result.setResultEnum(ResultEnum.DB_ERROR);
			return result;
		}

		return result;
	}

	@Override
	public CallServiceResult<List<UserInfoDO>> queryUserInfo(
			UserInfoQuery userInfoQuery) {
		CallServiceResult<List<UserInfoDO>> result = new CallServiceResult<List<UserInfoDO>>();
		if (userInfoQuery == null) {
			result.setResultEnum(ResultEnum.PARAMS_ERROR);
			return result;
		}

		List<UserInfoDO> userInfoDOs = userInfoManager.query(userInfoQuery);
		result.setReturnObject(userInfoDOs);
		return result;
	}

	@Override
	public CallServiceResult<Void> deleteUserInfo(UserInfoDO userInfoDO) {
		CallServiceResult<Void> result = new CallServiceResult<Void>();
		if (userInfoDO == null) {
			result.setResultEnum(ResultEnum.PARAMS_ERROR);
			return result;
		}

		try {
			userInfoManager.delete(userInfoDO);
		} catch (Exception e) {
			logger.error("删除用户失败,id=" + userInfoDO.getId(), e);
			result.setResultEnum(ResultEnum.DB_ERROR);
			return result;
		}

		return result;
	}
}
