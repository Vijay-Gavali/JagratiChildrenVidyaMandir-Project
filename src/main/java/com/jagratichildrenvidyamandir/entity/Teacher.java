package com.jagratichildrenvidyamandir.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teacherId;

    @Column(length = 100)
    private String name;

    @Column(unique = true)
    private String email;

    private String phone;
    private String password;
    private String educationalDetails;
    private Integer yearOfExperience;
    private LocalDate dateOfBirth;
    private String aadharNo;
    private String address;

    // Many-to-Many relationship with ClassEntity
    @ManyToMany
    @JoinTable(
        name = "teacher_classes",
        joinColumns = @JoinColumn(name = "teacher_id"),
        inverseJoinColumns = @JoinColumn(name = "class_id")
    )
    private List<ClassEntity> classes = new ArrayList<>();
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Marks> marks = new ArrayList<>();

    // ---------- Constructors ----------
    public Teacher() {}

    // ---------- Getters & Setters ----------
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

    public List<ClassEntity> getClasses() { return classes; }
    public void setClasses(List<ClassEntity> classes) { this.classes = classes; }
    public List<Marks> getMarks() { return marks; }
    public void setMarks(List<Marks> marks) { this.marks = marks; }
   
}
