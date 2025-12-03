package com.jagratichildrenvidyamandir.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "classes")
public class ClassEntity  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer classId;

	@Column(length = 50)
	private String className;

	private Integer fees;
	
	// One class has many users (students)
    @OneToMany(mappedBy = "studentClass", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();
    
    @ManyToMany(mappedBy = "classes")
    private List<Teacher> teachers = new ArrayList<>();


	public ClassEntity() {
	}

	public ClassEntity(Integer classId, String className, Integer fees) {
		this.classId = classId;
		this.className = className;
		this.fees = fees;
	}

	// ---------- Getters & Setters ----------
	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getFees() {
		return fees;
	}

	public void setFees(Integer fees) {
		this.fees = fees;
	}

	public List<User> getUsers() {
	    return users;
	}

	public void setUsers(List<User> users) {
	    this.users = users;
	}

}
