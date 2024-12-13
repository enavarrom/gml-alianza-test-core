package com.enavarrom.tests.gml.alianza.customer.infrastructure.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ClientResponseRecord(String sharedKey, String businessId, String email, String phone,
                                   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDateTime createdDate) {
}
