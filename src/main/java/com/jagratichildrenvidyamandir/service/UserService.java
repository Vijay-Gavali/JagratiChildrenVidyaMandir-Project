package com.jagratichildrenvidyamandir.service;

import com.jagratichildrenvidyamandir.dto.UserDTO;
import com.jagratichildrenvidyamandir.entity.User;
import com.jagratichildrenvidyamandir.entity.SessionEntity;
import com.jagratichildrenvidyamandir.mapper.UserMapper;
import com.jagratichildrenvidyamandir.repository.UserRepository;
import com.jagratichildrenvidyamandir.repository.SessionRepository; // Ensure this import exists
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class UserService {

	private final UserRepository repository;
	private final SessionRepository sessionRepository; // Added SessionRepository
	private final UserMapper mapper;

	public UserService(UserRepository repository, SessionRepository sessionRepository, UserMapper mapper) {
		this.repository = repository;
		this.sessionRepository = sessionRepository;
		this.mapper = mapper;
	}

	public UserDTO createUser(Integer sessionId, UserDTO dto) {
		if (dto == null)
			return null;

		// Specific checks with specific return values
		if (dto.getAdmissionNo() != null && repository.existsByAdmissionNo(dto.getAdmissionNo()))
			throw new IllegalArgumentException("Admission number already exists");
		if (dto.getEmail() != null && repository.existsByEmail(dto.getEmail()))
			throw new IllegalArgumentException("Email already exists");
		if (dto.getStudentPhone() != null && repository.existsByStudentPhone(dto.getStudentPhone()))
			throw new IllegalArgumentException("Student phone already exists");
		if (dto.getStudentAadharNo() != null && repository.existsByStudentAadharNo(dto.getStudentAadharNo()))
			throw new IllegalArgumentException("Student Aadhar already exists");
		if (dto.getApaarId() != null && repository.existsByApaarId(dto.getApaarId()))
			throw new IllegalArgumentException("APAAR ID already exists");
		if (dto.getPanNo() != null && repository.existsByPanNo(dto.getPanNo()))
			throw new IllegalArgumentException("PAN number already exists");

		User entity = mapper.toEntity(dto);
		entity.setUserId(null);
		sessionRepository.findById(sessionId).ifPresent(entity::setSession);

		User saved = repository.save(entity);
		return mapper.toDto(saved);
	}

	// GET by id
	public UserDTO getUserById(Integer id) {
		return repository.findById(id).map(mapper::toDto).orElse(null);
	}

	// GET all (Filtered by Session)
	public List<UserDTO> getAllUsers(Integer sessionId) {
		return repository.findAll().stream()
				.filter(u -> u.getSession() != null && u.getSession().getSessionId().equals(sessionId))
				.map(mapper::toDto).collect(Collectors.toList());
	}

	// UPDATE - Updated to handle sessionId
	public UserDTO updateUser(Integer id, UserDTO dto, Integer sessionId) {
		Optional<User> opt = repository.findById(id);
		if (opt.isEmpty())
			return null;
		User existing = opt.get();

		// Check uniqueness for existing fields (ignoring current record)
		if (dto.getAdmissionNo() != null && !dto.getAdmissionNo().equals(existing.getAdmissionNo())
				&& repository.existsByAdmissionNo(dto.getAdmissionNo()))
			return null;
		if (dto.getEmail() != null && !dto.getEmail().equals(existing.getEmail())
				&& repository.existsByEmail(dto.getEmail()))
			return null;
		if (dto.getStudentPhone() != null && !dto.getStudentPhone().equals(existing.getStudentPhone())
				&& repository.existsByStudentPhone(dto.getStudentPhone()))
			return null;
		if (dto.getStudentAadharNo() != null && !dto.getStudentAadharNo().equals(existing.getStudentAadharNo())
				&& repository.existsByStudentAadharNo(dto.getStudentAadharNo()))
			return null;

		// NEW: Check uniqueness for new fields (ignoring current record)
		if (dto.getApaarId() != null && !dto.getApaarId().equals(existing.getApaarId())
				&& repository.existsByApaarId(dto.getApaarId()))
			return null;
		if (dto.getPanNo() != null && !dto.getPanNo().equals(existing.getPanNo())
				&& repository.existsByPanNo(dto.getPanNo()))
			return null;

		mapper.updateEntityFromDto(dto, existing);

		// Update session if it changed or needs refreshing
		sessionRepository.findById(sessionId).ifPresent(existing::setSession);

		User updated = repository.save(existing);
		return mapper.toDto(updated);
	}

	// DELETE
	public boolean deleteUser(Integer id) {
		if (!repository.existsById(id))
			return false;
		repository.deleteById(id);
		return true;
	}

	// AUTH: Changed from phone to admissionNo (Kept as is, no sessionId required
	// for login)
	public UserDTO authenticateStudent(String admissionNo, String password) {
		return repository.findByAdmissionNo(admissionNo)
				.filter(u -> u.getPassword() != null && u.getPassword().equals(password)).map(mapper::toDto)
				.orElse(null);
	}
}