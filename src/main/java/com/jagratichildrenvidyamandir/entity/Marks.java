package com.jagratichildrenvidyamandir.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "marks")
public class Marks {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer marksId;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;

	@ManyToOne
	@JoinColumn(name = "class_id")
	private ClassEntity classes;

	private Integer hindi;
	private Integer english;
	private Integer maths;
	private Integer science;
	private Integer socialScience;
	private Integer evs;
	private Integer computer;
	private Integer gk;
	private Integer drawing;
	private Integer sanskrit;

	private Integer totalMarks;
	private Double percentage;
	private String grade;
	private String status;
	// ✅ Exam
	private String examType;
	// ✅ SESSION (NEW FK)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "session_id", nullable = false)
	private SessionEntity session;

	private LocalDateTime createdDate = LocalDateTime.now();

	private Integer marathi;

	// Getters & setters
	public Integer getMarksId() {
		return marksId;
	}

	public void setMarksId(Integer marksId) {
		this.marksId = marksId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public ClassEntity getClasses() {
		return classes;
	}

	public void setClasses(ClassEntity classes) {
		this.classes = classes;
	}

	public SessionEntity getSession() {
		return session;
	}

	public void setSession(SessionEntity session) {
		this.session = session;
	}

	public Integer getMarathi() {
		return marathi;
	}

	public void setMarathi(Integer marathi) {
		this.marathi = marathi;
	}

	public Integer getHindi() {
		return hindi;
	}

	public void setHindi(Integer hindi) {
		this.hindi = hindi;
	}

	public Integer getEnglish() {
		return english;
	}

	public void setEnglish(Integer english) {
		this.english = english;
	}

	public Integer getSanskrit() {
		return sanskrit;
	}

	public void setSanskrit(Integer sanskrit) {
		this.sanskrit = sanskrit;
	}

	public Integer getMaths() {
		return maths;
	}

	public void setMaths(Integer maths) {
		this.maths = maths;
	}

	public Integer getEvs() {
		return evs;
	}

	public void setEvs(Integer evs) {
		this.evs = evs;
	}

	public Integer getScience() {
		return science;
	}

	public void setScience(Integer science) {
		this.science = science;
	}

	public Integer getSocialScience() {
		return socialScience;
	}

	public void setSocialScience(Integer socialScience) {
		this.socialScience = socialScience;
	}

	public Integer getComputer() {
		return computer;
	}

	public void setComputer(Integer computer) {
		this.computer = computer;
	}

	public Integer getGk() {
		return gk;
	}

	public void setGk(Integer gk) {
		this.gk = gk;
	}

	public Integer getDrawing() {
		return drawing;
	}

	public void setDrawing(Integer drawing) {
		this.drawing = drawing;
	}

	public Integer getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(Integer totalMarks) {
		this.totalMarks = totalMarks;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

}