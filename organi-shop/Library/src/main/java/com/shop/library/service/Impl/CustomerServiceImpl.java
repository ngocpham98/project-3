package com.shop.library.service.Impl;

import com.shop.library.dto.CustomerDto;
import com.shop.library.entity.Customer;
import com.shop.library.repository.CustomerRepository;
import com.shop.library.repository.RoleRepository;
import com.shop.library.service.CustomerService;
import com.shop.library.utils.UploadImage;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper mapper;
    private final UploadImage upload;
    public CustomerServiceImpl(CustomerRepository customerRepository, RoleRepository roleRepository, ModelMapper modelMapper, UploadImage upload) {
        this.customerRepository = customerRepository;
        this.roleRepository = roleRepository;
        this.mapper = modelMapper;
        this.upload = upload;
    }

    @Override
    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    @Override
    public Customer createAccount(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setUsername(customerDto.getUsername());
        customer.setPassword(customerDto.getPassword());
        customer.setRole(roleRepository.findByName("CUSTOMER"));
        return customerRepository.save(customer);
    }
    @Override
    public Customer updateInfo(MultipartFile image, CustomerDto customerDto) {
        try {
            Customer customer = customerRepository.getReferenceById(customerDto.getId());
            customer.setFirstName(customerDto.getFirstName());
            customer.setLastName(customerDto.getLastName());
            customer.setPhoneNumber(customerDto.getPhoneNumber());
            if(image.isEmpty()){
                customer.setAvatar(customer.getAvatar());
            } else {
                customer.setAvatar(upload.uploadCustomer(image));
            }
            return customerRepository.save(customer);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public CustomerDto toDto(Customer customer){
        return mapper.map(customer,CustomerDto.class);
    }
}
