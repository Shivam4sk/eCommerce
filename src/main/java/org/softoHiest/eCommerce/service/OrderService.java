package org.softoHiest.eCommerce.service;

import lombok.RequiredArgsConstructor;
import org.softoHiest.eCommerce.dto.responseDto.OrderResponse;
import org.softoHiest.eCommerce.model.CartItem;
import org.softoHiest.eCommerce.model.User;
import org.softoHiest.eCommerce.repository.OrderRepository;
import org.softoHiest.eCommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartItemService cartItemService;
    private final UserRepository userRepository;

    public OrderResponse createOrder(String userId) {

        //validate for cart items
        List<CartItem>  cartItems = cartItemService.getCart(userId);
        if (cartItems.isEmpty()){

        }
        //validate for user
        Optional<User> userOptional = userRepository.findById(Long.valueOf(userId));


