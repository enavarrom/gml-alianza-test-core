package com.enavarrom.tests.gml.alianza.customer.application.services;

import com.enavarrom.tests.gml.alianza.customer.application.exception.CustomerNotFoundException;
import com.enavarrom.tests.gml.alianza.customer.domain.entity.Customer;
import com.enavarrom.tests.gml.alianza.customer.domain.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer findBySharedKey(String sharedKey) {
        return customerRepository.findById(sharedKey).orElseThrow(()
                -> new CustomerNotFoundException("Customer Not found"));
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

}
