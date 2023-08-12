package br.pucminas.bff.adapters.web.in.quotes.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.bff.adapters.web.in.support.CustomerDtoSupport.defaultCustomerDto;
import static br.pucminas.bff.application.support.CustomerSupport.defaultCustomer;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerControllerMapperTest {

    @Test
    @DisplayName("Given Customer When Map Then Return Customer Dto")
    public void givenCustomerWhenMapThenReturnCustomerDto() {
        var customerDto = defaultCustomerDto().build();
        var customer = CustomerControllerMapper.mapToDomain(customerDto);

        assertThat(customer).isNotNull();
        assertThat(customer).hasNoNullFieldsOrProperties();
        assertThat(customer.getDocumentNumber()).isEqualTo(customerDto.getDocumentNumber());
        assertThat(customer.getName()).isEqualTo(customerDto.getName());
        assertThat(customer.getGender()).isEqualTo(customerDto.getGender());
        assertThat(customer.getDateOfBirth()).isEqualTo(customerDto.getDateOfBirth());
        assertThat(customer.getType()).isEqualTo(customerDto.getType());
        assertThat(customer.getEmail()).isEqualTo(customerDto.getEmail());
        assertThat(customer.getPhoneNumber()).isEqualTo(customerDto.getPhoneNumber());
    }

    @Test
    @DisplayName("Given Customer Dto When Map Then Return Customer")
    public void givenCustomerDtoWhenMapThenReturnCustomer() {
        var customer = defaultCustomer().build();
        var customerDto = CustomerControllerMapper.mapToDto(customer);

        assertThat(customerDto).isNotNull();
        assertThat(customerDto).hasNoNullFieldsOrProperties();
        assertThat(customerDto.getDocumentNumber()).isEqualTo(customer.getDocumentNumber());
        assertThat(customerDto.getName()).isEqualTo(customer.getName());
        assertThat(customerDto.getGender()).isEqualTo(customer.getGender());
        assertThat(customerDto.getDateOfBirth()).isEqualTo(customer.getDateOfBirth());
        assertThat(customerDto.getType()).isEqualTo(customer.getType());
        assertThat(customerDto.getEmail()).isEqualTo(customer.getEmail());
        assertThat(customerDto.getPhoneNumber()).isEqualTo(customer.getPhoneNumber());
    }

}