package com.shop.library.service;

import com.shop.library.entity.Cart;
import com.shop.library.entity.CartItem;
import com.shop.library.entity.Customer;

import java.util.Set;

public interface CartService {
    public Cart getCart(Customer customer);
    public Set<CartItem> getCartItems(long cartId);
    public Cart addItem(int quantity, long productId, Customer customer);
    public Cart updateQuantity(int quantity, long id, Customer customer);
    public void deleteItem(long id, Customer customer);

}
