package com.jagratichildrenvidyamandir.mapper;

import com.jagratichildrenvidyamandir.dto.TeacherDTO;
import com.jagratichildrenvidyamandir.entity.ClassEntity;
import com.jagratichildrenvidyamandir.entity.Teacher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class TeacherMapper {

    // DTO → Entity (NO classes here)
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
        return teacher;
    }

    // Entity → DTO
    public TeacherDTO toDTO(Teacher teacher) {
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

        dto.setClassNames(
            teacher.getClasses() == null ? new ArrayList<>() :
            teacher.getClasses().stream()
                .map(ClassEntity::getClassName)
                .toList()
        );

        return dto;
    }
}
