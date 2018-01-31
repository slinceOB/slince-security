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

	public BrowserProperties getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}
	
	
}
