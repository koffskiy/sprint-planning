package com.koffskiy.jira.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.koffskiy.jira.model.Issue;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IssueResponse extends BasePaginationResponse<Issue> {

	private Issue[] issues;

	public Issue[] getIssues() {
		return issues;
	}

	public void setIssues(Issue[] issues) {
		this.issues = issues;
	}
}
