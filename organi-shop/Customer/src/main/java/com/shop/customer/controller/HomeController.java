package com.shop.customer.controller;

import com.shop.library.entity.Cart;
import com.shop.library.entity.Customer;
import com.shop.library.service.CartService;
import com.shop.library.service.CategoryService;
import com.shop.library.service.CustomerService;
import com.shop.library.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CartService cartService;
    @GetMapping("/home")
    public String home(Model model, Principal principal, HttpSession session){
        if (principal!=null){
            Customer customer = customerService.findByUsername(principal.getName());
            Cart cart = cartService.getCart(customer);
            session.setAttribute("cartSize", cart.getTotalItems());
            session.setAttribute("username", principal.getName());

            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("products", productService.getAllProducts(""));
            return "index";
        } else return "redirect:/login";
    }
}
