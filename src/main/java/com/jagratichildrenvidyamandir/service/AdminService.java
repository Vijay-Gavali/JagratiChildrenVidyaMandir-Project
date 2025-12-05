package com.jagratichildrenvidyamandir.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jagratichildrenvidyamandir.dto.AdminDTO;
import com.jagratichildrenvidyamandir.entity.Admin;
import com.jagratichildrenvidyamandir.mapper.AdminMapper;
import com.jagratichildrenvidyamandir.repository.AdminRepository;

@Service
@Transactional(readOnly = true)
public class AdminService {

    private final AdminRepository adminRepo;
    private final AdminMapper mapper;

    public AdminService(AdminRepository adminRepo, AdminMapper mapper) {
        this.adminRepo = adminRepo;
        this.mapper = mapper;
    }

    public AdminDTO login(AdminDTO req) {

        Admin admin = adminRepo.findByPhoneNumber(req.getPhoneNumber())
                .orElseThrow(() -> new RuntimeException("Invalid phone or password"));

        if (!admin.getPassword().equals(req.getPassword())) {
            throw new RuntimeException("Invalid phone or password");
        }

        return mapper.toDto(admin); // password removed
    }
}
