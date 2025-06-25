package com.alten.ecommerce.repository;

import com.alten.ecommerce.model.CartItem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserEmail(String email);
    void deleteByUserEmailAndProductId(String email, Long productId);
}

