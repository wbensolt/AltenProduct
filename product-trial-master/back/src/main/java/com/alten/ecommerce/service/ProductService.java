package com.alten.ecommerce.service;

import com.alten.ecommerce.model.Product;
import com.alten.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Product create(Product product, String email) {
        if (!"admin@admin.com".equals(email)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only admin can create products.");
        }
        return productRepository.save(product);
    }

    public Product update(Long id, Product updated, String email) {
        if (!"admin@admin.com".equals(email)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only admin can update products.");
        }
        Product p = getById(id);
        updated.setId(p.getId());
        return productRepository.save(updated);
    }

    public void delete(Long id, String email) {
        if (!"admin@admin.com".equals(email)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only admin can delete products.");
        }
        productRepository.deleteById(id);
    }
}