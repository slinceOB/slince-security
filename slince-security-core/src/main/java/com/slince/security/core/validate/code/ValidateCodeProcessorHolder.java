package com.slince.security.core.validate.code;

import java.util.Map;

import org.hibernate.validator.internal.xml.ValidatedByType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateCodeProcessorHolder {
	
	@Autowired
	private Map<String, ValidateCodeProcessor> validateCodeProcessors;
	
	public ValidateCodeProcessor findValidateCodeProcesser(ValidateCodeType type) {
		return findValidateCodeProcessor(type.toString().toLowerCase());
	}

	public ValidateCodeProcessor findValidateCodeProcessor(String type) {
		String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
		ValidateCodeProcessor processor = validateCodeProcessors.get(name);
		if (processor == null) {
			throw new ValidateCodeException("验证码处理器" + name + "不存在");
		}
		return processor;
	}

}
