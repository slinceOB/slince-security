package com.slince.security.core.validate.code.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.slince.security.core.validate.code.ValidateCode;
import com.slince.security.core.validate.code.ValidateCodeException;
import com.slince.security.core.validate.code.ValidateCodeGenerator;
import com.slince.security.core.validate.code.ValidateCodeProcessor;
import com.slince.security.core.validate.code.ValidateCodeType;

public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {
	
	/**
	 * session 操作工具类
	 */
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
	/**
	 * 收集系统中所有的 {@link ValidateCodeGenerator} 接口的实现。
	 */
	@Autowired
	private Map<String, ValidateCodeGenerator> validateCodeGenerators;

	@Override
	public void create(ServletWebRequest request) throws Exception {
		C validateCode = generate(request);
		save(request, validateCode);
		send(request, validateCode);
	}

	

	@Override
	public void validate(ServletWebRequest request) {
		ValidateCodeType processorType = getValidateCodeType(request);
		String sessionKey = getSessionKey(request);

		C codeInSession = (C) sessionStrategy.getAttribute(request, sessionKey);

		String codeInRequest;
		try {
			codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
					processorType.getParamNameOnValidate());
		} catch (ServletRequestBindingException e) {
			throw new ValidateCodeException("获取验证码的值失败");
		}

		if (StringUtils.isBlank(codeInRequest)) {
			throw new ValidateCodeException(processorType + "验证码的值不能为空");
		}

		if (codeInSession == null) {
			throw new ValidateCodeException(processorType + "验证码不存在");
		}

		if (codeInSession.isExpired()) {
			sessionStrategy.removeAttribute(request, sessionKey);
			throw new ValidateCodeException(processorType + "验证码已过期");
		}

		if (!StringUtils.equalsIgnoreCase(codeInSession.getCode(), codeInRequest)) {
			throw new ValidateCodeException(processorType + "验证码不匹配");
		}

		sessionStrategy.removeAttribute(request, sessionKey);
	}
	
	/**
	 * 生成校验码
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private C generate(ServletWebRequest request) {
		String type = getValidateCodeType(request).toString().toLowerCase();
		String genertorName = type + ValidateCodeGenerator.class.getSimpleName();
		ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(genertorName);
		if(validateCodeGenerator == null) {
			throw new ValidateCodeException("验证码生成器" + genertorName + "不存在");
		}
		return (C) validateCodeGenerator.generate(request);
	}
	
	/**
	 * 保存验证码
	 * @param request
	 * @param validateCode
	 */
	private void save(ServletWebRequest request, C validateCode) {
		// 保存到session中的验证码只需要code 和  expireTime即可
		ValidateCode code = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
		
		sessionStrategy.setAttribute(request, getSessionKey(request), code);
	}
	
	/**
	 * 构建验证码放入session时的key
	 * @param request
	 * @return
	 */
	private String getSessionKey(ServletWebRequest request) {
		return SESSION_KEY_PREFIX + getValidateCodeType(request).toString().toUpperCase(); 
	}
	
	/**
	 * 发送验证码，由子类实现
	 * @param request
	 * @param validateCode
	 */
	protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;
	

	private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
		
		String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
		return ValidateCodeType.valueOf(type.toUpperCase());
	}
	
	

}
