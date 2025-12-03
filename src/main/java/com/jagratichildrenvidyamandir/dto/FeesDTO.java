package com.jagratichildrenvidyamandir.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jagratichildrenvidyamandir.entity.User;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FeesDTO {

	private Integer feesId;
	private BigDecimal amount;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dueDate;

	private String paymentStatus;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate paymentDate;

	private BigDecimal remainingAmount;
	private BigDecimal paidAmount;

	private Integer userId; // optional reference

	public FeesDTO() {
	}

	public FeesDTO(Integer feesId, BigDecimal amount, LocalDate dueDate, String paymentStatus, LocalDate paymentDate,
			BigDecimal remainingAmount, BigDecimal paidAmount, Integer userId) {
		super();
		this.feesId = feesId;
		this.amount = amount;
		this.dueDate = dueDate;
		this.paymentStatus = paymentStatus;
		this.paymentDate = paymentDate;
		this.remainingAmount = remainingAmount;
		this.paidAmount = paidAmount;
		this.userId = userId;
	}

	// getters & setters
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

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
