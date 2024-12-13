package com.enavarrom.tests.gml.alianza.customer.infrastructure.api.controller;

import com.enavarrom.tests.gml.alianza.customer.application.services.ClientService;
import com.enavarrom.tests.gml.alianza.customer.infrastructure.api.dto.ClientRequestRecord;
import com.enavarrom.tests.gml.alianza.customer.infrastructure.api.dto.ClientResponseRecord;
import com.enavarrom.tests.gml.alianza.customer.infrastructure.api.mapper.ClientMapper;
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

    private final ClientService clientService;


    @GetMapping("/{sharedKey}")
    public ResponseEntity<ClientResponseRecord> getCustomerBySharedKey(@PathVariable String sharedKey) {
        ClientResponseRecord clientResponseRecord = Optional
                .ofNullable(clientService.getBySharedKey(sharedKey))
                .map(ClientMapper.INSTANCE::toClientResponseRecord)
                .orElse(null);
        return ResponseEntity.ok(clientResponseRecord);
    }

    @GetMapping("/search/{sharedKey}")
    public ResponseEntity<List<ClientResponseRecord>> getAllCustomersBySharedKey(@PathVariable String sharedKey) {
        List<ClientResponseRecord> customerRecords = clientService.findBySharedKey(sharedKey)
                .stream()
                .map(ClientMapper.INSTANCE::toClientResponseRecord)
                .toList();
        return ResponseEntity.ok(customerRecords);
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseRecord>> getAllCustomers() {
        List<ClientResponseRecord> customerRecords = clientService.findAll()
                .stream()
                .map(ClientMapper.INSTANCE::toClientResponseRecord)
                .toList();
        return ResponseEntity.ok(customerRecords);
    }

    @PostMapping
    public ResponseEntity<Void> createClient(@Valid @RequestBody ClientRequestRecord clientRequestRecord) {
        clientService.create(ClientMapper.INSTANCE.fromClientRequestRecord(clientRequestRecord));
        return ResponseEntity.status(201).build();
    }

    @PutMapping
    public ResponseEntity<Void> editClient(@Valid @RequestBody ClientRequestRecord clientRequestRecord) {
        clientService.edit(ClientMapper.INSTANCE.fromClientRequestRecord(clientRequestRecord));
        return ResponseEntity.status(201).build();
    }

}
