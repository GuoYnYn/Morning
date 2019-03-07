package com.mec.morning.core;

import java.lang.reflect.Method;

// 拦截器的适配器
public class IntercepterAdaper extends MecIntercepter{
	
	public IntercepterAdaper() {
	}
	
	public IntercepterAdaper(Class<?> klass, Method method) {
		super(klass, method);
	}

	@Override
	public boolean before(Object object) {
		return true;
	}

	@Override
	public Object after(Object object) {
		return object;
	}

	@Override
	public void doException(Throwable e) {		
	}

}
