package com.jagratichildrenvidyamandir.mapper;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jagratichildrenvidyamandir.dto.FeesDTO;
import com.jagratichildrenvidyamandir.entity.Fees;
import com.jagratichildrenvidyamandir.repository.UserRepository;

@Component
public class FeesMapper {

	@Autowired
	private UserRepository userRepository;

	public Fees toEntity(FeesDTO dto) {
		Fees entity = new Fees();

		entity.setFeesId(dto.getFeesId());
		entity.setAmount(dto.getAmount());
		entity.setPaidAmount(dto.getPaidAmount() != null ? dto.getPaidAmount() : BigDecimal.ZERO);

		BigDecimal remaining = dto.getRemainingAmount() != null ? dto.getRemainingAmount() : dto.getAmount();

		entity.setRemainingAmount(remaining);

		entity.setPaymentStatus(remaining.compareTo(BigDecimal.ZERO) == 0 ? "PAID" : "UNPAID");

		if (dto.getUserId() != null) {
			entity.setUser(
					userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));
		}

		return entity;
	}

	public FeesDTO toDto(Fees entity) {
		return new FeesDTO(entity.getFeesId(), entity.getAmount(), entity.getPaymentStatus(),
				entity.getRemainingAmount(), entity.getPaidAmount(),
				entity.getUser() != null ? entity.getUser().getUserId() : null);
	}

	public void updateEntityFromDto(FeesDTO dto, Fees entity) {
		System.out.println("DTO received: amount=" + dto.getAmount() + ", paid=" + dto.getPaidAmount()); // DEBUG

		// 1. Update ONLY amount and paidAmount (derived fields ignored)
		if (dto.getAmount() != null) {
			entity.setAmount(dto.getAmount());
		}

		if (dto.getPaidAmount() != null) {
			entity.setPaidAmount(dto.getPaidAmount());
		}

		// 2. **CRITICAL**: Calculate remaining = amount - paid (ignores
		// DTO.remainingAmount)
		BigDecimal amount = entity.getAmount() != null ? entity.getAmount() : BigDecimal.ZERO;
		BigDecimal paid = entity.getPaidAmount() != null ? entity.getPaidAmount() : BigDecimal.ZERO;
		BigDecimal remaining = amount.subtract(paid);

		entity.setRemainingAmount(remaining);

		// 3. Set status based on calculation
		if (remaining.compareTo(BigDecimal.ZERO) == 0) {
			entity.setPaymentStatus("PAID");
		} else if (paid.compareTo(BigDecimal.ZERO) > 0) {
			entity.setPaymentStatus("PARTIAL");
		} else {
			entity.setPaymentStatus("UNPAID");
		}

		System.out.println("Final: " + amount + " - " + paid + " = " + remaining); // DEBUG
	}
}
