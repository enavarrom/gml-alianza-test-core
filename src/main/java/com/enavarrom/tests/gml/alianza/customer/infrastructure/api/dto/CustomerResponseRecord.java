package com.enavarrom.tests.gml.alianza.customer.infrastructure.api.dto;

import java.time.LocalDateTime;

public record CustomerResponseRecord(String sharedKey, String businessId, String email, String phone,
                                     LocalDateTime createdDate) {
}
