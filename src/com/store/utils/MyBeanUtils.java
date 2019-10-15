package com.store.utils;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

public class MyBeanUtils {

	public static void populate(Object obj, Map<String, String[]> map) {
		try {
			// ����BeanUtils���ַ���"1992-3-3"��user�����setBithday();�������ݲ���������,�ֶ���BeanUtilsע��һ��ʱ������ת����
			// 1_����ʱ�����͵�ת����
			DateConverter dt = new DateConverter();
			// 2_����ת���ĸ�ʽ
			dt.setPattern("yyyy-MM-dd");
			// 3_ע��ת����
			ConvertUtils.register(dt, java.util.Date.class);
			
			BeanUtils.populate(obj, map);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public static<T> T  populate(Class<T> clazz, Map<String, String[]> map) {
		try {
			
			T obj=clazz.newInstance();
			
			// ����BeanUtils���ַ���"1992-3-3"��user�����setBithday();�������ݲ���������,�ֶ���BeanUtilsע��һ��ʱ������ת����
			// 1_����ʱ�����͵�ת����
			DateConverter dt = new DateConverter();
			// 2_����ת���ĸ�ʽ
			dt.setPattern("yyyy-MM-dd");
			// 3_ע��ת����
			ConvertUtils.register(dt, java.util.Date.class);
			
			BeanUtils.populate(obj, map);
			
			return obj;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}		
	}
	
}
