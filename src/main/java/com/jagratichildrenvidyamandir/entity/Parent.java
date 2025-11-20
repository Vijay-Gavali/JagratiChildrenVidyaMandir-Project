package com.jagratichildrenvidyamandir.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "parents")
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer parentId;

    private String parentName;

    @Column(unique = true)
    private String phone;

    @Column(unique = true)
    private String email;

    private String password;

    public Parent() {}

    public Parent(Integer parentId, String parentName, String phone, String email, String password) {
        this.parentId = parentId;
        this.parentName = parentName;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    // ---------- Getters & Setters ----------
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
