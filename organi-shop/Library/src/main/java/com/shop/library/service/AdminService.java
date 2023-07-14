package com.shop.library.service;

import com.shop.library.dto.AdminDto;
import com.shop.library.entity.Admin;

public interface AdminService {
    public Admin findByUserName(String username);
    public Admin addAdmin(AdminDto adminDto);
}
