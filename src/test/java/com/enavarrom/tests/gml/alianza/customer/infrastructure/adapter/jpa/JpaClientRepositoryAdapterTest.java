package com.enavarrom.tests.gml.alianza.customer.infrastructure.adapter.jpa;

import com.enavarrom.tests.gml.alianza.customer.domain.entity.Client;
import com.enavarrom.tests.gml.alianza.customer.domain.repository.ClientRepository;
import com.enavarrom.tests.gml.alianza.customer.infrastructure.adapter.jpa.mapper.JpaClientMapper;
import com.enavarrom.tests.gml.alianza.customer.infrastructure.adapter.jpa.repository.JpaClientRepository;
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
class JpaClientRepositoryAdapterTest {

    @Autowired
    private JpaClientRepository jpaClientRepository;

    @Autowired
    private ClientRepository clientRepository;

    private Client client;

    @BeforeEach
    void setUp() {
        client = new Client();
        client.setSharedKey("1234");
        client.setBusinessId("John Doe");
        client.setEmail("jhon.doe@email.com");
        client.setPhone("3005555555");
        jpaClientRepository.save(JpaClientMapper.INSTANCE.fromClient(client));
    }

    @Test
    void testGetBySharedKey() {
        Optional<Client> foundCustomer = clientRepository.getBySharedKey("1234");
        assertTrue(foundCustomer.isPresent());
        assertEquals("John Doe", foundCustomer.get().getBusinessId());
    }

    @Test
    void testFindAll() {
        List<Client> clients = clientRepository.findAll();
        assertEquals(1, clients.size());
        assertEquals("John Doe", clients.get(0).getBusinessId());
    }

    @Test
    void testSave() {
        Client newClient = new Client();
        newClient.setSharedKey("5678");
        newClient.setBusinessId("Jane Doe");
        newClient.setEmail("jane.doe@email.com");
        newClient.setPhone("3005555553");

        clientRepository.save(newClient);

        Optional<Client> foundCustomer = clientRepository.getBySharedKey("5678");
        assertTrue(foundCustomer.isPresent());
        assertEquals("Jane Doe", foundCustomer.get().getBusinessId());
    }
}


