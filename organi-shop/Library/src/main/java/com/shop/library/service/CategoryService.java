package com.shop.library.service;

import com.shop.library.entity.Category;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategories();
    public List<Category> getCategoriesBySearch(String keyword);
    public Category getCategory(long id);
    public Category addCategory(MultipartFile image, Category category);
    public Category updateCategory(MultipartFile image,long id,Category category);
    public void deleteCategory(long id);
}
