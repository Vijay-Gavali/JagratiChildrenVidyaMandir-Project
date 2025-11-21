package com.jagratichildrenvidyamandir.repository;

import com.jagratichildrenvidyamandir.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Integer> {
    Optional<Parent> findByPhone(String phone);
    Optional<Parent> findByEmail(String email);
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);
}
