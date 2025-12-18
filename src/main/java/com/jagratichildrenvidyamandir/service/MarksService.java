package com.jagratichildrenvidyamandir.service;

import com.jagratichildrenvidyamandir.dto.MarksDTO;
import com.jagratichildrenvidyamandir.entity.Marks;
import com.jagratichildrenvidyamandir.repository.MarksRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarksService {

    private final MarksRepository marksRepository;

    public MarksService(MarksRepository marksRepository) {
        this.marksRepository = marksRepository;
    }

    // Save new marks from entity
    public Marks saveMarks(Marks marks) {
        marks.calculate(); // auto-calc total & grade
        return marksRepository.save(marks);
    }

    // Save new marks from DTO
    public MarksDTO saveMarks(MarksDTO dto) {
        Marks marks = new Marks();
        marks.setTeacherId(dto.getTeacherId());
        marks.setClassId(dto.getClassId());
        marks.setStudentId(dto.getStudentId());
        marks.setExamType(dto.getExamType());
        marks.setMonth(dto.getMonth());
        marks.setQuarter(dto.getQuarter());
        marks.setYear(dto.getYear());
        marks.setMarathi(dto.getMarathi());
        marks.setHindi(dto.getHindi());
        marks.setEnglish(dto.getEnglish());
        marks.setSanskrit(dto.getSanskrit());
        marks.setMaths(dto.getMaths());
        marks.setScience(dto.getScience());
        marks.setHistory(dto.getHistory());
        marks.setGeography(dto.getGeography());

        marks.calculate();

        Marks saved = marksRepository.save(marks);

        // Update DTO with generated ID, total & grade
        dto.setMarksId(saved.getMarksId());
        dto.setTotalMarks(saved.getTotalMarks());
        dto.setGrade(saved.getGrade());

        return dto;
    }

    // Update existing marks
    public Marks updateMarks(Integer id, Marks marks) {
        Marks existing = marksRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marks Not Found"));

        existing.setTeacherId(marks.getTeacherId());
        existing.setClassId(marks.getClassId());
        existing.setStudentId(marks.getStudentId());
        existing.setExamType(marks.getExamType());
        existing.setMonth(marks.getMonth());
        existing.setQuarter(marks.getQuarter());
        existing.setYear(marks.getYear());
        existing.setMarathi(marks.getMarathi());
        existing.setHindi(marks.getHindi());
        existing.setEnglish(marks.getEnglish());
        existing.setSanskrit(marks.getSanskrit());
        existing.setMaths(marks.getMaths());
        existing.setScience(marks.getScience());
        existing.setHistory(marks.getHistory());
        existing.setGeography(marks.getGeography());

        existing.calculate();

        return marksRepository.save(existing);
    }

    // Fetch all marks
    public List<MarksDTO> getAllMarks() {
        return marksRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Fetch marks by student
    public List<MarksDTO> getMarksByStudent(Integer studentId) {
        return marksRepository.findByStudentId(studentId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Fetch marks by class
    public List<MarksDTO> getMarksByClass(Integer classId) {
        return marksRepository.findByClassId(classId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Convert entity to DTO
    private MarksDTO toDTO(Marks m) {
        MarksDTO dto = new MarksDTO();
        dto.setMarksId(m.getMarksId());
        dto.setTeacherId(m.getTeacherId());
        dto.setClassId(m.getClassId());
        dto.setStudentId(m.getStudentId());
        dto.setExamType(m.getExamType());
        dto.setMonth(m.getMonth());
        dto.setQuarter(m.getQuarter());
        dto.setYear(m.getYear());
        dto.setMarathi(m.getMarathi());
        dto.setHindi(m.getHindi());
        dto.setEnglish(m.getEnglish());
        dto.setSanskrit(m.getSanskrit());
        dto.setMaths(m.getMaths());
        dto.setScience(m.getScience());
        dto.setHistory(m.getHistory());
        dto.setGeography(m.getGeography());
        dto.setTotalMarks(m.getTotalMarks());
        dto.setGrade(m.getGrade());
        return dto;
    }
}
