package com.jagratichildrenvidyamandir.mapper;

import org.springframework.stereotype.Component;

import com.jagratichildrenvidyamandir.dto.NoticeDTO;
import com.jagratichildrenvidyamandir.entity.Notice;
import com.jagratichildrenvidyamandir.repository.SessionRepository;

@Component
public class NoticeMapper {

	private final SessionRepository sessionRepository;

	public NoticeMapper(SessionRepository sessionRepository) {
		this.sessionRepository = sessionRepository;
	}

	public NoticeDTO toDto(Notice entity) {
		if (entity == null)
			return null;

		return new NoticeDTO(entity.getNoticeId(), entity.getTitle(), entity.getSubject(), entity.getIssuedBy(),
				entity.getDate(), entity.getMessage(),
				entity.getSession() != null ? entity.getSession().getSessionId() : null);
	}

	public Notice toEntity(NoticeDTO dto) {
		if (dto == null)
			return null;

		Notice n = new Notice();
		n.setNoticeId(dto.getNoticeId());
		n.setTitle(dto.getTitle());
		n.setSubject(dto.getSubject());
		n.setIssuedBy(dto.getIssuedBy());
		n.setDate(dto.getDate());
		n.setMessage(dto.getMessage());

		// Set session if sessionId is provided
		if (dto.getSessionId() != null) {
			sessionRepository.findById(dto.getSessionId()).ifPresent(n::setSession);
		}
		return n;
	}
}
