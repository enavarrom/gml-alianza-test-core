package com.enavarrom.tests.gml.alianza.customer.infrastructure.api.controller;

import com.enavarrom.tests.gml.alianza.customer.application.services.CustomerService;
import com.enavarrom.tests.gml.alianza.customer.infrastructure.api.dto.CustomerRequestRecord;
import com.enavarrom.tests.gml.alianza.customer.infrastructure.api.dto.CustomerResponseRecord;
import com.enavarrom.tests.gml.alianza.customer.infrastructure.api.mapper.CustomerMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/customers")
@RestController
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @GetMapping("/{sharedKey}")
    public ResponseEntity<CustomerResponseRecord> getCustomerBySharedKey(@PathVariable String sharedKey) {
        CustomerResponseRecord customerResponseRecord = Optional
                .ofNullable(customerService.findBySharedKey(sharedKey))
                .map(CustomerMapper.INSTANCE::toCustomerResponseRecord)
                .orElse(null);
        return ResponseEntity.ok(customerResponseRecord);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseRecord>> getAllCustomers() {
        List<CustomerResponseRecord> customerRecords = customerService.findAll()
                .stream()
                .map(CustomerMapper.INSTANCE::toCustomerResponseRecord)
                .toList();
        return ResponseEntity.ok(customerRecords);
    }

    @PostMapping
    public ResponseEntity<Void> addCustomer(@Valid @RequestBody CustomerRequestRecord customerRequestRecord) {
        customerService.addCustomer(CustomerMapper.INSTANCE.fromCustomerRequestRecord(customerRequestRecord));
        return ResponseEntity.status(201).build();
    }

}
