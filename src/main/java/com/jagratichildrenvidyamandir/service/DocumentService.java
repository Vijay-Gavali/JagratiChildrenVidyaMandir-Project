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
import java.util.Optional;
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
        if (user == null || file == null || file.isEmpty()) return null;

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

    /**
     * Update (replace) or create the document for given userId and type.
     * If a document of that type already exists for the user, it will be replaced (old file deleted, DB row updated).
     * If no such document exists, a new row will be created.
     *
     * Returns DocumentDTO on success, null if user not found or file invalid.
     */
    public DocumentDTO updateOrReplaceByUserAndType(Integer userId, DocumentType type, MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) return null;

        // validate user
        Optional<User> userOpt = userRepo.findById(userId);
        if (userOpt.isEmpty()) return null;
        User user = userOpt.get();

        // find existing docs for this user+type
        List<Document> existing = docRepo.findByUserUserIdAndType(userId, type);

        Document target;
        if (existing != null && !existing.isEmpty()) {
            // use the first existing document as the target to update
            target = existing.get(0);

            // attempt to delete old file
            try {
                if (target.getFilePath() != null) {
                    Path old = Path.of(target.getFilePath());
                    Files.deleteIfExists(old);
                }
            } catch (Exception delEx) {
                // log and continue (do not fail entire request because delete failed)
                System.err.println("Warning: could not delete old file: " + delEx.getMessage());
            }

            // remove any additional duplicates (if more than one existed) to keep single record
            if (existing.size() > 1) {
                for (int i = 1; i < existing.size(); i++) {
                    Document dup = existing.get(i);
                    try {
                        if (dup.getFilePath() != null) Files.deleteIfExists(Path.of(dup.getFilePath()));
                    } catch (Exception ignore) {
                    }
                    try {
                        docRepo.delete(dup);
                    } catch (Exception ignore) {
                    }
                }
            }
        } else {
            // create new document entity
            target = new Document();
            target.setUser(user);
            target.setType(type);
        }

        // ensure directory exists
        String folder = BASE_DIR + userId + "/" + type + "/";
        File dir = new File(folder);
        if (!dir.exists()) dir.mkdirs();

        // write new file
        String uniqueName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path newPath = Path.of(folder + uniqueName);
        Files.write(newPath, file.getBytes());

        // update metadata
        target.setFilePath(newPath.toString());
        target.setOriginalName(file.getOriginalFilename());
        target.setUploadedAt(LocalDateTime.now());

        // save
        Document saved = docRepo.save(target);

        // map to DTO
        DocumentDTO dto = new DocumentDTO();
        dto.setDocumentId(saved.getDocumentId());
        dto.setUserId(saved.getUser().getUserId());
        dto.setType(saved.getType());
        dto.setFilePath(saved.getFilePath());
        dto.setOriginalName(saved.getOriginalName());
        dto.setUploadedAt(saved.getUploadedAt());
        return dto;
    }

}
