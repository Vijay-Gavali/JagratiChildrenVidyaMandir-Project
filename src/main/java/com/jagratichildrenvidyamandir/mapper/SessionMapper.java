package com.jagratichildrenvidyamandir.mapper;

import org.springframework.stereotype.Component;
import com.jagratichildrenvidyamandir.entity.SessionEntity;
import com.jagratichildrenvidyamandir.dto.SessionDTO;

@Component
public class SessionMapper {

	public SessionDTO toDto(SessionEntity e) {
		if (e == null)
			return null;
		return new SessionDTO(e.getSessionId(), e.getName(), e.getStartDate(), e.getEndDate(), e.isActive());
	}

	public SessionEntity toEntity(SessionDTO dto) {
		if (dto == null)
			return null;
		return new SessionEntity(dto.getSessionId(), dto.getName(), dto.getStartDate(), dto.getEndDate(),
				dto.isActive());
	}

	// update existing entity from DTO (used in updates)
	public void updateEntityFromDto(SessionDTO dto, SessionEntity entity) {
		if (dto.getName() != null)
			entity.setName(dto.getName());
		entity.setStartDate(dto.getStartDate());
		entity.setEndDate(dto.getEndDate());
		entity.setActive(dto.isActive());
	}
}
