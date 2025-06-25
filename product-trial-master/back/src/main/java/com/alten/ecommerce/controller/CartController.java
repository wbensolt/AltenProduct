package com.alten.ecommerce.controller;

import com.alten.ecommerce.model.CartItem;
import com.alten.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired private CartService cartService;

    @GetMapping
    public List<CartItem> getCart(@RequestHeader("email") String email) {
        return cartService.getCart(email);
    }

    @PostMapping
    public CartItem addToCart(@RequestBody Map<String, Object> payload, @RequestHeader("email") String email) {
        Long productId = Long.valueOf(payload.get("productId").toString());
        int quantity = Integer.parseInt(payload.get("quantity").toString());
        return cartService.addToCart(productId, quantity, email);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> removeFromCart(@PathVariable Long productId, @RequestHeader("email") String email) {
        cartService.removeFromCart(productId, email);
        return ResponseEntity.ok().build();
    }
}

