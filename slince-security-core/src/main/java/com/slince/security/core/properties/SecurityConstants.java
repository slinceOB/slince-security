package com.slince.security.core.properties;

/**
 * 
 * @author 95307
 *
 */
public interface SecurityConstants {

	/**
	 * 当请求需要身份认证时，默认跳转的url
	 * 
	 * @see SecurityController
	 */
	public static final String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";
	/**
	 * 默认的用户名密码登录请求处理url
	 */
	public static final String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";

	public static final String DEFAULT_STATIC_RESOURCES_URL = "/static/**";

}
