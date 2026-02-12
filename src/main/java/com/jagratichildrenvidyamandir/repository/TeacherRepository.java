package com.jagratichildrenvidyamandir.repository;

import com.jagratichildrenvidyamandir.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    // Check if email or phone already exists
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);

    // Login with phone + password
    Optional<Teacher> findByPhoneAndPassword(String phone, String password);

    // Login with email + password
    Optional<Teacher> findByEmailAndPassword(String email, String password);

    // Get all teachers assigned to a particular classId
    List<Teacher> findAllByClasses_ClassId(Integer classId);
	boolean existsByAadharNo(String aadharNo);
    
}
