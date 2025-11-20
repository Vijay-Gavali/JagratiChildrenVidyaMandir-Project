package com.jagratichildrenvidyamandir.service;

import com.jagratichildrenvidyamandir.dto.UserDTO;
import com.jagratichildrenvidyamandir.entity.User;
import com.jagratichildrenvidyamandir.mapper.UserMapper;
import com.jagratichildrenvidyamandir.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // CREATE with uniqueness checks
    public UserDTO createUser(UserDTO dto) {
        if (dto == null) return null;

        // uniqueness checks
        if (dto.getAdmissionNo() != null && repository.existsByAdmissionNo(dto.getAdmissionNo())) return null;
        if (dto.getEmail() != null && repository.existsByEmail(dto.getEmail())) return null;
        if (dto.getStudentPhone() != null && repository.existsByStudentPhone(dto.getStudentPhone())) return null;
        if (dto.getStudentAadharNo() != null && repository.existsByStudentAadharNo(dto.getStudentAadharNo())) return null;

        User entity = mapper.toEntity(dto);
        entity.setUserId(null); // ensure auto-generated
        User saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    // GET ONE
    public UserDTO getUserById(Integer id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElse(null);
    }

    // GET ALL
    public List<UserDTO> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    // GET by unique fields
    public UserDTO getByAdmissionNo(String admissionNo) {
        return repository.findByAdmissionNo(admissionNo).map(mapper::toDto).orElse(null);
    }

    public UserDTO getByEmail(String email) {
        return repository.findByEmail(email).map(mapper::toDto).orElse(null);
    }

    public UserDTO getByPhone(String phone) {
        return repository.findByStudentPhone(phone).map(mapper::toDto).orElse(null);
    }

    public UserDTO getByAadhar(String aadhar) {
        return repository.findByStudentAadharNo(aadhar).map(mapper::toDto).orElse(null);
    }

    // UPDATE with uniqueness checks
    public UserDTO updateUser(Integer id, UserDTO dto) {
        return repository.findById(id)
                .map(existing -> {
                    // if admissionNo changed and new one exists -> reject (return null)
                    if (dto.getAdmissionNo() != null && !dto.getAdmissionNo().equals(existing.getAdmissionNo())
                            && repository.existsByAdmissionNo(dto.getAdmissionNo())) {
                        return null;
                    }
                    if (dto.getEmail() != null && !dto.getEmail().equals(existing.getEmail())
                            && repository.existsByEmail(dto.getEmail())) {
                        return null;
                    }
                    if (dto.getStudentPhone() != null && !dto.getStudentPhone().equals(existing.getStudentPhone())
                            && repository.existsByStudentPhone(dto.getStudentPhone())) {
                        return null;
                    }
                    if (dto.getStudentAadharNo() != null && !dto.getStudentAadharNo().equals(existing.getStudentAadharNo())
                            && repository.existsByStudentAadharNo(dto.getStudentAadharNo())) {
                        return null;
                    }

                    mapper.updateEntityFromDto(dto, existing);
                    User updated = repository.save(existing);
                    return mapper.toDto(updated);
                })
                .orElse(null);
    }

    // DELETE
    public boolean deleteUser(Integer id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
