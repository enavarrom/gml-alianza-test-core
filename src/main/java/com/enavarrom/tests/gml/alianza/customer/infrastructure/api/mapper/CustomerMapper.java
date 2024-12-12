package com.enavarrom.tests.gml.alianza.customer.infrastructure.api.mapper;

import com.enavarrom.tests.gml.alianza.customer.domain.entity.Customer;
import com.enavarrom.tests.gml.alianza.customer.infrastructure.api.dto.CustomerRequestRecord;
import com.enavarrom.tests.gml.alianza.customer.infrastructure.api.dto.CustomerResponseRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerResponseRecord toCustomerResponseRecord(Customer customer);

    @Mapping(target = "createdDate", ignore = true)
    Customer fromCustomerRequestRecord(CustomerRequestRecord customerRequestRecord);

}
