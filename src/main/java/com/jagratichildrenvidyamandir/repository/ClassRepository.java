package com.jagratichildrenvidyamandir.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jagratichildrenvidyamandir.entity.ClassEntity;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Integer> {
	// add custom queries if needed

	// Fetch class with users to avoid lazy-loading issues outside transaction
	@EntityGraph(attributePaths = "users")
	Optional<ClassEntity> findById(Integer id);
}
