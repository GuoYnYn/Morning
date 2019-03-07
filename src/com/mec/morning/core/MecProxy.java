package com.mec.morning.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MecProxy {
	private Object proxy;	// 代理对象
	private Object object;	// 原对象
	private List<MecIntercepter> intercepterList; // 拦截器链
	
	MecProxy() {
		intercepterList = new ArrayList<>();
	}

	@SuppressWarnings("unchecked")
	<T> T getProxy() {
		return (T) proxy;
	}

	<T> MecProxy setProxy(T proxy) {
		this.proxy = proxy;
		return this;
	}

	Object getObject() {
		return object;
	}

	void setObject(Object object) {
		this.object = object;
	}
	
	void addIntercepter(MecIntercepter inter) throws Exception{
		if (intercepterList.contains(inter)) {
			String name = inter.getClass().getName();
			throw new IntercepterAlreadyExistException("拦截器" + name + "已存在");
		}
		intercepterList.add(inter);
	}
	
	void removeIntercepter(MecIntercepter inter) {
		if (!intercepterList.contains(inter)) {
			return;
		}
		intercepterList.remove(inter);
	}
	
	boolean doBefore(Method method, Object[] args) {
		for (MecIntercepter inter : intercepterList) {
			if (!inter.getMethod().equals(method)) {
				continue;
			}
			if (inter.before(args) == false) {
				return false;
			}
		}
		return true;
	}

	Object doAfter(Method method, Object result) {
		for (MecIntercepter inter : intercepterList) {
			if (!inter.getMethod().equals(method)) {
				continue;
			}
			result = inter.after(inter);
		}
		return result;
	}
	
	void doDealException(Method method, Throwable e) {
		for (MecIntercepter inter : intercepterList) {
			if (!inter.getMethod().equals(method)) {
				continue;
			}
			inter.doException(e);
		}
	}
	
}
