package com.slince.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.slince.security.core.properties.SecurityProperties;
import com.slince.security.core.validate.code.image.DefaultImageCodeGenerator;
import com.slince.security.core.validate.code.image.ImageCodeGenerator;
import com.slince.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.slince.security.core.validate.code.sms.SmsCodeSender;

/**
 * 验证码配置相关
 * @author tianpengfei
 *
 */
@Configuration
public class ValidateCodeBeanConfig {

	@Autowired
	private SecurityProperties securityProperties;
	
	@Bean
	@ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
	public ValidateCodeGenerator imageValidateCodeGenerator() {
		DefaultImageCodeGenerator defaultImageCodeGenerator = new DefaultImageCodeGenerator();
		defaultImageCodeGenerator.setSecurityProperties(securityProperties);
		return defaultImageCodeGenerator;
	}
	
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)
	public SmsCodeSender smsCodeSender() {
		return new DefaultSmsCodeSender();
	}
}
