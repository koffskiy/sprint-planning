package com.koffskiy.web.controllers;

import com.koffskiy.jira.model.Credentials;
import com.koffskiy.jira.response.AuthentificationResponse;
import com.koffskiy.web.service.JiraAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/auth")
public class LoginController {

	@Autowired
	private JiraAuthService jiraAuthService;

	@RequestMapping(method = RequestMethod.GET)
	public String login() {
		return "login.html";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String login(Credentials credentials, HttpServletResponse response) {
		AuthentificationResponse authentificate = jiraAuthService.authentificate(credentials);
		response.addCookie(new Cookie(authentificate.getSession().getName(), authentificate.getSession().getValue()));

		return "/index";
	}
}
