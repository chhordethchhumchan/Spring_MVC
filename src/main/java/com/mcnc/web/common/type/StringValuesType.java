package com.mcnc.web.common.type;

import java.util.Collection;
import java.util.HashSet;

public class StringValuesType<T extends Enum<T>> extends HashSet<T> {

	private static final long serialVersionUID = 1L;

	public StringValuesType() {
		super();
	}

	public StringValuesType(Collection<? extends T> c) {
		super(c);
	}
}