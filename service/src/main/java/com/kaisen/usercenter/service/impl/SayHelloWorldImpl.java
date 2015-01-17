package com.kaisen.usercenter.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kaisen.usercenter.service.ISayHelloWorld;

@Service(version = "1.0.0")
public class SayHelloWorldImpl implements ISayHelloWorld {
	@Override
	public String sayHelloWorld() {
		return "Hello World!";
	}
}
