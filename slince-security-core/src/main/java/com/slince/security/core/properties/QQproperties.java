package com.slince.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;
/**
 * qq配置
 * @author tianpengfei
 *
 */
public class QQproperties extends SocialProperties {

	/**
	 * 第三方平台标识
	 */
	private String providerId = "qq";

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
}
