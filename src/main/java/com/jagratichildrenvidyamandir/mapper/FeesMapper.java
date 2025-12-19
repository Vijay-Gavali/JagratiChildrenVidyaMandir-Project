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
		entity.setPaidAmount(
			dto.getPaidAmount() != null ? dto.getPaidAmount() : BigDecimal.ZERO
		);

		BigDecimal remaining =
			dto.getRemainingAmount() != null
				? dto.getRemainingAmount()
				: dto.getAmount();

		entity.setRemainingAmount(remaining);

		entity.setPaymentStatus(
			remaining.compareTo(BigDecimal.ZERO) == 0 ? "PAID" : "UNPAID"
		);

		if (dto.getUserId() != null) {
			entity.setUser(
				userRepository.findById(dto.getUserId())
					.orElseThrow(() -> new RuntimeException("User not found"))
			);
		}

		return entity;
	}

	public FeesDTO toDto(Fees entity) {
		return new FeesDTO(
			entity.getFeesId(),
			entity.getAmount(),
			entity.getPaymentStatus(),
			entity.getRemainingAmount(),
			entity.getPaidAmount(),
			entity.getUser() != null ? entity.getUser().getUserId() : null
		);
	}

	public void updateEntityFromDto(FeesDTO dto, Fees entity) {
		entity.setAmount(dto.getAmount());
		entity.setPaidAmount(dto.getPaidAmount());
		entity.setRemainingAmount(dto.getRemainingAmount());

		entity.setPaymentStatus(
			entity.getRemainingAmount().compareTo(BigDecimal.ZERO) == 0
				? "PAID"
				: "PARTIAL"
		);
	}
}
