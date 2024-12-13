package com.enavarrom.tests.gml.alianza.customer.infrastructure.api.mapper;

import com.enavarrom.tests.gml.alianza.customer.domain.entity.Client;
import com.enavarrom.tests.gml.alianza.customer.infrastructure.api.dto.ClientRequestRecord;
import com.enavarrom.tests.gml.alianza.customer.infrastructure.api.dto.ClientResponseRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientResponseRecord toClientResponseRecord(Client client);

    @Mapping(target = "createdDate", ignore = true)
    Client fromClientRequestRecord(ClientRequestRecord clientRequestRecord);

}
