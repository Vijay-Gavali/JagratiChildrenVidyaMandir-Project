package com.jagratichildrenvidyamandir.service;

import com.jagratichildrenvidyamandir.dto.ClassDTO;
import com.jagratichildrenvidyamandir.mapper.ClassMapper;
import com.jagratichildrenvidyamandir.entity.Class;
import com.jagratichildrenvidyamandir.repository.ClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassService {

    private final ClassRepository repository;
    private final ClassMapper mapper;

    public ClassService(ClassRepository repository, ClassMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // CREATE
    public ClassDTO createClass(ClassDTO dto) {
        Class entity = mapper.toEntity(dto);
        entity.setClassId(null); // auto-increment
        Class saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    // GET ONE
    public ClassDTO getClassById(Integer id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElse(null);
    }

    // GET ALL
    public List<ClassDTO> getAllClasses() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    // UPDATE
    public ClassDTO updateClass(Integer id, ClassDTO dto) {
        return repository.findById(id)
                .map(existing -> {
                    mapper.updateEntityFromDto(dto, existing);
                    Class updated = repository.save(existing);
                    return mapper.toDto(updated);
                })
                .orElse(null);
    }

    // DELETE
    public boolean deleteClass(Integer id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
