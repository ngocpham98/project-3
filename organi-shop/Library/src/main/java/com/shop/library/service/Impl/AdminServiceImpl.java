package com.shop.library.service.Impl;

import com.shop.library.dto.AdminDto;
import com.shop.library.entity.Admin;
import com.shop.library.repository.AdminRepository;
import com.shop.library.repository.RoleRepository;
import com.shop.library.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Admin findByUserName(String username) {
        return adminRepository.findByUsername(username);
    }

    @Override
    public Admin addAdmin(AdminDto adminDto) {
        Admin admin = new Admin();
        admin.setFirstName(adminDto.getFirstName());
        admin.setLastName(adminDto.getLastName());
        admin.setUsername(adminDto.getUsername());
        admin.setPassword(adminDto.getPassword());
        admin.setRole(roleRepository.findByName("ADMIN"));
        return adminRepository.save(admin);
    }
}
