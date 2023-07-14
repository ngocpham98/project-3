package com.shop.customer.controller;

import com.shop.library.dto.AddressDto;
import com.shop.library.entity.Cart;
import com.shop.library.entity.Customer;
import com.shop.library.entity.Order;
import com.shop.library.entity.OrderDetail;
import com.shop.library.service.AddressService;
import com.shop.library.service.CartService;
import com.shop.library.service.CustomerService;
import com.shop.library.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private CartService cartService;
    @GetMapping("/checkout")
    public String preOrder(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        } else{
            model.addAttribute("address",new AddressDto());
            model.addAttribute("action","/account/address/add");
            Customer customer = customerService.findByUsername(principal.getName());

            Cart cart = cartService.getCart(customer);
            model.addAttribute("addresses", addressService.getAddresses(customer));
            model.addAttribute("cartItems", cartService.getCartItems(cart.getId()));
            model.addAttribute("cart", cartService.getCart(customer));
            return "checkout";
        }
    }
    @GetMapping("/checkout/success")
    public String orderSuccess(){
        return "order-success";
    }
    @PostMapping("/checkout")
    @Transactional
    public String saveOrder(Principal principal, @RequestParam("address") long addressId,
                            @RequestParam(name = "note", required = false) String note){
        if(principal == null){
            return "redirect:/login";
        } else {
            Customer customer = customerService.findByUsername(principal.getName());
            Cart cart = cartService.getCart(customer);
            orderService.saveOrder(addressId,customer,note);
            return "redirect:/checkout/success";
        }
    }
    @GetMapping("/account/orders")
    public String viewOrderHistory(Model model, Principal principal, HttpSession session){
        if(principal == null){
            return "redirect:/login";
        } else{
            Customer customer = customerService.findByUsername(principal.getName());
            Cart cart = cartService.getCart(customer);
            session.setAttribute("cartSize", cart.getTotalItems());
            model.addAttribute("orders",orderService.viewListOrders(customer));
            return "order-history";
        }
    }

    @GetMapping("/account/orders/detail/{id}")
    public String viewOrderDetail(Model model, @PathVariable long id){
        Order order = orderService.getOrder(id);
        List<OrderDetail> orderDetails = orderService.viewOrderDetail(order.getId());
        model.addAttribute("order", order);
        model.addAttribute("itemNumber", orderService.orderDetailItemTotal(orderDetails));
        model.addAttribute("items", orderDetails);

        return "order-detail";
    }

//    @GetMapping("/account/orders/{id}")
//    public String acceptOder(@PathVariable long id){
//        orderService.deleteOrder(id);
//        return "redirect:/account/orders";
//    }

}
