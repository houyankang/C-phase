package com.abyg.spring.demo2;

import org.junit.Test;

public class SpringDemo2 {
	
	@Test
	public void test1(){
		ProductDaoImpl productDao = new ProductDaoImpl();
		productDao.add();
		productDao.update();
	}
	@Test
	public void test2(){
		ProductDaoImpl productDao = new ProductDaoImpl();
		ProductDaoImpl proxy = new CGLibProxy(productDao).createProxy();
		proxy.add();
		proxy.update();
	}
}
