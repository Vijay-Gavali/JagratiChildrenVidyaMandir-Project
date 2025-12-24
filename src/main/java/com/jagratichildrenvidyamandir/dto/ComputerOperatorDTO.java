package com.jagratichildrenvidyamandir.dto;

public class ComputerOperatorDTO {

	private Integer computerOperatorId;
	private String phoneNumber;
	private String password;

	public ComputerOperatorDTO() {
		// TODO Auto-generated constructor stub
	}

	public ComputerOperatorDTO(Integer computerOperatorId, String phoneNumber, String password) {
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
