package com.slince.security.core.social.weixin.api;

/**
 * 微信调用接口
 * 
 * @author tianpengfei
 *
 */
public interface WeiXin {

	WeiXinUserInfo getUserInfo(String openId);
	
}
