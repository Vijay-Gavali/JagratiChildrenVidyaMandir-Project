package com.jagratichildrenvidyamandir.mapper;

import com.jagratichildrenvidyamandir.dto.FeesDTO;
import com.jagratichildrenvidyamandir.entity.*;
import org.springframework.stereotype.Component;

@Component
public class FeesMapper {

    public FeesDTO toDto(Fees entity) {
        if (entity == null) return null;

        FeesDTO dto = new FeesDTO();
        dto.setFeesId(entity.getFeesId());
        dto.setAmount(entity.getAmount());
        dto.setDueDate(entity.getDueDate());
        dto.setPaymentStatus(entity.getPaymentStatus());
        dto.setPaymentDate(entity.getPaymentDate());
        dto.setRemainingAmount(entity.getRemainingAmount());
        dto.setPaidAmount(entity.getPaidAmount());
        return dto;
    }

    public Fees toEntity(FeesDTO dto) {
        if (dto == null) return null;

        Fees entity = new Fees();
        entity.setFeesId(dto.getFeesId());
        entity.setAmount(dto.getAmount());
        entity.setDueDate(dto.getDueDate());
        entity.setPaymentStatus(dto.getPaymentStatus());
        entity.setPaymentDate(dto.getPaymentDate());
        entity.setRemainingAmount(dto.getRemainingAmount());
        entity.setPaidAmount(dto.getPaidAmount());
        return entity;
    }

    public void updateEntityFromDto(FeesDTO dto, Fees entity) {
        entity.setAmount(dto.getAmount());
        entity.setDueDate(dto.getDueDate());
        entity.setPaymentStatus(dto.getPaymentStatus());
        entity.setPaymentDate(dto.getPaymentDate());
        entity.setRemainingAmount(dto.getRemainingAmount());
        entity.setPaidAmount(dto.getPaidAmount());
    }
}
