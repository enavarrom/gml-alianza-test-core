package com.enavarrom.tests.gml.alianza.customer.infrastructure.api.controller;

import com.enavarrom.tests.gml.alianza.customer.application.exception.ClientNotFoundException;
import com.enavarrom.tests.gml.alianza.customer.application.services.ClientService;
import com.enavarrom.tests.gml.alianza.customer.domain.entity.Client;
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
class ClientControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ClientService clientService;

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
        Client client = new Client();
        client.setSharedKey("1234");
        client.setBusinessId("John Doe");

        when(clientService.getBySharedKey("1234")).thenReturn(client);

        mockMvc.perform(get("/customers/1234"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sharedKey").value("1234"))
                .andExpect(jsonPath("$.businessId").value("John Doe"));

        verify(clientService, times(1)).getBySharedKey("1234");
    }

    @Test
    void getCustomerBySharedKey_ShouldReturnNotFound_WhenCustomerDoesNotExist() throws Exception {
        when(clientService.getBySharedKey("1234")).thenThrow(new ClientNotFoundException("Customer Not found"));

        mockMvc.perform(get("/customers/1234"))
                .andExpect(status().isNotFound());

        verify(clientService, times(1)).getBySharedKey("1234");
    }

    @Test
    void getAllCustomers_ShouldReturnAllCustomers() throws Exception {
        Client client = new Client();
        client.setSharedKey("1234");
        client.setBusinessId("John Doe");

        when(clientService.findAll()).thenReturn(Collections.singletonList(client));

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].sharedKey").value("1234"))
                .andExpect(jsonPath("$[0].businessId").value("John Doe"));

        verify(clientService, times(1)).findAll();
    }

    @Test
    void addCustomer_ShouldReturnCreatedStatus() throws Exception {
        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"john.doe@example.com\",\"businessId\":\"John Doe\"" +
                                ",\"sharedKey\":\"johndoe\",\"phone\":\"3005555555\"}"))
                .andExpect(status().isCreated());

        verify(clientService, times(1)).create(any());
    }

    @Test
    void addCustomer_ShouldReturnBadRequest() throws Exception {
        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"john.doe@example.com\",\"businessId\":\"John Doe\"" +
                                ",\"sharedKey\":\"johndoe\",\"phone\":\"1005555555\"}"))
                .andExpect(status().isBadRequest());

        verify(clientService, times(0)).create(any());
    }
}

