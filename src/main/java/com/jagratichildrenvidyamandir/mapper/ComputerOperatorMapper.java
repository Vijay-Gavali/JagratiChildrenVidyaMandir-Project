package com.jagratichildrenvidyamandir.mapper;

import com.jagratichildrenvidyamandir.dto.ComputerOperatorDTO;
import com.jagratichildrenvidyamandir.entity.ComputerOperator;

public class ComputerOperatorMapper {

    public static ComputerOperator toEntity(ComputerOperatorDTO dto) {
        if (dto == null) return null;

        ComputerOperator entity = new ComputerOperator();
        entity.setComputerOperatorId(dto.getComputerOperatorId());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setPassword(dto.getPassword());
        return entity;
    }

    public static ComputerOperatorDTO toDTO(ComputerOperator entity) {
        if (entity == null) return null;

        return new ComputerOperatorDTO(
                entity.getComputerOperatorId(),
                entity.getPhoneNumber(),
                entity.getPassword()
        );
    }
}
