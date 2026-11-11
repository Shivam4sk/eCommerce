package org.softoHiest.eCommerce.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.softoHiest.eCommerce.dto.OrderItemDto;
import org.softoHiest.eCommerce.model.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderResponse {

    private Long id;

    private Long userId;

    private BigDecimal totalAmount;

    private OrderStatus status;

    private List<OrderItemDto> items;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
