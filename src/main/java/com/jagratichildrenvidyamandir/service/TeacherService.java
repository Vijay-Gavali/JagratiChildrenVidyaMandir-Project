package com.jagratichildrenvidyamandir.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jagratichildrenvidyamandir.dto.ClassDTO;
import com.jagratichildrenvidyamandir.dto.TeacherDTO;
import com.jagratichildrenvidyamandir.dto.UserDTO;
import com.jagratichildrenvidyamandir.entity.ClassEntity;
import com.jagratichildrenvidyamandir.entity.Teacher;
import com.jagratichildrenvidyamandir.entity.User;
import com.jagratichildrenvidyamandir.mapper.TeacherMapper;
import com.jagratichildrenvidyamandir.repository.ClassRepository;
import com.jagratichildrenvidyamandir.repository.TeacherDocumentRepository;
import com.jagratichildrenvidyamandir.repository.TeacherRepository;

import jakarta.transaction.Transactional;

@Service
public class TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private ClassRepository classRepository;

	@Autowired
	private TeacherMapper teacherMapper;
	
	@Autowired
	private TeacherDocumentRepository documentRepository;

	@Transactional
	public TeacherDTO registerTeacher(TeacherDTO dto) {

		if (teacherRepository.existsByEmail(dto.getEmail())) {
			throw new RuntimeException("Email already exists!");
		}
		if (teacherRepository.existsByPhone(dto.getPhone())) {
			throw new RuntimeException("Phone already exists!");
		}

		if (dto.getClassNames() == null || dto.getClassNames().isEmpty()) {
			throw new RuntimeException("At least one class is required");
		}

		if (dto.getPassword() == null || dto.getPassword().isEmpty()) {
			throw new RuntimeException("enter the password");
		}

		Teacher teacher = teacherMapper.toEntity(dto);

		List<ClassEntity> classes = classRepository.findByClassNameIn(dto.getClassNames());

		if (classes.size() != dto.getClassNames().size()) {
			throw new RuntimeException("Invalid class name found");
		}

		teacher.setClasses(classes);

		Teacher saved = teacherRepository.save(teacher);

		return teacherMapper.toDTO(saved);
	}

	// ================= UPDATE TEACHER =================
	public TeacherDTO updateTeacher(Integer id, TeacherDTO dto) {

		Teacher existing = teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found"));

		// Validate email & phone uniqueness
		if (!existing.getEmail().equals(dto.getEmail()) && teacherRepository.existsByEmail(dto.getEmail())) {
			throw new RuntimeException("Email already exists!");
		}
		if (!existing.getPhone().equals(dto.getPhone()) && teacherRepository.existsByPhone(dto.getPhone())) {
			throw new RuntimeException("Phone already exists!");
		}

		// Update fields
		existing.setName(dto.getName());
		existing.setEmail(dto.getEmail());
		existing.setPhone(dto.getPhone());
		existing.setPassword(dto.getPassword());
		existing.setEducationalDetails(dto.getEducationalDetails());
		existing.setYearOfExperience(dto.getYearOfExperience());
		existing.setDateOfBirth(dto.getDateOfBirth());
		existing.setAadharNo(dto.getAadharNo());
		existing.setAddress(dto.getAddress());

		Teacher updated = teacherRepository.save(existing);

		TeacherDTO response = teacherMapper.toDTO(updated);
		response.setClassNames(updated.getClasses() != null
				? updated.getClasses().stream().map(ClassEntity::getClassName).collect(Collectors.toList())
				: new ArrayList<>());

		return response;
	}

	// ================= GET TEACHER BY ID =================
	public TeacherDTO getTeacherById(Integer id) {
		Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found"));

		TeacherDTO dto = teacherMapper.toDTO(teacher);
		dto.setClassNames(teacher.getClasses() != null
				? teacher.getClasses().stream().map(ClassEntity::getClassName).collect(Collectors.toList())
				: new ArrayList<>());

		return dto;
	}

	@Transactional
	public void deleteTeacher(Integer id) {
		Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found"));

		// remove relationships
		for (ClassEntity cls : teacher.getClasses()) {
			cls.getTeachers().remove(teacher);
		}
		teacher.getClasses().clear();
        documentRepository.deleteByTeacherId(id);
		teacherRepository.delete(teacher);
	}

	// ================= GET ALL TEACHERS =================
	public List<TeacherDTO> getAllTeachers() {
		return teacherRepository.findAll().stream().map(t -> {
			TeacherDTO dto = teacherMapper.toDTO(t);
			dto.setClassNames(t.getClasses() != null
					? t.getClasses().stream().map(ClassEntity::getClassName).collect(Collectors.toList())
					: new ArrayList<>());
			return dto;
		}).collect(Collectors.toList());
	}

	// ================= ASSIGN CLASSES TO TEACHER =================
	public Teacher assignClassesToTeacher(Integer teacherId, List<Integer> classIds) {
		Teacher teacher = teacherRepository.findById(teacherId)
				.orElseThrow(() -> new RuntimeException("Teacher not found"));

		List<ClassEntity> classes = classRepository.findAllById(classIds);

		if (classes.size() != classIds.size()) {
			throw new RuntimeException("Invalid class ID found!");
		}

		teacher.setClasses(classes);

		// Update bidirectional mapping
		for (ClassEntity cls : classes) {
			List<Teacher> teachers = cls.getTeachers();
			if (!teachers.contains(teacher)) {
				teachers.add(teacher);
				cls.setTeachers(teachers);
			}
		}

		return teacherRepository.save(teacher);
	}

	// ================= GET CLASSES BY TEACHER =================
	public List<ClassDTO> getClassesByTeacher(Integer teacherId) {
		Teacher teacher = teacherRepository.findById(teacherId)
				.orElseThrow(() -> new RuntimeException("Teacher not found"));

		if (teacher.getClasses() == null || teacher.getClasses().isEmpty()) {
			return new ArrayList<>();
		}

		return teacher.getClasses().stream().map(c -> new ClassDTO(c.getClassId(), c.getClassName()))
				.collect(Collectors.toList());
	}

	// ================= GET STUDENTS BY TEACHER =================
	public List<UserDTO> getStudentsByTeacher(Integer teacherId) {
		Teacher teacher = teacherRepository.findById(teacherId)
				.orElseThrow(() -> new RuntimeException("Teacher not found"));

		if (teacher.getClasses() == null || teacher.getClasses().isEmpty()) {
			return new ArrayList<>();
		}

		List<UserDTO> result = new ArrayList<>();
		for (ClassEntity cls : teacher.getClasses()) {
			for (User student : cls.getStudents()) {
				UserDTO dto = new UserDTO();
				dto.setUserId(student.getUserId());
				dto.setName(student.getName());
				dto.setAdmissionNo(student.getAdmissionNo());
				dto.setAdmissionDate(student.getAdmissionDate());
				result.add(dto);
			}
		}

		return result;
	}

	// ================= TEACHER LOGIN =================
	public Teacher authenticateTeacher(String username, String password) {
		return teacherRepository.findByPhoneAndPassword(username, password)
				.or(() -> teacherRepository.findByEmailAndPassword(username, password)).orElse(null);
	}

	// ================= GET TEACHERS BY CLASS ID =================
	public List<TeacherDTO> getTeachersByClassId(Integer classId) {
		List<Teacher> teachers = teacherRepository.findAllByClasses_ClassId(classId);

		return teachers.stream().map(t -> {
			TeacherDTO dto = teacherMapper.toDTO(t);
			dto.setClassNames(t.getClasses() != null
					? t.getClasses().stream().map(ClassEntity::getClassName).collect(Collectors.toList())
					: new ArrayList<>());
			return dto;
		}).collect(Collectors.toList());
	}
}
