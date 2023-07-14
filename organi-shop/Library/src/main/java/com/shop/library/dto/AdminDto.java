package com.shop.library.dto;


import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AdminDto {
    private Long id;
    private String firstName;
    private String lastName;
    @Size(min = 3,max = 20, message = "username must be 3-20 characters")
    private String username;
    @Size(min = 3,max = 20, message = "password must be 3-20 characters")
    private String password;
    private String repeatPassword;
}
