package com.jagratichildrenvidyamandir.service;

import com.jagratichildrenvidyamandir.dto.MarksDTO;
import com.jagratichildrenvidyamandir.entity.Marks;
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

    // ================= ADD SINGLE =================
    /*public MarksDTO addMarks(MarksDTO dto) {
        Marks marks = buildMarks(dto);
        return mapper.toDTO(marksRepo.save(marks));
    }*/

    // ================= ADD BULK =================
   /* public List<MarksDTO> addBulkMarks(List<MarksDTO> dtoList) {
        return dtoList.stream()
                .map(dto -> mapper.toDTO(marksRepo.save(buildMarks(dto))))
                .toList();
    }*/

    // ================= UPDATE =================
    public MarksDTO updateMarks(Integer id, MarksDTO dto) {

        Marks marks = marksRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Marks not found"));

        setSubjects(marks, dto);
        calculateResult(marks);

        return mapper.toDTO(marksRepo.save(marks));
    }


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
            marks.setMarathi(dto.getMarathi());
            marks.setHindi(dto.getHindi());
            marks.setEnglish(dto.getEnglish());
            marks.setMaths(dto.getMaths());
            marks.setScience(dto.getScience());
            marks.setSocialScience(dto.getSocialScience());
            marks.setEvs(dto.getEvs());
            marks.setComputer(dto.getComputer());
            marks.setGk(dto.getGk());
            marks.setDrawing(dto.getDrawing());
            marks.setSanskrit(dto.getSanskrit());

            calculateResult(marks);

            return mapper.toDTO(marksRepo.save(marks));

        }).toList();
    }

 // ================= DELETE MARK =================

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

    // ================= COMMON BUILDER =================
    private Marks buildMarks(MarksDTO dto) {

        Marks marks = new Marks();

        marks.setUser(userRepo.findById(dto.getStudentId()).orElseThrow());
        marks.setTeacher(teacherRepo.findById(dto.getTeacherId()).orElseThrow());
        marks.setClasses(classRepo.findById(dto.getClassId()).orElseThrow());
        marks.setSession(sessionRepo.findById(dto.getSessionId()).orElseThrow());
        marks.setExamType(dto.getExamType());

        setSubjects(marks, dto);
        calculateResult(marks);

        return marks;
    }

    // ================= SUBJECT SETTER =================
    private void setSubjects(Marks m, MarksDTO dto) {

        m.setMarathi(dto.getMarathi());
        m.setHindi(dto.getHindi());
        m.setEnglish(dto.getEnglish());
        m.setMaths(dto.getMaths());
        m.setScience(dto.getScience());
        m.setSocialScience(dto.getSocialScience());
        m.setEvs(dto.getEvs());
        m.setComputer(dto.getComputer());
        m.setGk(dto.getGk());
        m.setDrawing(dto.getDrawing());
        m.setSanskrit(dto.getSanskrit());
    }

    // ================= RESULT LOGIC (CORRECT) =================
    private void calculateResult(Marks m) {

        int total = 0;
        int subjects = 0;

        total += add(m.getMarathi()); subjects += count(m.getMarathi());
        total += add(m.getHindi()); subjects += count(m.getHindi());
        total += add(m.getEnglish()); subjects += count(m.getEnglish());
        total += add(m.getMaths()); subjects += count(m.getMaths());
        total += add(m.getScience()); subjects += count(m.getScience());
        total += add(m.getSocialScience()); subjects += count(m.getSocialScience());
        total += add(m.getEvs()); subjects += count(m.getEvs());
        total += add(m.getComputer()); subjects += count(m.getComputer());
        total += add(m.getGk()); subjects += count(m.getGk());
        total += add(m.getDrawing()); subjects += count(m.getDrawing());
        total += add(m.getSanskrit()); subjects += count(m.getSanskrit());

        m.setTotalMarks(total);

        double percentage = subjects == 0 ? 0 : (double) total / subjects;
        m.setPercentage(percentage);

        if (percentage >= 75) m.setGrade("A");
        else if (percentage >= 60) m.setGrade("B");
        else if (percentage >= 35) m.setGrade("C");
        else m.setGrade("D");

        m.setStatus(percentage >= 35 ? "PASS" : "FAIL");
    }

    private int add(Integer v) {
        return v == null ? 0 : v;
    }

    private int count(Integer v) {
        return v == null ? 0 : 1;
    }
}