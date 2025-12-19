package com.jagratichildrenvidyamandir.service;

import com.jagratichildrenvidyamandir.dto.ClassDTO;
import com.jagratichildrenvidyamandir.dto.ClassWithTeachersDTO;
import com.jagratichildrenvidyamandir.dto.TeacherDTO;

import com.jagratichildrenvidyamandir.dto.UserDTO;
import com.jagratichildrenvidyamandir.mapper.ClassMapper;
import com.jagratichildrenvidyamandir.entity.ClassEntity;
import com.jagratichildrenvidyamandir.entity.Teacher;
import com.jagratichildrenvidyamandir.repository.ClassRepository;
import com.jagratichildrenvidyamandir.repository.TeacherRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClassService {

    private final ClassRepository repository;
    private final ClassMapper mapper;
    private final ClassRepository classRepository;
    private final TeacherRepository teacherRepository;

    

    public ClassService(ClassRepository repository, ClassMapper mapper,ClassRepository classRepository,TeacherRepository teacherRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this. classRepository= classRepository;
        this.teacherRepository= teacherRepository;
    }

    // CREATE
    public ClassDTO createClass(ClassDTO dto) {
    	ClassEntity entity = mapper.toEntity(dto);
        entity.setClassId(null); // auto-increment
        ClassEntity saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    // GET ONE
    public ClassDTO getClassById(Integer id) {
        return repository.findById(id)
                .map(mapper::toFullDto)
                .orElse(null);
    }

    // GET ALL
    public List<ClassDTO> getAllClasses() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    // UPDATE
    public ClassDTO updateClass(Integer id, ClassDTO dto) {
        return repository.findById(id)
                .map(existing -> {
                    mapper.updateEntityFromDto(dto, existing);
                    ClassEntity updated = repository.save(existing);
                    return mapper.toDto(updated);
                })
                .orElse(null);
    }

    // DELETE
    public boolean deleteClass(Integer id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
   
    public ClassWithTeachersDTO getClassWithTeachers(Integer classId) {

        ClassEntity classEntity = classRepository.findById(classId)
                .orElseThrow(() -> new RuntimeException("Class not found"));

        ClassWithTeachersDTO response = new ClassWithTeachersDTO();
        response.setClassId(classEntity.getClassId());
        response.setClassName(classEntity.getClassName());

        response.setTeachers(
                classEntity.getTeachers()
                        .stream()
                        .map(this::mapTeacher)
                        .collect(Collectors.toList())
        );

        return response;
    }

    private TeacherDTO mapTeacher(Teacher teacher) {
        TeacherDTO dto = new TeacherDTO();
        dto.setTeacherId(teacher.getTeacherId());
        dto.setName(teacher.getName());
        dto.setEmail(teacher.getEmail());
        dto.setPhone(teacher.getPhone());
        dto.setPassword(teacher.getPassword());
        dto.setEducationalDetails(teacher.getEducationalDetails());
        dto.setYearOfExperience(teacher.getYearOfExperience());
        dto.setDateOfBirth(teacher.getDateOfBirth());
        dto.setAadharNo(teacher.getAadharNo());
        dto.setAddress(teacher.getAddress());

        // Map class names instead of IDs
        if (teacher.getClasses() != null && !teacher.getClasses().isEmpty()) {
            dto.setClassNames(
                    teacher.getClasses()
                            .stream()
                            .map(c -> c.getClassName())
                            .collect(Collectors.toList())
            );
        } else {
            dto.setClassNames(new ArrayList<>());
        }

        return dto;
    }
}

