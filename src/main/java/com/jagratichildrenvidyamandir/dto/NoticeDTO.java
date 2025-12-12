package com.jagratichildrenvidyamandir.dto;

import java.time.LocalDate;

public class NoticeDTO {

	private Integer noticeId;
	private String title;
	private String subject;
	private String issuedBy;
	private LocalDate date;
	private String message;
	
    private Integer sessionId; // Add session reference

	public NoticeDTO() {
	}

	public NoticeDTO(Integer noticeId, String title, String subject, String issuedBy, LocalDate date, String message, Integer sessionId) {
		this.noticeId = noticeId;
		this.title = title;
		this.subject = subject;
		this.issuedBy = issuedBy;
		this.date = date;
		this.message = message;
        this.sessionId = sessionId;

	}

	public Integer getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getIssuedBy() {
		return issuedBy;
	}

	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }	
}
