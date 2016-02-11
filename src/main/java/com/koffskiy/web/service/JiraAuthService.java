package com.koffskiy.web.service;

import com.koffskiy.jira.model.Credentials;
import com.koffskiy.jira.response.AuthentificationResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.MediaType;

@Component
public class JiraAuthService {

	private final Client client;
	private final String url;

	@Autowired
	public JiraAuthService(Client client, String jiraUrl) {
		url = jiraUrl + "/jira/rest/auth/1/session";
		this.client = client;
	}

	public AuthentificationResponse authentificate(Credentials credentials) {
		AuthentificationResponse response = client
				.resource(url)
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.entity(credentials, MediaType.APPLICATION_JSON_TYPE)
				.post(AuthentificationResponse.class);

		return response;
	}

}
