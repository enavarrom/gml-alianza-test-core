package com.enavarrom.tests.gml.alianza.customer.application.services;

import com.enavarrom.tests.gml.alianza.customer.application.exception.ClientNotFoundException;
import com.enavarrom.tests.gml.alianza.customer.domain.entity.Client;
import com.enavarrom.tests.gml.alianza.customer.domain.repository.ClientRepository;
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

class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBySharedKey_Found() {
        // Arrange
        String sharedKey = "12345";
        Client client = new Client();
        client.setSharedKey(sharedKey);
        when(clientRepository.getBySharedKey(sharedKey)).thenReturn(Optional.of(client));

        // Act
        Client result = clientService.getBySharedKey(sharedKey);

        // Assert
        assertNotNull(result);
        assertEquals(sharedKey, result.getSharedKey());
        verify(clientRepository, times(1)).getBySharedKey(sharedKey);
    }

    @Test
    void testGetBySharedKey_NotFound() {
        // Arrange
        String sharedKey = "12345";
        when(clientRepository.getBySharedKey(sharedKey)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(ClientNotFoundException.class, () -> {
            clientService.getBySharedKey(sharedKey);
        });

        assertEquals("Client Not found", exception.getMessage());
        verify(clientRepository, times(1)).getBySharedKey(sharedKey);
    }

    @Test
    void testFindAll() {
        // Arrange
        Client client1 = new Client();
        Client client2 = new Client();
        when(clientRepository.findAll()).thenReturn(Arrays.asList(client1, client2));

        // Act
        List<Client> clients = clientService.findAll();

        // Assert
        assertNotNull(clients);
        assertEquals(2, clients.size());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void testCreate() {
        // Arrange
        Client client = new Client();

        // Act
        clientService.create(client);

        // Assert
        verify(clientRepository, times(1)).save(client);
    }
}

