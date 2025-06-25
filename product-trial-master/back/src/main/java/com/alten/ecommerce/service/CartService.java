package com.alten.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alten.ecommerce.model.CartItem;
import com.alten.ecommerce.model.Product;
import com.alten.ecommerce.model.User;
import com.alten.ecommerce.repository.CartItemRepository;
import com.alten.ecommerce.repository.ProductRepository;
import com.alten.ecommerce.repository.UserRepository;

@Service
public class CartService {
    @Autowired private CartItemRepository cartRepo;
    @Autowired private ProductRepository productRepo;
    @Autowired private UserRepository userRepo;

    public List<CartItem> getCart(String email) {
        return cartRepo.findByUserEmail(email);
    }

    public CartItem addToCart(Long productId, int quantity, String email) {
        User user = userRepo.findByEmail(email).orElseThrow();
        Product product = productRepo.findById(productId).orElseThrow();

        CartItem item = new CartItem();
        item.setUser(user);
        item.setProduct(product);
        item.setQuantity(quantity);

        return cartRepo.save(item);
    }

    public void removeFromCart(Long productId, String email) {
        cartRepo.deleteByUserEmailAndProductId(email, productId);
    }
}

