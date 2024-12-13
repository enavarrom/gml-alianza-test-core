package com.enavarrom.tests.gml.alianza.customer.domain.repository;

import com.enavarrom.tests.gml.alianza.customer.domain.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {

    Optional<Client> getBySharedKey(String sharedKey);

    List<Client> findBySharedKey(String sharedKey);

    List<Client> findAll();

    void save(Client client);

    boolean existBySharedKey(String sharedKey);

}
