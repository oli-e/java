package com.example.demo.backend.service;

import com.example.demo.backend.entity.Cart;
import com.example.demo.backend.entity.Product;
import com.example.demo.backend.repository.CartRepository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CartService {
    private static final Logger LOGGER = Logger.getLogger(CartService.class.getName());
    private CartRepository cartRepository;

    public CartService(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    public long count() { return cartRepository.count();}

    public void delete(Cart cart) {
        cartRepository.delete(cart);
    }

    public void save(Cart cart){
        if (cart == null) {
            LOGGER.log(Level.SEVERE, "Something's wrong");
            return;
        }
        cartRepository.save(cart);
    }

}
