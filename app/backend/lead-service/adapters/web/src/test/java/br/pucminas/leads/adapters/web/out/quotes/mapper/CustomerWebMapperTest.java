package br.pucminas.leads.adapters.web.out.quotes.mapper;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.leads.adapters.web.support.CustomerDtoSupport.defaultCustomerDto;
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

}