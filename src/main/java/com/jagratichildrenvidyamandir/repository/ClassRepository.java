package com.jagratichildrenvidyamandir.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.jagratichildrenvidyamandir.entity.ClassEntity;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Integer> {
	// add custom queries if needed
	@EntityGraph(attributePaths = "students")
	Optional<ClassEntity> findById(Integer id);

	List<ClassEntity> findByClassNameIn(List<String> classNames);
    List<ClassEntity> findBySession_SessionId(Integer sessionId);

}
