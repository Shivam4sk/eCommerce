package org.softoHiest.eCommerce.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDto {
    private String street;
    private String city;
    private String state;
    private String country;
    private String  zipcode;
}
