package org.softoHiest.eCommerce.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.softoHiest.eCommerce.dto.OrderItemDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Long id;

    private Long userId;

    private BigDecimal totalAmount;

    private String status;

    private List<OrderItemDto> items;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
