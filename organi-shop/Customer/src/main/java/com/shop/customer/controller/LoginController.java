package com.shop.customer.controller;

import com.shop.library.dto.CustomerDto;
import com.shop.library.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private BCryptPasswordEncoder encoder;
    @GetMapping({"/login","/"})
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String registerForm(Model model, HttpSession session){
        model.addAttribute("customer", new CustomerDto());
        return "register";
    }
    @PostMapping("/register")
    public String register(@ModelAttribute("customer") @Valid CustomerDto customerDto, BindingResult result,
                           RedirectAttributes redirectAttributes, HttpSession session, Model model){
        try {
            if(result.hasErrors()){
                System.out.println("error");
            } else {
                if (customerService.findByUsername(customerDto.getUsername())!=null){
                    System.out.println("Account existed");
                    model.addAttribute("failure", "Account existed");
                }
                else {
                    if(customerDto.getPassword().equals(customerDto.getRepeatPassword())){
                        customerDto.setPassword(encoder.encode(customerDto.getPassword()));
                        customerService.createAccount(customerDto);
                        System.out.println("Account created successfully");
                        model.addAttribute("success", "Register successfully");
                    } else {
                        System.out.println("Repeat password does not match");
                        model.addAttribute("failure", "Repeat password error");
                    }
                }
            }
        } catch (Exception e){
            model.addAttribute("failure", "Register error");
            System.out.println(e.getMessage());
        }
        return "register";
    }

}
