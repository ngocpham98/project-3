package com.shop.library.repository;

import com.shop.library.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    public Admin findByUsername(String username);
}
