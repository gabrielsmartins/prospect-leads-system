package br.pucminas.leads.adapters.web.in.leads.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.leads.application.support.CustomerSupport.defaultCustomer;
import static org.assertj.core.api.Assertions.assertThat;
class CustomerControllerMapperTest {

    @Test
    @DisplayName("Given Customer When Map Then Return Customer Dto")
    public void givenCustomerWhenMapThenReturnCustomerDto() {
        var customer = defaultCustomer().build();

        var customerDto = CustomerControllerMapper.mapToDto(customer);

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