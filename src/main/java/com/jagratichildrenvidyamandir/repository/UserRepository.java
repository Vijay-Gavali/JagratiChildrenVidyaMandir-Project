package com.jagratichildrenvidyamandir.repository;

import com.jagratichildrenvidyamandir.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByAdmissionNo(String admissionNo);
    Optional<User> findByEmail(String email);
    Optional<User> findByStudentPhone(String studentPhone);
    Optional<User> findByStudentAadharNo(String studentAadharNo);

    boolean existsByAdmissionNo(String admissionNo);
    boolean existsByEmail(String email);
    boolean existsByStudentPhone(String studentPhone);
    boolean existsByStudentAadharNo(String studentAadharNo);
    
    List<User> findByStudentClassClassId(Integer classId);

    // NEW: find students by parentPhone
    List<User> findByParentPhone(String parentPhone);
}
