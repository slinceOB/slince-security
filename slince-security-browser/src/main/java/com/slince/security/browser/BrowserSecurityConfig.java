package com.slince.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.slince.security.browser.authentication.SlinceAuthenticationSuccessHandler;
import com.slince.security.core.properties.SecurityConstants;
import com.slince.security.core.properties.SecurityProperties;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private AuthenticationSuccessHandler slinceAuthenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler slinceAuthenticationFailureHandler;
	
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()
			.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
			.loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
			.successHandler(slinceAuthenticationSuccessHandler)
			.failureHandler(slinceAuthenticationFailureHandler)
			.and()
			.authorizeRequests()
			.antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
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
