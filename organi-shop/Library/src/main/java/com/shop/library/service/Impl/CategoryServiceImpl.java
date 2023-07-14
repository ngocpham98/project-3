package com.shop.library.service.Impl;

import com.shop.library.entity.Category;
import com.shop.library.repository.CategoryRepository;
import com.shop.library.service.CategoryService;
import com.shop.library.utils.UploadImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UploadImage uploadImage;
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> getCategoriesBySearch(String keyword) {
        return categoryRepository.searchCategory(keyword);
    }

    @Override
    public Category getCategory(long id) {
        return categoryRepository.getReferenceById(id);
    }

    @Override
    public Category addCategory(MultipartFile image, Category category) {
        try {
            category.setImage(uploadImage.uploadCate(image));
            return categoryRepository.save(category);
        } catch (Exception e){
            System.out.println("fail to upload category image ");
            return null;
        }
    }

    @Override
    public Category updateCategory(MultipartFile image,long id, Category category) {
        Category categoryUpdate = categoryRepository.getReferenceById(id);
        try {
            categoryUpdate.setName(category.getName());
            categoryUpdate.setImage(uploadImage.uploadCate(image));
            return categoryRepository.save(categoryUpdate);
        } catch (Exception e){
            System.out.println("fail to upload category image ");
            return null;
        }
    }
    @Override
    public void deleteCategory(long id) {
        categoryRepository.deleteById(id);
    }
}
