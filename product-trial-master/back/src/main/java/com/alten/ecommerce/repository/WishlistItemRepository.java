package com.alten.ecommerce.repository;

import com.alten.ecommerce.model.WishlistItem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {
    List<WishlistItem> findByUserEmail(String email);
    void deleteByUserEmailAndProductId(String email, Long productId);
}

