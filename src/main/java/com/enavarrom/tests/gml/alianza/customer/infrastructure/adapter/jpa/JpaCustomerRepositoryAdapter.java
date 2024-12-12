package com.enavarrom.tests.gml.alianza.customer.infrastructure.adapter.jpa;

import com.enavarrom.tests.gml.alianza.customer.domain.entity.Customer;
import com.enavarrom.tests.gml.alianza.customer.domain.repository.CustomerRepository;
import com.enavarrom.tests.gml.alianza.customer.infrastructure.adapter.jpa.mapper.JpaCustomerMapper;
import com.enavarrom.tests.gml.alianza.customer.infrastructure.adapter.jpa.repository.JpaCustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class JpaCustomerRepositoryAdapter implements CustomerRepository {

    private final JpaCustomerRepository jpaCustomerRepository;

    @Override
    public Optional<Customer> findById(String id) {
        return jpaCustomerRepository.findById(id)
                .map(JpaCustomerMapper.INSTANCE::toCustomer);
    }

    @Override
    public List<Customer> findAll() {
        return jpaCustomerRepository.findAll()
                .stream()
                .map(JpaCustomerMapper.INSTANCE::toCustomer)
                .toList();
    }

    @Override
    public void save(Customer customer) {
        Optional.ofNullable(customer)
                .map(JpaCustomerMapper.INSTANCE::fromCustomer)
                .ifPresent(jpaCustomerRepository::save);
    }

}
