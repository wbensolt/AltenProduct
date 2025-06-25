package com.alten.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alten.ecommerce.model.Product;
import com.alten.ecommerce.model.User;
import com.alten.ecommerce.model.WishlistItem;
import com.alten.ecommerce.repository.ProductRepository;
import com.alten.ecommerce.repository.UserRepository;
import com.alten.ecommerce.repository.WishlistItemRepository;

@Service
public class WishlistService {
    @Autowired private WishlistItemRepository wishlistRepo;
    @Autowired private ProductRepository productRepo;
    @Autowired private UserRepository userRepo;

    public List<WishlistItem> getWishlist(String email) {
        return wishlistRepo.findByUserEmail(email);
    }

    public WishlistItem addToWishlist(Long productId, String email) {
        User user = userRepo.findByEmail(email).orElseThrow();
        Product product = productRepo.findById(productId).orElseThrow();

        WishlistItem item = new WishlistItem();
        item.setUser(user);
        item.setProduct(product);

        return wishlistRepo.save(item);
    }

    public void removeFromWishlist(Long productId, String email) {
        wishlistRepo.deleteByUserEmailAndProductId(email, productId);
    }
}

