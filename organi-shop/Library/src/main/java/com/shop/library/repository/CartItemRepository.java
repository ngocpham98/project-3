package com.shop.library.repository;

import com.shop.library.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    public CartItem findByCartIdAndProductId(long cartId, long productId);
    public Set<CartItem> findByCartId(long cartId);
    public void deleteByCartId(long cartId);
}
