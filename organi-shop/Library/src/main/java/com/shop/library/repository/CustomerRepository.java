package com.shop.library.repository;

import com.shop.library.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository <Customer,Long> {
    public Customer findByUsername(String username);
}
