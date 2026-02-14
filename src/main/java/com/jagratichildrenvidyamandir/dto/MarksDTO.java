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

    private Integer hindiTheoryOutOf;
    private Integer hindiProjectOutOf;
    private Integer hindiTotalOutOf;

    // ================= English =================
    private Integer englishTheory;
    private Integer englishProject;
    private Integer englishTotal;

    private Integer englishTheoryOutOf;
    private Integer englishProjectOutOf;
    private Integer englishTotalOutOf;

    // ================= Maths =================
    private Integer mathsTheory;
    private Integer mathsProject;
    private Integer mathsTotal;

    private Integer mathsTheoryOutOf;
    private Integer mathsProjectOutOf;
    private Integer mathsTotalOutOf;

    // ================= EVS =================
    private Integer evsTheory;
    private Integer evsProject;
    private Integer evsTotal;

    private Integer evsTheoryOutOf;
    private Integer evsProjectOutOf;
    private Integer evsTotalOutOf;

    // ================= Science =================
    private Integer scienceTheory;
    private Integer scienceProject;
    private Integer scienceTotal;

    private Integer scienceTheoryOutOf;
    private Integer scienceProjectOutOf;
    private Integer scienceTotalOutOf;

    // ================= Social Science =================
    private Integer socialScienceTheory;
    private Integer socialScienceProject;
    private Integer socialScienceTotal;

    private Integer socialScienceTheoryOutOf;
    private Integer socialScienceProjectOutOf;
    private Integer socialScienceTotalOutOf;

    // ================= Sanskrit =================
    private Integer sanskritTheory;
    private Integer sanskritProject;
    private Integer sanskritTotal;

    private Integer sanskritTheoryOutOf;
    private Integer sanskritProjectOutOf;
    private Integer sanskritTotalOutOf;

    // ================= Marathi =================
    private Integer marathiTheory;
    private Integer marathiProject;
    private Integer marathiTotal;

    private Integer marathiTheoryOutOf;
    private Integer marathiProjectOutOf;
    private Integer marathiTotalOutOf;

    // ================= GK (NO PROJECT) =================
    private Integer gkTheory;
    private Integer gkTotal;

    private Integer gkTheoryOutOf;
    private Integer gkTotalOutOf;

    // ================= Computer (NO PROJECT) =================
    private Integer computerTheory;
    private Integer computerTotal;

    private Integer computerTheoryOutOf;
    private Integer computerTotalOutOf;

    // ================= Drawing (NO PROJECT) =================
    private Integer drawingTheory;
    private Integer drawingTotal;

    private Integer drawingTheoryOutOf;
    private Integer drawingTotalOutOf;

    // ================= FINAL RESULT =================
    private Integer totalMarks;

    // This will store GRAND TOTAL OUT OF
    private Integer grandTotal;

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

    public Integer getHindiTheoryOutOf() {
        return hindiTheoryOutOf;
    }

    public void setHindiTheoryOutOf(Integer hindiTheoryOutOf) {
        this.hindiTheoryOutOf = hindiTheoryOutOf;
    }

    public Integer getHindiProjectOutOf() {
        return hindiProjectOutOf;
    }

    public void setHindiProjectOutOf(Integer hindiProjectOutOf) {
        this.hindiProjectOutOf = hindiProjectOutOf;
    }

    public Integer getHindiTotalOutOf() {
        return hindiTotalOutOf;
    }

    public void setHindiTotalOutOf(Integer hindiTotalOutOf) {
        this.hindiTotalOutOf = hindiTotalOutOf;
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

    public Integer getEnglishTheoryOutOf() {
        return englishTheoryOutOf;
    }

    public void setEnglishTheoryOutOf(Integer englishTheoryOutOf) {
        this.englishTheoryOutOf = englishTheoryOutOf;
    }

    public Integer getEnglishProjectOutOf() {
        return englishProjectOutOf;
    }

    public void setEnglishProjectOutOf(Integer englishProjectOutOf) {
        this.englishProjectOutOf = englishProjectOutOf;
    }

    public Integer getEnglishTotalOutOf() {
        return englishTotalOutOf;
    }

    public void setEnglishTotalOutOf(Integer englishTotalOutOf) {
        this.englishTotalOutOf = englishTotalOutOf;
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

    public Integer getMathsTheoryOutOf() {
        return mathsTheoryOutOf;
    }

    public void setMathsTheoryOutOf(Integer mathsTheoryOutOf) {
        this.mathsTheoryOutOf = mathsTheoryOutOf;
    }

    public Integer getMathsProjectOutOf() {
        return mathsProjectOutOf;
    }

    public void setMathsProjectOutOf(Integer mathsProjectOutOf) {
        this.mathsProjectOutOf = mathsProjectOutOf;
    }

    public Integer getMathsTotalOutOf() {
        return mathsTotalOutOf;
    }

    public void setMathsTotalOutOf(Integer mathsTotalOutOf) {
        this.mathsTotalOutOf = mathsTotalOutOf;
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

    public Integer getEvsTheoryOutOf() {
        return evsTheoryOutOf;
    }

    public void setEvsTheoryOutOf(Integer evsTheoryOutOf) {
        this.evsTheoryOutOf = evsTheoryOutOf;
    }

    public Integer getEvsProjectOutOf() {
        return evsProjectOutOf;
    }

    public void setEvsProjectOutOf(Integer evsProjectOutOf) {
        this.evsProjectOutOf = evsProjectOutOf;
    }

    public Integer getEvsTotalOutOf() {
        return evsTotalOutOf;
    }

    public void setEvsTotalOutOf(Integer evsTotalOutOf) {
        this.evsTotalOutOf = evsTotalOutOf;
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

    public Integer getScienceTheoryOutOf() {
        return scienceTheoryOutOf;
    }

    public void setScienceTheoryOutOf(Integer scienceTheoryOutOf) {
        this.scienceTheoryOutOf = scienceTheoryOutOf;
    }

    public Integer getScienceProjectOutOf() {
        return scienceProjectOutOf;
    }

    public void setScienceProjectOutOf(Integer scienceProjectOutOf) {
        this.scienceProjectOutOf = scienceProjectOutOf;
    }

    public Integer getScienceTotalOutOf() {
        return scienceTotalOutOf;
    }

    public void setScienceTotalOutOf(Integer scienceTotalOutOf) {
        this.scienceTotalOutOf = scienceTotalOutOf;
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

    public Integer getSocialScienceTheoryOutOf() {
        return socialScienceTheoryOutOf;
    }

    public void setSocialScienceTheoryOutOf(Integer socialScienceTheoryOutOf) {
        this.socialScienceTheoryOutOf = socialScienceTheoryOutOf;
    }

    public Integer getSocialScienceProjectOutOf() {
        return socialScienceProjectOutOf;
    }

    public void setSocialScienceProjectOutOf(Integer socialScienceProjectOutOf) {
        this.socialScienceProjectOutOf = socialScienceProjectOutOf;
    }

    public Integer getSocialScienceTotalOutOf() {
        return socialScienceTotalOutOf;
    }

    public void setSocialScienceTotalOutOf(Integer socialScienceTotalOutOf) {
        this.socialScienceTotalOutOf = socialScienceTotalOutOf;
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

    public Integer getSanskritTheoryOutOf() {
        return sanskritTheoryOutOf;
    }

    public void setSanskritTheoryOutOf(Integer sanskritTheoryOutOf) {
        this.sanskritTheoryOutOf = sanskritTheoryOutOf;
    }

    public Integer getSanskritProjectOutOf() {
        return sanskritProjectOutOf;
    }

    public void setSanskritProjectOutOf(Integer sanskritProjectOutOf) {
        this.sanskritProjectOutOf = sanskritProjectOutOf;
    }

    public Integer getSanskritTotalOutOf() {
        return sanskritTotalOutOf;
    }

    public void setSanskritTotalOutOf(Integer sanskritTotalOutOf) {
        this.sanskritTotalOutOf = sanskritTotalOutOf;
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

    public Integer getMarathiTheoryOutOf() {
        return marathiTheoryOutOf;
    }

    public void setMarathiTheoryOutOf(Integer marathiTheoryOutOf) {
        this.marathiTheoryOutOf = marathiTheoryOutOf;
    }

    public Integer getMarathiProjectOutOf() {
        return marathiProjectOutOf;
    }

    public void setMarathiProjectOutOf(Integer marathiProjectOutOf) {
        this.marathiProjectOutOf = marathiProjectOutOf;
    }

    public Integer getMarathiTotalOutOf() {
        return marathiTotalOutOf;
    }

    public void setMarathiTotalOutOf(Integer marathiTotalOutOf) {
        this.marathiTotalOutOf = marathiTotalOutOf;
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

    public Integer getGkTheoryOutOf() {
        return gkTheoryOutOf;
    }

    public void setGkTheoryOutOf(Integer gkTheoryOutOf) {
        this.gkTheoryOutOf = gkTheoryOutOf;
    }

    public Integer getGkTotalOutOf() {
        return gkTotalOutOf;
    }

    public void setGkTotalOutOf(Integer gkTotalOutOf) {
        this.gkTotalOutOf = gkTotalOutOf;
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

    public Integer getComputerTheoryOutOf() {
        return computerTheoryOutOf;
    }

    public void setComputerTheoryOutOf(Integer computerTheoryOutOf) {
        this.computerTheoryOutOf = computerTheoryOutOf;
    }

    public Integer getComputerTotalOutOf() {
        return computerTotalOutOf;
    }

    public void setComputerTotalOutOf(Integer computerTotalOutOf) {
        this.computerTotalOutOf = computerTotalOutOf;
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

    public Integer getDrawingTheoryOutOf() {
        return drawingTheoryOutOf;
    }

    public void setDrawingTheoryOutOf(Integer drawingTheoryOutOf) {
        this.drawingTheoryOutOf = drawingTheoryOutOf;
    }

    public Integer getDrawingTotalOutOf() {
        return drawingTotalOutOf;
    }

    public void setDrawingTotalOutOf(Integer drawingTotalOutOf) {
        this.drawingTotalOutOf = drawingTotalOutOf;
    }

    // ================= FINAL RESULT =================
    public Integer getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(Integer totalMarks) {
        this.totalMarks = totalMarks;
    }

    public Integer getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Integer grandTotal) {
        this.grandTotal = grandTotal;
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
