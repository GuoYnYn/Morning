package com.mec.morning.core;

import java.lang.reflect.Method;

// 拦截器
public abstract class MecIntercepter {
	private Class<?> klass;
	private Method method;
	
	public MecIntercepter() {
	}
	
	public MecIntercepter(Class<?> klass, Method method) {
		this.klass = klass;
		this.method = method;
	}

	public Class<?> getKlass() {
		return klass;
	}
	
	public void setKlass(Class<?> klass) {
		this.klass = klass;
	}
	
	public Method getMethod() {
		return method;
	}
	
	public void setMethod(Method method) {
		this.method = method;
	}
	
	public abstract boolean before(Object object);
	public abstract Object after(Object object);
	public abstract void doException(Throwable e);
}
