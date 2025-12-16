package com.jagratichildrenvidyamandir.dto;

public class MarksDTO {

    private Integer marksId;
    private Integer teacherId;
    private Integer classId;
    private Integer studentId;

    private String examType;
    private Integer month;
    private Integer quarter;
    private Integer year;

    private Integer marathi;
    private Integer hindi;
    private Integer english;
    private Integer sanskrit;
    private Integer maths;
    private Integer science;
    private Integer history;
    private Integer geography;

    private Integer totalMarks;
    private String grade;

    // Getters & Setters

    public Integer getMarksId() { return marksId; }
    public void setMarksId(Integer marksId) { this.marksId = marksId; }

    public Integer getTeacherId() { return teacherId; }
    public void setTeacherId(Integer teacherId) { this.teacherId = teacherId; }

    public Integer getClassId() { return classId; }
    public void setClassId(Integer classId) { this.classId = classId; }

    public Integer getStudentId() { return studentId; }
    public void setStudentId(Integer studentId) { this.studentId = studentId; }

    public String getExamType() { return examType; }
    public void setExamType(String examType) { this.examType = examType; }

    public Integer getMonth() { return month; }
    public void setMonth(Integer month) { this.month = month; }

    public Integer getQuarter() { return quarter; }
    public void setQuarter(Integer quarter) { this.quarter = quarter; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

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

    public Integer getScience() { return science; }
    public void setScience(Integer science) { this.science = science; }

    public Integer getHistory() { return history; }
    public void setHistory(Integer history) { this.history = history; }

    public Integer getGeography() { return geography; }
    public void setGeography(Integer geography) { this.geography = geography; }

    public Integer getTotalMarks() { return totalMarks; }
    public void setTotalMarks(Integer totalMarks) { this.totalMarks = totalMarks; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
}
