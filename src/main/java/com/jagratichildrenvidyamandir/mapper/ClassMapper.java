package com.jagratichildrenvidyamandir.mapper;

import com.jagratichildrenvidyamandir.dto.ClassDTO;
import com.jagratichildrenvidyamandir.entity.Class;

import org.springframework.stereotype.Component;

@Component
public class ClassMapper {

    public ClassDTO toDto(Class entity) {
        if (entity == null) return null;
        ClassDTO dto = new ClassDTO();
        dto.setClassId(entity.getClassId());
        dto.setClassName(entity.getClassName());
        dto.setFees(entity.getFees());
        return dto;
    }

    public Class toEntity(ClassDTO dto) {
        if (dto == null) return null;
        Class entity = new Class();
        entity.setClassId(dto.getClassId()); // usually null for create
        entity.setClassName(dto.getClassName());
        entity.setFees(dto.getFees());
        return entity;
    }

    public void updateEntityFromDto(ClassDTO dto, Class entity) {
        if (dto == null || entity == null) return;
        entity.setClassName(dto.getClassName());
        entity.setFees(dto.getFees());
        // do NOT overwrite id (unless you intentionally want to)
    }
}
