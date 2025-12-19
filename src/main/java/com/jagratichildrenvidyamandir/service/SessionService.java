package com.jagratichildrenvidyamandir.service;

import com.jagratichildrenvidyamandir.dto.SessionDTO;
import com.jagratichildrenvidyamandir.entity.SessionEntity;
import com.jagratichildrenvidyamandir.mapper.SessionMapper;
import com.jagratichildrenvidyamandir.repository.SessionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SessionService {

	private final SessionRepository repo;
    private final SessionMapper mapper;

    public SessionService(SessionRepository repo, SessionMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public SessionDTO create(SessionDTO dto) {
        // optional: check duplicate by name
        repo.findByName(dto.getName()).ifPresent(s -> {
            throw new IllegalArgumentException("Session with same name already exists: " + dto.getName());
        });

        SessionEntity e = mapper.toEntity(dto);
        e.setSessionId(null);
        SessionEntity saved = repo.save(e);
        return mapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public List<SessionDTO> getAll() {
        return repo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public SessionDTO getById(Integer id) {
        SessionEntity e = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Session not found: " + id));
        return mapper.toDto(e);
    }

    public SessionDTO update(Integer id, SessionDTO dto) {
        SessionEntity existing = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Session not found: " + id));
        mapper.updateEntityFromDto(dto, existing);
        SessionEntity saved = repo.save(existing);
        return mapper.toDto(saved);
    }

    public void delete(Integer id) {
        if (!repo.existsById(id)) throw new EntityNotFoundException("Session not found: " + id);
        repo.deleteById(id);
    }
}
