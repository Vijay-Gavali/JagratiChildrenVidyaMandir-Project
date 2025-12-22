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

        // ✅ Exam
        dto.setExamType(marks.getExamType());

        // ✅ Subject Marks (INCLUDING MARATHI)
        dto.setMarathi(marks.getMarathi());
        dto.setHindi(marks.getHindi());
        dto.setEnglish(marks.getEnglish());
        dto.setMaths(marks.getMaths());
        dto.setScience(marks.getScience());
        dto.setSocialScience(marks.getSocialScience());
        dto.setEvs(marks.getEvs());
        dto.setComputer(marks.getComputer());
        dto.setGk(marks.getGk());
        dto.setDrawing(marks.getDrawing());
        dto.setSanskrit(marks.getSanskrit());

        // ✅ Result
        dto.setTotalMarks(marks.getTotalMarks());
        dto.setPercentage(marks.getPercentage());
        dto.setGrade(marks.getGrade());
        dto.setStatus(marks.getStatus());
        dto.setCreatedDate(marks.getCreatedDate());

        return dto;
    }
}