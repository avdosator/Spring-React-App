package com.avdo.spring.app.service.domain.model;

import com.avdo.spring.app.entity.Cart;
import com.avdo.spring.app.entity.Product;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CartItem {

    private Long id;

    private Cart cart;

    private Product product;

    private int quantity;
}