package com.shop.library.service;

import com.shop.library.dto.AddressDto;
import com.shop.library.entity.Address;
import com.shop.library.entity.Customer;

import java.util.List;

public interface AddressService {
    public List<Address> getAddresses(Customer customer);
    public AddressDto getAddressDto(long id);
    public Address addAddress(AddressDto addressDto, Customer customer);
    public Address updateAddress(long id,AddressDto addressDto);
    public void deleteAddress(long id);

}
