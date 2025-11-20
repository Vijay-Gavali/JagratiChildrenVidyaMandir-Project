package com.jagratichildrenvidyamandir.mapper;

import com.jagratichildrenvidyamandir.dto.ParentDTO;
import com.jagratichildrenvidyamandir.entity.Parent;
import org.springframework.stereotype.Component;

@Component
public class ParentMapper {

    public ParentDTO toDto(Parent entity) {
        if (entity == null) return null;

        ParentDTO dto = new ParentDTO();
        dto.setParentId(entity.getParentId());
        dto.setParentName(entity.getParentName());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        return dto;
    }

    public Parent toEntity(ParentDTO dto) {
        if (dto == null) return null;

        Parent parent = new Parent();
        parent.setParentId(dto.getParentId());
        parent.setParentName(dto.getParentName());
        parent.setPhone(dto.getPhone());
        parent.setEmail(dto.getEmail());
        parent.setPassword(dto.getPassword());
        return parent;
    }

    public void updateEntityFromDto(ParentDTO dto, Parent entity) {
        if (dto == null || entity == null) return;
        entity.setParentName(dto.getParentName());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
    }
}
