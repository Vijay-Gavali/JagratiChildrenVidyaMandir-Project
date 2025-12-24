package com.jagratichildrenvidyamandir.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ComputerOperator {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer computerOperatorId;

	@Column(unique = true)
	private String phoneNumber;

	private String password;

	public ComputerOperator() {
		// TODO Auto-generated constructor stub
	}

	public ComputerOperator(Integer computerOperatorId, String phoneNumber, String password) {
		super();
		this.computerOperatorId = computerOperatorId;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}

	public Integer getComputerOperatorId() {
		return computerOperatorId;
	}

	public void setComputerOperatorId(Integer computerOperatorId) {
		this.computerOperatorId = computerOperatorId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
