package com.slince.security.core.validate.code;

import com.slince.security.core.properties.SecurityConstants;

public enum ValidateCodeType {

	/**
	 * 短信验证码
	 */
	SMS{
		public String getParamNameOnValidate() {
			return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
		}
	},
	
	/**
	 * 图形验证码
	 */
	IMAGE{
		public String getParamNameOnValidate() {
			return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
		}
	};
	
	/**
	 * 校验时，从请求中获取的参数的名字
	 * @return
	 */
	public abstract String getParamNameOnValidate();
	
}
