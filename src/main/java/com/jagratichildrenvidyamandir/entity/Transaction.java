package com.jagratichildrenvidyamandir.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "transaction_id", unique = true, nullable = false)
	private String transactionId;

	@Column(nullable = false)
	private Double amount;

	@Column(name = "payment_date", nullable = false)
	private LocalDateTime paymentDate;

	@Column(name = "payment_mode", nullable = false)
	private String paymentMode; // CASH, CARD, ONLINE, UPI, etc.

	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "upi_id")
	private String upiId;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private String status; // SUCCESS, FAILED, PENDING, REFUNDED

	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(name = "receipt_number", unique = true)
	private String receiptNumber;

	@Column(name = "remarks")
	private String remarks;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "session_id", nullable = true) // set false if session is mandatory
	private SessionEntity session;

	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
		updatedAt = LocalDateTime.now();

		// Generate transaction ID if not provided
		if (transactionId == null) {
			transactionId = generateTransactionId();
		}

		// Generate receipt number if not provided
		if (receiptNumber == null) {
			receiptNumber = generateReceiptNumber();
		}
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now();
	}

	private String generateTransactionId() {
		String prefix = "TXN";
		String timestamp = String.valueOf(System.currentTimeMillis());
		String random = String.valueOf((int) (Math.random() * 1000));
		return prefix + timestamp.substring(timestamp.length() - 6) + random;
	}

	private String generateReceiptNumber() {
		String prefix = "RCPT";
		String timestamp = String.valueOf(System.currentTimeMillis());
		String random = String.valueOf((int) (Math.random() * 10000));
		return prefix + timestamp.substring(timestamp.length() - 8) + random;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getUpiId() {
		return upiId;
	}

	public void setUpiId(String upiId) {
		this.upiId = upiId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getReceiptNumber() {
		return receiptNumber;
	}

	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SessionEntity getSession() {
		return session;
	}

	public void setSession(SessionEntity session) {
		this.session = session;
	}
}
