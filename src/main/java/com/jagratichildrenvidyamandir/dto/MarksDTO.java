package com.jagratichildrenvidyamandir.dto;

import java.time.LocalDateTime;

public class MarksDTO {

    private Integer marksId;

    private Integer studentId;
    private String studentName;

    private Integer teacherId;
    private String teacherName;

    private Integer classId;
    private String className;

    private Integer sessionId;
    private String sessionName;

    private String examType;

    // ================= Hindi =================
    private Integer hindiTheory;
    private Integer hindiProject;
    private Integer hindiTotal;

    // ================= English =================
    private Integer englishTheory;
    private Integer englishProject;
    private Integer englishTotal;

    // ================= Maths =================
    private Integer mathsTheory;
    private Integer mathsProject;
    private Integer mathsTotal;

    // ================= EVS =================
    private Integer evsTheory;
    private Integer evsProject;
    private Integer evsTotal;

    // ================= Science =================
    private Integer scienceTheory;
    private Integer scienceProject;
    private Integer scienceTotal;

    // ================= Social Science =================
    private Integer socialScienceTheory;
    private Integer socialScienceProject;
    private Integer socialScienceTotal;

    // ================= Sanskrit =================
    private Integer sanskritTheory;
    private Integer sanskritProject;
    private Integer sanskritTotal;

    // ================= Marathi =================
    private Integer marathiTheory;
    private Integer marathiProject;
    private Integer marathiTotal;

    // ================= GK (NO PROJECT) =================
    private Integer gkTheory;
    private Integer gkTotal;

    // ================= Computer (NO PROJECT) =================
    private Integer computerTheory;
    private Integer computerTotal;

    // ================= Drawing (NO PROJECT) =================
    private Integer drawingTheory;
    private Integer drawingTotal;

    // ================= FINAL RESULT =================
    private Integer totalMarks;
    private Double percentage;
    private String grade;
    private String status;

    private LocalDateTime createdDate;

    // ================= GETTERS & SETTERS =================

    public Integer getMarksId() {
        return marksId;
    }

