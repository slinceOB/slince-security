package com.slince.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.slince.security.browser.logout.SlinceLogoutSuccessHandler;
import com.slince.security.core.properties.SecurityProperties;

/**
 * 浏览器环境下扩展点配置，配置在这里的bean，业务系统都可以通过声明同类型或同名的bean来覆盖安全
 * 
 * 模块默认的配置。
 * 
 * @author tianpengfei
 *
 */
@Configuration
public class BrowserSecurityBeanConfig {
	
	@Autowired
	private SecurityProperties securityProperties;
	
	/**
	 * 退出时的处理策略配置
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(LogoutSuccessHandler.class)
	public LogoutSuccessHandler logoutSuccessHandler() {
		return new SlinceLogoutSuccessHandler(securityProperties.getBrowser().getLogoutUrl());
	}

}
