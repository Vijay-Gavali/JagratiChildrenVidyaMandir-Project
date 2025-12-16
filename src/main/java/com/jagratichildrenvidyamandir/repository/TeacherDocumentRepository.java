package com.jagratichildrenvidyamandir.repository;

import com.jagratichildrenvidyamandir.entity.TeacherDocument;
import com.jagratichildrenvidyamandir.enums.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherDocumentRepository extends JpaRepository<TeacherDocument, Integer> {

    // Find all documents for a specific teacher
    List<TeacherDocument> findByTeacherTeacherId(Integer teacherId);

    // Find documents for a specific teacher and type
    List<TeacherDocument> findByTeacherTeacherIdAndType(Integer teacherId, DocumentType type);
}
