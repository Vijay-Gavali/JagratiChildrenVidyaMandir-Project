package com.jagratichildrenvidyamandir.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "notice")
public class Notice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notice_id")
	private Integer noticeId;

	@Column(name = "title", length = 200, nullable = false)
	private String title;

	@Column(name = "subject", length = 300, nullable = false)
	private String subject;

	@Column(name = "issued_by", length = 150)
	private String issuedBy;

	@Column(name = "notice_date")
	private LocalDate date;

	@Column(name = "message", length = 2000)
	private String message;

	// Many-to-One relationship with SessionEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "session_id", nullable = false)
	private SessionEntity session;

	public Notice() {
	}

	public Notice(Integer noticeId, String title, String subject, String issuedBy, LocalDate date, String message,
			SessionEntity session) {
		this.noticeId = noticeId;
		this.title = title;
		this.subject = subject;
		this.issuedBy = issuedBy;
		this.date = date;
		this.message = message;
		this.session = session;

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
	

    public SessionEntity getSession() {
        return session;
    }

    public void setSession(SessionEntity session) {
        this.session = session;
    }
}
