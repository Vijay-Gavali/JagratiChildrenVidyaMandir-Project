package com.jagratichildrenvidyamandir.service;

import com.jagratichildrenvidyamandir.dto.FeesDTO;
import com.jagratichildrenvidyamandir.mapper.FeesMapper;
import com.jagratichildrenvidyamandir.entity.*;
import com.jagratichildrenvidyamandir.repository.FeesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeesService {

    private final FeesRepository repository;
    private final FeesMapper mapper;

    public FeesService(FeesRepository repository, FeesMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public FeesDTO createFees(FeesDTO dto) {
        Fees entity = mapper.toEntity(dto);
        entity.setFeesId(null); // auto increment
        Fees saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    public FeesDTO getFeesById(Integer id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElse(null);
    }

    public List<FeesDTO> getAllFees() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public FeesDTO updateFees(Integer id, FeesDTO dto) {
        return repository.findById(id)
                .map(existing -> {
                    mapper.updateEntityFromDto(dto, existing);
                    Fees updated = repository.save(existing);
                    return mapper.toDto(updated);
                })
                .orElse(null);
    }

    public boolean deleteFees(Integer id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
