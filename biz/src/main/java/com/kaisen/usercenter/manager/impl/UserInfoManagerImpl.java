package com.kaisen.usercenter.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.kaisen.usercenter.domain.UserInfoDO;
import com.kaisen.usercenter.dao.UserInfoDAO;
import com.kaisen.usercenter.manager.IUserInfoManager;
import com.kaisen.usercenter.query.UserInfoQuery;

@Service
public class UserInfoManagerImpl implements IUserInfoManager {
	private static Logger logger = LoggerFactory
			.getLogger(UserInfoManagerImpl.class);

	@Resource
	private UserInfoDAO userInfoDAO;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertUserInfo(UserInfoDO userInfoDO) {
		logger.debug("插入用户信息{}", userInfoDO);
		Integer num = userInfoDAO.insertUserInfo(userInfoDO);
		logger.debug("插入用户记录成功,共{}条", num);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateUserInfo(UserInfoDO userInfoDO) {
		logger.debug("更新用户信{}", userInfoDO);
		Integer num = userInfoDAO.updateUserInfo(userInfoDO);
		logger.debug("更新用户记录成功,共{}条", num);
	}

	@Override
	public List<UserInfoDO> queryUserInfo(UserInfoQuery userInfoQuery) {
		return userInfoDAO.queryUserInfo(userInfoQuery);
	}

	@Override
	public UserInfoDO queryUserInfoByMobilePhoneNo(String mobilePhoneNo) {
		UserInfoQuery userInfoQuery = new UserInfoQuery();
		userInfoQuery.setMobilePhoneNo(mobilePhoneNo);
		List<UserInfoDO> userInfoDOs = userInfoDAO.queryUserInfo(userInfoQuery);

		return CollectionUtils.isEmpty(userInfoDOs) ? null : userInfoDOs.get(0);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteUserInfo(Long id) {
		logger.debug("删除用户信息,用户ID={}", id);
		UserInfoDO userInfoDO = new UserInfoDO();
		userInfoDO.setId(id);
		Integer num = userInfoDAO.deleteUserInfo(userInfoDO);
		logger.debug("删除用户信息成功,共{}条", num);
	}
}
