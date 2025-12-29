package com.jagratichildrenvidyamandir.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classes")
public class ClassEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer classId;

	@Column(length = 50)
	private String className;

	private Integer fees;

	// One class has many students
	@OneToMany(mappedBy = "studentClass", fetch = FetchType.LAZY)
	private List<User> students = new ArrayList<>();

	@ManyToMany(mappedBy = "classes")
	private List<Teacher> teachers = new ArrayList<>();

	@OneToMany(mappedBy = "classes", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Marks> marks = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "session_id", nullable = false)
	private SessionEntity session;

	// ---------- Constructors ----------
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

	public List<User> getStudents() {
		return students;
	}

	public void setStudents(List<User> students) {
		this.students = students;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public List<Marks> getMarks() {
		return marks;
	}

	public void setMarks(List<Marks> marks) {
		this.marks = marks;
	}

	public SessionEntity getSession() {
		return session;
	}

	public void setSession(SessionEntity session) {
		this.session = session;
	}
	
}
