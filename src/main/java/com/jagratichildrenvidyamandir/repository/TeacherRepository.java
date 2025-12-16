package com.jagratichildrenvidyamandir.repository;

import com.jagratichildrenvidyamandir.entity.Teacher;
import com.jagratichildrenvidyamandir.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String studentPhone);
    Optional<Teacher> findByPhoneAndPassword(String phone, String password);

    // login with email + password
    Optional<Teacher> findByEmailAndPassword(String email, String password);
}
