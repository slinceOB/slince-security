package com.slince.security.core.properties;

public class BrowserProperties {
	
	private String loginPage = "/slince-login.html";

	public String getLoginPage() {
		System.out.println("--->" + loginPage + "<---");
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		System.out.println("<---"+loginPage+"--->");
		this.loginPage = loginPage;
	}
	
	

}
