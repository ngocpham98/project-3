package com.shop.library.service.Impl;

import com.shop.library.dto.ProductDto;
import com.shop.library.entity.Product;
import com.shop.library.repository.CategoryRepository;
import com.shop.library.repository.ProductRepository;
import com.shop.library.service.ProductService;
import com.shop.library.utils.UploadImage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UploadImage uploadImage;
    @Autowired
    private ModelMapper mapper;

    @Override
    public List<Product> getAllProducts(String keyword) {
        if (keyword != null) {
            return productRepository.findAll(keyword);
        }
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @Override
    public ProductDto getProductDtoById(long id) {
        Product product = productRepository.getReferenceById(id);
        return toDto(product);
    }

    @Override
    public Product addProduct(MultipartFile image, ProductDto productDto) {
        Product product = new Product();
        addUpdate(image, product, productDto);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(MultipartFile image, long id, ProductDto productDto) {
        Product product = productRepository.getReferenceById(id);
        addUpdate(image, product, productDto);
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    public void addUpdate(MultipartFile image, Product product, ProductDto productDto) {
        try {
            product.setName(productDto.getName());
            product.setQuantity(productDto.getQuantity());
            product.setPrice(productDto.getPrice());
            if (image.isEmpty()) {
                product.setImage(null);
            } else {
                product.setImage(uploadImage.upload(image));
            }
            product.setDescription(productDto.getDescription());
            product.setCategory(categoryRepository.getReferenceById(productDto.getCategoryId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ProductDto toDto(Product product) {
        return mapper.map(product, ProductDto.class);
    }
}
