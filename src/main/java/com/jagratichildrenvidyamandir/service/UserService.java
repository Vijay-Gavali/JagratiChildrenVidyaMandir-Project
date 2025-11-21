package com.jagratichildrenvidyamandir.service;

import com.jagratichildrenvidyamandir.dto.UserDTO;
import com.jagratichildrenvidyamandir.entity.User;
import com.jagratichildrenvidyamandir.mapper.UserMapper;
import com.jagratichildrenvidyamandir.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // CREATE
    public UserDTO createUser(UserDTO dto) {
        if (dto == null) return null;
        // uniqueness checks
        if (dto.getAdmissionNo() != null && repository.existsByAdmissionNo(dto.getAdmissionNo())) return null;
        if (dto.getEmail() != null && repository.existsByEmail(dto.getEmail())) return null;
        if (dto.getStudentPhone() != null && repository.existsByStudentPhone(dto.getStudentPhone())) return null;
        if (dto.getStudentAadharNo() != null && repository.existsByStudentAadharNo(dto.getStudentAadharNo())) return null;

        User entity = mapper.toEntity(dto);
        entity.setUserId(null);
        User saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    // GET by id
    public UserDTO getUserById(Integer id) {
        return repository.findById(id).map(mapper::toDto).orElse(null);
    }

    // GET all
    public List<UserDTO> getAllUsers() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    // UPDATE
    public UserDTO updateUser(Integer id, UserDTO dto) {
        Optional<User> opt = repository.findById(id);
        if (opt.isEmpty()) return null;
        User existing = opt.get();

        if (dto.getAdmissionNo() != null && !dto.getAdmissionNo().equals(existing.getAdmissionNo())
                && repository.existsByAdmissionNo(dto.getAdmissionNo())) return null;
        if (dto.getEmail() != null && !dto.getEmail().equals(existing.getEmail())
                && repository.existsByEmail(dto.getEmail())) return null;
        if (dto.getStudentPhone() != null && !dto.getStudentPhone().equals(existing.getStudentPhone())
                && repository.existsByStudentPhone(dto.getStudentPhone())) return null;
        if (dto.getStudentAadharNo() != null && !dto.getStudentAadharNo().equals(existing.getStudentAadharNo())
                && repository.existsByStudentAadharNo(dto.getStudentAadharNo())) return null;

        mapper.updateEntityFromDto(dto, existing);
        User updated = repository.save(existing);
        return mapper.toDto(updated);
    }

    // DELETE
    public boolean deleteUser(Integer id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }

    // AUTH: student login by studentPhone + password (plain)
    public UserDTO authenticateStudent(String phone, String password) {
        return repository.findByStudentPhone(phone)
                .filter(u -> u.getPassword() != null && u.getPassword().equals(password))
                .map(mapper::toDto)
                .orElse(null);
    }
}
