package br.pucminas.notifications.adapters.messaging.in.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.notifications.adapters.messaging.in.support.LeadProcessedMessageSupport.defaultCustomerMessage;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerConsumerMapperTest {

    @Test
    @DisplayName("Given Customer Message When Map Then Return Customer")
    public void givenCustomerMessageWhenMapThenReturnCustomer() {
        var customerMessage = defaultCustomerMessage().build();
        var customer = CustomerConsumerMapper.mapToDomain(customerMessage);

        assertThat(customer).isNotNull();
        assertThat(customer).hasNoNullFieldsOrProperties();
        assertThat(customer.getName()).isEqualTo(customerMessage.getName());
        assertThat(customer.getType()).isEqualTo(customerMessage.getType().name());
        assertThat(customer.getGender()).isEqualTo(customerMessage.getGender().name());
        assertThat(customer.getDocumentNumber()).isEqualTo(customerMessage.getDocumentNumber());
        assertThat(customer.getDateOfBirth()).isEqualTo(customerMessage.getDateOfBirth());
        assertThat(customer.getPhoneNumber()).isEqualTo(customerMessage.getPhoneNumber());
        assertThat(customer.getEmail()).isEqualTo(customerMessage.getEmail());
    }

}