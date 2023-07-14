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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class ProductController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CustomerService customerService;

    @GetMapping("/products")
    public String getProducts(Model model, Principal principal, HttpSession session,
                              @RequestParam (name = "search", required = false) String search){
        if(principal == null){
            return "redirect:/login";
        }else {
            Customer customer = customerService.findByUsername(principal.getName());
            Cart cart = cartService.getCart(customer);
            session.setAttribute("cartSize", cart.getTotalItems());

            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("products", productService.getAllProducts(search));
            model.addAttribute("totalProducts", productService.getAllProducts(search).size());
            model.addAttribute("search", search);
            return "shop-products";
        }
    }
    @GetMapping("/products/category/{id}")
    public String getProductCategory(@PathVariable long id, Model model,
                                     Principal principal, HttpSession session){
        if(principal == null){
            return "redirect:/login";
        }else {
            Customer customer = customerService.findByUsername(principal.getName());
            Cart cart = cartService.getCart(customer);
            session.setAttribute("cartSize", cart.getTotalItems());
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("products", productService.getProductsByCategory(id));
            model.addAttribute("totalProducts", productService.getProductsByCategory(id).size());
            return "shop-products";
        }
    }
    @GetMapping("/products/{id}")
    public String getProductDetail(@PathVariable long id, Model model,
                                   Principal principal, HttpSession session){
        if(principal == null){
            return "redirect:/login";
        }else {
            Customer customer = customerService.findByUsername(principal.getName());
            Cart cart = cartService.getCart(customer);
            session.setAttribute("cartSize", cart.getTotalItems());
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("product", productService.getProductDtoById(id));
            return "product-detail";
        }
    }
}
