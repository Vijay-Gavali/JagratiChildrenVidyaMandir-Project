package com.jagratichildrenvidyamandir.dto;

public class ParentDTO {

    private Integer parentId;
    private String parentName;
    private String phone;
    private String email;
    private String password;

    public ParentDTO() {}

    public ParentDTO(Integer parentId, String parentName, String phone, String email, String password) {
        this.parentId = parentId;
        this.parentName = parentName;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    // Getters & Setters
    public Integer getParentId() { return parentId; }
    public void setParentId(Integer parentId) { this.parentId = parentId; }

    public String getParentName() { return parentName; }
    public void setParentName(String parentName) { this.parentName = parentName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
