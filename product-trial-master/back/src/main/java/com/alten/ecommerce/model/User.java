package com.alten.ecommerce.model;


import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users") 
public class User {
    @Id @GeneratedValue
    private Long id;
    private String username;
    private String firstname;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<CartItem> cart;

    @OneToMany(mappedBy = "user")
    private List<WishlistItem> wishlist;
}
