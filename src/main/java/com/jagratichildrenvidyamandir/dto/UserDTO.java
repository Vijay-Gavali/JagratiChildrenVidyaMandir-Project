package com.jagratichildrenvidyamandir.dto;

public class UserDTO {

    private Integer userId;
    private String name;
    private String admissionNo;
    private String admissionDate;
    private String password;
    private String fatherName;
    private String motherName;
    private String dob;
    private String studentPhone;
    private String email;
    private String parentPhone;
    private String address;
    private String gender;
    private String studentAadharNo;
    private String parentAadharNo;
    private String rte;
    private String tcNumber;
    private String ssmId;
    private String passoutClass;
    private Integer studentClassId;

    // --- NEW FIELDS ---
    private String caste;
    private String subCaste;
    private String religion;
    private String apaarId;
    private String panNo;

    public UserDTO() {
    }

    // Getters & Setters (Existing + New)
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAdmissionNo() { return admissionNo; }
    public void setAdmissionNo(String admissionNo) { this.admissionNo = admissionNo; }
    public String getAdmissionDate() { return admissionDate; }
    public void setAdmissionDate(String admissionDate) { this.admissionDate = admissionDate; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getFatherName() { return fatherName; }
    public void setFatherName(String fatherName) { this.fatherName = fatherName; }
    public String getMotherName() { return motherName; }
    public void setMotherName(String motherName) { this.motherName = motherName; }
    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }
    public String getStudentPhone() { return studentPhone; }
    public void setStudentPhone(String studentPhone) { this.studentPhone = studentPhone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getParentPhone() { return parentPhone; }
    public void setParentPhone(String parentPhone) { this.parentPhone = parentPhone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getStudentAadharNo() { return studentAadharNo; }
    public void setStudentAadharNo(String studentAadharNo) { this.studentAadharNo = studentAadharNo; }
    public String getParentAadharNo() { return parentAadharNo; }
    public void setParentAadharNo(String parentAadharNo) { this.parentAadharNo = parentAadharNo; }
    public String getRte() { return rte; }
    public void setRte(String rte) { this.rte = rte; }
    public String getTcNumber() { return tcNumber; }
    public void setTcNumber(String tcNumber) { this.tcNumber = tcNumber; }
    public String getSsmId() { return ssmId; }
    public void setSsmId(String ssmId) { this.ssmId = ssmId; }
    public String getPassoutClass() { return passoutClass; }
    public void setPassoutClass(String passoutClass) { this.passoutClass = passoutClass; }
    public Integer getStudentClassId() { return studentClassId; }
    public void setStudentClassId(Integer studentClassId) { this.studentClassId = studentClassId; }

    // New Getters & Setters
    public String getCaste() { return caste; }
    public void setCaste(String caste) { this.caste = caste; }
    public String getSubCaste() { return subCaste; }
    public void setSubCaste(String subCaste) { this.subCaste = subCaste; }
    public String getReligion() { return religion; }
    public void setReligion(String religion) { this.religion = religion; }
    public String getApaarId() { return apaarId; }
    public void setApaarId(String apaarId) { this.apaarId = apaarId; }
    public String getPanNo() { return panNo; }
    public void setPanNo(String panNo) { this.panNo = panNo; }
}