package com.jagratichildrenvidyamandir.dto;

import java.util.List;

public class ClassWithTeachersDTO {

    private Integer classId;
    
    private String className;
    private List<TeacherDTO> teachers;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<TeacherDTO> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<TeacherDTO> teachers) {
        this.teachers = teachers;
    }
}
