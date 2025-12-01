package com.jagratichildrenvidyamandir.service;

import com.jagratichildrenvidyamandir.dto.DocumentDTO;
import com.jagratichildrenvidyamandir.entity.Document;
import com.jagratichildrenvidyamandir.entity.User;
import com.jagratichildrenvidyamandir.enums.DocumentType;
import com.jagratichildrenvidyamandir.repository.DocumentRepository;
import com.jagratichildrenvidyamandir.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    private final DocumentRepository docRepo;
    private final UserRepository userRepo;

    private final String BASE_DIR = "uploads/users/";

    public DocumentService(DocumentRepository docRepo, UserRepository userRepo) {
        this.docRepo = docRepo;
        this.userRepo = userRepo;
    }

    // SAVE document
    public DocumentDTO uploadDocument(Integer userId, DocumentType type, MultipartFile file) throws Exception {

        User user = userRepo.findById(userId).orElse(null);
        if (user == null || file.isEmpty()) return null;

        // create folder:
        String folder = BASE_DIR + userId + "/" + type + "/";
        File dir = new File(folder);
        if (!dir.exists()) dir.mkdirs();

        // create unique file
        String uniqueName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Path.of(folder + uniqueName);
        Files.write(filePath, file.getBytes());

        // save in DB
        Document doc = new Document();
        doc.setUser(user);
        doc.setType(type);
        doc.setFilePath(filePath.toString());
        doc.setOriginalName(file.getOriginalFilename());
        doc.setUploadedAt(LocalDateTime.now());

        Document saved = docRepo.save(doc);

        // convert to DTO:
        DocumentDTO dto = new DocumentDTO();
        dto.setDocumentId(saved.getDocumentId());
        dto.setUserId(userId);
        dto.setType(saved.getType());
        dto.setFilePath(saved.getFilePath());
        dto.setOriginalName(saved.getOriginalName());
        dto.setUploadedAt(saved.getUploadedAt());

        return dto;
    }

    public List<DocumentDTO> getUserDocuments(Integer userId) {

        return docRepo.findByUserUserId(userId)
                .stream()
                .map(doc -> {
                    DocumentDTO dto = new DocumentDTO();
                    dto.setDocumentId(doc.getDocumentId());
                    dto.setUserId(userId);
                    dto.setType(doc.getType());
                    dto.setFilePath(doc.getFilePath());
                    dto.setOriginalName(doc.getOriginalName());
                    dto.setUploadedAt(doc.getUploadedAt());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
