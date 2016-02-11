package com.koffskiy.web.service;

import java.util.List;

import javax.ws.rs.core.Cookie;

import com.google.common.collect.Lists;
import com.koffskiy.jira.model.Board;
import com.koffskiy.jira.model.Issue;
import com.koffskiy.jira.model.Sprint;
import com.koffskiy.jira.response.BoardResponse;
import com.koffskiy.jira.response.IssueResponse;
import com.koffskiy.jira.response.SprintResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JiraService {

	public static final String BOARD_URL = "/board";
	private static final String SPRINT_URL = "/sprint";
	public static final String ISSUE_URL = "issue";
	private final WebResource base;

	@Autowired
	public JiraService(Client client, String jiraUrl) {
		base = client.resource(jiraUrl + "/rest/agile/1.0");
	}

	public List<Board> findBoardsByName(String boardName, String session) {
		BoardResponse response = base.path(BOARD_URL)
				.queryParam("name", boardName)
				.cookie(Cookie.valueOf(session))
				.get(BoardResponse.class);

		return Lists.newArrayList(response.getValues());
	}

	public List<Sprint> findSprintsForBoard(Long boardId, String session) {
		SprintResponse response = base.path(BOARD_URL).path(boardId.toString()).path(SPRINT_URL)
				.queryParam("state", "active,future")
				.cookie(Cookie.valueOf(session))
				.get(SprintResponse.class);

		return Lists.newArrayList(response.getValues());
	}


	public List<Issue> findIsssuesForBoard(Long boardId, String session) {
		IssueResponse response = base.path(BOARD_URL).path(boardId.toString()).path(ISSUE_URL)
				.cookie(Cookie.valueOf(session))
				.get(IssueResponse.class);

		return Lists.newArrayList(response.getIssues());
	}

	public List<Issue> findIsssuesForSprint(Long sprintId, String session) {
		IssueResponse response = base.path(SPRINT_URL).path(sprintId.toString()).path(ISSUE_URL)
				.cookie(Cookie.valueOf(session))
				.get(IssueResponse.class);

		return Lists.newArrayList(response.getIssues());
	}

}
