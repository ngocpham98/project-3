package com.shop.library.service;

import com.shop.library.dto.ProductDto;
import com.shop.library.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts(String keyword);
    public List<Product> getProductsByCategory(long categoryId);
    public ProductDto getProductDtoById(long id);
    public Product addProduct(MultipartFile image, ProductDto productDto);
    public Product updateProduct(MultipartFile image, long id,ProductDto productDto);
    public void deleteProduct(long id);

}
