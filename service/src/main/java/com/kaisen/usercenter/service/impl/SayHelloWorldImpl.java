package com.kaisen.usercenter.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kaisen.usercenter.service.SayHelloWorld;

@Service(version = "1.0.0")
public class SayHelloWorldImpl implements SayHelloWorld {
	@Override
	public String sayHelloWorld() {
		return "Hello World!";
	}
}
