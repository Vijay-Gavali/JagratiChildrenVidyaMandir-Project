package com.jagratichildrenvidyamandir.mapper;

import com.jagratichildrenvidyamandir.dto.TeacherDTO;
import com.jagratichildrenvidyamandir.entity.ClassEntity;
import com.jagratichildrenvidyamandir.entity.Teacher;
import com.jagratichildrenvidyamandir.repository.ClassRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeacherMapper {

    private final ClassRepository classRepository;

    // ✅ Constructor Injection (IMPORTANT)
    public TeacherMapper(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    // ================= DTO → ENTITY =================
    public Teacher toEntity(TeacherDTO dto) {

        Teacher teacher = new Teacher();

        teacher.setTeacherId(dto.getTeacherId());
        teacher.setName(dto.getName());
        teacher.setEmail(dto.getEmail());
        teacher.setPhone(dto.getPhone());
        teacher.setPassword(dto.getPassword());
        teacher.setEducationalDetails(dto.getEducationalDetails());
        teacher.setYearOfExperience(dto.getYearOfExperience());
        teacher.setDateOfBirth(dto.getDateOfBirth());
        teacher.setAadharNo(dto.getAadharNo());
        teacher.setAddress(dto.getAddress());

        // 🔥 SET CLASSES (Many-to-Many)
        if (dto.getClassIds() != null && !dto.getClassIds().isEmpty()) {
            List<ClassEntity> classes =
                    classRepository.findAllById(dto.getClassIds());
            teacher.setClasses(classes);
        } else {
            teacher.setClasses(new ArrayList<>());
        }

        return teacher;
    }

    // ================= ENTITY → DTO =================
    public TeacherDTO toDTO(Teacher teacher) {

        TeacherDTO dto = new TeacherDTO();

        dto.setTeacherId(teacher.getTeacherId());
        dto.setName(teacher.getName());
        dto.setEmail(teacher.getEmail());
        dto.setPhone(teacher.getPhone());
        dto.setEducationalDetails(teacher.getEducationalDetails());
        dto.setYearOfExperience(teacher.getYearOfExperience());
        dto.setDateOfBirth(teacher.getDateOfBirth());
        dto.setAadharNo(teacher.getAadharNo());
        dto.setAddress(teacher.getAddress());

        // ✅ Send only class names in response
        if (teacher.getClasses() != null && !teacher.getClasses().isEmpty()) {
            dto.setClassNames(
                    teacher.getClasses()
                            .stream()
                            .map(ClassEntity::getClassName)
                            .collect(Collectors.toList())
            );
        } else {
            dto.setClassNames(new ArrayList<>());
        }

        return dto;
    }
}
