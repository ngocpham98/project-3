package com.shop.admin.controller;

import com.shop.library.entity.Category;
import com.shop.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    ////////////////////////Read///////////////////////////////////////
    @GetMapping("/categories")
    public String getAllCategory(Model model, Principal principal, @RequestParam(name = "search", required = false) String search){
        if(principal == null){
            return "redirect:/login";
        } else {
            if(search == null){
                model.addAttribute("categories", categoryService.getAllCategories());
            } else {
                model.addAttribute("categories", categoryService.getCategoriesBySearch(search));
            }

        }
        return "categories";
    }
    ////////////////////////Create///////////////////////////////////////
    @GetMapping("/categories/add")
    public String addCategoryForm(Model model){
        model.addAttribute("category", new Category());
        model.addAttribute("action", "/categories/add");
        return "categoryAdd";
    }

    @PostMapping("/categories/add")
    public String addCategory(@ModelAttribute("category") Category category,
                              @RequestParam("cateImage")MultipartFile image){
        categoryService.addCategory(image,category);
        return "redirect:/categories";
    }
    ////////////////////////Update///////////////////////////////////////
    @GetMapping("/categories/update/{id}")
    public String updateCategoryForm(@PathVariable long id, Model model){
        model.addAttribute("category", categoryService.getCategory(id));
        model.addAttribute("action", "/categories/update/"+id);
        return "categoryAdd";
    }
    @PostMapping("categories/update/{id}")
    public String updateCategory(@ModelAttribute("category") Category category, @PathVariable long id,
                                 @RequestParam("cateImage")MultipartFile image){
        categoryService.updateCategory(image,id,category);
        return "redirect:/categories";
    }
    ////////////////////////Delete///////////////////////////////////////
    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable long id){
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}
