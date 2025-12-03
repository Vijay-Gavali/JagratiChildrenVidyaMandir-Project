package com.jagratichildrenvidyamandir.service;

import com.jagratichildrenvidyamandir.dto.TeacherDTO;
import com.jagratichildrenvidyamandir.entity.Teacher;
import com.jagratichildrenvidyamandir.mapper.TeacherMapper;
import com.jagratichildrenvidyamandir.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherMapper teacherMapper;

    // Register Teacher
    public TeacherDTO registerTeacher(TeacherDTO dto) {
        if (teacherRepository.existsByEmail(dto.getEmail()))
            throw new RuntimeException("Email already exists!");
        if (teacherRepository.existsByPhone(dto.getPhone()))
            throw new RuntimeException("Phone number already exists!");

        Teacher teacher = teacherMapper.toEntity(dto);
        Teacher saved = teacherRepository.save(teacher);
        return teacherMapper.toDTO(saved);
    }

    // Get all teachers
    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll().stream()
                .map(teacherMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Get teacher by ID
    public TeacherDTO getTeacherById(Integer id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id " + id));
        return teacherMapper.toDTO(teacher);
    }

    // Update teacher
    public TeacherDTO updateTeacher(Integer id, TeacherDTO dto) {
        Teacher existing = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id " + id));

        if (!existing.getEmail().equals(dto.getEmail()) && teacherRepository.existsByEmail(dto.getEmail()))
            throw new RuntimeException("Email already exists!");
        if (!existing.getPhone().equals(dto.getPhone()) && teacherRepository.existsByPhone(dto.getPhone()))
            throw new RuntimeException("Phone already exists!");

        existing.setClassId(dto.getClassId());
        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setPhone(dto.getPhone());
        existing.setPassword(dto.getPassword());
        existing.setEducationalDetails(dto.getEducationalDetails());
        existing.setYearOfExperience(dto.getYearOfExperience());
        existing.setDateOfBirth(dto.getDateOfBirth());
        existing.setAadharNo(dto.getAadharNo());
        existing.setAddress(dto.getAddress());
        existing.setAprNo(dto.getAprNo());   // ⭐ ADDED


        Teacher updated = teacherRepository.save(existing);
        return teacherMapper.toDTO(updated);
    }
  
    // Delete teacher
    public void deleteTeacher(Integer id) {
        if (!teacherRepository.existsById(id))
            throw new RuntimeException("Teacher not found with id " + id);
        teacherRepository.deleteById(id);
    }
}
