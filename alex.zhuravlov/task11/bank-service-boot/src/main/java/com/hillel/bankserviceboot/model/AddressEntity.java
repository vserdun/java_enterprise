package com.hillel.bankserviceboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class AddressEntity {
    private String street;
    private String city;
    private String zip;
    private String state;
}
