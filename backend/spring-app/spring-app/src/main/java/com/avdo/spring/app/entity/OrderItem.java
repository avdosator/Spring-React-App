package com.avdo.spring.app.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "order_item")
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    private Product product;

    private Order order;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;
}
