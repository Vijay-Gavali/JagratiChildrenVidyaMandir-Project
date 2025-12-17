package com.jagratichildrenvidyamandir.mapper;

import com.jagratichildrenvidyamandir.dto.TeacherDTO;
import com.jagratichildrenvidyamandir.entity.Teacher;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeacherMapper {

    // Convert Entity to DTO
    public TeacherDTO toDTO(Teacher teacher) {
        if (teacher == null) return null;

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
        dto.setAprNo(teacher.getAprNo());
        dto.setAddress(teacher.getAddress());

        // Convert comma-separated string to List<Integer>
        if (teacher.getClassIds() != null && !teacher.getClassIds().isEmpty()) {
            List<Integer> classIds = Arrays.stream(teacher.getClassIds().split(","))
                                           .map(Integer::parseInt)
                                           .collect(Collectors.toList());
            dto.setClassIds(classIds);
        }

        return dto;
    }

    // Convert DTO to Entity
    public Teacher toEntity(TeacherDTO dto) {
        if (dto == null) return null;

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
        teacher.setAprNo(dto.getAprNo());
        teacher.setAddress(dto.getAddress());

        // Convert List<Integer> to comma-separated string
        if (dto.getClassIds() != null && !dto.getClassIds().isEmpty()) {
            String classIdStr = dto.getClassIds().stream()
                                    .map(String::valueOf)
                                    .collect(Collectors.joining(","));
            teacher.setClassIds(classIdStr);
        }

        return teacher;
    }
}
