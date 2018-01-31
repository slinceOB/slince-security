package com.slince.security.browser;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.slince.security.core.authentication.AbstractChannelSecurityConfig;
import com.slince.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.slince.security.core.properties.SecurityConstants;
import com.slince.security.core.properties.SecurityProperties;
import com.slince.security.core.validate.code.ValidateCodeSecurityConfig;

@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
	
	protected void configure(HttpSecurity http) throws Exception {
		
		applyPasswordAuthenticationConfig(http);
		
		http.apply(validateCodeSecurityConfig)
			.and()
			.apply(smsCodeAuthenticationSecurityConfig)
			.and()
			.rememberMe()
				.tokenRepository(persistentTokenRepository())
				.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeseconds())
				.userDetailsService(userDetailsService)
			.and()
			.authorizeRequests()
			.antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
					securityProperties.getBrowser().getLoginPage(),
					SecurityConstants.DEFAULT_STATIC_RESOURCES_URL,
					SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+ "/*").permitAll()  // 匹配到这里的内容，不用认证
			.anyRequest()
			.authenticated()
			.and()
			.csrf().disable();
	};
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
//		tokenRepository.setCreateTableOnStartup(true);
		return tokenRepository;
	}
}
