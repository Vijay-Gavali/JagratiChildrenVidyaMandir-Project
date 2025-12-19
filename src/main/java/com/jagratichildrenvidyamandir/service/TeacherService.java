package com.jagratichildrenvidyamandir.service;

import com.jagratichildrenvidyamandir.dto.ClassDTO;
import com.jagratichildrenvidyamandir.dto.TeacherDTO;
import com.jagratichildrenvidyamandir.dto.UserDTO;
import com.jagratichildrenvidyamandir.entity.ClassEntity;
import com.jagratichildrenvidyamandir.entity.Teacher;
import com.jagratichildrenvidyamandir.entity.User;
import com.jagratichildrenvidyamandir.mapper.TeacherMapper;
import com.jagratichildrenvidyamandir.repository.ClassRepository;
import com.jagratichildrenvidyamandir.repository.TeacherRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private TeacherMapper teacherMapper;


    // ================= REGISTER TEACHER =================
    public TeacherDTO registerTeacher(TeacherDTO dto) {

        // Validate unique email & phone
        if (teacherRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }
        if (teacherRepository.existsByPhone(dto.getPhone())) {
            throw new RuntimeException("Phone already exists!");
        }

        // Fetch class entities for provided class IDs
        List<ClassEntity> classes = classRepository.findAllById(dto.getClassIds());
        if (classes.size() != dto.getClassIds().size()) {
            throw new RuntimeException("One or more class IDs are invalid!");
        }

        // Map DTO to Entity
        Teacher teacher = teacherMapper.toEntity(dto, classes);

        // Save and return DTO
        Teacher saved = teacherRepository.save(teacher);
        return teacherMapper.toDTO(saved);
    }

    // ================= UPDATE TEACHER =================
    public TeacherDTO updateTeacher(Integer id, TeacherDTO dto) {

        Teacher existing = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        // Validate email & phone uniqueness
        if (!existing.getEmail().equals(dto.getEmail()) && teacherRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }
        if (!existing.getPhone().equals(dto.getPhone()) && teacherRepository.existsByPhone(dto.getPhone())) {
            throw new RuntimeException("Phone already exists!");
        }

        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setPhone(dto.getPhone());
        existing.setPassword(dto.getPassword());
        existing.setEducationalDetails(dto.getEducationalDetails());
        existing.setYearOfExperience(dto.getYearOfExperience());
        existing.setDateOfBirth(dto.getDateOfBirth());
        existing.setAadharNo(dto.getAadharNo());
        existing.setAddress(dto.getAddress());

        // Update classes if provided
        if (dto.getClassIds() != null && !dto.getClassIds().isEmpty()) {
            List<ClassEntity> classes = classRepository.findAllById(dto.getClassIds());
            if (classes.size() != dto.getClassIds().size()) {
                throw new RuntimeException("One or more class IDs are invalid!");
            }
            existing.setClasses(classes);
        }

        Teacher updated = teacherRepository.save(existing);
        return teacherMapper.toDTO(updated);
    }

    
 // ================= GET TEACHER BY ID =================
    public TeacherDTO getTeacherById(Integer id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id " + id));
        return teacherMapper.toDTO(teacher);
    }
    // ================= DELETE TEACHER =================
    public void deleteTeacher(Integer id) {
        if (!teacherRepository.existsById(id)) {
            throw new RuntimeException("Teacher not found with id " + id);
        }
        teacherRepository.deleteById(id);
    }
 // ================= GET ALL TEACHERS =================
    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(teacherMapper::toDTO)
                .collect(Collectors.toList());
    }
 // ================= ASSIGN CLASSES TO TEACHER =================
    public Teacher assignClassesToTeacher(Integer teacherId, List<Integer> classIds) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        // Fetch all valid classes
        List<ClassEntity> classes = classRepository.findAllById(classIds);
        if (classes.size() != classIds.size()) {
            throw new RuntimeException("Invalid class id found!");
        }

        // Assign classes to teacher (Many-to-Many)
        teacher.setClasses(classes);

        // Optionally, add this teacher to each class (bidirectional)
        for (ClassEntity cls : classes) {
            List<Teacher> teachers = cls.getTeachers();
            if (!teachers.contains(teacher)) {
                teachers.add(teacher);
                cls.setTeachers(teachers);
            }
        }

        return teacherRepository.save(teacher);
    }

    // ================= GET CLASSES BY TEACHER =================
    public List<ClassDTO> getClassesByTeacher(Integer teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        if (teacher.getClasses() == null || teacher.getClasses().isEmpty()) {
            return List.of();
        }

        return teacher.getClasses()
                .stream()
                .map(c -> new ClassDTO(c.getClassId(), c.getClassName()))
                .collect(Collectors.toList());
    }

    // ================= GET STUDENTS BY TEACHER =================
    public List<UserDTO> getStudentsByTeacher(Integer teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        if (teacher.getClasses() == null || teacher.getClasses().isEmpty()) {
            return new ArrayList<>();
        }

        List<UserDTO> result = new ArrayList<>();
        for (ClassEntity cls : teacher.getClasses()) {
        	for (User student : cls.getStudents()) {
        	    UserDTO dto = new UserDTO();
        	    dto.setUserId(student.getUserId());
        	    dto.setName(student.getName());
        	    dto.setAdmissionNo(student.getAdmissionNo());
        	    dto.setAdmissionDate(student.getAdmissionDate());
        	    result.add(dto);
        	}

        }

        return result;
    }

    // ================= TEACHER LOGIN =================
    public Teacher authenticateTeacher(String username, String password) {
        return teacherRepository.findByPhoneAndPassword(username, password)
                .or(() -> teacherRepository.findByEmailAndPassword(username, password))
                .orElse(null);
    }
 // ================= GET TEACHERS BY CLASS ID =================
 // Get all teachers assigned to a specific classId

    // Get all teachers assigned to a specific classId
    public List<TeacherDTO> getTeachersByClassId(Integer classId) {
        // Use the repository method you already have
        List<Teacher> teachers = teacherRepository.findAllByClasses_ClassId(classId);

        return teachers.stream().map(t -> {
            TeacherDTO dto = new TeacherDTO();
            dto.setTeacherId(t.getTeacherId());
            dto.setName(t.getName());
            dto.setEmail(t.getEmail());
            dto.setPhone(t.getPhone());
            dto.setPassword(t.getPassword());
            dto.setEducationalDetails(t.getEducationalDetails());
            dto.setYearOfExperience(t.getYearOfExperience());
            dto.setDateOfBirth(t.getDateOfBirth());
            dto.setAadharNo(t.getAadharNo());
            dto.setAddress(t.getAddress());

            // Map classes to classIds
            dto.setClassIds(t.getClasses().stream()
                    .map(c -> c.getClassId())
                    .collect(Collectors.toList())
            );

            return dto;
        }).collect(Collectors.toList());
    }
}

