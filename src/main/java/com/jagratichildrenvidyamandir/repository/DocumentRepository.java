package com.jagratichildrenvidyamandir.repository;

import com.jagratichildrenvidyamandir.entity.Document;
import com.jagratichildrenvidyamandir.enums.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Integer> {

    List<Document> findByUserUserId(Integer userId);

    List<Document> findByUserUserIdAndType(Integer userId, DocumentType type);
}
