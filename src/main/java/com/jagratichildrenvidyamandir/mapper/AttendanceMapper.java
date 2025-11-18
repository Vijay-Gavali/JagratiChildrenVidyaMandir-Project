package com.jagratichildrenvidyamandir.mapper;

import com.jagratichildrenvidyamandir.dto.AttendanceDTO;
import com.jagratichildrenvidyamandir.entity.*;
import org.springframework.stereotype.Component;

@Component
public class AttendanceMapper {

    public AttendanceDTO toDto(Attendance entity) {
        if (entity == null) return null;

        AttendanceDTO dto = new AttendanceDTO();
        dto.setAttendanceId(entity.getAttendanceId());
        dto.setDate(entity.getDate());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public Attendance toEntity(AttendanceDTO dto) {
        if (dto == null) return null;

        Attendance entity = new Attendance();
        entity.setAttendanceId(dto.getAttendanceId());
        entity.setDate(dto.getDate());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    public void updateEntityFromDto(AttendanceDTO dto, Attendance entity) {
        entity.setDate(dto.getDate());
        entity.setStatus(dto.getStatus());
    }
}
