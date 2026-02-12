package com.jagratichildrenvidyamandir.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jagratichildrenvidyamandir.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    List<Attendance> findByUserUserId(Integer userId);

    List<Attendance> findByDate(LocalDate date);

    boolean existsByUser_StudentClass_ClassIdAndDate(Integer classId, LocalDate date);

    List<Attendance> findByUser_StudentClass_ClassIdAndDate(Integer classId, LocalDate date);

    // ✅ NEW (for save-single)
    Attendance findByUser_UserIdAndDate(Integer userId, LocalDate date);
}