    public void setMarksId(Integer marksId) {
        this.marksId = marksId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

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

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    // ================= Hindi =================
    public Integer getHindiTheory() {
        return hindiTheory;
    }

    public void setHindiTheory(Integer hindiTheory) {
        this.hindiTheory = hindiTheory;
    }

    public Integer getHindiProject() {
        return hindiProject;
    }

    public void setHindiProject(Integer hindiProject) {
        this.hindiProject = hindiProject;
    }

    public Integer getHindiTotal() {
        return hindiTotal;
    }

    public void setHindiTotal(Integer hindiTotal) {
        this.hindiTotal = hindiTotal;
    }

    // ================= English =================
    public Integer getEnglishTheory() {
        return englishTheory;
    }

    public void setEnglishTheory(Integer englishTheory) {
        this.englishTheory = englishTheory;
    }

    public Integer getEnglishProject() {
        return englishProject;
    }

    public void setEnglishProject(Integer englishProject) {
        this.englishProject = englishProject;
    }

    public Integer getEnglishTotal() {
        return englishTotal;
    }

    public void setEnglishTotal(Integer englishTotal) {
        this.englishTotal = englishTotal;
    }

    // ================= Maths =================
    public Integer getMathsTheory() {
        return mathsTheory;
    }

    public void setMathsTheory(Integer mathsTheory) {
        this.mathsTheory = mathsTheory;
    }

    public Integer getMathsProject() {
        return mathsProject;
    }

    public void setMathsProject(Integer mathsProject) {
        this.mathsProject = mathsProject;
    }

    public Integer getMathsTotal() {
        return mathsTotal;
    }

    public void setMathsTotal(Integer mathsTotal) {
        this.mathsTotal = mathsTotal;
    }

    // ================= EVS =================
    public Integer getEvsTheory() {
        return evsTheory;
    }

    public void setEvsTheory(Integer evsTheory) {
        this.evsTheory = evsTheory;
    }

    public Integer getEvsProject() {
        return evsProject;
    }

    public void setEvsProject(Integer evsProject) {
        this.evsProject = evsProject;
    }

    public Integer getEvsTotal() {
        return evsTotal;
    }

    public void setEvsTotal(Integer evsTotal) {
        this.evsTotal = evsTotal;
    }

    // ================= Science =================
    public Integer getScienceTheory() {
        return scienceTheory;
    }

    public void setScienceTheory(Integer scienceTheory) {
        this.scienceTheory = scienceTheory;
    }

    public Integer getScienceProject() {
        return scienceProject;
    }

    public void setScienceProject(Integer scienceProject) {
        this.scienceProject = scienceProject;
    }

    public Integer getScienceTotal() {
        return scienceTotal;
    }

    public void setScienceTotal(Integer scienceTotal) {
        this.scienceTotal = scienceTotal;
    }

    // ================= Social Science =================
    public Integer getSocialScienceTheory() {
        return socialScienceTheory;
    }

    public void setSocialScienceTheory(Integer socialScienceTheory) {
        this.socialScienceTheory = socialScienceTheory;
    }

    public Integer getSocialScienceProject() {
        return socialScienceProject;
    }

    public void setSocialScienceProject(Integer socialScienceProject) {
        this.socialScienceProject = socialScienceProject;
    }

    public Integer getSocialScienceTotal() {
        return socialScienceTotal;
    }

    public void setSocialScienceTotal(Integer socialScienceTotal) {
        this.socialScienceTotal = socialScienceTotal;
    }

    // ================= Sanskrit =================
    public Integer getSanskritTheory() {
        return sanskritTheory;
    }

    public void setSanskritTheory(Integer sanskritTheory) {
        this.sanskritTheory = sanskritTheory;
    }

    public Integer getSanskritProject() {
        return sanskritProject;
    }

    public void setSanskritProject(Integer sanskritProject) {
        this.sanskritProject = sanskritProject;
    }

    public Integer getSanskritTotal() {
        return sanskritTotal;
    }

    public void setSanskritTotal(Integer sanskritTotal) {
        this.sanskritTotal = sanskritTotal;
    }

    // ================= Marathi =================
    public Integer getMarathiTheory() {
        return marathiTheory;
    }

    public void setMarathiTheory(Integer marathiTheory) {
        this.marathiTheory = marathiTheory;
    }

    public Integer getMarathiProject() {
        return marathiProject;
    }

    public void setMarathiProject(Integer marathiProject) {
        this.marathiProject = marathiProject;
    }

    public Integer getMarathiTotal() {
        return marathiTotal;
    }

    public void setMarathiTotal(Integer marathiTotal) {
        this.marathiTotal = marathiTotal;
    }

    // ================= GK =================
    public Integer getGkTheory() {
        return gkTheory;
    }

    public void setGkTheory(Integer gkTheory) {
        this.gkTheory = gkTheory;
    }

    public Integer getGkTotal() {
        return gkTotal;
    }

    public void setGkTotal(Integer gkTotal) {
        this.gkTotal = gkTotal;
    }

    // ================= Computer =================
    public Integer getComputerTheory() {
        return computerTheory;
    }

    public void setComputerTheory(Integer computerTheory) {
        this.computerTheory = computerTheory;
    }

    public Integer getComputerTotal() {
        return computerTotal;
    }

    public void setComputerTotal(Integer computerTotal) {
        this.computerTotal = computerTotal;
    }

    // ================= Drawing =================
    public Integer getDrawingTheory() {
        return drawingTheory;
    }

    public void setDrawingTheory(Integer drawingTheory) {
        this.drawingTheory = drawingTheory;
    }

    public Integer getDrawingTotal() {
        return drawingTotal;
    }

    public void setDrawingTotal(Integer drawingTotal) {
        this.drawingTotal = drawingTotal;
    }

    // ================= FINAL RESULT =================
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
}
