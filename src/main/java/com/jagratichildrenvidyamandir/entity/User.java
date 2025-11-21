package com.jagratichildrenvidyamandir.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String studentClass;
    private String rte;
    private String tcNumber;
    private String ssmId;
    private String passoutClass;

    public User() {}

    // Full constructor (keep existing fields)
    public User(Integer userId, String name, String admissionNo, String admissionDate, String password, String fatherName,
                String motherName, String dob, String studentPhone, String email, String parentPhone, String address,
                String gender, String studentAadharNo, String parentAadharNo, String studentClass, String rte,
                String tcNumber, String ssmId, String passoutClass) {
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
    }

    // Getters & setters for all fields
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

    public String getStudentClass() { return studentClass; }
    public void setStudentClass(String studentClass) { this.studentClass = studentClass; }

    public String getRte() { return rte; }
    public void setRte(String rte) { this.rte = rte; }

    public String getTcNumber() { return tcNumber; }
    public void setTcNumber(String tcNumber) { this.tcNumber = tcNumber; }

    public String getSsmId() { return ssmId; }
    public void setSsmId(String ssmId) { this.ssmId = ssmId; }

    public String getPassoutClass() { return passoutClass; }
    public void setPassoutClass(String passoutClass) { this.passoutClass = passoutClass; }
}
