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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired private ClassRepository classRepository;
    

    public TeacherService(TeacherRepository teacherRepository, ClassRepository classRepository, TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.classRepository = classRepository;
        this.teacherMapper = teacherMapper;
    }

 // Register teacher
    public TeacherDTO registerTeacher(TeacherDTO dto) {
        if (teacherRepository.existsByEmail(dto.getEmail()))
            throw new RuntimeException("Email already exists!");
        if (teacherRepository.existsByPhone(dto.getPhone()))
            throw new RuntimeException("Phone number already exists!");

        // Validate classes exist
        if (dto.getClassIds() != null && !dto.getClassIds().isEmpty()) {
            List<Integer> existingIds = classRepository.findAllById(dto.getClassIds())
                    .stream().map(ClassEntity::getClassId).toList();
            if (existingIds.size() != dto.getClassIds().size()) {
                throw new RuntimeException("One or more class IDs are invalid!");
            }
        }

        Teacher teacher = teacherMapper.toEntity(dto);
        Teacher saved = teacherRepository.save(teacher);
        return teacherMapper.toDTO(saved);
    }

    // Assign classes later if needed
    public Teacher assignClassesToTeacher(Integer teacherId, List<Integer> classIds) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        // Validate classes exist
        List<Integer> existingIds = classRepository.findAllById(classIds)
                .stream().map(ClassEntity::getClassId).toList();
        if (existingIds.size() != classIds.size()) {
            throw new RuntimeException("One or more class IDs are invalid!");
        }

        String classIdsCsv = classIds.stream().map(String::valueOf).collect(Collectors.joining(","));
        teacher.setClassIds(classIdsCsv);
        return teacherRepository.save(teacher);
    }

    // Get assigned classes
    public List<ClassDTO> getClassesByTeacher(Integer teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        if (teacher.getClassIds() == null || teacher.getClassIds().isEmpty()) {
            return List.of(); // return empty list if no classes assigned
        }

        List<Integer> classIds = Arrays.stream(teacher.getClassIds().split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        return classRepository.findAllById(classIds)
                .stream()
                .map(c -> new ClassDTO(c.getClassId(), c.getClassName()))
                .collect(Collectors.toList());
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
    //
    public List<UserDTO> getStudentsByTeacher(Integer teacherId) {
    	

    	    Teacher teacher = teacherRepository.findById(teacherId)
    	            .orElseThrow(() -> new RuntimeException("Teacher not found"));

    	    String classIds = teacher.getClassIds();   // example: "1,3,5"

    	    if (classIds == null || classIds.isEmpty()) {
    	        return new ArrayList<>();
    	    }

    	    List<Integer> ids = Arrays.stream(classIds.split(","))
    	            .map(Integer::parseInt)
    	            .toList();

    	    List<ClassEntity> classList = classRepository.findAllById(ids);
    	    List<UserDTO> result = new ArrayList<>();

    	    for (ClassEntity cls : classList) {
    	        for (User s : cls.getStudents()) {

    	            UserDTO dto = new UserDTO();
    	            dto.setUserId(s.getUserId());
    	            dto.setName(s.getName());
    	            dto.setAdmissionNo(s.getAdmissionNo());
    	            dto.setAdmissionDate(s.getAdmissionDate());

    	            result.add(dto);
    	        }
    	    }
    	    return result;
    	}
//loginof the tecaher
    public Teacher authenticateTeacher(String username, String password) {
        return teacherRepository.findByPhoneAndPassword(username, password)
                .or(() -> teacherRepository.findByEmailAndPassword(username, password))
                .orElse(null);
    }

}


    


