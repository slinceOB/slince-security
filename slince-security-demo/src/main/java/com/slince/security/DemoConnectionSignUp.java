/**
 * 
 */
package com.slince.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * 
 * 用户第一次登录时，没有在该平台注册，系统自动注册
 * 
 * @author tianpengfei
 *
 */
//@Component
public class DemoConnectionSignUp implements ConnectionSignUp {

	/* (non-Javadoc)
	 * @see org.springframework.social.connect.ConnectionSignUp#execute(org.springframework.social.connect.Connection)
	 */
	@Override
	public String execute(Connection<?> connection) {
		//根据社交用户信息默认创建用户并返回用户唯一标识
		return connection.getDisplayName();
	}

}
