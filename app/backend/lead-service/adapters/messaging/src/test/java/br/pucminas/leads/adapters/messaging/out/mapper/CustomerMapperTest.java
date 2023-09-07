package br.pucminas.leads.adapters.messaging.out.mapper;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.leads.application.support.CustomerSupport.defaultCustomer;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerMapperTest {

    @Test
    @DisplayName("Given Customer When Map Then Return Customer Message")
    public void givenCustomerWhenMapThenReturnCustomerMessage() {
        var customer = defaultCustomer().build();
        var customerMessage = CustomerMapper.mapToMessage(customer);

        assertThat(customerMessage).isNotNull();
        assertThat(customerMessage).hasNoNullFieldsOrProperties();
        assertThat(customerMessage.getName()).isEqualTo(customer.getName());
        assertThat(customerMessage.getGender().name()).isEqualTo(customer.getGender());
        assertThat(customerMessage.getType().name()).isEqualTo(customer.getType());
        assertThat(customerMessage.getDateOfBirth()).isEqualTo(customer.getDateOfBirth());
        assertThat(customerMessage.getPhoneNumber()).isEqualTo(customer.getPhoneNumber());
        assertThat(customerMessage.getEmail()).isEqualTo(customer.getEmail());
    }

}