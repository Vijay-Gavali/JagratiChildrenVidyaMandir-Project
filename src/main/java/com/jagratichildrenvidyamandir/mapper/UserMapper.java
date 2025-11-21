package com.jagratichildrenvidyamandir.mapper;

import com.jagratichildrenvidyamandir.dto.UserDTO;
import com.jagratichildrenvidyamandir.entity.ClassEntity;
import com.jagratichildrenvidyamandir.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDto(User entity) {
        if (entity == null) return null;
        UserDTO dto = new UserDTO();
        dto.setUserId(entity.getUserId());
        dto.setName(entity.getName());
        dto.setAdmissionNo(entity.getAdmissionNo());
        dto.setAdmissionDate(entity.getAdmissionDate());
        dto.setPassword(entity.getPassword());
        dto.setFatherName(entity.getFatherName());
        dto.setMotherName(entity.getMotherName());
        dto.setDob(entity.getDob());
        dto.setStudentPhone(entity.getStudentPhone());
        dto.setEmail(entity.getEmail());
        dto.setParentPhone(entity.getParentPhone());
        dto.setAddress(entity.getAddress());
        dto.setGender(entity.getGender());
        dto.setStudentAadharNo(entity.getStudentAadharNo());
        dto.setParentAadharNo(entity.getParentAadharNo());
        // map relation as id only:
        dto.setStudentClassId(entity.getStudentClass() != null ? entity.getStudentClass().getClassId() : null);
        dto.setRte(entity.getRte());
        dto.setTcNumber(entity.getTcNumber());
        dto.setSsmId(entity.getSsmId());
        dto.setPassoutClass(entity.getPassoutClass());
        return dto;
    }

    public User toEntity(UserDTO dto) {
        if (dto == null) return null;
        User entity = new User();
        entity.setUserId(dto.getUserId()); // usually null for create
        entity.setName(dto.getName());
        entity.setAdmissionNo(dto.getAdmissionNo());
        entity.setAdmissionDate(dto.getAdmissionDate());
        entity.setPassword(dto.getPassword());
        entity.setFatherName(dto.getFatherName());
        entity.setMotherName(dto.getMotherName());
        entity.setDob(dto.getDob());
        entity.setStudentPhone(dto.getStudentPhone());
        entity.setEmail(dto.getEmail());
        entity.setParentPhone(dto.getParentPhone());
        entity.setAddress(dto.getAddress());
        entity.setGender(dto.getGender());
        entity.setStudentAadharNo(dto.getStudentAadharNo());
        entity.setParentAadharNo(dto.getParentAadharNo());

        // map class id -> ClassEntity (reference by id)
        if (dto.getStudentClassId() != null) {
            ClassEntity cls = new ClassEntity();
            cls.setClassId(dto.getStudentClassId());
            entity.setStudentClass(cls);
        } else {
            entity.setStudentClass(null);
        }

        entity.setRte(dto.getRte());
        entity.setTcNumber(dto.getTcNumber());
        entity.setSsmId(dto.getSsmId());
        entity.setPassoutClass(dto.getPassoutClass());
        return entity;
    }

    public void updateEntityFromDto(UserDTO dto, User entity) {
        if (dto == null || entity == null) return;
        // do NOT overwrite id
        entity.setName(dto.getName());
        entity.setAdmissionNo(dto.getAdmissionNo());
        entity.setAdmissionDate(dto.getAdmissionDate());
        entity.setPassword(dto.getPassword());
        entity.setFatherName(dto.getFatherName());
        entity.setMotherName(dto.getMotherName());
        entity.setDob(dto.getDob());
        entity.setStudentPhone(dto.getStudentPhone());
        entity.setEmail(dto.getEmail());
        entity.setParentPhone(dto.getParentPhone());
        entity.setAddress(dto.getAddress());
        entity.setGender(dto.getGender());
        entity.setStudentAadharNo(dto.getStudentAadharNo());
        entity.setParentAadharNo(dto.getParentAadharNo());

        // CORRECT mapping: use studentClassId from DTO
        if (dto.getStudentClassId() != null) {
            ClassEntity cls = new ClassEntity();
            cls.setClassId(dto.getStudentClassId());
            entity.setStudentClass(cls);
        } else {
            entity.setStudentClass(null);
        }

        entity.setRte(dto.getRte());
        entity.setTcNumber(dto.getTcNumber());
        entity.setSsmId(dto.getSsmId());
        entity.setPassoutClass(dto.getPassoutClass());
    }
}
