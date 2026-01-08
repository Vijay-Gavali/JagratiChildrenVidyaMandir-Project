package com.jagratichildrenvidyamandir.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transfer_certificate")
public class TransferCertificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tcId;

    // Original user reference (future audit ke liye)
    private Integer userId;

    private String tcNumber;

    private String name;
    private String admissionNo;
    private String admissionDate;

    private String fatherName;
    private String motherName;
    private String dob;

    private String studentPhone;
    private String parentPhone;
    private String address;
    private String gender;

    private String studentAadharNo;
    private String parentAadharNo;

    private String rte;
    private String ssmId;
    private String passoutClass;

    private String caste;
    private String subCaste;
    private String religion;

    private String apaarId;
    private String panNo;

    private String className;
    private String sessionName;

    private LocalDate tcIssueDate;

    public TransferCertificate() {}

	public Integer getTcId() {
		return tcId;
	}

	public void setTcId(Integer tcId) {
		this.tcId = tcId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTcNumber() {
		return tcNumber;
	}

	public void setTcNumber(String tcNumber) {
		this.tcNumber = tcNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdmissionNo() {
		return admissionNo;
	}

	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}

	public String getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getStudentPhone() {
		return studentPhone;
	}

	public void setStudentPhone(String studentPhone) {
		this.studentPhone = studentPhone;
	}

	public String getParentPhone() {
		return parentPhone;
	}

	public void setParentPhone(String parentPhone) {
		this.parentPhone = parentPhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStudentAadharNo() {
		return studentAadharNo;
	}

	public void setStudentAadharNo(String studentAadharNo) {
		this.studentAadharNo = studentAadharNo;
	}

	public String getParentAadharNo() {
		return parentAadharNo;
	}

	public void setParentAadharNo(String parentAadharNo) {
		this.parentAadharNo = parentAadharNo;
	}

	public String getRte() {
		return rte;
	}

	public void setRte(String rte) {
		this.rte = rte;
	}

	public String getSsmId() {
		return ssmId;
	}

	public void setSsmId(String ssmId) {
		this.ssmId = ssmId;
	}

	public String getPassoutClass() {
		return passoutClass;
	}

	public void setPassoutClass(String passoutClass) {
		this.passoutClass = passoutClass;
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public String getSubCaste() {
		return subCaste;
	}

	public void setSubCaste(String subCaste) {
		this.subCaste = subCaste;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getApaarId() {
		return apaarId;
	}

	public void setApaarId(String apaarId) {
		this.apaarId = apaarId;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public LocalDate getTcIssueDate() {
		return tcIssueDate;
	}

	public void setTcIssueDate(LocalDate tcIssueDate) {
		this.tcIssueDate = tcIssueDate;
	}

    
}
