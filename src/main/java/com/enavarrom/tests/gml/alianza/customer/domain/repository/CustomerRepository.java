package com.enavarrom.tests.gml.alianza.customer.domain.repository;

import com.enavarrom.tests.gml.alianza.customer.domain.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    Optional<Customer> findById(String id);

    List<Customer> findAll();

    void save(Customer customer);

}
