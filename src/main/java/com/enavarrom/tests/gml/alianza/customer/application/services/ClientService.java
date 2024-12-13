package com.enavarrom.tests.gml.alianza.customer.application.services;

import com.enavarrom.tests.gml.alianza.customer.application.exception.ClientAlreadyExistException;
import com.enavarrom.tests.gml.alianza.customer.application.exception.ClientNotFoundException;
import com.enavarrom.tests.gml.alianza.customer.domain.entity.Client;
import com.enavarrom.tests.gml.alianza.customer.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client getBySharedKey(String sharedKey) {
        return clientRepository.getBySharedKey(sharedKey).orElseThrow(()
                -> new ClientNotFoundException("Client Not found"));
    }

    public List<Client> findBySharedKey(String sharedKey) {
        if (Strings.isBlank(sharedKey)) {
            return findAll();
        }
        return clientRepository.findBySharedKey(sharedKey);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public void create(Client client) {
        String sharedKey = client.getSharedKey();
        if (!Strings.isBlank(sharedKey) && clientRepository.existBySharedKey(sharedKey)) {
            throw new ClientAlreadyExistException("Client Exist");
        }

        if (Strings.isBlank(sharedKey)) {
            sharedKey = "";
            String[] nameSplit = client.getBusinessId().split(" ");
            for(int i=0; i<nameSplit.length; i++) {
                if (i != nameSplit.length - 1) {
                    sharedKey=sharedKey.concat(String.valueOf(nameSplit[i].charAt(0)));
                }
                else {
                    sharedKey=sharedKey.concat(nameSplit[i]);
                }
            }
        }

        client.setSharedKey(sharedKey.toLowerCase());
        clientRepository.save(client);
    }

    public void edit(Client client) {
        if (!clientRepository.existBySharedKey(client.getSharedKey())) {
         throw new ClientNotFoundException("Client Not found");
        }
        clientRepository.save(client);
    }

}
