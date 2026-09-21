package org.softoHiest.eCommerce.controller;

import lombok.RequiredArgsConstructor;
import org.softoHiest.eCommerce.dto.requestDto.CartItemRequest;
import org.softoHiest.eCommerce.service.CartItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cartItem")
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping("/addCart")
    public ResponseEntity<String> addToCart(@RequestHeader("X-User_ID") String userId,
                                          @RequestBody CartItemRequest cartItemRequest) {
        if (!cartItemService.addToCart(userId, cartItemRequest)) {
            return ResponseEntity.badRequest().body("Product out of stock or user not found or Product not found");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}