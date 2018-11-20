package com.mcnc.web.common;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class BeanUtils extends org.springframework.beans.BeanUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(BeanUtils.class);
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	static {
		objectMapper
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
			.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	}
	
	public static TypeFactory typeFactory() {
		return objectMapper.getTypeFactory();
	}
	
	public static String toJson(Object object) throws JsonProcessingException {
		return objectMapper.writeValueAsString(object);
	}
	
	public static <T> T convert(Object source, Class<T> targetClass) {
		return objectMapper.convertValue(source, targetClass);
	}
	
	public static <T> T convert(Object source, TypeReference<?> toValueTypeRef) {
		return objectMapper.convertValue(source, toValueTypeRef);
	}
	
	public static <T> T convert(Object source, JavaType javaType) {
		return objectMapper.convertValue(source, javaType);
	}
	
	public static Object getPropertyValue(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		PropertyUtilsBean util = new PropertyUtilsBean();
		Object value = null;
		try {
			value = util.getNestedProperty(bean, name);
		} catch (Exception e) {
			logger.error("Error accessing property: {} in {}", name, bean, e);
		}
		return value;
	}
}
