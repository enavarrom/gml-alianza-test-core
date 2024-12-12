package com.enavarrom.tests.gml.alianza.customer.infrastructure.adapter.jpa.repository;

import com.enavarrom.tests.gml.alianza.customer.infrastructure.adapter.jpa.entity.JpaCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCustomerRepository extends JpaRepository<JpaCustomer, String> {
}
