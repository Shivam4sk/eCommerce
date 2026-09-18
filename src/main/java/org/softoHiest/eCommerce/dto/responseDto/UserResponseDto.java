package org.softoHiest.eCommerce.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.softoHiest.eCommerce.dto.requestDto.AddressRequestDto;
import org.softoHiest.eCommerce.model.enums.UserRole;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRole role = UserRole.CUSTOMER;
    private AddressRequestDto address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
