package com.enavarrom.tests.gml.alianza.customer.infrastructure.adapter.jpa;

import com.enavarrom.tests.gml.alianza.customer.domain.entity.Client;
import com.enavarrom.tests.gml.alianza.customer.domain.repository.ClientRepository;
import com.enavarrom.tests.gml.alianza.customer.infrastructure.adapter.jpa.mapper.JpaClientMapper;
import com.enavarrom.tests.gml.alianza.customer.infrastructure.adapter.jpa.repository.JpaClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class JpaClientRepositoryAdapter implements ClientRepository {

    private final JpaClientRepository jpaClientRepository;

    @Override
    public Optional<Client> getBySharedKey(String sharedKey) {
        return jpaClientRepository.findById(sharedKey)
                .map(JpaClientMapper.INSTANCE::toClient);
    }

    @Override
    public List<Client> findBySharedKey(String sharedKey) {
        return jpaClientRepository.findBySharedKeyContaining(sharedKey)
                .stream()
                .map(JpaClientMapper.INSTANCE::toClient)
                .toList();
    }

    @Override
    public List<Client> findAll() {
        return jpaClientRepository.findAll()
                .stream()
                .map(JpaClientMapper.INSTANCE::toClient)
                .toList();
    }

    @Override
    public void save(Client client) {
        Optional.ofNullable(client)
                .map(JpaClientMapper.INSTANCE::fromClient)
                .ifPresent(jpaClientRepository::save);
    }

    @Override
    public boolean existBySharedKey(String sharedKey) {
        return jpaClientRepository.existsById(sharedKey);
    }

}
