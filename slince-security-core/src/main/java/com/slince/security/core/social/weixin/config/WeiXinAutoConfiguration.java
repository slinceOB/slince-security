package com.slince.security.core.social.weixin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

import com.slince.security.core.properties.SecurityProperties;
import com.slince.security.core.properties.WeiXinProperties;
import com.slince.security.core.social.weixin.SlinceConnectView;
import com.slince.security.core.social.weixin.connect.WeiXinConnectionFactory;

@Configuration
@ConditionalOnProperty(prefix = "slince.security.social.weixin", name = "app-id")
public class WeiXinAutoConfiguration extends SocialAutoConfigurerAdapter {
	
	@Autowired
	private SecurityProperties securityProperties;

	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		WeiXinProperties weixinProperties = securityProperties.getSocial().getWeixin();
		return new WeiXinConnectionFactory(weixinProperties.getProviderId(), weixinProperties.getAppId(), weixinProperties.getAppSecret());
	}

	@Bean({"connect/weixinConnect", "connect/weixinConnected"})
	@ConditionalOnMissingBean( name = "weixinConnectedView")
	public View weixinConnectedView() {
		return new SlinceConnectView();
	}
	
}
