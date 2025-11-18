package com.jagratichildrenvidyamandir.service;

import com.jagratichildrenvidyamandir.dto.AttendanceDTO;
import com.jagratichildrenvidyamandir.mapper.AttendanceMapper;
import com.jagratichildrenvidyamandir.entity.*;
import com.jagratichildrenvidyamandir.repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    private final AttendanceRepository repository;
    private final AttendanceMapper mapper;

    public AttendanceService(AttendanceRepository repository, AttendanceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public AttendanceDTO createAttendance(AttendanceDTO dto) {
        Attendance entity = mapper.toEntity(dto);
        entity.setAttendanceId(null);
        Attendance saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    public AttendanceDTO getAttendanceById(Integer id) {
        return repository.findById(id).map(mapper::toDto).orElse(null);
    }

    public List<AttendanceDTO> getAllAttendance() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public AttendanceDTO updateAttendance(Integer id, AttendanceDTO dto) {
        return repository.findById(id)
                .map(existing -> {
                    mapper.updateEntityFromDto(dto, existing);
                    Attendance updated = repository.save(existing);
                    return mapper.toDto(updated);
                })
                .orElse(null);
    }

    public boolean deleteAttendance(Integer id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
