package com.mec.morning.core;

import java.util.HashMap;
import java.util.Map;

public class ProxyBeanFactory {
	private static final Map<String, String> beanNameMap;
	private static final Map<String, MecProxy> beanMap;
	
	static {
		beanNameMap = new HashMap<>();
		beanMap = new HashMap<>();
	}
	
	protected ProxyBeanFactory() {
	}
	
	protected void addBeanName(String beanName, String className) throws Exception{
		String orgName = beanNameMap.get(beanName);
		if (orgName != null) {
			throw new BeanNameAlreadyExistException("bean" + orgName + "重复");
		}
		beanNameMap.put(beanName, className);
	}
	
	protected String getBeanClassName(String beanName) {
		return beanNameMap.get(beanName);
	}
	
	protected MecProxy getMecProxy(String className) {
		return beanMap.get(className);
	}
	
	protected void creatCGLProxy(Object object) {
		cglProxy(object, object.getClass());
	}
	
	protected void creatCGLProxy(Class<?> klass) throws Exception {
		cglProxy(klass.newInstance(), klass);
	}
	
	private void cglProxy(Object object, Class<?> klass) {
		
	}
	
}
