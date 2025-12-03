package com.jagratichildrenvidyamandir.mapper;

import com.jagratichildrenvidyamandir.dto.TeacherDTO;
import com.jagratichildrenvidyamandir.entity.Teacher;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {

    public TeacherDTO toDTO(Teacher teacher) {
        if (teacher == null) return null;
        TeacherDTO dto = new TeacherDTO();

        dto.setTeacherId(teacher.getTeacherId());
        dto.setClassId(teacher.getClassId());
        dto.setName(teacher.getName());
        dto.setEmail(teacher.getEmail());
        dto.setPhone(teacher.getPhone());
        dto.setPassword(teacher.getPassword());
        dto.setEducationalDetails(teacher.getEducationalDetails());
        dto.setYearOfExperience(teacher.getYearOfExperience());
        dto.setDateOfBirth(teacher.getDateOfBirth());
        dto.setAadharNo(teacher.getAadharNo());
        dto.setAprNo(teacher.getAprNo());          // ⭐ NEW LINE
        dto.setAddress(teacher.getAddress());
        dto.setDocumentPath(teacher.getDocumentPath());


        return dto;
    }

    public Teacher toEntity(TeacherDTO dto) {
        if (dto == null) return null;
        Teacher teacher = new Teacher();

        teacher.setTeacherId(dto.getTeacherId());
        teacher.setClassId(dto.getClassId());
        teacher.setName(dto.getName());
        teacher.setEmail(dto.getEmail());
        teacher.setPhone(dto.getPhone());
        teacher.setPassword(dto.getPassword());
        teacher.setEducationalDetails(dto.getEducationalDetails());
        teacher.setYearOfExperience(dto.getYearOfExperience());
        teacher.setDateOfBirth(dto.getDateOfBirth());
        teacher.setAadharNo(dto.getAadharNo());
        teacher.setAprNo(dto.getAprNo());          // ⭐ NEW LINE
        teacher.setAddress(dto.getAddress());
        teacher.setDocumentPath(dto.getDocumentPath());


        return teacher;
    }
}
