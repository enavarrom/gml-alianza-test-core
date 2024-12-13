package com.enavarrom.tests.gml.alianza.customer.infrastructure.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

public record ClientRequestRecord(
                                    String sharedKey,
                                    @NotNull(message = "The business Id can not be null")
                                    String businessId,
                                    @Email(message = "Invalid email")
                                    @NotNull(message = "The email can not be null")
                                    String email,
                                    @Pattern(
                                            regexp = "^[63]\\d{9}$",
                                            message = "Invalid phone number"
                                    )
                                    @NotNull(message = "The phone can not be null")
                                    String phone,
                                    Date startDate,
                                    Date endDate) {
}
