package com.shop.library.repository;

import com.shop.library.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository <Address,Long> {
    public List<Address> findByCustomerId(long id);
}
