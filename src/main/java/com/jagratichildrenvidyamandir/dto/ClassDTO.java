package com.jagratichildrenvidyamandir.dto;

public class ClassDTO {

    private Integer classId;
    private String className;
    private Integer fees;

    public ClassDTO() {
    }

    public ClassDTO(Integer classId, String className, Integer fees) {
        this.classId = classId;
        this.className = className;
        this.fees = fees;
    }

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

    public Integer getFees() {
        return fees;
    }

    public void setFees(Integer fees) {
        this.fees = fees;
    }
}
