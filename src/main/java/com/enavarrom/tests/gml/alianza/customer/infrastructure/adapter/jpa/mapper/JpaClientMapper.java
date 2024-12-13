package com.enavarrom.tests.gml.alianza.customer.infrastructure.adapter.jpa.mapper;

import com.enavarrom.tests.gml.alianza.customer.domain.entity.Client;
import com.enavarrom.tests.gml.alianza.customer.infrastructure.adapter.jpa.entity.JpaClient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JpaClientMapper {

    JpaClientMapper INSTANCE = Mappers.getMapper(JpaClientMapper.class);

    Client toClient(JpaClient jpaClient);

    JpaClient fromClient(Client client);

}
