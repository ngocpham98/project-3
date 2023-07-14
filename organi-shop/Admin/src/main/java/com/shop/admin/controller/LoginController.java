package com.shop.admin.controller;

import com.shop.library.dto.AdminDto;
import com.shop.library.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private AdminService adminService;

    @GetMapping({"/login","/"})
    public String loginForm(){
        return "login";
    }
    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("admin", new AdminDto());
        return "register";
    }
    @PostMapping("/register")
    public String addNewAdmin(@Valid @ModelAttribute("admin") AdminDto adminDto, BindingResult result,
                              Model model){
        try{
            if(result.hasErrors()){
                return "redirect:/register";
            }
            if(adminDto.getPassword().equals(adminDto.getRepeatPassword())){
                adminDto.setPassword(encoder.encode(adminDto.getPassword()));
                adminService.addAdmin(adminDto);
            } else{
                return "redirect:/register";
            }

        }catch(Exception e){
            System.out.println(e);
        }
        return "redirect:/register";
    }

    @GetMapping("/home")
    public String home(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()){
            return "index";
        }
        else return "redirect:/login";
    }
}
