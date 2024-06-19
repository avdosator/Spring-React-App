package com.avdo.spring.app.service;

import com.avdo.spring.app.dto.CreateOrderRequest;
import com.avdo.spring.app.entity.*;
import com.avdo.spring.app.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final OrderItemService orderItemService;

    @Autowired
    public OrderService(OrderRepository orderRepository, CartService cartService, OrderItemService orderItemService) {

        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.orderItemService = orderItemService;
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("There is no order with that ID"));
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(CreateOrderRequest createOrderRequest) {
        User user = extractUserFromToken();
        Cart cart = cartService.findByUserId(user.getId());

        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(cart.getItems().stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum());
        order.setOrderStatus("Pending");
        order.setDateCreated(Date.valueOf(LocalDate.now()));

        orderRepository.save(order);

        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice());

            orderItemService.createOrderItem(orderItem);
        }
    }

    private User extractUserFromToken() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
