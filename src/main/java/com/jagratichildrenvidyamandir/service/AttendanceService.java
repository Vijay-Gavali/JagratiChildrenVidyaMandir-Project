package com.jagratichildrenvidyamandir.service;

import java.time.LocalDate;
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
                .map(attendance -> {

                    AttendanceDTO dto = mapper.toDto(attendance);

                    // ✅ add student name
                    if (attendance.getUser() != null) {
                        dto.setStudentName(attendance.getUser().getName());
                    }

                    return dto;

                })
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
    @Transactional
    public List<AttendanceDTO> markAttendanceBulk(List<AttendanceDTO> dtoList) {

        LocalDate today = LocalDate.now();

        // ✅ Check if ANY attendance already marked today
        boolean alreadyMarked = dtoList.stream()
                .anyMatch(dto ->
                        repository.existsByUser_UserIdAndDate(dto.getUserId(), today)
                );

        // 🔁 If already marked → just fetch today attendance
        if (alreadyMarked) {
            return repository.findByDate(today)
                    .stream()
                    .map(mapper::toDto)
                    .collect(Collectors.toList());
        }

        // ✅ Else → mark attendance
        List<Attendance> attendanceList = dtoList.stream().map(dto -> {

            Attendance attendance = new Attendance();
            attendance.setDate(today);
            attendance.setStatus(dto.getStatus());

            User user = new User();
            user.setUserId(dto.getUserId());
            attendance.setUser(user);

            return attendance;

        }).collect(Collectors.toList());

        repository.saveAll(attendanceList);

        // ✅ Return today's attendance after save
        return repository.findByDate(today)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<AttendanceDTO> getTodayAttendance() {

        LocalDate today = LocalDate.now();

        return repository.findByDate(today)
                .stream()
                .map(attendance -> {

                    AttendanceDTO dto = mapper.toDto(attendance);

                    // student name via JPA relation
                    dto.setStudentName(
                            attendance.getUser() != null
                                    ? attendance.getUser().getName()
                                    : null
                    );

                    return dto;
                })
                .collect(Collectors.toList());
    }


}

