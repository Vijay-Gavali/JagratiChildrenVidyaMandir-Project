package com.jagratichildrenvidyamandir.dto;

public class AdminDTO {
	
	private Integer adminId;
	private String phoneNumber;
	private String password;

	public AdminDTO() {
	}

	public AdminDTO(Integer adminId, String phoneNumber, String password) {
		this.adminId = adminId;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
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
