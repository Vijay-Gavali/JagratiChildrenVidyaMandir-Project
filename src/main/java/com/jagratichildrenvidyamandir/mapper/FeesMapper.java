package com.jagratichildrenvidyamandir.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jagratichildrenvidyamandir.dto.FeesDTO;
import com.jagratichildrenvidyamandir.entity.Fees;
import com.jagratichildrenvidyamandir.entity.User;
import com.jagratichildrenvidyamandir.repository.UserRepository;

@Component
public class FeesMapper {
	
	@Autowired
	private UserRepository userRepository;

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
        
        if (entity.getUser() != null)
            dto.setUserId(entity.getUser().getUserId());

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
        
        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found: " + dto.getUserId()));
            entity.setUser(user);
        }
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
