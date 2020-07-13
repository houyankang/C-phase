package com.abyg.spring.demo1;

import org.junit.Test;

public class SpringDemo1 {
	
	@Test
	public void test1(){
		UserDao userDao = new UserDaoImpl();
		userDao.add();
		userDao.update();
	}
	
	@Test
	public void test2(){
		// 被代理对象
		UserDao userDao = new UserDaoImpl();
		// 创建代理对象的时候传入被代理对象.
		UserDao proxy = new JDKProxy(userDao).createProxy();
		proxy.add();
		proxy.update();
	}
}
