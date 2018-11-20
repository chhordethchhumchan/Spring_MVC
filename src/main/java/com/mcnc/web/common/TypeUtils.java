package com.mcnc.web.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.mcnc.web.common.type.MultiValuesType;
import com.mcnc.web.common.type.StringValuesType;
import com.mcnc.web.common.type.Type;

public class TypeUtils {
	
	public static final String STRING_DEFAULT_VALUE 	= "";
	public static final int    NUMBERIC_DEFAULT_VALUE 	= 0;

	public static <T> Object getDefaultValue(Class<?> clazz) {
		Object defaultValue = null;
		if (String.class.isAssignableFrom(clazz) || Type.class.isAssignableFrom(clazz)) {
			defaultValue = STRING_DEFAULT_VALUE;
		} else if (Number.class.isAssignableFrom(clazz)) {
			defaultValue = NUMBERIC_DEFAULT_VALUE;
		}
		return defaultValue;
	}
	
	public static <E extends Enum<E>> String getBinaryMaskString(Class<E> enumClass, MultiValuesType<E> multiValuesType) {
		String bitArrayStr = "";
		
		E[] enumConstants = enumClass.getEnumConstants();
		
		if (multiValuesType != null) {
			String[] bitArray = new String[enumConstants.length];
			
			for (int i = 0; i < enumConstants.length; i++) {
				boolean exist = false;
				for (E value : multiValuesType) {
					if (enumConstants[i] == value) {
						exist = true;
						break;
					}
				}
				bitArray[i] = exist ? "1" : "0";
			}
			
			for (String bit : bitArray) {
				bitArrayStr += bit;
			}
		} else {
			bitArrayStr = StringUtils.leftPad("", enumConstants.length, "0");
		}
		
		return bitArrayStr;
	}
	
	public static <E extends Enum<E>> String getEnumString(Class<E> enumClass, StringValuesType<E> stringValuesType) {
		String enumStr = "";
		
		if( stringValuesType != null ) {
			for (E value : stringValuesType) {
				if( enumStr == "" ) {
					enumStr = value.toString();
				} else {
					enumStr = enumStr + ";" + value;
				}
				
			}
		}
		
		return enumStr;
	}
	
	public static <E extends Enum<E>> MultiValuesType<E> getMultiValuesType(Class<E> enumClass, String binaryMaskString) {
		MultiValuesType<E> multiValuesType = null;
		
		if (binaryMaskString != null) {
			String bitArrayStr = binaryMaskString.toString();
			E[] enumConstants = enumClass.getEnumConstants();
			
			List<E> values = new ArrayList<E>();
			for (int i = 0; i < binaryMaskString.toString().length(); i++) {
				if (bitArrayStr.charAt(i) == '1') {
					values.add(enumConstants[i]);
				}
			}
			multiValuesType = new MultiValuesType<E>(values);
		}
		
		return multiValuesType;
	}
	
	public static <E extends Enum<E>> StringValuesType<E> getStringValuesType(Class<E> enumClass, String enumString) {
		StringValuesType<E> stringValuesType = null;
		
		if (enumString != null) {
			E[] enumConstants = enumClass.getEnumConstants();
			String[] enumList = enumString.split(";");
			List<E> values = new ArrayList<E>();
			
			for (String value : enumList) {
				
				for (E enumConstant : enumConstants) {
					
					if( enumConstant.toString().equals(value) ) {
						values.add( enumConstant );
					}
				}
			}
			
			stringValuesType = new StringValuesType<E>(values);
		}
		
		return stringValuesType;
	}
}
