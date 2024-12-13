package com.enavarrom.tests.gml.alianza.customer.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Client {

    private String sharedKey;

    private String businessId;

    private String email;

    private String phone;

    private Date startDate;

    private Date endDate;

    private LocalDateTime createdDate;
}
