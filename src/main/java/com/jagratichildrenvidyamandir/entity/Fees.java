package com.jagratichildrenvidyamandir.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "fees")
public class Fees {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fees_id")
	private Integer feesId;

	@Column(name = "amount", precision = 10, scale = 2)
	private BigDecimal amount;

	@Column(name = "payment_status")
	private String paymentStatus; // values: Paid / Pending

	@Column(name = "remaining_amount", precision = 10, scale = 2)
	private BigDecimal remainingAmount;

	@Column(name = "paid_amount", precision = 10, scale = 2)
	private BigDecimal paidAmount;

	// MANY FEES belong to ONE STUDENT (USER)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id") // Foreign key in fees table
	private User user;

	public Fees() {
	}

	public Fees(Integer feesId, BigDecimal amount, LocalDate dueDate, String paymentStatus, LocalDate paymentDate,
			BigDecimal remainingAmount, BigDecimal paidAmount, User user) {
		super();
		this.feesId = feesId;
		this.amount = amount;
		this.paymentStatus = paymentStatus;
		this.remainingAmount = remainingAmount;
		this.paidAmount = paidAmount;
		this.user = user;
	}

	// Getters & setters
	public Integer getFeesId() {
		return feesId;
	}

	public void setFeesId(Integer feesId) {
		this.feesId = feesId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public BigDecimal getRemainingAmount() {
		return remainingAmount;
	}

	public void setRemainingAmount(BigDecimal remainingAmount) {
		this.remainingAmount = remainingAmount;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
