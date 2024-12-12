package com.enavarrom.tests.gml.alianza.customer.application.services;

import com.enavarrom.tests.gml.alianza.customer.application.exception.CustomerNotFoundException;
import com.enavarrom.tests.gml.alianza.customer.domain.entity.Customer;
import com.enavarrom.tests.gml.alianza.customer.domain.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindBySharedKey_Found() {
        // Arrange
        String sharedKey = "12345";
        Customer customer = new Customer();
        customer.setSharedKey(sharedKey);
        when(customerRepository.findById(sharedKey)).thenReturn(Optional.of(customer));

        // Act
        Customer result = customerService.findBySharedKey(sharedKey);

        // Assert
        assertNotNull(result);
        assertEquals(sharedKey, result.getSharedKey());
        verify(customerRepository, times(1)).findById(sharedKey);
    }

    @Test
    void testFindBySharedKey_NotFound() {
        // Arrange
        String sharedKey = "12345";
        when(customerRepository.findById(sharedKey)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(CustomerNotFoundException.class, () -> {
            customerService.findBySharedKey(sharedKey);
        });

        assertEquals("Customer Not found", exception.getMessage());
        verify(customerRepository, times(1)).findById(sharedKey);
    }

    @Test
    void testFindAll() {
        // Arrange
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        // Act
        List<Customer> customers = customerService.findAll();

        // Assert
        assertNotNull(customers);
        assertEquals(2, customers.size());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void testAddCustomer() {
        // Arrange
        Customer customer = new Customer();

        // Act
        customerService.addCustomer(customer);

        // Assert
        verify(customerRepository, times(1)).save(customer);
    }
}

