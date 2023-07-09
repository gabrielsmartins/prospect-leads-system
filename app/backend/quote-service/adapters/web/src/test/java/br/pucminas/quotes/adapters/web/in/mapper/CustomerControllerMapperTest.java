package br.pucminas.quotes.adapters.web.in.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.quotes.adapters.web.in.support.CustomerDtoSupport.defaultCustomerDto;
import static br.pucminas.quotes.application.support.CustomerSupport.defaultCustomer;
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
        assertThat(customer.getGender().getCode()).isEqualTo(customerDto.getGender().getCode());
        assertThat(customer.getDateOfBirth()).isEqualTo(customerDto.getDateOfBirth());
        assertThat(customer.getType().getCode()).isEqualTo(customerDto.getType().getCode());
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
        assertThat(customerDto.getGender().getCode()).isEqualTo(customer.getGender().getCode());
        assertThat(customerDto.getDateOfBirth()).isEqualTo(customer.getDateOfBirth());
        assertThat(customerDto.getType().getCode()).isEqualTo(customer.getType().getCode());
    }

}