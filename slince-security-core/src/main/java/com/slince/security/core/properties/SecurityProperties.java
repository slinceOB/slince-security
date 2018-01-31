package com.slince.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * slince-security 安全框架配置中心
 * 
 * @author 95307
 *
 */
@ConfigurationProperties(prefix = "slince.security")
public class SecurityProperties {
	
	private BrowserProperties browser = new BrowserProperties();
	
	private ValidateCodeProperties code = new ValidateCodeProperties();

	public BrowserProperties getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}

	public ValidateCodeProperties getCode() {
		return code;
	}

	public void setCode(ValidateCodeProperties code) {
		this.code = code;
	}
	
	
	
	
}
