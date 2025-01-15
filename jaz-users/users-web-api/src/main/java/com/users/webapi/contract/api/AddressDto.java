package com.users.webapi.contract.api;


import lombok.Data;
import lombok.experimental.Accessors;

//@Getter
//@Setter
//@EqualsAndHashCode
@Data
@Accessors(fluent = true, chain = true)
public class AddressDto {
    private LocationDto location;
    private String street;
    private int number;

}
