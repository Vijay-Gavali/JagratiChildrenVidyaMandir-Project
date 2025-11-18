package com.jagratichildrenvidyamandir.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    int userId;

    String name;
    String admissionNumber;
    String admissionDate;
    String password;
    String fatherName;
    String motherName;
    String dob;
    String studentPhone;
    String email;
    String parentPhone;
    String address;
    String gender;
    String studentAadharNo;
    String parentAadharNo;
    String studentClass;
    String rte;
    String tcNumber;
    String ssmId;
    String passoutClass;
    
    public User() {
		// TODO Auto-generated constructor stub
	}
    

}
