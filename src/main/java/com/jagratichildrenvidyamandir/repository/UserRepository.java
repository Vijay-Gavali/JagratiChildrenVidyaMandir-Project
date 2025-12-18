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
    
    // NEW: Finders for new unique fields
    Optional<User> findByApaarId(String apaarId);
    Optional<User> findByPanNo(String panNo);

    boolean existsByAdmissionNo(String admissionNo);
    boolean existsByEmail(String email);
    boolean existsByStudentPhone(String studentPhone);
    boolean existsByStudentAadharNo(String studentAadharNo);
    
    // NEW: Existence checks for new unique fields
    boolean existsByApaarId(String apaarId);
    boolean existsByPanNo(String panNo);
    
    List<User> findByStudentClassClassId(Integer classId);

    // find students by parentPhone
    List<User> findByParentPhone(String parentPhone);
}