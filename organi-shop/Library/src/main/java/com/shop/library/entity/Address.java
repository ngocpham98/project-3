package com.shop.library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="address_id")
    private Long id;
    private String province;
    private String city;
    private String district;
    private String street;
    private String detail;
    private String recipientName;
    private String recipientPhone;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    public String addressToString(){
        return detail + ", "+ street + ", "+ district + ", " + city + ", "+ province;
    }
}
