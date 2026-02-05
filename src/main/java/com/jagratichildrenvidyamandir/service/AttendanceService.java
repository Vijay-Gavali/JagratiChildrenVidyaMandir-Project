package com.jagratichildrenvidyamandir.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jagratichildrenvidyamandir.dto.AttendanceDTO;
import com.jagratichildrenvidyamandir.entity.Attendance;
import com.jagratichildrenvidyamandir.entity.User;
import com.jagratichildrenvidyamandir.repository.AttendanceRepository;

@Service
public class AttendanceService {

    private final AttendanceRepository repository;

    public AttendanceService(AttendanceRepository repository) {
        this.repository = repository;
    }

    /* ================= CREATE ================= */
    public AttendanceDTO createAttendance(AttendanceDTO dto) {

        Attendance attendance = new Attendance();
        attendance.setDate(dto.getDate());
        attendance.setStatus(dto.getStatus());

        User user = new User();
        user.setUserId(dto.getUserId());
        attendance.setUser(user);

        Attendance saved = repository.save(attendance);
        return mapToDTO(saved);
    }

    /* ================= GET BY ID ================= */
    public AttendanceDTO getAttendanceById(Integer id) {
        return repository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    /* ================= GET ALL ================= */
    public List<AttendanceDTO> getAllAttendance() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /* ================= UPDATE ================= */
    public AttendanceDTO updateAttendance(Integer id, AttendanceDTO dto) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setStatus(dto.getStatus());
                    Attendance updated = repository.save(existing);
                    return mapToDTO(updated);
                })
                .orElse(null);
    }

    /* ================= DELETE ================= */
    public boolean deleteAttendance(Integer id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }

    /* ================= USER ATTENDANCE ================= */
    @Transactional(readOnly = true)
    public List<AttendanceDTO> getAttendanceByUserId(Integer userId) {
        return repository.findByUserUserId(userId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /* ================= MARK ATTENDANCE (BULK) ================= */
    @Transactional
    public List<AttendanceDTO> markAttendanceBulk(List<AttendanceDTO> dtoList) {

        LocalDate today = LocalDate.now();

        boolean alreadyMarked = dtoList.stream()
                .anyMatch(dto ->
                        repository.existsByUser_UserIdAndDate(dto.getUserId(), today)
                );

        // 🔁 If already marked → return today's attendance
        if (alreadyMarked) {
            return repository.findByDate(today)
                    .stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());
        }

        // ✅ Save attendance
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

        return repository.findByDate(today)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /* ================= TODAY ATTENDANCE ================= */
    @Transactional(readOnly = true)
    public List<AttendanceDTO> getTodayAttendance() {

        LocalDate today = LocalDate.now();

        return repository.findByDate(today)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /* ================= DTO MAPPER (🔥 FINAL FIX) ================= */
    private AttendanceDTO mapToDTO(Attendance attendance) {

        AttendanceDTO dto = new AttendanceDTO();
        dto.setAttendanceId(attendance.getAttendanceId());
        dto.setDate(attendance.getDate());
        dto.setStatus(attendance.getStatus());

        if (attendance.getUser() != null) {

            dto.setUserId(attendance.getUser().getUserId());
            dto.setStudentName(attendance.getUser().getName());

            // ✅ CLASS NAME FIX (ROOT CAUSE SOLVED)
            if (attendance.getUser().getStudentClass() != null) {
                dto.setClassName(
                        attendance.getUser()
                                  .getStudentClass()
                                  .getClassName()
                );
            }
        }

        return dto;
    }
}
