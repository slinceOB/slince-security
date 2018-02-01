package com.slince.security.core.validate.code.sms;

/**
 * 短信验证码发送者，供使用者实现
 * @author tianpengfei
 *
 */
public interface SmsCodeSender {
	
	void send(String mobile, String code);

}
