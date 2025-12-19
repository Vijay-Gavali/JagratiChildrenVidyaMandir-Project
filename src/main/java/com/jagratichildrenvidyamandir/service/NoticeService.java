package com.jagratichildrenvidyamandir.service;

import com.jagratichildrenvidyamandir.dto.NoticeDTO;
import com.jagratichildrenvidyamandir.entity.Notice;
import com.jagratichildrenvidyamandir.entity.SessionEntity;
import com.jagratichildrenvidyamandir.mapper.NoticeMapper;
import com.jagratichildrenvidyamandir.repository.NoticeRepository;
import com.jagratichildrenvidyamandir.repository.SessionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NoticeService {

	private final NoticeRepository noticeRepo;
	private final SessionRepository sessionRepo;
	private final NoticeMapper mapper;

	public NoticeService(NoticeRepository noticeRepo, SessionRepository sessionRepo, NoticeMapper mapper) {
		this.noticeRepo = noticeRepo;
		this.sessionRepo = sessionRepo;
		this.mapper = mapper;
	}

	// Create notice with session ID
	public NoticeDTO createNotice(NoticeDTO dto) {
		if (dto.getSessionId() == null) {
			throw new IllegalArgumentException("Session ID is required");
		}

		Notice entity = mapper.toEntity(dto);
		entity.setNoticeId(null);

		// Validate session exists
		SessionEntity session = sessionRepo.findById(dto.getSessionId())
				.orElseThrow(() -> new EntityNotFoundException("Session not found with id: " + dto.getSessionId()));
		entity.setSession(session);

		Notice saved = noticeRepo.save(entity);
		return mapper.toDto(saved);
	}

	// Get all notices (no filter)
	@Transactional(readOnly = true)
	public List<NoticeDTO> getAllNotices() {
		return noticeRepo.findAllOrderByDateDesc().stream().map(mapper::toDto).collect(Collectors.toList());
	}

	// Get notices by session ID
	@Transactional(readOnly = true)
	public List<NoticeDTO> getNoticesBySessionId(Integer sessionId) {
		// Verify session exists
		if (!sessionRepo.existsById(sessionId)) {
			throw new EntityNotFoundException("Session not found with id: " + sessionId);
		}

		return noticeRepo.findBySessionSessionIdOrderByDateDesc(sessionId).stream().map(mapper::toDto)
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public NoticeDTO getNoticeById(Integer id) {
		Notice n = noticeRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Notice not found: " + id));
		return mapper.toDto(n);
	}

	public void deleteNotice(Integer id) {
		if (!noticeRepo.existsById(id)) {
			throw new EntityNotFoundException("Notice not found: " + id);
		}
		noticeRepo.deleteById(id);
	}

	public NoticeDTO updateNotice(Integer id, NoticeDTO dto) {
		Notice existing = noticeRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Notice not found: " + id));

		existing.setTitle(dto.getTitle());
		existing.setSubject(dto.getSubject());
		existing.setIssuedBy(dto.getIssuedBy());
		existing.setDate(dto.getDate());
		existing.setMessage(dto.getMessage());

		// Update session if provided
		if (dto.getSessionId() != null) {
			SessionEntity session = sessionRepo.findById(dto.getSessionId())
					.orElseThrow(() -> new EntityNotFoundException("Session not found with id: " + dto.getSessionId()));
			existing.setSession(session);
		}

		Notice saved = noticeRepo.save(existing);
		return mapper.toDto(saved);
	}
}