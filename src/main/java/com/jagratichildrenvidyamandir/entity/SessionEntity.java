package com.jagratichildrenvidyamandir.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "academic_session")
public class SessionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "session_id")
	private Integer sessionId;

	@Column(name = "name", nullable = false, unique = true, length = 20)
	private String name; // e.g. "2025-26"

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "end_date")
	private LocalDate endDate;

	@Column(name = "is_active", nullable = false)
	private boolean active = true;

	// One-to-Many relationship with Notice
	@OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Notice> notices = new ArrayList<>();
	
	@OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Transaction> transactions = new ArrayList<>();

	public SessionEntity() {
	}

	public SessionEntity(Integer sessionId, String name, LocalDate startDate, LocalDate endDate, boolean active) {
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

	public List<Notice> getNotices() {
		return notices;
	}

	public void setNotices(List<Notice> notices) {
		this.notices = notices;
	}

	// Helper method to add notice
	public void addNotice(Notice notice) {
		notices.add(notice);
		notice.setSession(this);
	}

	// Helper method to remove notice
	public void removeNotice(Notice notice) {
		notices.remove(notice);
		notice.setSession(null);
	}
	
	public List<Transaction> getTransactions() {
	    return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
	    this.transactions = transactions;
	}

	// helper methods
	public void addTransaction(Transaction tx) {
	    transactions.add(tx);
	    tx.setSession(this);
	}

	public void removeTransaction(Transaction tx) {
	    transactions.remove(tx);
	    tx.setSession(null);
	}
}
