package com.enavarrom.tests.gml.alianza.customer.infrastructure.api.controller;

import com.enavarrom.tests.gml.alianza.customer.application.exception.CustomerNotFoundException;
import com.enavarrom.tests.gml.alianza.customer.application.services.CustomerService;
import com.enavarrom.tests.gml.alianza.customer.domain.entity.Customer;
import com.enavarrom.tests.gml.alianza.customer.infrastructure.api.dto.CustomerResponseRecord;
import com.enavarrom.tests.gml.alianza.customer.infrastructure.api.mapper.CustomerMapper;
import com.enavarrom.tests.gml.alianza.customer.infrastructure.exception.CustomerApiExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(customerController)
                .setControllerAdvice(new CustomerApiExceptionHandler())
                .build();
    }

    @Test
    void getCustomerBySharedKey_ShouldReturnCustomer_WhenCustomerExists() throws Exception {
        Customer customer = new Customer();
        customer.setSharedKey("1234");
        customer.setBusinessId("John Doe");

        when(customerService.findBySharedKey("1234")).thenReturn(customer);

        mockMvc.perform(get("/customers/1234"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sharedKey").value("1234"))
                .andExpect(jsonPath("$.businessId").value("John Doe"));

        verify(customerService, times(1)).findBySharedKey("1234");
    }

    @Test
    void getCustomerBySharedKey_ShouldReturnNotFound_WhenCustomerDoesNotExist() throws Exception {
        when(customerService.findBySharedKey("1234")).thenThrow(new CustomerNotFoundException("Customer Not found"));

        mockMvc.perform(get("/customers/1234"))
                .andExpect(status().isNotFound());

        verify(customerService, times(1)).findBySharedKey("1234");
    }

    @Test
    void getAllCustomers_ShouldReturnAllCustomers() throws Exception {
        Customer customer = new Customer();
        customer.setSharedKey("1234");
        customer.setBusinessId("John Doe");

        when(customerService.findAll()).thenReturn(Collections.singletonList(customer));

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].sharedKey").value("1234"))
                .andExpect(jsonPath("$[0].businessId").value("John Doe"));

        verify(customerService, times(1)).findAll();
    }

    @Test
    void addCustomer_ShouldReturnCreatedStatus() throws Exception {
        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"john.doe@example.com\",\"businessId\":\"John Doe\"" +
                                ",\"sharedKey\":\"johndoe\",\"phone\":\"3005555555\"}"))
                .andExpect(status().isCreated());

        verify(customerService, times(1)).addCustomer(any());
    }

    @Test
    void addCustomer_ShouldReturnBadRequest() throws Exception {
        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"john.doe@example.com\",\"businessId\":\"John Doe\"" +
                                ",\"sharedKey\":\"johndoe\",\"phone\":\"1005555555\"}"))
                .andExpect(status().isBadRequest());

        verify(customerService, times(0)).addCustomer(any());
    }
}

