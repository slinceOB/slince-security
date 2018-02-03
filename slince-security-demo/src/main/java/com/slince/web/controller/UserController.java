package com.slince.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.slince.dto.User;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private ProviderSignInUtils providerSignInUtils;

	@GetMapping
	public List<User> query() {
		List<User> users = new ArrayList<User>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		return users;
	}
	
	@PostMapping("/regist")
	public void regist(User user, HttpServletRequest request) {
		
		//不管是注册用户还是绑定用户，都会拿到一个用户唯一标识。
		String userId = user.getUsername();
		providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
	}
}
