package com.enavarrom.tests.gml.alianza.customer.domain.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
public class Customer {

    private String sharedKey;

    private String businessId;

    private String email;

    private String phone;

    @Setter(AccessLevel.NONE)
    private LocalDateTime createdDate;
}
