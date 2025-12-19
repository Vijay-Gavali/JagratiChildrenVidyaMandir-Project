package com.jagratichildrenvidyamandir.dto;

import java.time.LocalDate;

public class SessionDTO {

	private Integer sessionId;
	private String name;
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean active;

	public SessionDTO() {
	}

	public SessionDTO(Integer sessionId, String name, LocalDate startDate, LocalDate endDate, boolean active) {
		this.sessionId = sessionId;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.active = active;
	}

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
