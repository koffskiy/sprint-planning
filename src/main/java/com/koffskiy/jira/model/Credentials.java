package com.koffskiy.jira.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hp on 11/01/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Credentials {

	private String username;
	private String password;

	public Credentials(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Credentials() {
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
