package com.slince.security.core.social.qq.api;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 从QQ获取用户信息
 * 
 * 注意：
 * 		AbstractOAuth2ApiBinding中提供的  accessToken 是一个类级别的变量，所以QQImpl是一个多实例
 * @author tianpengfei
 *
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
	
	private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
	
	private String appId;
	
	private String openId;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public QQImpl(String accessToken, String appId) {
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
		this.appId = appId;
		
		String url = String.format(URL_GET_OPENID, accessToken);
		String result = getRestTemplate().getForObject(url, String.class);
		
		logger.debug("从QQ获取到的关于openid的结果：" + result);
		
		// 根据qq的对接文档，获取openid
		this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
	}
	

	@Override
	public QQUserInfo getUserInfo() {

		String url  = String.format(URL_GET_USERINFO, appId, openId);
		String result = getRestTemplate().getForObject(url, String.class);
		
		logger.info("从QQ获取到的关于用户信息：" + result);
		
		QQUserInfo user = null;
		try {
			user = objectMapper.readValue(result, QQUserInfo.class);
			user.setOpenId(openId);
			return user;
		} catch(Exception e ) {
			throw new RuntimeException("从QQ获取用户信息失败" + e);
		}
		
	}

}
