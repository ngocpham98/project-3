package com.shop.customer.controller;

import com.shop.library.entity.Cart;
import com.shop.library.entity.Customer;
import com.shop.library.service.CartService;
import com.shop.library.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/cart")
    public String getCart(Model model, Principal principal, HttpSession session) {
        if (principal == null) {
            return "redirect:/login";
        } else {
            Customer customer = customerService.findByUsername(principal.getName());
            Cart cart = cartService.getCart(customer);
            session.setAttribute("cartSize", cart.getTotalItems());
            model.addAttribute("cart", cart);
            model.addAttribute("items", cartService.getCartItems(cart.getId()));
            return "shopping-cart";
        }
    }

    ///////////////add into cart//////////////////
    @PostMapping("/cart/add")
    public String addItem(HttpServletRequest request,
                          @RequestParam("id") long productId,
                          @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity,
                          Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/login";
        } else {
            Customer customer = customerService.findByUsername(principal.getName());

            Cart cart = cartService.addItem(quantity, productId, customer);
            if(cart == null){
                model.addAttribute("quantityError", true);
            }
        }
        return "redirect:" + request.getHeader("Referer");
    }

    /////////////////update quantity//////////////
    @PostMapping("/cart/update")
    public String updateItemQuantity(Principal principal,
                                     @RequestParam("id") long id,
                                     @RequestParam("quantity") int quantity) {
        Customer customer = customerService.findByUsername(principal.getName());
        cartService.updateQuantity(quantity, id, customer);
        return "redirect:/cart";
    }

    /////////////////delete quantity//////////////
    @GetMapping("/cart/delete/{id}")
    public String deleteItem(Principal principal,
                             @PathVariable long id) {
        Customer customer = customerService.findByUsername(principal.getName());
        cartService.deleteItem(id, customer);
        return "redirect:/cart";
    }
}
