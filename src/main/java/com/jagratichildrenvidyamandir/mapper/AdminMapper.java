package com.jagratichildrenvidyamandir.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jagratichildrenvidyamandir.dto.AdminDTO;
import com.jagratichildrenvidyamandir.entity.Admin;

@Component
public class AdminMapper {

    public AdminDTO toDto(Admin entity) {
        if (entity == null) return null;
        return new AdminDTO(entity.getAdminId(), entity.getPhoneNumber(), entity.getPassword());
    }

    public Admin toEntity(AdminDTO dto) {
        if (dto == null) return null;
        Admin a = new Admin();
        a.setAdminId(dto.getAdminId());
        a.setPhoneNumber(dto.getPhoneNumber());
        a.setPassword(dto.getPassword());
        return a;
    }
}