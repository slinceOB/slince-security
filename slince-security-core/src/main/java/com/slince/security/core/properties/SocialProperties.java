package com.slince.security.core.properties;

/**
 * 第三方登录配置
 * @author tianpengfei
 *
 */
public class SocialProperties {

	private String filterProcessesUrl = "/auth";
	
	private QQproperties qq = new QQproperties();
	
	private WeiXinProperties weixin = new WeiXinProperties();

	public String getFilterProcessesUrl() {
		return filterProcessesUrl;
	}

	public void setFilterProcessesUrl(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}

	public QQproperties getQq() {
		return qq;
	}

	public void setQq(QQproperties qq) {
		this.qq = qq;
	}

	public WeiXinProperties getWeixin() {
		return weixin;
	}

	public void setWeixin(WeiXinProperties weixin) {
		this.weixin = weixin;
	}
	
}
