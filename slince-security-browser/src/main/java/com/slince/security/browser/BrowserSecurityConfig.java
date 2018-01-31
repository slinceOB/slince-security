package com.slince.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.slince.security.core.authentication.AbstractChannelSecurityConfig;
import com.slince.security.core.properties.SecurityConstants;
import com.slince.security.core.properties.SecurityProperties;
import com.slince.security.core.validate.code.ValidateCodeSecurityConfig;

@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;
	
	protected void configure(HttpSecurity http) throws Exception {
		
		applyPasswordAuthenticationConfig(http);
		
		http.apply(validateCodeSecurityConfig)
			.and()
			.authorizeRequests()
			.antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
					SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+ "/*",
					securityProperties.getBrowser().getLoginPage(),
					SecurityConstants.DEFAULT_STATIC_RESOURCES_URL).permitAll()  // 匹配到这里的内容，不用认证
			.anyRequest()
			.authenticated()
			.and()
			.csrf().disable();
	};
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
