package com.enavarrom.tests.gml.alianza.customer.infrastructure.adapter.jpa.repository;

import com.enavarrom.tests.gml.alianza.customer.infrastructure.adapter.jpa.entity.JpaClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaClientRepository extends JpaRepository<JpaClient, String> {

    List<JpaClient> findBySharedKeyContaining(String id);

}
