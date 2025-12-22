package com.jagratichildrenvidyamandir.dto;

import java.time.LocalDate;
import java.util.List;

public class TeacherDTO {

    private Integer teacherId;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String educationalDetails;
    private Integer yearOfExperience;
    private LocalDate dateOfBirth;
    private String aadharNo;
    private String address;
    public TeacherDTO() {}
    // Add this constructor
    public TeacherDTO(Integer teacherId, String name, String email) {
        this.teacherId = teacherId;
        this.name = name;
        this.email = email;
    }

    private List<String> classNames;  // <-- add this field

    private List<Integer> classIds;
    // Getters & Setters
    public Integer getTeacherId() { return teacherId; }
    public void setTeacherId(Integer teacherId) { this.teacherId = teacherId; }

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

    public List<String> getClassNames() { return classNames; }
    public void setClassNames(List<String> classNames) { this.classNames = classNames; }
    public List<Integer> getClassIds() {
        return classIds;
    }

    public void setClassIds(List<Integer> classIds) {
        this.classIds = classIds;
    }
}
