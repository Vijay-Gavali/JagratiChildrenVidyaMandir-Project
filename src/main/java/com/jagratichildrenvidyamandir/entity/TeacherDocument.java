package com.jagratichildrenvidyamandir.entity;

import com.jagratichildrenvidyamandir.enums.DocumentType;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "teacher_documents")
public class TeacherDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer documentId;

    // Proper many-to-one mapping to Teacher
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @Enumerated(EnumType.STRING)
    private DocumentType type;

    private String filePath;      // stored location
    private String originalName;  // original filename
    private LocalDateTime uploadedAt;

    public TeacherDocument() {}

    // Getters & Setters
    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public DocumentType getType() {
        return type;
    }

    public void setType(DocumentType type) {
        this.type = type;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
}
