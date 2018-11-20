package com.mcnc.web.common;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.mcnc.web.common.BeanUtils;
import com.mcnc.web.common.JsonField;

public class JsonFieldArgumentResolver implements HandlerMethodArgumentResolver {
	
	private ObjectMapper objectMapper;
	
	public JsonFieldArgumentResolver() {
		this(new ObjectMapper());
	}

	public JsonFieldArgumentResolver(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(JsonField.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
		ServletServerHttpRequest httpRequest = new ServletServerHttpRequest(servletRequest);

		Map<String, String> requestData = objectMapper.readValue(httpRequest.getBody(), new TypeReference<HashMap<Object, Object>>() {});

		JsonField annotation = parameter.getParameterAnnotation(JsonField.class);
		String paramName = (annotation != null && !StringUtils.isEmpty(annotation.value()) ? annotation.value() : parameter.getParameterName());

		Object requestParamValue = BeanUtils.getPropertyValue(requestData, paramName);
		
		// calculate parameter value
		
		Object paramValue = null;
		
		Class<?> paramType = parameter.getParameterType();
		Type genericParamType = parameter.getGenericParameterType();
		if (genericParamType instanceof ParameterizedType) {
			// generic type
			Type[] actualTypeArguments = ((ParameterizedType) genericParamType).getActualTypeArguments();
			
			ParameterizedType parameterize = TypeUtils.parameterize(paramType, actualTypeArguments);
			JavaType msgDataJavaType = objectMapper.getTypeFactory().constructFromCanonical(parameterize.getTypeName());
			 
			paramValue = objectMapper.convertValue(requestParamValue, msgDataJavaType);
			
		} else {
			paramValue = objectMapper.convertValue(requestParamValue, paramType);
		}
		
		return paramValue;
	}

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}
}
