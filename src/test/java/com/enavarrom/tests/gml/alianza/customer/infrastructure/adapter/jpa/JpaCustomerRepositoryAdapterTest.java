package com.enavarrom.tests.gml.alianza.customer.infrastructure.adapter.jpa;

import com.enavarrom.tests.gml.alianza.customer.domain.entity.Customer;
import com.enavarrom.tests.gml.alianza.customer.domain.repository.CustomerRepository;
import com.enavarrom.tests.gml.alianza.customer.infrastructure.adapter.jpa.mapper.JpaCustomerMapper;
import com.enavarrom.tests.gml.alianza.customer.infrastructure.adapter.jpa.repository.JpaCustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class JpaCustomerRepositoryAdapterTest {

    @Autowired
    private JpaCustomerRepository jpaCustomerRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setSharedKey("1234");
        customer.setBusinessId("John Doe");
        customer.setEmail("jhon.doe@email.com");
        customer.setPhone("3005555555");
        jpaCustomerRepository.save(JpaCustomerMapper.INSTANCE.fromCustomer(customer));
    }

    @Test
    void testFindById() {
        Optional<Customer> foundCustomer = customerRepository.findById("1234");
        assertTrue(foundCustomer.isPresent());
        assertEquals("John Doe", foundCustomer.get().getBusinessId());
    }

    @Test
    void testFindAll() {
        List<Customer> customers = customerRepository.findAll();
        assertEquals(1, customers.size());
        assertEquals("John Doe", customers.get(0).getBusinessId());
    }

    @Test
    void testSave() {
        Customer newCustomer = new Customer();
        newCustomer.setSharedKey("5678");
        newCustomer.setBusinessId("Jane Doe");
        newCustomer.setEmail("jane.doe@email.com");
        newCustomer.setPhone("3005555553");

        customerRepository.save(newCustomer);

        Optional<Customer> foundCustomer = customerRepository.findById("5678");
        assertTrue(foundCustomer.isPresent());
        assertEquals("Jane Doe", foundCustomer.get().getBusinessId());
    }
}


