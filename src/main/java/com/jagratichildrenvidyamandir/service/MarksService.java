package com.jagratichildrenvidyamandir.service;

import com.jagratichildrenvidyamandir.dto.MarksDTO;
import com.jagratichildrenvidyamandir.entity.*;
import com.jagratichildrenvidyamandir.mapper.MarksMapper;
import com.jagratichildrenvidyamandir.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarksService {

    @Autowired private MarksRepository marksRepo;
    @Autowired private UserRepository userRepo;
    @Autowired private TeacherRepository teacherRepo;
    @Autowired private ClassRepository classRepo;
    @Autowired private SessionRepository sessionRepo;
    @Autowired private MarksMapper mapper;

    // ================= BULK ADD =================
    public List<MarksDTO> addBulkMarks(List<MarksDTO> dtoList) {

        return dtoList.stream().map(dto -> {

            if (dto.getStudentId() == null ||
                dto.getTeacherId() == null ||
                dto.getClassId() == null ||
                dto.getSessionId() == null) {
                throw new IllegalArgumentException(
                    "studentId, teacherId, classId, sessionId must not be null"
                );
            }

            Marks marks = new Marks();

            marks.setUser(
                userRepo.findById(dto.getStudentId())
                    .orElseThrow(() -> new RuntimeException("User not found: " + dto.getStudentId()))
            );

            marks.setTeacher(
                teacherRepo.findById(dto.getTeacherId())
                    .orElseThrow(() -> new RuntimeException("Teacher not found: " + dto.getTeacherId()))
            );

            marks.setClasses(
                classRepo.findById(dto.getClassId())
                    .orElseThrow(() -> new RuntimeException("Class not found: " + dto.getClassId()))
            );

            marks.setSession(
                sessionRepo.findById(dto.getSessionId())
                    .orElseThrow(() -> new RuntimeException("Session not found: " + dto.getSessionId()))
            );

            marks.setExamType(dto.getExamType());

            // ================= MARKS =================
            marks.setHindiTheory(dto.getHindiTheory());
            marks.setHindiProject(dto.getHindiProject());

            marks.setEnglishTheory(dto.getEnglishTheory());
            marks.setEnglishProject(dto.getEnglishProject());

            marks.setMathsTheory(dto.getMathsTheory());
            marks.setMathsProject(dto.getMathsProject());

            marks.setEvsTheory(dto.getEvsTheory());
            marks.setEvsProject(dto.getEvsProject());

            marks.setScienceTheory(dto.getScienceTheory());
            marks.setScienceProject(dto.getScienceProject());

            marks.setSocialScienceTheory(dto.getSocialScienceTheory());
            marks.setSocialScienceProject(dto.getSocialScienceProject());

            marks.setSanskritTheory(dto.getSanskritTheory());
            marks.setSanskritProject(dto.getSanskritProject());

            marks.setMarathiTheory(dto.getMarathiTheory());
            marks.setMarathiProject(dto.getMarathiProject());

            // Only theory subjects
            marks.setGkTheory(dto.getGkTheory());
            marks.setComputerTheory(dto.getComputerTheory());
            marks.setDrawingTheory(dto.getDrawingTheory());

            // ================= OUT OF =================
            marks.setHindiTheoryOutof(dto.getHindiTheoryOutOf());
            marks.setHindiProjectOutof(dto.getHindiProjectOutOf());

            marks.setEnglishTheoryOutof(dto.getEnglishTheoryOutOf());
            marks.setEnglishProjectOutof(dto.getEnglishProjectOutOf());

            marks.setMathsTheoryOutof(dto.getMathsTheoryOutOf());
            marks.setMathsProjectOutof(dto.getMathsProjectOutOf());

            marks.setEvsTheoryOutof(dto.getEvsTheoryOutOf());
            marks.setEvsProjectOutof(dto.getEvsProjectOutOf());

            marks.setScienceTheoryOutof(dto.getScienceTheoryOutOf());
            marks.setScienceProjectOutof(dto.getScienceProjectOutOf());

            marks.setSocialScienceTheoryOutof(dto.getSocialScienceTheoryOutOf());
            marks.setSocialScienceProjectOutof(dto.getSocialScienceProjectOutOf());

            marks.setSanskritTheoryOutof(dto.getSanskritTheoryOutOf());
            marks.setSanskritProjectOutof(dto.getSanskritProjectOutOf());

            marks.setMarathiTheoryOutof(dto.getMarathiTheoryOutOf());
            marks.setMarathiProjectOutof(dto.getMarathiProjectOutOf());

            // only theory outof
            marks.setGkTheoryOutof(dto.getGkTheoryOutOf());
            marks.setComputerTheoryOutof(dto.getComputerTheoryOutOf());
            marks.setDrawingTheoryOutof(dto.getDrawingTheoryOutOf());

            // Calculate totals + outof totals + result
            calculateResult(marks);

            return mapper.toDTO(marksRepo.save(marks));

        }).toList();
    }

    // ================= UPDATE MARKS =================
    public MarksDTO updateMarks(Integer id, MarksDTO dto) {

        Marks marks = marksRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Marks not found"));

        // ================= MARKS =================
        marks.setHindiTheory(dto.getHindiTheory());
        marks.setHindiProject(dto.getHindiProject());

        marks.setEnglishTheory(dto.getEnglishTheory());
        marks.setEnglishProject(dto.getEnglishProject());

        marks.setMathsTheory(dto.getMathsTheory());
        marks.setMathsProject(dto.getMathsProject());

        marks.setEvsTheory(dto.getEvsTheory());
        marks.setEvsProject(dto.getEvsProject());

        marks.setScienceTheory(dto.getScienceTheory());
        marks.setScienceProject(dto.getScienceProject());

        marks.setSocialScienceTheory(dto.getSocialScienceTheory());
        marks.setSocialScienceProject(dto.getSocialScienceProject());

        marks.setSanskritTheory(dto.getSanskritTheory());
        marks.setSanskritProject(dto.getSanskritProject());

        marks.setMarathiTheory(dto.getMarathiTheory());
        marks.setMarathiProject(dto.getMarathiProject());

        // only theory subjects
        marks.setGkTheory(dto.getGkTheory());
        marks.setComputerTheory(dto.getComputerTheory());
        marks.setDrawingTheory(dto.getDrawingTheory());

        // ================= OUT OF =================
        marks.setHindiTheoryOutof(dto.getHindiTheoryOutOf());
        marks.setHindiProjectOutof(dto.getHindiProjectOutOf());

        marks.setEnglishTheoryOutof(dto.getEnglishTheoryOutOf());
        marks.setEnglishProjectOutof(dto.getEnglishProjectOutOf());

        marks.setMathsTheoryOutof(dto.getMathsTheoryOutOf());
        marks.setMathsProjectOutof(dto.getMathsProjectOutOf());

        marks.setEvsTheoryOutof(dto.getEvsTheoryOutOf());
        marks.setEvsProjectOutof(dto.getEvsProjectOutOf());

        marks.setScienceTheoryOutof(dto.getScienceTheoryOutOf());
        marks.setScienceProjectOutof(dto.getScienceProjectOutOf());

        marks.setSocialScienceTheoryOutof(dto.getSocialScienceTheoryOutOf());
        marks.setSocialScienceProjectOutof(dto.getSocialScienceProjectOutOf());

        marks.setSanskritTheoryOutof(dto.getSanskritTheoryOutOf());
        marks.setSanskritProjectOutof(dto.getSanskritProjectOutOf());

        marks.setMarathiTheoryOutof(dto.getMarathiTheoryOutOf());
        marks.setMarathiProjectOutof(dto.getMarathiProjectOutOf());

        marks.setGkTheoryOutof(dto.getGkTheoryOutOf());
        marks.setComputerTheoryOutof(dto.getComputerTheoryOutOf());
        marks.setDrawingTheoryOutof(dto.getDrawingTheoryOutOf());

        calculateResult(marks);

        return mapper.toDTO(marksRepo.save(marks));
    }

    // ================= DELETE =================
    public void deleteMarks(Integer marksId) {
        if (!marksRepo.existsById(marksId)) {
            throw new RuntimeException("Marks not found with id " + marksId);
        }
        marksRepo.deleteById(marksId);
    }

    // ================= VIEW =================
    public List<MarksDTO> getAll() {
        return marksRepo.findAll().stream()
                .map(mapper::toDTO).toList();
    }

    public List<MarksDTO> getByStudent(Integer studentId) {
        return marksRepo.findByUser_UserId(studentId)
                .stream().map(mapper::toDTO).toList();
    }

    public MarksDTO getMarksById(Integer id) {
        return marksRepo.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Marks not found"));
    }

    // ================= RESULT CALCULATION =================
    private void calculateResult(Marks m) {

        // ===== Subject totals (MARKS) =====
        int hindiTotal = safe(m.getHindiTheory()) + safe(m.getHindiProject());
        int englishTotal = safe(m.getEnglishTheory()) + safe(m.getEnglishProject());
        int mathsTotal = safe(m.getMathsTheory()) + safe(m.getMathsProject());
        int evsTotal = safe(m.getEvsTheory()) + safe(m.getEvsProject());
        int scienceTotal = safe(m.getScienceTheory()) + safe(m.getScienceProject());
        int socialTotal = safe(m.getSocialScienceTheory()) + safe(m.getSocialScienceProject());
        int sanskritTotal = safe(m.getSanskritTheory()) + safe(m.getSanskritProject());
        int marathiTotal = safe(m.getMarathiTheory()) + safe(m.getMarathiProject());

        // ===== Only theory subjects =====
        int gkTotal = safe(m.getGkTheory());
        int computerTotal = safe(m.getComputerTheory());
        int drawingTotal = safe(m.getDrawingTheory());

        // ===== Save totals (MARKS) =====
        m.setHindiTotal(hindiTotal);
        m.setEnglishTotal(englishTotal);
        m.setMathsTotal(mathsTotal);
        m.setEvsTotal(evsTotal);
        m.setScienceTotal(scienceTotal);
        m.setSocialScienceTotal(socialTotal);
        m.setSanskritTotal(sanskritTotal);
        m.setMarathiTotal(marathiTotal);

        m.setGkTotal(gkTotal);
        m.setComputerTotal(computerTotal);
        m.setDrawingTotal(drawingTotal);

        // ===== Subject totals (OUT OF) =====
        int hindiTotalOutOf = safe(m.getHindiTheoryOutof()) + safe(m.getHindiProjectOutof());
        int englishTotalOutOf = safe(m.getEnglishTheoryOutof()) + safe(m.getEnglishProjectOutof());
        int mathsTotalOutOf = safe(m.getMathsTheoryOutof()) + safe(m.getMathsProjectOutof());
        int evsTotalOutOf = safe(m.getEvsTheoryOutof()) + safe(m.getEvsProjectOutof());
        int scienceTotalOutOf = safe(m.getScienceTheoryOutof()) + safe(m.getScienceProjectOutof());
        int socialTotalOutOf = safe(m.getSocialScienceTheoryOutof()) + safe(m.getSocialScienceProjectOutof());
        int sanskritTotalOutOf = safe(m.getSanskritTheoryOutof()) + safe(m.getSanskritProjectOutof());
        int marathiTotalOutOf = safe(m.getMarathiTheoryOutof()) + safe(m.getMarathiProjectOutof());

        int gkTotalOutOf = safe(m.getGkTheoryOutof());
        int computerTotalOutOf = safe(m.getComputerTheoryOutof());
        int drawingTotalOutOf = safe(m.getDrawingTheoryOutof());

        // ===== Save totals (OUT OF) =====
        m.setHindiTotalOutof(hindiTotalOutOf);
        m.setEnglishTotalOutof(englishTotalOutOf);
        m.setMathsTotalOutof(mathsTotalOutOf);
        m.setEvsTotalOutof(evsTotalOutOf);
        m.setScienceTotalOutof(scienceTotalOutOf);
        m.setSocialScienceTotalOutof(socialTotalOutOf);
        m.setSanskritTotalOutof(sanskritTotalOutOf);
        m.setMarathiTotalOutof(marathiTotalOutOf);

        m.setGkTotalOutof(gkTotalOutOf);
        m.setComputerTotalOutof(computerTotalOutOf);
        m.setDrawingTotalOutof(drawingTotalOutOf);

        // ===== Grand Total Marks (Obtained) =====
        int totalMarks =
                hindiTotal + englishTotal + mathsTotal + evsTotal + scienceTotal +
                socialTotal + sanskritTotal + marathiTotal +
                gkTotal + computerTotal + drawingTotal;

        m.setTotalMarks(totalMarks);

        // ===== Grand Total Out Of =====
        int grandOutOf =
                hindiTotalOutOf + englishTotalOutOf + mathsTotalOutOf + evsTotalOutOf + scienceTotalOutOf +
                socialTotalOutOf + sanskritTotalOutOf + marathiTotalOutOf +
                gkTotalOutOf + computerTotalOutOf + drawingTotalOutOf;

        m.setGrandTotal(grandOutOf);

        // ===== Percentage =====
        double percentage = grandOutOf == 0 ? 0 : ((double) totalMarks / grandOutOf) * 100;
        m.setPercentage(percentage);

        // ===== Grade =====
        if (percentage >= 75) m.setGrade("A+");
        else if (percentage >= 60) m.setGrade("A");
        else if (percentage >= 50) m.setGrade("B");
        else if (percentage >= 35) m.setGrade("C");
        else m.setGrade("FAIL");

        // ===== PASS / FAIL =====
        m.setStatus(percentage >= 35 ? "PASS" : "FAIL");
    }

    private int safe(Integer v) {
        return v == null ? 0 : v;
    }
}
