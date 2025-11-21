package com.jagratichildrenvidyamandir.service;

import com.jagratichildrenvidyamandir.dto.ParentDTO;
import com.jagratichildrenvidyamandir.dto.UserDTO;
import com.jagratichildrenvidyamandir.entity.Parent;
import com.jagratichildrenvidyamandir.entity.User;
import com.jagratichildrenvidyamandir.mapper.ParentMapper;
import com.jagratichildrenvidyamandir.mapper.UserMapper;
import com.jagratichildrenvidyamandir.repository.ParentRepository;
import com.jagratichildrenvidyamandir.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParentService {

    private final ParentRepository parentRepo;
    private final ParentMapper parentMapper;
    private final UserRepository userRepo;
    private final UserMapper userMapper;

    public ParentService(ParentRepository parentRepo, ParentMapper parentMapper,
                         UserRepository userRepo, UserMapper userMapper) {
        this.parentRepo = parentRepo;
        this.parentMapper = parentMapper;
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    public ParentDTO createParent(ParentDTO dto) {
        if (dto == null) return null;
        if (dto.getPhone() != null && parentRepo.existsByPhone(dto.getPhone())) return null;
        if (dto.getEmail() != null && parentRepo.existsByEmail(dto.getEmail())) return null;
        Parent p = parentMapper.toEntity(dto);
        p.setParentId(null);
        Parent saved = parentRepo.save(p);
        return parentMapper.toDto(saved);
    }

    public ParentDTO getParentById(Integer id) {
        return parentRepo.findById(id).map(parentMapper::toDto).orElse(null);
    }

    public List<ParentDTO> getAllParents() {
        return parentRepo.findAll().stream().map(parentMapper::toDto).collect(Collectors.toList());
    }

    public ParentDTO updateParent(Integer id, ParentDTO dto) {
        return parentRepo.findById(id).map(existing -> {
            if (dto.getPhone() != null && !dto.getPhone().equals(existing.getPhone())
                    && parentRepo.existsByPhone(dto.getPhone())) return null;
            if (dto.getEmail() != null && !dto.getEmail().equals(existing.getEmail())
                    && parentRepo.existsByEmail(dto.getEmail())) return null;
            parentMapper.updateEntityFromDto(dto, existing);
            Parent updated = parentRepo.save(existing);
            return parentMapper.toDto(updated);
        }).orElse(null);
    }

    public boolean deleteParent(Integer id) {
        if (!parentRepo.existsById(id)) return false;
        parentRepo.deleteById(id);
        return true;
    }

    // Authenticate parent (phone + password) - plain compare
    public ParentDTO authenticateParent(String phone, String password) {
        return parentRepo.findByPhone(phone)
                .filter(p -> p.getPassword() != null && p.getPassword().equals(password))
                .map(parentMapper::toDto)
                .orElse(null);
    }

    // Get children by parent phone
    public List<UserDTO> getChildrenByParentPhone(String parentPhone) {
        List<User> children = userRepo.findByParentPhone(parentPhone);
        return children.stream().map(userMapper::toDto).collect(Collectors.toList());
    }
}
