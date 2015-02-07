package com.kaisen.usercenter.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kaisen.usercenter.caches.UserInfoCache;
import com.kaisen.usercenter.dao.UserInfoDAO;
import com.kaisen.usercenter.domain.UserInfoDO;
import com.kaisen.usercenter.manager.IUserInfoManager;
import com.kaisen.usercenter.query.UserInfoQuery;

@Service
public class UserInfoManagerImpl implements IUserInfoManager {
	private static Logger logger = LoggerFactory
			.getLogger(UserInfoManagerImpl.class);

	@Resource
	private UserInfoDAO userInfoDAO;

	@Resource
	private UserInfoCache userInfoCache;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insert(UserInfoDO userInfoDO) {
		logger.debug("插入用户信息{}", userInfoDO);
		Integer num = userInfoDAO.insert(userInfoDO);
		logger.debug("插入用户记录成功,id={}", num);

		userInfoCache.addWithNoReply(userInfoDO.getMobilePhoneNo(), userInfoDO);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(UserInfoDO userInfoDO) {
		logger.debug("更新用户信息{}", userInfoDO);
		Integer num = userInfoDAO.update(userInfoDO);
		logger.debug("更新用户信息成功,共{}条", num);

		userInfoCache.deleteWithNoReply(userInfoDO.getMobilePhoneNo());
	}

	@Override
	public List<UserInfoDO> query(UserInfoQuery userInfoQuery) {
		return userInfoDAO.query(userInfoQuery);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(UserInfoDO userInfoDO) {
		logger.debug("删除用户信息{}", userInfoDO);
		Integer num = userInfoDAO.delete(userInfoDO);
		logger.debug("删除用户信息成功,共{}条", num);

		userInfoCache.deleteWithNoReply(userInfoDO.getMobilePhoneNo());
	}

	@Override
	public UserInfoDO getUserInfo(String mobilePhoneNo) {
		UserInfoDO userInfoDO = userInfoCache.get(mobilePhoneNo);
		if (userInfoDO == null) {
			UserInfoQuery userInfoQuery = new UserInfoQuery();
			userInfoQuery.setMobilePhoneNo(mobilePhoneNo);
			List<UserInfoDO> userInfos = this.query(userInfoQuery);
			if (CollectionUtils.isNotEmpty(userInfos)) {
				userInfoDO = userInfos.get(0);
				userInfoCache.setWithNoReply(mobilePhoneNo, userInfoDO);
			}
		}

		return userInfoDO;
	}
}
