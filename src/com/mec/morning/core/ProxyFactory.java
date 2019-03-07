package com.mec.morning.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

// 生成这个代理以及这个代理的拦截
public class ProxyFactory {
	private MecProxy mecProxy;
	
	public ProxyFactory() {
	}
	
	MecProxy getMecProxy() {
		return mecProxy;
	}

	<T> T getGCLProxy(Object object, Class<?> klass) {
		T proxy = CGLProxy(object, klass);
		mecProxy = new MecProxy();
		mecProxy.setProxy(proxy);
		mecProxy.setObject(object);
		
		return proxy;
	}
	
	@SuppressWarnings("unchecked")
	private <T> T CGLProxy(Object object, Class<?> klass) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(klass);
		MethodInterceptor methodIntercepter = new MethodInterceptor() {
			
			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
				return doInvoke(object, method, args);
			}
		};
		enhancer.setCallback(methodIntercepter);
		return (T) enhancer.create();
	}
	
	private  Object doInvoke(Object object, Method method, Object[] args) throws Throwable {
		Object result = null;
		if (mecProxy.doBefore(method, args) == false) {
			return null;
		}
		try {
			result = method.invoke(object, args);
			mecProxy.doAfter(method, result);
		} catch (Throwable e) {
			mecProxy.doDealException(method, e);
			throw e;
		}
		return result;
	}
	
	<T> T getGDkProxy(Object object, Class<?> klass) {
		T proxy = JDKProxy(object, klass);
		mecProxy = new MecProxy();
		mecProxy.setProxy(proxy);
		mecProxy.setObject(object);
		
		return proxy;
	}
	
	@SuppressWarnings("unchecked")
	private <T> T JDKProxy(Object object, Class<?> klass) {
		ClassLoader classLoader = klass.getClassLoader();
		Class<?>[] interfaces = klass.getInterfaces();
		
		InvocationHandler invocationHandler = new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				return doInvoke(object, method, args);
			}
		};
		
		return (T) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
	}
	
}
