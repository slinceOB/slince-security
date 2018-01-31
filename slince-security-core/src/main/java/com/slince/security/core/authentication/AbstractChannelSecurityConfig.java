package com.slince.security.core.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.slince.security.core.properties.SecurityConstants;

public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	protected AuthenticationSuccessHandler slinceAuthenticationSuccessHandler;
	
	@Autowired
	protected AuthenticationFailureHandler slinceAuthenticationFailureHandler;
	
	protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
		http.formLogin()
			.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
			.loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
			.successHandler(slinceAuthenticationSuccessHandler)
			.failureHandler(slinceAuthenticationFailureHandler);
	}

}
