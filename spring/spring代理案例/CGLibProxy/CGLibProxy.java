package com.abyg.spring.demo2;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * 使用CGLib生成代理对象
 */
public class CGLibProxy implements MethodInterceptor{
	private ProductDaoImpl productDao;

	public CGLibProxy(ProductDaoImpl productDao) {
		super();
		this.productDao = productDao;
	}
	
	public ProductDaoImpl createProxy(){
		// 使用CGLIB生成代理:
		// 1.创建核心类:
		Enhancer enhancer = new Enhancer();
		// 2.为其设置父类:
		enhancer.setSuperclass(productDao.getClass());
		// 3.设置回调:
		enhancer.setCallback(this);
		// 4.创建代理:
		return (ProductDaoImpl) enhancer.create();
	}

	
	public Object intercept(Object proxy, Method method, Object[] args,
			MethodProxy methodProxy) throws Throwable {
		if("add".equals(method.getName())){
			System.out.println("日志记录...");
			Object obj = methodProxy.invokeSuper(proxy, args);
			return obj;
		}
		return methodProxy.invokeSuper(proxy, args);
	}
}
