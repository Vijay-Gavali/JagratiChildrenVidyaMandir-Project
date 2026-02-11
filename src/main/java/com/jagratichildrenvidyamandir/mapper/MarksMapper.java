package com.jagratichildrenvidyamandir.mapper;

import com.jagratichildrenvidyamandir.dto.MarksDTO;
import com.jagratichildrenvidyamandir.entity.Marks;
import org.springframework.stereotype.Component;

@Component
public class MarksMapper {

    public MarksDTO toDTO(Marks marks) {

        MarksDTO dto = new MarksDTO();

        if (marks == null) return dto;

        dto.setMarksId(marks.getMarksId());

        // ✅ Student
        if (marks.getUser() != null) {
            dto.setStudentId(marks.getUser().getUserId());
            dto.setStudentName(marks.getUser().getName());
        }

        // ✅ Teacher
        if (marks.getTeacher() != null) {
            dto.setTeacherId(marks.getTeacher().getTeacherId());
            dto.setTeacherName(marks.getTeacher().getName());
        }

        // ✅ Class
        if (marks.getClasses() != null) {
            dto.setClassId(marks.getClasses().getClassId());
            dto.setClassName(marks.getClasses().getClassName());
        }

        // ✅ Session
        if (marks.getSession() != null) {
            dto.setSessionId(marks.getSession().getSessionId());
            dto.setSessionName(marks.getSession().getName());
        }

        // ✅ Exam Type
        dto.setExamType(marks.getExamType());

        // ================= Hindi =================
        dto.setHindiTheory(marks.getHindiTheory());
        dto.setHindiProject(marks.getHindiProject());
        dto.setHindiTotal(marks.getHindiTotal());

        // ================= English =================
        dto.setEnglishTheory(marks.getEnglishTheory());
        dto.setEnglishProject(marks.getEnglishProject());
        dto.setEnglishTotal(marks.getEnglishTotal());

        // ================= Maths =================
        dto.setMathsTheory(marks.getMathsTheory());
        dto.setMathsProject(marks.getMathsProject());
        dto.setMathsTotal(marks.getMathsTotal());

        // ================= EVS =================
        dto.setEvsTheory(marks.getEvsTheory());
        dto.setEvsProject(marks.getEvsProject());
        dto.setEvsTotal(marks.getEvsTotal());

        // ================= Science =================
        dto.setScienceTheory(marks.getScienceTheory());
        dto.setScienceProject(marks.getScienceProject());
        dto.setScienceTotal(marks.getScienceTotal());

        // ================= Social Science =================
        dto.setSocialScienceTheory(marks.getSocialScienceTheory());
        dto.setSocialScienceProject(marks.getSocialScienceProject());
        dto.setSocialScienceTotal(marks.getSocialScienceTotal());

        // ================= Sanskrit =================
        dto.setSanskritTheory(marks.getSanskritTheory());
        dto.setSanskritProject(marks.getSanskritProject());
        dto.setSanskritTotal(marks.getSanskritTotal());

        // ================= Marathi =================
        dto.setMarathiTheory(marks.getMarathiTheory());
        dto.setMarathiProject(marks.getMarathiProject());
        dto.setMarathiTotal(marks.getMarathiTotal());

        // ================= GK (NO PROJECT) =================
        dto.setGkTheory(marks.getGkTheory());
        dto.setGkTotal(marks.getGkTotal());

        // ================= Computer (NO PROJECT) =================
        dto.setComputerTheory(marks.getComputerTheory());
        dto.setComputerTotal(marks.getComputerTotal());

        // ================= Drawing (NO PROJECT) =================
        dto.setDrawingTheory(marks.getDrawingTheory());
        dto.setDrawingTotal(marks.getDrawingTotal());

        // ================= FINAL RESULT =================
        dto.setTotalMarks(marks.getTotalMarks());
        dto.setPercentage(marks.getPercentage());
        dto.setGrade(marks.getGrade());
        dto.setStatus(marks.getStatus());
        dto.setCreatedDate(marks.getCreatedDate());

        return dto;
    }
}
