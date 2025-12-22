package com.jagratichildrenvidyamandir.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jagratichildrenvidyamandir.dto.AttendanceDTO;
import com.jagratichildrenvidyamandir.entity.Attendance;
import com.jagratichildrenvidyamandir.entity.User;
import com.jagratichildrenvidyamandir.mapper.AttendanceMapper;
import com.jagratichildrenvidyamandir.repository.AttendanceRepository;

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
    
    // get user attendance using userId
    @Transactional(readOnly = true)
    public List<AttendanceDTO> getAttendanceByUserId(Integer userId) {
        return repository.findByUserUserId(userId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
//Akanksha 
 // ================= MARK ATTENDANCE (BULK) =================
    @Transactional
    public List<AttendanceDTO> markAttendanceBulk(List<AttendanceDTO> dtoList) {

        return dtoList.stream().map(dto -> {

            Attendance attendance = new Attendance();

            attendance.setDate(dto.getDate());
            attendance.setStatus(dto.getStatus());

            // ✅ Only userId needed
            User user = new User();
            user.setUserId(dto.getUserId());
            attendance.setUser(user);

            Attendance saved = repository.save(attendance);
            return mapper.toDto(saved);

        }).collect(Collectors.toList());
    }

}
