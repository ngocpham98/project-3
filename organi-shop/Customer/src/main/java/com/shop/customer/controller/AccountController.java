package com.shop.customer.controller;

import com.shop.library.dto.AddressDto;
import com.shop.library.dto.CustomerDto;
import com.shop.library.entity.Customer;
import com.shop.library.service.AddressService;
import com.shop.library.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
public class AccountController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AddressService addressService;

    /////////////////////////profile/////////////////////////////
    @GetMapping({"/account/profile", "/account"})
    public String getAccountProfile(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }else {
            Customer customer = customerService.findByUsername(principal.getName());
            model.addAttribute("account",customerService.toDto(customer));
            return "account-profile";
        }
    }
    @PostMapping("/account/profile")
    public String updateAccount(@ModelAttribute("account")CustomerDto customerDto,
                                @RequestParam("customerImage") MultipartFile image){
        customerService.updateInfo(image,customerDto);
        return "redirect:/account/profile";
    }

    ////////////////////////address////////////////////////////////
    @GetMapping("/account/address")
    public String getAccountAddress(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }else {
        Customer customer = customerService.findByUsername(principal.getName());
        model.addAttribute("addresses",addressService.getAddresses(customer));
        return "account-address";
        }
    }
    ///////Add address//////
    @GetMapping ("/account/address/add")
    public String addAddressForm(Model model){
            model.addAttribute("address",new AddressDto());
            model.addAttribute("action","/account/address/add");
            return "address-action";
    }
    @PostMapping("/account/address/add")
    public String addAddress(@ModelAttribute("address") AddressDto addressDto, Principal principal, HttpServletRequest request) {
        if(principal == null){
            return "redirect:/login";
        }else {
            Customer customer = customerService.findByUsername(principal.getName());
            addressService.addAddress(addressDto,customer);
            return "redirect:" + request.getHeader("Referer");
        }
    }
    ///////////update address//////////////
    @GetMapping ("/account/address/update/{id}")
    public String updateAddressForm(Model model, @PathVariable long id){
        model.addAttribute("address", addressService.getAddressDto(id));
        model.addAttribute("action","/account/address/update/" + id);
        return "address-action";
    }
    @PostMapping ("/account/address/update/{id}")
    public String updateAddress(@ModelAttribute("address") AddressDto addressDto , @PathVariable long id){
        addressService.updateAddress(id,addressDto);
        return "redirect:/account/address";
    }

    @GetMapping ("/account/address/delete/{id}")
    public String deleteAddress(@PathVariable long id){
        addressService.deleteAddress(id);
        return "redirect:/account/address";
    }
}
