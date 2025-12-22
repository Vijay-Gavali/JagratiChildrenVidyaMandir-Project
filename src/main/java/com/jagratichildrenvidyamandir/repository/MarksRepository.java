package com.jagratichildrenvidyamandir.repository;

import com.jagratichildrenvidyamandir.entity.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarksRepository extends JpaRepository<Marks, Integer> {

    // Fetch all marks for a particular student
    List<Marks> findByUserUserId(Integer userId);

    // Fetch all marks for a particular class
    List<Marks> findByClassesClassId(Integer classId);

    // Fetch all marks for a particular teacher
    List<Marks> findByTeacherTeacherId(Integer teacherId);
    List<Marks> findByUser_UserId(Integer userId);
    List<Marks> findBySession_SessionId(Integer sessionId);

}