package com.jagratichildrenvidyamandir.repository;

import com.jagratichildrenvidyamandir.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParentRepository extends JpaRepository<Parent, Integer> {

    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);

    Optional<Parent> findByPhone(String phone);
    Optional<Parent> findByEmail(String email);
}
