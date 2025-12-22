package com.jagratichildrenvidyamandir.dto;

import java.time.LocalDateTime;

public class MarksDTO {

    private Integer marksId;
    private Integer studentId;
    private String studentName;   // Added
    private Integer teacherId;
    private String teacherName;   // Added
    private Integer classId;
    private String className;
    private Integer sessionId;
    private String sessionName;
    private String examType;   
    private Integer marathi;
    private Integer hindi;
    private Integer english;
    private Integer sanskrit;
    private Integer maths;
    private Integer evs;
    private Integer science;
    private Integer socialScience;
    private Integer computer;
    private Integer gk;
    private Integer drawing;

    private Integer totalMarks;
    private Double percentage;
    private String grade;
    private String status;
    private UserDTO user; // full user info
    private LocalDateTime createdDate;

    // Getters and Setters
    public Integer getMarksId() { return marksId; }
    public void setMarksId(Integer marksId) { this.marksId = marksId; }
    public Integer getStudentId() { return studentId; }
    public void setStudentId(Integer studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public Integer getTeacherId() { return teacherId; }
    public void setTeacherId(Integer teacherId) { this.teacherId = teacherId; }

    public String getTeacherName() { return teacherName; }
    public void setTeacherName(String teacherName) { this.teacherName = teacherName; }

    public Integer getClassId() { return classId; }
    public void setClassId(Integer classId) { this.classId = classId; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }

    public Integer getMarathi() { return marathi; }
    public void setMarathi(Integer marathi) { this.marathi = marathi; }

    public Integer getHindi() { return hindi; }
    public void setHindi(Integer hindi) { this.hindi = hindi; }

    public Integer getEnglish() { return english; }
    public void setEnglish(Integer english) { this.english = english; }

    public Integer getSanskrit() { return sanskrit; }
    public void setSanskrit(Integer sanskrit) { this.sanskrit = sanskrit; }

    public Integer getMaths() { return maths; }
    public void setMaths(Integer maths) { this.maths = maths; }
    public UserDTO getUser() { return user; }
    public void setUser(UserDTO user) { this.user = user; }

    public Integer getEvs() { return evs; }
    public void setEvs(Integer evs) { this.evs = evs; }

    public Integer getScience() { return science; }
    public void setScience(Integer science) { this.science = science; }

    public Integer getSocialScience() { return socialScience; }
    public void setSocialScience(Integer socialScience) { this.socialScience = socialScience; }

    public Integer getComputer() { return computer; }
    public void setComputer(Integer computer) { this.computer = computer; }

    public Integer getGk() { return gk; }
    public void setGk(Integer gk) { this.gk = gk; }

    public Integer getDrawing() { return drawing; }
    public void setDrawing(Integer drawing) { this.drawing = drawing; }

    public Integer getTotalMarks() { return totalMarks; }
    public void setTotalMarks(Integer totalMarks) { this.totalMarks = totalMarks; }

    public Double getPercentage() { return percentage; }
    public void setPercentage(Double percentage) { this.percentage = percentage; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }
    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }
    public Integer getSessionId() { return sessionId; }
    public void setSessionId(Integer sessionId) { this.sessionId = sessionId; }

    public String getSessionName() { return sessionName; }
    public void setSessionName(String sessionName) { this.sessionName = sessionName; }

}
