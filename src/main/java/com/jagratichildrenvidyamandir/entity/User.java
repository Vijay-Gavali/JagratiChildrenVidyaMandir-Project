package com.jagratichildrenvidyamandir.entity;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	private String name;

	@Column(unique = true)
	private String admissionNo;

	private String admissionDate;
	private String password;
	private String fatherName;
	private String motherName;
	private String dob;

	@Column(unique = true)
	private String studentPhone;

	@Column(unique = true)
	private String email;

	private String parentPhone;
	private String address;
	private String gender;

	@Column(unique = true)
	private String studentAadharNo;

	private String parentAadharNo;
	private String rte;
	private String tcNumber;
	private String ssmId;
	private String passoutClass;

	// --- NEW FIELDS ---
	private String caste;
	private String subCaste;
	private String religion;

	@Column(unique = true)
	private String apaarId;

	@Column(unique = true)
	private String panNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "class_id")
	private ClassEntity studentClass;
	
	
	@OneToMany(mappedBy = "user",cascade =  CascadeType.ALL, orphanRemoval = true)
	private List<Fees> fees;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Transaction> transactions;

	public User() {
	}

	public User(Integer userId, String name, String admissionNo, String admissionDate, String password,
			String fatherName, String motherName, String dob, String studentPhone, String email, String parentPhone,
			String address, String gender, String studentAadharNo, String parentAadharNo, ClassEntity studentClass,
			String rte, String tcNumber, String ssmId, String passoutClass, String caste, String subCaste,
			String religion, String apaarId, String panNo) {
		this.userId = userId;
		this.name = name;
		this.admissionNo = admissionNo;
		this.admissionDate = admissionDate;
		this.password = password;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.dob = dob;
		this.studentPhone = studentPhone;
		this.email = email;
		this.parentPhone = parentPhone;
		this.address = address;
		this.gender = gender;
		this.studentAadharNo = studentAadharNo;
		this.parentAadharNo = parentAadharNo;
		this.studentClass = studentClass;
		this.rte = rte;
		this.tcNumber = tcNumber;
		this.ssmId = ssmId;
		this.passoutClass = passoutClass;
		this.caste = caste;
		this.subCaste = subCaste;
		this.religion = religion;
		this.apaarId = apaarId;
		this.panNo = panNo;
	}

	// GETTERS & SETTERS
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public ClassEntity getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(ClassEntity studentClass) {
		this.studentClass = studentClass;
	}

	public String getRte() {
		return rte;
	}

	public void setRte(String rte) {
		this.rte = rte;
	}

	public String getTcNumber() {
		return tcNumber;
	}

	public void setTcNumber(String tcNumber) {
		this.tcNumber = tcNumber;
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

	// New Getters & Setters
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

	public List<Fees> getFees() {
		return fees;
	}

	public void setFees(List<Fees> fees) {
		this.fees = fees;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
}