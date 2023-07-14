package com.shop.admin.controller;

import com.shop.library.dto.ProductDto;
import com.shop.library.service.CategoryService;
import com.shop.library.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    ////////////////////////Read///////////////////////////////////////
    @GetMapping("/products")
    public String getAllProducts(Model model, Principal principal, @RequestParam(name = "search", required = false) String search){
        if(principal == null){
            return "redirect:/login";
        } else {
            model.addAttribute("products", productService.getAllProducts(search));
            return "products";
        }
    }

    /////////////////////Create/////////////////////////////////////////////
    @GetMapping("/products/add")
    public String addProductForm(Model model){
        model.addAttribute("product",new ProductDto());
        model.addAttribute("categories",categoryService.getAllCategories());
        model.addAttribute("action","/products/add");
        return "productAdd";
    }
    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute("product")ProductDto productDto,
                             @RequestParam("productImage") MultipartFile file){
        productService.addProduct(file,productDto);
        return "redirect:/products";
    }

    ///////////////////////Update//////////////////////////////
    @GetMapping("/products/update/{id}")
    public String updateProductForm(@PathVariable long id, Model model){
        model.addAttribute("product",productService.getProductDtoById(id));
        model.addAttribute("categories",categoryService.getAllCategories());
        model.addAttribute("action","/products/update/"+id);
        return "productAdd";
    }
    @PostMapping("/products/update/{id}")
    public String updateProduct(@PathVariable long id, @ModelAttribute("product")ProductDto productDto,
                                @RequestParam("productImage") MultipartFile file){
        productService.updateProduct(file,id,productDto);
        return "redirect:/products";
    }

    ///////////////////////Delete//////////////////////////////
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable long id){
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
