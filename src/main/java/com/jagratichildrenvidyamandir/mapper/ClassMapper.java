package com.jagratichildrenvidyamandir.mapper;

import com.jagratichildrenvidyamandir.dto.ClassDTO;
import com.jagratichildrenvidyamandir.dto.UserDTO;
import com.jagratichildrenvidyamandir.entity.ClassEntity;
import com.jagratichildrenvidyamandir.entity.User;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClassMapper {

	public ClassDTO toDto(ClassEntity entity) {
		if (entity == null)
			return null;

		ClassDTO dto = new ClassDTO();
		dto.setClassId(entity.getClassId());
		dto.setClassName(entity.getClassName());
		dto.setFees(entity.getFees());

		// map users → students list
		if (entity.getStudents() != null) {
			List<UserDTO> students = entity.getStudents().stream().map(this::userToDto).collect(Collectors.toList());
			dto.setStudents(students);
		}

		return dto;
	}
	public ClassDTO toFullDto(ClassEntity entity) {
		if (entity == null)
			return null;

		ClassDTO dto = new ClassDTO();
		dto.setClassId(entity.getClassId());
		dto.setClassName(entity.getClassName());
		dto.setFees(entity.getFees());

		// map users → students list
		if (entity.getStudents() != null) {
			List<UserDTO> students = entity.getStudents().stream().map(this::userToDto).collect(Collectors.toList());
			dto.setStudents(students);
		}

		return dto;
	}

	public ClassEntity toEntity(ClassDTO dto) {
		if (dto == null)
			return null;

		ClassEntity entity = new ClassEntity();
		entity.setClassId(dto.getClassId());
		entity.setClassName(dto.getClassName());
		entity.setFees(dto.getFees());
		return entity;
	}

	public void updateEntityFromDto(ClassDTO dto, ClassEntity entity) {
		if (dto == null || entity == null)
			return;
		entity.setClassName(dto.getClassName());
		entity.setFees(dto.getFees());
	}

	// ---------------------- USER MAPPING ----------------------

	private UserDTO userToDto(User user) {
		if (user == null)
			return null;

		UserDTO dto = new UserDTO();
		dto.setUserId(user.getUserId());
		dto.setName(user.getName());
		dto.setAdmissionNo(user.getAdmissionNo());
		dto.setAdmissionDate(user.getAdmissionDate());
		dto.setPassword(user.getPassword());
		dto.setStudentPhone(user.getStudentPhone());
		dto.setEmail(user.getEmail());
		dto.setFatherName(user.getFatherName());
		dto.setMotherName(user.getMotherName());
		dto.setTcNumber(user.getTcNumber());

		// optional: add class name if needed

		return dto;
	}
}