package br.pucminas.leads.adapters.persistence.adapter.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.leads.adapters.persistence.support.CustomerEntitySupport.defaultCustomerEntity;
import static br.pucminas.leads.application.support.CustomerSupport.defaultCustomer;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerPersistenceMapperTest {

    @Test
    @DisplayName("Given Customer When Map Then Return Customer Entity")
    public void givenCustomerWhenMapThenReturnCustomerEntity() {
        var customer = defaultCustomer().build();
        var customerEntity = CustomerPersistenceMapper.mapToEntity(customer);

        assertThat(customerEntity).isNotNull();
        assertThat(customerEntity).hasNoNullFieldsOrProperties();
        assertThat(customerEntity.getName()).isEqualTo(customer.getName());
        assertThat(customerEntity.getGender()).isEqualTo(customer.getGender());
        assertThat(customerEntity.getType()).isEqualTo(customer.getType());
        assertThat(customerEntity.getDateOfBirth()).isEqualTo(customer.getDateOfBirth());
        assertThat(customerEntity.getPhoneNumber()).isEqualTo(customer.getPhoneNumber());
        assertThat(customerEntity.getEmail()).isEqualTo(customer.getEmail());
    }

    @Test
    @DisplayName("Given Customer Entity When Map Then Return Customer")
    public void givenCustomerEntityWhenMapThenReturnCustomer() {
        var customerEntity = defaultCustomerEntity().build();
        var customer = CustomerPersistenceMapper.mapToDomain(customerEntity);

        assertThat(customer).isNotNull();
        assertThat(customer).hasNoNullFieldsOrProperties();
        assertThat(customer.getName()).isEqualTo(customerEntity.getName());
        assertThat(customer.getGender()).isEqualTo(customerEntity.getGender());
        assertThat(customer.getType()).isEqualTo(customerEntity.getType());
        assertThat(customer.getDateOfBirth()).isEqualTo(customerEntity.getDateOfBirth());
        assertThat(customer.getPhoneNumber()).isEqualTo(customerEntity.getPhoneNumber());
        assertThat(customer.getEmail()).isEqualTo(customerEntity.getEmail());
    }

}