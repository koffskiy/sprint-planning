package com.koffskiy.jira.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BasePaginationResponse<T> {

	private int startAt;
	private int maxResults;
	private int total;
	private T[] values;

	public int getStartAt() {
		return startAt;
	}

	public void setStartAt(int startAt) {
		this.startAt = startAt;
	}

	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public T[] getValues() {
		return values;
	}

	public void setValues(T[] values) {
		this.values = values;
	}
}
