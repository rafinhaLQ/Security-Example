package com.github.rafinhalq.securityexample.repository.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Address {

    private boolean isMain;

    private String cep;

    private String addressLine1;

    private String addressLine2;

    private String district;

    private String city;

    private String state;
}
