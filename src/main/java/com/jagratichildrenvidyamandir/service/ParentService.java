package com.jagratichildrenvidyamandir.service;

import com.jagratichildrenvidyamandir.dto.ParentDTO;
import com.jagratichildrenvidyamandir.entity.Parent;
import com.jagratichildrenvidyamandir.mapper.ParentMapper;
import com.jagratichildrenvidyamandir.repository.ParentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParentService {

    private final ParentRepository repository;
    private final ParentMapper mapper;

    public ParentService(ParentRepository repository, ParentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // CREATE
    public ParentDTO createParent(ParentDTO dto) {
        if (repository.existsByPhone(dto.getPhone())) return null;
        if (repository.existsByEmail(dto.getEmail())) return null;

        Parent entity = mapper.toEntity(dto);
        entity.setParentId(null);

        Parent saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    // GET BY ID
    public ParentDTO getParentById(Integer id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElse(null);
    }

    // GET ALL
    public List<ParentDTO> getAllParents() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    // UPDATE
    public ParentDTO updateParent(Integer id, ParentDTO dto) {
        return repository.findById(id)
                .map(existing -> {
                    // unique checks
                    if (!existing.getPhone().equals(dto.getPhone()) && repository.existsByPhone(dto.getPhone()))
                        return null;

                    if (!existing.getEmail().equals(dto.getEmail()) && repository.existsByEmail(dto.getEmail()))
                        return null;

                    mapper.updateEntityFromDto(dto, existing);
                    Parent updated = repository.save(existing);
                    return mapper.toDto(updated);
                })
                .orElse(null);
    }

    // DELETE
    public boolean deleteParent(Integer id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
