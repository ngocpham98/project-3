package com.shop.library.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String avatar;
    @Size(min = 3,max = 20, message = "username must be 3-20 characters")
    private String username;
    @Size(min = 3,max = 20, message = "password must be 3-20 characters")
    private String password;
    private String repeatPassword;
}
