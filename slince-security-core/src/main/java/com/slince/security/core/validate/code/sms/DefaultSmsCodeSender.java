package com.slince.security.core.validate.code.sms;

/**
 * 短信发送者默认实现
 * @author tianpengfei
 *
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

	@Override
	public void send(String mobile, String code) {
		System.out.println("向手机"+mobile+"发送短信验证码"+code);
	}

}
