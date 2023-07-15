package br.pucminas.quotes.adapters.persistence.adapter.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.quotes.adapters.persistence.support.CustomerEntitySupport.defaultCustomerEntity;
import static br.pucminas.quotes.application.support.CustomerSupport.defaultCustomer;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerPersistenceMapperTest {

    @Test
    @DisplayName("Given Customer When Map Then Return Customer Entity")
    public void givenCustomerWhenMapThenReturnCustomerEntity() {
        var customer = defaultCustomer().build();
        var customerEntity = CustomerPersistenceMapper.mapToEntity(customer);

        assertThat(customerEntity).isNotNull();
        assertThat(customerEntity).hasNoNullFieldsOrProperties();
        assertThat(customerEntity.getType()).isEqualTo(customer.getType());
        assertThat(customerEntity.getName()).isEqualTo(customer.getName());
        assertThat(customerEntity.getDocumentNumber()).isEqualTo(customer.getDocumentNumber());
        assertThat(customerEntity.getGender()).isEqualTo(customer.getGender());
        assertThat(customerEntity.getDateOfBirth()).isEqualTo(customer.getDateOfBirth());
    }

    @Test
    @DisplayName("Given Customer Entity When Map Then Return Customer")
    public void givenCustomerEntityWhenMapThenReturnCustomer() {
        var customerEntity = defaultCustomerEntity().build();
        var customer = CustomerPersistenceMapper.mapToDomain(customerEntity);

        assertThat(customer).isNotNull();
        assertThat(customer).hasNoNullFieldsOrProperties();
        assertThat(customer.getType()).isEqualTo(customerEntity.getType());
        assertThat(customer.getName()).isEqualTo(customerEntity.getName());
        assertThat(customer.getDocumentNumber()).isEqualTo(customerEntity.getDocumentNumber());
        assertThat(customer.getGender()).isEqualTo(customerEntity.getGender());
        assertThat(customer.getDateOfBirth()).isEqualTo(customerEntity.getDateOfBirth());
    }

}