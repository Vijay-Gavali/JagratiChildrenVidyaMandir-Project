package com.jagratichildrenvidyamandir.dto;

import java.math.BigDecimal;

public class FeesDTO {

	private Integer feesId;
	private BigDecimal amount;

	private String paymentStatus;

	private BigDecimal remainingAmount;
	private BigDecimal paidAmount;

	private Integer userId; // optional reference

	public FeesDTO() {
	}

	public FeesDTO(Integer feesId, BigDecimal amount, String paymentStatus,
			BigDecimal remainingAmount, BigDecimal paidAmount, Integer userId) {
		super();
		this.feesId = feesId;
		this.amount = amount;
		this.paymentStatus = paymentStatus;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
