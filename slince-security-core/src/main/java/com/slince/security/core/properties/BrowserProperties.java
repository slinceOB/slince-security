package com.slince.security.core.properties;

public class BrowserProperties {
	
	private String loginPage = "/slince-login.html";
	
	
	private LoginResponseType loginType = LoginResponseType.JSON;
	
	

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
	
	
	

}
