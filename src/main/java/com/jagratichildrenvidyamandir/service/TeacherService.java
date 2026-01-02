package com.jagratichildrenvidyamandir.service;

import com.jagratichildrenvidyamandir.dto.ClassDTO;
import com.jagratichildrenvidyamandir.dto.TeacherDTO;
import com.jagratichildrenvidyamandir.dto.UserDTO;
import com.jagratichildrenvidyamandir.entity.ClassEntity;
import com.jagratichildrenvidyamandir.entity.SessionEntity;
import com.jagratichildrenvidyamandir.entity.Teacher;
import com.jagratichildrenvidyamandir.entity.Transaction;
import com.jagratichildrenvidyamandir.entity.User;
import com.jagratichildrenvidyamandir.mapper.TeacherMapper;
import com.jagratichildrenvidyamandir.mapper.TransactionMapper;
import com.jagratichildrenvidyamandir.repository.ClassRepository;
import com.jagratichildrenvidyamandir.repository.SessionRepository;
import com.jagratichildrenvidyamandir.repository.TeacherRepository;
import com.jagratichildrenvidyamandir.repository.TransactionRepository;
import com.jagratichildrenvidyamandir.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    @Autowired
	private TransactionMapper transactionMapper;
    @Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SessionRepository sessionRepository;

    // ================= REGISTER TEACHER =================
    public TeacherDTO registerTeacher(TeacherDTO dto) {

        // 🔐 Validate
        if (teacherRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        if (teacherRepository.existsByPhone(dto.getPhone())) {
            throw new RuntimeException("Phone already exists");
        }

        // 1️⃣ Map DTO → Entity
        Teacher teacher = teacherMapper.toEntity(dto);

        // 2️⃣ Set ManyToMany (🔥 REQUIRED)
        if (dto.getClassIds() != null && !dto.getClassIds().isEmpty()) {
            List<ClassEntity> classes =
                    classRepository.findAllById(dto.getClassIds());
            teacher.setClasses(classes);
        }

        // 3️⃣ Save
        Teacher savedTeacher = teacherRepository.save(teacher);

        // 4️⃣ Prepare response
        TeacherDTO response = teacherMapper.toDTO(savedTeacher);

        response.setClassNames(
                savedTeacher.getClasses() != null
                        ? savedTeacher.getClasses()
                                .stream()
                                .map(ClassEntity::getClassName)
                                .collect(Collectors.toList())
                        : new ArrayList<>()
        );

        return response;
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

        // Update fields
        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setPhone(dto.getPhone());
        existing.setPassword(dto.getPassword());
        existing.setEducationalDetails(dto.getEducationalDetails());
        existing.setYearOfExperience(dto.getYearOfExperience());
        existing.setDateOfBirth(dto.getDateOfBirth());
        existing.setAadharNo(dto.getAadharNo());
        existing.setAddress(dto.getAddress());

        Teacher updated = teacherRepository.save(existing);

        TeacherDTO response = teacherMapper.toDTO(updated);
        response.setClassNames(
                updated.getClasses() != null
                        ? updated.getClasses().stream()
                        .map(ClassEntity::getClassName)
                        .collect(Collectors.toList())
                        : new ArrayList<>()
        );

        return response;
    }

    // ================= GET TEACHER BY ID =================
    public TeacherDTO getTeacherById(Integer id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        TeacherDTO dto = teacherMapper.toDTO(teacher);
        dto.setClassNames(
                teacher.getClasses() != null
                        ? teacher.getClasses().stream()
                        .map(ClassEntity::getClassName)
                        .collect(Collectors.toList())
                        : new ArrayList<>()
        );

        return dto;
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
                .map(t -> {
                    TeacherDTO dto = teacherMapper.toDTO(t);
                    dto.setClassNames(
                            t.getClasses() != null
                                    ? t.getClasses().stream().map(ClassEntity::getClassName).collect(Collectors.toList())
                                    : new ArrayList<>()
                    );
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // ================= ASSIGN CLASSES TO TEACHER =================
    public Teacher assignClassesToTeacher(Integer teacherId, List<Integer> classIds) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        List<ClassEntity> classes = classRepository.findAllById(classIds);

        if (classes.size() != classIds.size()) {
            throw new RuntimeException("Invalid class ID found!");
        }

        teacher.setClasses(classes);

        // Update bidirectional mapping
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
            return new ArrayList<>();
        }

        return teacher.getClasses().stream()
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

        Set<Integer> addedStudentIds = new HashSet<>();
        List<UserDTO> result = new ArrayList<>();

        for (ClassEntity cls : teacher.getClasses()) {

            if (cls.getStudents() == null) continue;

            for (User student : cls.getStudents()) {

                // avoid duplicate students
                if (addedStudentIds.contains(student.getUserId())) {
                    continue;
                }

                UserDTO dto = new UserDTO();
                dto.setUserId(student.getUserId());
                dto.setName(student.getName());
                dto.setAdmissionNo(student.getAdmissionNo());
                dto.setAdmissionDate(student.getAdmissionDate());

                result.add(dto);
                addedStudentIds.add(student.getUserId());
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
    public List<TeacherDTO> getTeachersByClassId(Integer classId) {
        List<Teacher> teachers = teacherRepository.findAllByClasses_ClassId(classId);

        return teachers.stream()
                .map(t -> {
                    TeacherDTO dto = teacherMapper.toDTO(t);
                    dto.setClassNames(
                            t.getClasses() != null
                                    ? t.getClasses().stream().map(ClassEntity::getClassName).collect(Collectors.toList())
                                    : new ArrayList<>()
                    );
                    return dto;
                })
                .collect(Collectors.toList());
    }
 // ================= GET STUDENTS ALLOWED FOR TEACHER =================
    public List<UserDTO> getStudentsAllowedForTeacher(Integer teacherId) {

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
               
                dto.setGender(student.getGender());
                dto.setStudentPhone(student.getStudentPhone());

                // ---------- CLASS INFO ----------
                dto.setStudentClassId(cls.getClassId());
                dto.setStudentClassName(cls.getClassName());

                result.add(dto);
            }
        }

        return result;
    }
   
}