package com.shop.library.repository;

import com.shop.library.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ProductRepository extends JpaRepository<Product,Long> {
    public List<Product> findByCategoryId(long id);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%" + "OR p.category.name LIKE %?1%")
    public List<Product> findAll(String keyword);
}
