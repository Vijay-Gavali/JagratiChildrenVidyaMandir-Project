package com.jagratichildrenvidyamandir.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class EnquiryDTO {

	private Integer enquiryId;
	private String parentName;
	private String contactNo;

	// Accept/produce date in ISO format (e.g. "2025-11-18")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate enquiryDate;

	private String enquiryMessage;

	public EnquiryDTO() {
	}

	public EnquiryDTO(Integer enquiryId, String parentName, String contactNo, LocalDate enquiryDate,
			String enquiryMessage) {
		this.enquiryId = enquiryId;
		this.parentName = parentName;
		this.contactNo = contactNo;
		this.enquiryDate = enquiryDate;
		this.enquiryMessage = enquiryMessage;
	}

	// Getters & Setters
	public Integer getEnquiryId() {
		return enquiryId;
	}

	public void setEnquiryId(Integer enquiryId) {
		this.enquiryId = enquiryId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public LocalDate getEnquiryDate() {
		return enquiryDate;
	}

	public void setEnquiryDate(LocalDate enquiryDate) {
		this.enquiryDate = enquiryDate;
	}

	public String getEnquiryMessage() {
		return enquiryMessage;
	}

	public void setEnquiryMessage(String enquiryMessage) {
		this.enquiryMessage = enquiryMessage;
	}
}
