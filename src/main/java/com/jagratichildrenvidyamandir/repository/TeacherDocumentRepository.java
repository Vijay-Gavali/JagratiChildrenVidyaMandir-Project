package com.jagratichildrenvidyamandir.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jagratichildrenvidyamandir.entity.TeacherDocument;
import com.jagratichildrenvidyamandir.enums.DocumentType;

import jakarta.transaction.Transactional;

@Repository
public interface TeacherDocumentRepository extends JpaRepository<TeacherDocument, Integer> {

    // Find all documents for a specific teacher
    List<TeacherDocument> findByTeacherTeacherId(Integer teacherId);

    // Find documents for a specific teacher and type
    List<TeacherDocument> findByTeacherTeacherIdAndType(Integer teacherId, DocumentType type);

	@Modifying
    @Transactional
    @Query("DELETE FROM TeacherDocument d WHERE d.teacher.teacherId = :teacherId")
    void deleteByTeacherId(@Param("teacherId") Integer teacherId);
}
