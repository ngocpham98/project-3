package com.shop.library.service;

import com.shop.library.dto.CustomerDto;
import com.shop.library.entity.Customer;
import org.springframework.web.multipart.MultipartFile;

public interface CustomerService {
    public Customer findByUsername(String username);
    public Customer createAccount (CustomerDto customerDto);
    public Customer updateInfo(MultipartFile image, CustomerDto customerDto);
    public CustomerDto toDto(Customer customer);
}
