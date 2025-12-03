package com.jagratichildrenvidyamandir.dto;

import java.time.LocalDate;

public class TeacherDTO {
    private Integer teacherId;
    private Integer classId;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String educationalDetails;
    private Integer yearOfExperience;
    private LocalDate dateOfBirth;
    private String aadharNo;
    private String address;
    private String aprNo;   // ⭐ NEW FIELD
    private String documentPath;


    // ---------- Getters & Setters ----------
    public Integer getTeacherId() { return teacherId; }
    public void setTeacherId(Integer teacherId) { this.teacherId = teacherId; }

    public Integer getClassId() { return classId; }
    public void setClassId(Integer classId) { this.classId = classId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEducationalDetails() { return educationalDetails; }
    public void setEducationalDetails(String educationalDetails) { this.educationalDetails = educationalDetails; }

    public Integer getYearOfExperience() { return yearOfExperience; }
    public void setYearOfExperience(Integer yearOfExperience) { this.yearOfExperience = yearOfExperience; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getAadharNo() { return aadharNo; }
    public void setAadharNo(String aadharNo) { this.aadharNo = aadharNo; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getAprNo() { return aprNo; }                  // ⭐ NEW GETTER
    public void setAprNo(String aprNo) { this.aprNo = aprNo; }  // ⭐ NEW SETTER
    public String getDocumentPath() { return documentPath; }
    public void setDocumentPath(String documentPath) { this.documentPath = documentPath; }

}
