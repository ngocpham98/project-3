package com.shop.library.service;

import com.shop.library.entity.Customer;
import com.shop.library.entity.Order;
import com.shop.library.entity.OrderDetail;

import java.util.List;

public interface OrderService {
    public List<Order> viewListOrders(Customer customer);
    public Order getOrder(long id);
    public List<OrderDetail> viewOrderDetail(long id);
    public int orderDetailItemTotal(List<OrderDetail>orderDetails);
    public void saveOrder(long addressId, Customer customer, String note);
}
