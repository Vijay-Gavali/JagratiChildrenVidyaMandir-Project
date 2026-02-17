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

    // ================= Hindi =================
    private Integer hindiTheory;
    private Integer hindiProject;
    private Integer hindiTotal;

    @Column(name = "hindi_theory_outof")
    private Integer hindiTheoryOutof;

    @Column(name = "hindi_project_outof")
    private Integer hindiProjectOutof;

    @Column(name = "hindi_total_outof")
    private Integer hindiTotalOutof;

    // ================= English =================
    private Integer englishTheory;
    private Integer englishProject;
    private Integer englishTotal;

    @Column(name = "english_theory_outof")
    private Integer englishTheoryOutof;

    @Column(name = "english_project_outof")
    private Integer englishProjectOutof;

    @Column(name = "english_total_outof")
    private Integer englishTotalOutof;

    // ================= Maths =================
    private Integer mathsTheory;
    private Integer mathsProject;
    private Integer mathsTotal;

    @Column(name = "maths_theory_outof")
    private Integer mathsTheoryOutof;

    @Column(name = "maths_project_outof")
    private Integer mathsProjectOutof;

    @Column(name = "maths_total_outof")
    private Integer mathsTotalOutof;

    // ================= EVS =================
    private Integer evsTheory;
    private Integer evsProject;
    private Integer evsTotal;

    @Column(name = "evs_theory_outof")
    private Integer evsTheoryOutof;

    @Column(name = "evs_project_outof")
    private Integer evsProjectOutof;

    @Column(name = "evs_total_outof")
    private Integer evsTotalOutof;

    // ================= Science =================
    private Integer scienceTheory;
    private Integer scienceProject;
    private Integer scienceTotal;

    @Column(name = "science_theory_outof")
    private Integer scienceTheoryOutof;

    @Column(name = "science_project_outof")
    private Integer scienceProjectOutof;

    @Column(name = "science_total_outof")
    private Integer scienceTotalOutof;

    // ================= Social Science =================
    private Integer socialScienceTheory;
    private Integer socialScienceProject;
    private Integer socialScienceTotal;

    @Column(name = "social_science_theory_outof")
    private Integer socialScienceTheoryOutof;

    @Column(name = "social_science_project_outof")
    private Integer socialScienceProjectOutof;

    @Column(name = "social_science_total_outof")
    private Integer socialScienceTotalOutof;

    // ================= Sanskrit =================
    private Integer sanskritTheory;
    private Integer sanskritProject;
    private Integer sanskritTotal;

    @Column(name = "sanskrit_theory_outof")
    private Integer sanskritTheoryOutof;

    @Column(name = "sanskrit_project_outof")
    private Integer sanskritProjectOutof;

    @Column(name = "sanskrit_total_outof")
    private Integer sanskritTotalOutof;

    // ================= Marathi =================
    private Integer marathiTheory;
    private Integer marathiProject;
    private Integer marathiTotal;

    @Column(name = "marathi_theory_outof")
    private Integer marathiTheoryOutof;

    @Column(name = "marathi_project_outof")
    private Integer marathiProjectOutof;

    @Column(name = "marathi_total_outof")
    private Integer marathiTotalOutof;

    // ================= GK (GRADE) =================
    private String gkTheory;
    private String gkTotal;

    @Column(name = "gk_theory_outof")
    private Integer gkTheoryOutof;

    @Column(name = "gk_total_outof")
    private Integer gkTotalOutof;

    // ================= Computer (GRADE) =================
    private String computerTheory;
    private String computerTotal;

    @Column(name = "computer_theory_outof")
    private Integer computerTheoryOutof;

    @Column(name = "computer_total_outof")
    private Integer computerTotalOutof;

    // ================= Drawing (GRADE) =================
    private String drawingTheory;
    private String drawingTotal;

    @Column(name = "drawing_theory_outof")
    private Integer drawingTheoryOutof;

    @Column(name = "drawing_total_outof")
    private Integer drawingTotalOutof;

    // ================= FINAL RESULT =================
    private Integer totalMarks;

    // now it will store GRAND OUT OF
    private Integer grandTotal;

    private Double percentage;
    private String grade;
    private String status;

    private String examType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private SessionEntity session;

    private LocalDateTime createdDate = LocalDateTime.now();

    // ================= GETTERS & SETTERS =================

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

    public Integer getHindiTheoryOutof() {
        return hindiTheoryOutof;
    }

    public void setHindiTheoryOutof(Integer hindiTheoryOutof) {
        this.hindiTheoryOutof = hindiTheoryOutof;
    }

    public Integer getHindiProjectOutof() {
        return hindiProjectOutof;
    }

    public void setHindiProjectOutof(Integer hindiProjectOutof) {
        this.hindiProjectOutof = hindiProjectOutof;
    }

    public Integer getHindiTotalOutof() {
        return hindiTotalOutof;
    }

    public void setHindiTotalOutof(Integer hindiTotalOutof) {
        this.hindiTotalOutof = hindiTotalOutof;
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

    public Integer getEnglishTheoryOutof() {
        return englishTheoryOutof;
    }

    public void setEnglishTheoryOutof(Integer englishTheoryOutof) {
        this.englishTheoryOutof = englishTheoryOutof;
    }

    public Integer getEnglishProjectOutof() {
        return englishProjectOutof;
    }

    public void setEnglishProjectOutof(Integer englishProjectOutof) {
        this.englishProjectOutof = englishProjectOutof;
    }

    public Integer getEnglishTotalOutof() {
        return englishTotalOutof;
    }

    public void setEnglishTotalOutof(Integer englishTotalOutof) {
        this.englishTotalOutof = englishTotalOutof;
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

    public Integer getMathsTheoryOutof() {
        return mathsTheoryOutof;
    }

    public void setMathsTheoryOutof(Integer mathsTheoryOutof) {
        this.mathsTheoryOutof = mathsTheoryOutof;
    }

    public Integer getMathsProjectOutof() {
        return mathsProjectOutof;
    }

    public void setMathsProjectOutof(Integer mathsProjectOutof) {
        this.mathsProjectOutof = mathsProjectOutof;
    }

    public Integer getMathsTotalOutof() {
        return mathsTotalOutof;
    }

    public void setMathsTotalOutof(Integer mathsTotalOutof) {
        this.mathsTotalOutof = mathsTotalOutof;
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

    public Integer getEvsTheoryOutof() {
        return evsTheoryOutof;
    }

    public void setEvsTheoryOutof(Integer evsTheoryOutof) {
        this.evsTheoryOutof = evsTheoryOutof;
    }

    public Integer getEvsProjectOutof() {
        return evsProjectOutof;
    }

    public void setEvsProjectOutof(Integer evsProjectOutof) {
        this.evsProjectOutof = evsProjectOutof;
    }

    public Integer getEvsTotalOutof() {
        return evsTotalOutof;
    }

    public void setEvsTotalOutof(Integer evsTotalOutof) {
        this.evsTotalOutof = evsTotalOutof;
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

    public Integer getScienceTheoryOutof() {
        return scienceTheoryOutof;
    }

    public void setScienceTheoryOutof(Integer scienceTheoryOutof) {
        this.scienceTheoryOutof = scienceTheoryOutof;
    }

    public Integer getScienceProjectOutof() {
        return scienceProjectOutof;
    }

    public void setScienceProjectOutof(Integer scienceProjectOutof) {
        this.scienceProjectOutof = scienceProjectOutof;
    }

    public Integer getScienceTotalOutof() {
        return scienceTotalOutof;
    }

    public void setScienceTotalOutof(Integer scienceTotalOutof) {
        this.scienceTotalOutof = scienceTotalOutof;
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

    public Integer getSocialScienceTheoryOutof() {
        return socialScienceTheoryOutof;
    }

    public void setSocialScienceTheoryOutof(Integer socialScienceTheoryOutof) {
        this.socialScienceTheoryOutof = socialScienceTheoryOutof;
    }

    public Integer getSocialScienceProjectOutof() {
        return socialScienceProjectOutof;
    }

    public void setSocialScienceProjectOutof(Integer socialScienceProjectOutof) {
        this.socialScienceProjectOutof = socialScienceProjectOutof;
    }

    public Integer getSocialScienceTotalOutof() {
        return socialScienceTotalOutof;
    }

    public void setSocialScienceTotalOutof(Integer socialScienceTotalOutof) {
        this.socialScienceTotalOutof = socialScienceTotalOutof;
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

    public Integer getSanskritTheoryOutof() {
        return sanskritTheoryOutof;
    }

    public void setSanskritTheoryOutof(Integer sanskritTheoryOutof) {
        this.sanskritTheoryOutof = sanskritTheoryOutof;
    }

    public Integer getSanskritProjectOutof() {
        return sanskritProjectOutof;
    }

    public void setSanskritProjectOutof(Integer sanskritProjectOutof) {
        this.sanskritProjectOutof = sanskritProjectOutof;
    }

    public Integer getSanskritTotalOutof() {
        return sanskritTotalOutof;
    }

    public void setSanskritTotalOutof(Integer sanskritTotalOutof) {
        this.sanskritTotalOutof = sanskritTotalOutof;
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

    public Integer getMarathiTheoryOutof() {
        return marathiTheoryOutof;
    }

    public void setMarathiTheoryOutof(Integer marathiTheoryOutof) {
        this.marathiTheoryOutof = marathiTheoryOutof;
    }

    public Integer getMarathiProjectOutof() {
        return marathiProjectOutof;
    }

    public void setMarathiProjectOutof(Integer marathiProjectOutof) {
        this.marathiProjectOutof = marathiProjectOutof;
    }

    public Integer getMarathiTotalOutof() {
        return marathiTotalOutof;
    }

    public void setMarathiTotalOutof(Integer marathiTotalOutof) {
        this.marathiTotalOutof = marathiTotalOutof;
    }

    // ================= GK =================
    public String getGkTheory() {
        return gkTheory;
    }

    public void setGkTheory(String gkTheory) {
        this.gkTheory = gkTheory;
    }

    public String getGkTotal() {
        return gkTotal;
    }

    public void setGkTotal(String gkTotal) {
        this.gkTotal = gkTotal;
    }

    public Integer getGkTheoryOutof() {
        return gkTheoryOutof;
    }

    public void setGkTheoryOutof(Integer gkTheoryOutof) {
        this.gkTheoryOutof = gkTheoryOutof;
    }

    public Integer getGkTotalOutof() {
        return gkTotalOutof;
    }

    public void setGkTotalOutof(Integer gkTotalOutof) {
        this.gkTotalOutof = gkTotalOutof;
    }

    // ================= Computer =================
    public String getComputerTheory() {
        return computerTheory;
    }

    public void setComputerTheory(String computerTheory) {
        this.computerTheory = computerTheory;
    }

    public String getComputerTotal() {
        return computerTotal;
    }

    public void setComputerTotal(String computerTotal) {
        this.computerTotal = computerTotal;
    }

    public Integer getComputerTheoryOutof() {
        return computerTheoryOutof;
    }

    public void setComputerTheoryOutof(Integer computerTheoryOutof) {
        this.computerTheoryOutof = computerTheoryOutof;
    }

    public Integer getComputerTotalOutof() {
        return computerTotalOutof;
    }

    public void setComputerTotalOutof(Integer computerTotalOutof) {
        this.computerTotalOutof = computerTotalOutof;
    }

    // ================= Drawing =================
    public String getDrawingTheory() {
        return drawingTheory;
    }

    public void setDrawingTheory(String drawingTheory) {
        this.drawingTheory = drawingTheory;
    }

    public String getDrawingTotal() {
        return drawingTotal;
    }

    public void setDrawingTotal(String drawingTotal) {
        this.drawingTotal = drawingTotal;
    }

    public Integer getDrawingTheoryOutof() {
        return drawingTheoryOutof;
    }

    public void setDrawingTheoryOutof(Integer drawingTheoryOutof) {
        this.drawingTheoryOutof = drawingTheoryOutof;
    }

    public Integer getDrawingTotalOutof() {
        return drawingTotalOutof;
    }

    public void setDrawingTotalOutof(Integer drawingTotalOutof) {
        this.drawingTotalOutof = drawingTotalOutof;
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
}
