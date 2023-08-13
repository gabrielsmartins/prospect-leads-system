package br.pucminas.leads.adapters.web.out.quotes.mapper;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.leads.adapters.web.support.CustomerDtoSupport.defaultCustomerDto;
import static br.pucminas.leads.application.support.CustomerSupport.defaultCustomer;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerWebMapperTest {

    @Test
    @DisplayName("Given Customer Dto When Map Then Return Customer")
    public void givenCustomerDtoWhenMapThenReturnCustomer() {
        var customerDto = defaultCustomerDto().build();

        var customer = CustomerWebMapper.mapToDomain(customerDto);

        assertThat(customer).isNotNull();
        assertThat(customer).hasNoNullFieldsOrProperties();
        assertThat(customer.getType()).isEqualTo(customerDto.getType());
        assertThat(customer.getName()).isEqualTo(customerDto.getName());
        assertThat(customer.getDocumentNumber()).isEqualTo(customerDto.getDocumentNumber());
        assertThat(customer.getGender()).isEqualTo(customerDto.getGender());
        assertThat(customer.getDateOfBirth()).isEqualTo(customerDto.getDateOfBirth());
        assertThat(customer.getEmail()).isEqualTo(customerDto.getEmail());
        assertThat(customer.getPhoneNumber()).isEqualTo(customerDto.getPhoneNumber());
    }

    @Test
    @DisplayName("Given Customer When Map Then Return Customer Dto")
    public void givenCustomerWhenMapThenReturnCustomerDto() {
        var customer = defaultCustomer().build();

        var customerDto = CustomerWebMapper.mapToDto(customer);

        assertThat(customerDto).isNotNull();
        assertThat(customerDto).hasNoNullFieldsOrProperties();
        assertThat(customerDto.getType()).isEqualTo(customer.getType());
        assertThat(customerDto.getName()).isEqualTo(customer.getName());
        assertThat(customerDto.getDocumentNumber()).isEqualTo(customer.getDocumentNumber());
        assertThat(customerDto.getGender()).isEqualTo(customer.getGender());
        assertThat(customerDto.getDateOfBirth()).isEqualTo(customer.getDateOfBirth());
        assertThat(customerDto.getEmail()).isEqualTo(customer.getEmail());
        assertThat(customerDto.getPhoneNumber()).isEqualTo(customer.getPhoneNumber());
    }

}