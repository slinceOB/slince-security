package com.slince.security.core.social.qq.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import com.slince.security.core.social.qq.api.QQ;
import com.slince.security.core.social.qq.api.QQUserInfo;

public class QQAdapter implements ApiAdapter<QQ> {

	/**
	 * 测试请求qq信息的平台是否可用
	 */
	@Override
	public boolean test(QQ api) {

		return true;
	}

	@Override
	public void setConnectionValues(QQ api, ConnectionValues values) {
		QQUserInfo userInfo = api.getUserInfo();
		
		values.setDisplayName(userInfo.getNickname());
		values.setImageUrl(userInfo.getFigureurl_qq_1());
		values.setProfileUrl(null);  // 个人主页，针对微博。facebook等
		values.setProviderUserId(userInfo.getOpenId());
	}

	@Override
	public UserProfile fetchUserProfile(QQ api) {
		return null;
	}

	@Override
	public void updateStatus(QQ api, String message) {
		
	}

}
