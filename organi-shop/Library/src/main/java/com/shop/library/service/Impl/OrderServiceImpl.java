package com.shop.library.service.Impl;

import com.shop.library.entity.*;
import com.shop.library.repository.*;
import com.shop.library.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Order> viewListOrders(Customer customer) {
        return orderRepository.findByCustomerId(customer.getId());
    }

    @Override
    public Order getOrder(long id) {
        return orderRepository.getReferenceById(id);
    }

    @Override
    public List<OrderDetail> viewOrderDetail(long id) {
        return orderDetailRepository.findByOrderId(id);
    }

    @Override
    public int orderDetailItemTotal(List<OrderDetail> orderDetails) {
        int size = 0;
        for(OrderDetail orderDetail : orderDetails){
            size += orderDetail.getItemQuantity();
        }
        return size;
    }

    @Override
    public void saveOrder(long addressId, Customer customer, String note) {
        Calendar calendar = Calendar.getInstance();
        Cart cart = cartRepository.findByCustomerId(customer.getId());
        Address address = addressRepository.getReferenceById(addressId);
        Order order = new Order();

        order.setOrderStatus("proceeding");
        order.setShippingFee(5.0);
        order.setPhoneNumber(address.getRecipientPhone());
        order.setAddress(address.addressToString());
        order.setNote(note);
        order.setCustomer(customer);
        order.setOrderDate(calendar.getTime());
        order.setDeliveryDate(getDelivery());
        order.setSubtotal(cart.getTotalPrice());
        order.setTotal(order.getSubtotal() + order.getShippingFee());
        List<OrderDetail>orderList = new ArrayList<>();

        for(CartItem item : cartItemRepository.findByCartId(cart.getId())){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            Product product = item.getProduct();
            orderDetail.setProduct(product);
            orderDetail.setItemQuantity(item.getQuantity());
            product.setQuantity(product.getQuantity() - item.getQuantity());
            orderDetail.setItemCost(item.getTotalPrice());
            orderList.add(orderDetail);
            orderDetailRepository.save(orderDetail);
            productRepository.save(product);
        }
        order.setOrderDetails(orderList);
        orderRepository.save(order);
        cartItemRepository.deleteByCartId(cart.getId());
        cart.setTotalItems(0);
        cart.setTotalPrice(0);
        cartRepository.save(cart);
    }

    public Date getDelivery() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 3);
        return calendar.getTime();
    }
}
