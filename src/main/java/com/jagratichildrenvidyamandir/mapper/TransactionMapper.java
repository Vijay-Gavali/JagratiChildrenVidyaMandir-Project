package com.jagratichildrenvidyamandir.mapper;

import org.springframework.stereotype.Component;

import com.jagratichildrenvidyamandir.dto.TransactionDTO;
import com.jagratichildrenvidyamandir.entity.Transaction;

@Component
public class TransactionMapper {

	// Convert TransactionRequestDTO to Transaction Entity
	public Transaction toEntity(TransactionDTO dto) {
		if (dto == null) {
			return null;
		}

		Transaction transaction = new Transaction();
		transaction.setAmount(dto.getAmount());
		transaction.setPaymentMode(dto.getPaymentMode());
		transaction.setBankName(dto.getBankName());
		transaction.setUpiId(dto.getUpiId());
		transaction.setDescription(dto.getDescription());
		transaction.setRemarks(dto.getRemarks());

		return transaction;
	}

	// Convert Transaction Entity to TransactionResponseDTO
	public TransactionDTO toDto(Transaction entity) {
		if (entity == null) {
			return null;
		}

		TransactionDTO dto = new TransactionDTO();
		dto.setId(entity.getId());
		dto.setTransactionId(entity.getTransactionId());
		dto.setAmount(entity.getAmount());
		dto.setPaymentDate(entity.getPaymentDate());
		dto.setPaymentMode(entity.getPaymentMode());
		dto.setBankName(entity.getBankName());
		dto.setUpiId(entity.getUpiId());
		dto.setDescription(entity.getDescription());
		dto.setStatus(entity.getStatus());
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());
		dto.setReceiptNumber(entity.getReceiptNumber());
		dto.setRemarks(entity.getRemarks());

		try {
			if (entity.getUser() != null) {
				dto.setUserId(entity.getUser().getUserId());
			}
		} catch (org.hibernate.LazyInitializationException lie) {
			System.out.println(lie);
		}
		if (entity.getSession() != null) {
			dto.setSessionId(entity.getSession().getSessionId());
		} else {
			dto.setSessionId(null);
		}

		return dto;
	}

	// Update existing Transaction Entity with DTO data
	public void updateEntity(TransactionDTO dto, Transaction entity) {
		if (dto == null || entity == null) {
			return;
		}

		if (dto.getAmount() != null) {
			entity.setAmount(dto.getAmount());
		}
		if (dto.getPaymentMode() != null) {
			entity.setPaymentMode(dto.getPaymentMode());
		}
		if (dto.getBankName() != null) {
			entity.setBankName(dto.getBankName());
		}
		if (dto.getUpiId() != null) {
			entity.setUpiId(dto.getUpiId());
		}
		if (dto.getDescription() != null) {
			entity.setDescription(dto.getDescription());
		}
		if (dto.getRemarks() != null) {
			entity.setRemarks(dto.getRemarks());
		}
	}
}