package org.softoHiest.eCommerce.controller;


import lombok.RequiredArgsConstructor;
import org.softoHiest.eCommerce.dto.responseDto.OrderResponse;
import org.softoHiest.eCommerce.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<OrderResponse> createOrder(@RequestHeader("X-User-ID") String userId){
        OrderResponse order = orderService.createOrder(userId);
        return new ResponseEntity<OrderResponse>(order, HttpStatus.CREATED);
    }
}
