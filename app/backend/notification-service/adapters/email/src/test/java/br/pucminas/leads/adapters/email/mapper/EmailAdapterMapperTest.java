package br.pucminas.leads.adapters.email.mapper;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.leads.adapters.email.mapper.EmailAdapterMapper.FROM_EMAIL;
import static br.pucminas.leads.application.support.NotificationSupport.defaultNotification;
import static org.assertj.core.api.Assertions.assertThat;

class EmailAdapterMapperTest {

    @Test
    @DisplayName("Given Notification When Map Then Return Email")
    public void givenNotificationWhenMapThenReturnEmail() {
        var notification = defaultNotification().build();
        var emailDto = EmailAdapterMapper.mapToEmail(notification);

        var insuranceQuote = notification.getInsuranceQuote();
        var customer = insuranceQuote.getCustomer();
        var product = insuranceQuote.getProduct();

        assertThat(emailDto).isNotNull();
        assertThat(emailDto).hasNoNullFieldsOrProperties();
        assertThat(emailDto.getFromEmail()).isEqualTo(FROM_EMAIL);
        assertThat(emailDto.getToEmail()).isEqualTo(customer.getEmail());
        assertThat(emailDto.getCustomerName()).isEqualTo(customer.getName());
        assertThat(emailDto.getProductName()).isEqualTo(product.getName());
        assertThat(emailDto.getTotalMonthlyPremiumAmount()).isEqualTo(insuranceQuote.getTotalMonthlyPremiumAmount());
        assertThat(emailDto.getTotalCoverageAmount()).isEqualTo(insuranceQuote.getTotalCoverageAmount());
        assertThat(emailDto.getCoverages()).isEqualTo(insuranceQuote.getCoverages());
        assertThat(emailDto.getAssistances()).isEqualTo(insuranceQuote.getAssistances());
    }

}