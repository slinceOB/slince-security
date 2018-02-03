package com.slince.security.core.social.qq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

import com.slince.security.core.properties.QQproperties;
import com.slince.security.core.properties.SecurityProperties;
import com.slince.security.core.social.qq.connect.QQConnectionFactory;

@Configuration
@ConditionalOnProperty(prefix = "slince.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter{

	@Autowired
	private SecurityProperties securityProperties;
	
	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		QQproperties qqConfig = securityProperties.getSocial().getQq();
		return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
	}

}
