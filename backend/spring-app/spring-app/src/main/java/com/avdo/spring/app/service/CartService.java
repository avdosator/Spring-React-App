package com.avdo.spring.app.service;

import com.avdo.spring.app.entity.Cart;
import com.avdo.spring.app.entity.User;
import com.avdo.spring.app.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final UserServiceImpl userService;

    @Autowired
    public CartService(CartRepository cartRepository, UserServiceImpl userService) {
        this.cartRepository = cartRepository;
        this.userService = userService;
    }

    public Cart createCart(Long id) {
        User user = userService.findById(id);
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setDateCreated(Date.valueOf(LocalDate.now()));
        return cartRepository.save(cart);
    }

    public Cart findByUserId(Long id) {
        return cartRepository.findByUserId(id).orElse(null);
    }
}
