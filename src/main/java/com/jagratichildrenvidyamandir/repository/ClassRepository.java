package com.jagratichildrenvidyamandir.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jagratichildrenvidyamandir.entity.ClassEntity;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Integer> {

    // Fetch class with users (keep as is)
   // @EntityGraph(attributePaths = "users")
    //Optional<ClassEntity> findById(Integer id);

	// Fetch class with users (as it is)
    @EntityGraph(attributePaths = "students")
    Optional<ClassEntity> findById(Integer id);

    // Fetch class along with teachers
    @EntityGraph(attributePaths = "teachers")
    Optional<ClassEntity> findWithTeachersByClassId(Integer id);
}
