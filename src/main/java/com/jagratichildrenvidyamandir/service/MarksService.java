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

    // ================= ADD SINGLE MARK =================
    public MarksDTO addMarks(MarksDTO dto) {

        Marks marks = new Marks();

        marks.setUser(userRepo.findById(dto.getStudentId()).orElseThrow());
        marks.setTeacher(teacherRepo.findById(dto.getTeacherId()).orElseThrow());
        marks.setClasses(classRepo.findById(dto.getClassId()).orElseThrow());
        marks.setSession(sessionRepo.findById(dto.getSessionId()).orElseThrow());

        marks.setExamType(dto.getExamType());

        // ✅ SUBJECT MARKS (INCLUDING MARATHI)
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
    }

    // ================= UPDATE MARK =================
    public MarksDTO updateMarks(Integer id, MarksDTO dto) {

        Marks marks = marksRepo.findById(id).orElseThrow();

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
            throw new RuntimeException("Marks not found with id: " + marksId);
        }

        marksRepo.deleteById(marksId);
    }

    // ================= VIEW =================
    public List<MarksDTO> getAll() {
        return marksRepo.findAll().stream().map(mapper::toDTO).toList();
    }

    public List<MarksDTO> getByStudent(Integer studentId) {
        return marksRepo.findByUser_UserId(studentId)
                .stream().map(mapper::toDTO).toList();
    }

    // ================= RESULT LOGIC =================
    private void calculateResult(Marks m) {

        int total =
                safe(m.getMarathi()) +
                safe(m.getHindi()) +
                safe(m.getEnglish()) +
                safe(m.getMaths()) +
                safe(m.getScience()) +
                safe(m.getSocialScience()) +
                safe(m.getEvs()) +
                safe(m.getComputer()) +
                safe(m.getGk()) +
                safe(m.getDrawing()) +
                safe(m.getSanskrit());

        m.setTotalMarks(total);

        double percentage = total / 11.0;
        m.setPercentage(percentage);

        if (percentage >= 75) m.setGrade("A");
        else if (percentage >= 60) m.setGrade("B");
        else if (percentage >= 35) m.setGrade("C");
        else m.setGrade("D");

        m.setStatus(percentage >= 35 ? "PASS" : "FAIL");
    }

    private int safe(Integer v) {
        return v == null ? 0 : v;
    }
}