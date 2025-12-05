package com.jagratichildrenvidyamandir.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jagratichildrenvidyamandir.dto.FeesDTO;
import com.jagratichildrenvidyamandir.entity.Fees;
import com.jagratichildrenvidyamandir.mapper.FeesMapper;
import com.jagratichildrenvidyamandir.repository.FeesRepository;
import com.jagratichildrenvidyamandir.repository.UserRepository;

@Service
public class FeesService {

	private final FeesRepository feesRepository;
	private final FeesMapper mapper;
	private final UserRepository userRepository;

	public FeesService(FeesRepository repository, FeesMapper mapper, UserRepository userRepository) {
		this.feesRepository = repository;
		this.mapper = mapper;
		this.userRepository = userRepository;
	}

	public FeesDTO createFees(FeesDTO dto) {
		Fees entity = mapper.toEntity(dto);
		entity.setFeesId(null); // auto increment
		Fees saved = feesRepository.save(entity);
		return mapper.toDto(saved);
	}

	public FeesDTO getFeesById(Integer id) {
		return feesRepository.findById(id).map(mapper::toDto).orElse(null);
	}

	public List<FeesDTO> getAllFees() {
		return feesRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
	}

	public FeesDTO updateFees(Integer id, FeesDTO dto) {
		return feesRepository.findById(id).map(existing -> {
			mapper.updateEntityFromDto(dto, existing);
			Fees updated = feesRepository.save(existing);
			return mapper.toDto(updated);
		}).orElse(null);
	}

	public boolean deleteFees(Integer id) {
		if (!feesRepository.existsById(id))
			return false;
		feesRepository.deleteById(id);
		return true;
	}

	public List<FeesDTO> getFeesByUserId(Integer userId) {
		if (!userRepository.existsById(userId)) {
			throw new IllegalArgumentException("User not found with id: " + userId);
		}

		List<Fees> feesList = feesRepository.findByUserId(userId);

		return feesList.stream()
				.map(f -> new FeesDTO(f.getFeesId(), f.getAmount(), f.getDueDate(), f.getPaymentStatus(),
						f.getPaymentDate(), f.getRemainingAmount(), f.getPaidAmount(),
						f.getUser() != null ? f.getUser().getUserId() : null))
				.collect(Collectors.toList());
	}
}