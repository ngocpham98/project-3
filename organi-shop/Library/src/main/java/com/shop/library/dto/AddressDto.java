package com.shop.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private Long id;
    private String province;
    private String city;
    private String district;
    private String street;
    private String detail;
    private String recipientName;
    private String recipientPhone;

}
