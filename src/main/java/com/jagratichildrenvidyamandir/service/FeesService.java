package com.jagratichildrenvidyamandir.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jagratichildrenvidyamandir.dto.FeesDTO;
import com.jagratichildrenvidyamandir.entity.Fees;
import com.jagratichildrenvidyamandir.mapper.FeesMapper;
import com.jagratichildrenvidyamandir.repository.FeesRepository;
import com.jagratichildrenvidyamandir.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class FeesService {

	private final FeesRepository feesRepository;
	private final FeesMapper mapper;
	private final UserRepository userRepository;

	public FeesService(FeesRepository feesRepository, FeesMapper mapper, UserRepository userRepository) {
		this.feesRepository = feesRepository;
		this.mapper = mapper;
		this.userRepository = userRepository;
	}

	public FeesDTO createFees(FeesDTO dto) {
		if (!userRepository.existsById(dto.getUserId())) {
			throw new IllegalArgumentException("User not found");
		}

		Fees entity = mapper.toEntity(dto);
		entity.setFeesId(null);

		return mapper.toDto(feesRepository.save(entity));
	}

	public FeesDTO getFeesById(Integer id) {
		return feesRepository.findById(id).map(mapper::toDto).orElse(null);
	}

	public List<FeesDTO> getAllFees() {
		return feesRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
	}

	@Transactional
	public FeesDTO updateFees(Integer id, FeesDTO dto) {
		return feesRepository.findById(id).map(existing -> {
			mapper.updateEntityFromDto(dto, existing);
			return mapper.toDto(feesRepository.save(existing));
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
			throw new IllegalArgumentException("User not found");
		}

		return feesRepository.findByUserId(userId).stream().map(mapper::toDto).collect(Collectors.toList());
	}
}
