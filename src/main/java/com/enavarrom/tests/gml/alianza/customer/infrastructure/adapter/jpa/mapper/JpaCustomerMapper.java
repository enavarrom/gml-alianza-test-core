package com.enavarrom.tests.gml.alianza.customer.infrastructure.adapter.jpa.mapper;

import com.enavarrom.tests.gml.alianza.customer.domain.entity.Customer;
import com.enavarrom.tests.gml.alianza.customer.infrastructure.adapter.jpa.entity.JpaCustomer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JpaCustomerMapper {

    JpaCustomerMapper INSTANCE = Mappers.getMapper(JpaCustomerMapper.class);

    Customer toCustomer(JpaCustomer jpaCustomer);

    JpaCustomer fromCustomer(Customer customer);

}
