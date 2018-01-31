package com.slince.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException {

	private static final long serialVersionUID = -1038970869377303442L;

	public ValidateCodeException(String msg) {
		super(msg);
	}

}
