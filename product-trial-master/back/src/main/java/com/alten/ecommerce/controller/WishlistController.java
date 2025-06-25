package com.alten.ecommerce.controller;

import com.alten.ecommerce.model.WishlistItem;
import com.alten.ecommerce.service.WishlistService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
    @Autowired private WishlistService wishlistService;

    @GetMapping
    public List<WishlistItem> getWishlist(@RequestHeader("email") String email) {
        return wishlistService.getWishlist(email);
    }

    @PostMapping
    public WishlistItem addToWishlist(@RequestBody Map<String, Object> payload, @RequestHeader("email") String email) {
        Long productId = Long.valueOf(payload.get("productId").toString());
        return wishlistService.addToWishlist(productId, email);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> removeFromWishlist(@PathVariable Long productId, @RequestHeader("email") String email) {
        wishlistService.removeFromWishlist(productId, email);
        return ResponseEntity.ok().build();
    }
}
