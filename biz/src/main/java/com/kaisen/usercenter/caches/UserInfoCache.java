package com.kaisen.usercenter.caches;

import javax.annotation.Resource;

import net.rubyeye.xmemcached.MemcachedClient;

import org.springframework.stereotype.Component;

import com.kaisen.common.cache.BaseCache;
import com.kaisen.usercenter.domain.UserInfoDO;

@Component
public class UserInfoCache extends BaseCache<UserInfoDO> {
	@Resource
	private MemcachedClient memcachedClient;

	// exp time
	private static final int EXPIRATION_TIME = 60 * 60 * 24;

	@Override
	protected MemcachedClient getMemcachedClient() {
		return memcachedClient;
	}

	@Override
	protected int getExpirationTime() {
		return EXPIRATION_TIME;
	}
}
