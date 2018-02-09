package com.slince.security.core.properties;

public class BrowserProperties {
	
	private SessionProperties session = new SessionProperties();
	
	/**
	 * 登录界面
	 */
	private String loginPage = "/slince-login.html";
	
	/**
	 * 注册界面
	 */
	private String signUpUrl = "/slince-signUp.html";
	
	/**
	 * 登录成功后，自动跳转的页面
	 */
	private String signInSuccessUrl = "/index.html";
	
	/**
	 * 登出跳转界面
	 */
	private String logoutUrl = "/slince-login.html";
	
	private LoginResponseType loginType = LoginResponseType.JSON;
	
	private int rememberMeseconds = 3600;

	public String getLoginPage() {
		System.out.println("--->" + loginPage + "<---");
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		System.out.println("<---"+loginPage+"--->");
		this.loginPage = loginPage;
	}

	public LoginResponseType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginResponseType loginType) {
		this.loginType = loginType;
	}

	public int getRememberMeseconds() {
		return rememberMeseconds;
	}

	public void setRememberMeseconds(int rememberMeseconds) {
		this.rememberMeseconds = rememberMeseconds;
	}

	public String getSignUpUrl() {
		return signUpUrl;
	}

	public void setSignUpUrl(String signUpUrl) {
		this.signUpUrl = signUpUrl;
	}

	public String getLogoutUrl() {
		return logoutUrl;
	}

	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	public SessionProperties getSession() {
		return session;
	}

	public void setSession(SessionProperties session) {
		this.session = session;
	}

	public String getSignInSuccessUrl() {
		return signInSuccessUrl;
	}

	public void setSignInSuccessUrl(String signInSuccessUrl) {
		this.signInSuccessUrl = signInSuccessUrl;
	}
	
}
