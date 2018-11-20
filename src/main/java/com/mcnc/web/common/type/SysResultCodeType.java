package com.mcnc.web.common.type;

public enum SysResultCodeType implements ResultCodeType {
	
	SUCCESS			("0000", "Success"),
	ERROR_400		("ERR_400", "Invalid request"),
	ERROR_401		("ERR_401", "Unauthorized request"),
	ERROR_404		("ERR_404", "Request not found"),
	ERROR_500		("ERR_500", "Internal server error");

	final String value;
	final String description;

	private SysResultCodeType(String value, String description) {
		this.value = value;
		this.description = description;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String getDescription() {
		return description;
	}
}
