package com.jagratichildrenvidyamandir.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jagratichildrenvidyamandir.dto.ComputerOperatorDTO;
import com.jagratichildrenvidyamandir.entity.ComputerOperator;
import com.jagratichildrenvidyamandir.mapper.ComputerOperatorMapper;
import com.jagratichildrenvidyamandir.repository.ComputerOperatorRepository;

@Service
public class ComputerOperatorService {

    private final ComputerOperatorRepository repository;

    public ComputerOperatorService(ComputerOperatorRepository repository) {
        this.repository = repository;
    }

    /* CREATE */
    public ComputerOperatorDTO create(ComputerOperatorDTO dto) {
        ComputerOperator saved =
                repository.save(ComputerOperatorMapper.toEntity(dto));
        return ComputerOperatorMapper.toDTO(saved);
    }

    /* READ ALL */
    public List<ComputerOperatorDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(ComputerOperatorMapper::toDTO)
                .collect(Collectors.toList());
    }

    /* READ BY ID */
    public ComputerOperatorDTO getById(Integer id) {
        return repository.findById(id)
                .map(ComputerOperatorMapper::toDTO)
                .orElse(null);
    }

    /* UPDATE */
    public ComputerOperatorDTO update(Integer id, ComputerOperatorDTO dto) {
        return repository.findById(id).map(existing -> {
            existing.setPhoneNumber(dto.getPhoneNumber());
            existing.setPassword(dto.getPassword());
            return ComputerOperatorMapper.toDTO(repository.save(existing));
        }).orElse(null);
    }

    /* DELETE */
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    /* LOGIN */
    public ComputerOperatorDTO login(String phoneNumber, String password) {
        return repository
                .findByPhoneNumberAndPassword(phoneNumber, password)
                .map(ComputerOperatorMapper::toDTO)
                .orElse(null);
    }
}
