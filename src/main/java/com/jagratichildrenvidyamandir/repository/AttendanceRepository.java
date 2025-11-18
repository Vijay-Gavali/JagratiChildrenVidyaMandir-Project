package com.jagratichildrenvidyamandir.repository;

import com.jagratichildrenvidyamandir.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
}
