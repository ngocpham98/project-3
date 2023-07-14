package com.shop.library.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;
    @Column(name = "order_date")
    private Date orderDate;
    @Column(name = "delivery_date")
    private Date deliveryDate;
    @Column(name = "order_status")
    private String orderStatus;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String address;
    @Column(name = "shipping_fee")
    private double ShippingFee;
    private double subtotal;
    private double total;
    private String note;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    public String createDateString(){
        String dateString = orderDate.toString();
        String[] correctString = dateString.split("\\.");
        return correctString[0];
    }
    public String deliveryDateString(){
        String dateString = deliveryDate.toString();

        String[] correctString = dateString.split("\\.");

        return correctString[0];
    }
}
