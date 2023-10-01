package br.pucminas.notifications.adapters.messaging.in.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.notifications.adapters.messaging.in.support.LeadProcessedMessageSupport.defaultInsuranceQuoteMessage;
import static org.assertj.core.api.Assertions.assertThat;

class InsuranceQuoteConsumerMapperTest {

    @Test
    @DisplayName("Given Insurance Quote Message When Map Then Return Insurance Quote")
    public void givenInsuranceQuoteMessageWhenMapThenReturnInsuranceQuote() {
        var insuranceQuoteMessage = defaultInsuranceQuoteMessage().build();
        var insuranceQuote = InsuranceQuoteConsumerMapper.mapToDomain(insuranceQuoteMessage);

        assertThat(insuranceQuote).isNotNull();
        assertThat(insuranceQuote).hasNoNullFieldsOrProperties();
        assertThat(insuranceQuote.getId()).isEqualTo(insuranceQuoteMessage.getId());
        assertThat(insuranceQuote.getType()).isEqualTo(insuranceQuoteMessage.getType().name());
        assertThat(insuranceQuote.isFinished()).isEqualTo(insuranceQuoteMessage.getFinished());
        assertThat(insuranceQuote.getCoverages()).isEqualTo(insuranceQuoteMessage.getCoverages());
        assertThat(insuranceQuote.getAssistances()).isEqualTo(insuranceQuoteMessage.getAssistances());
        assertThat(insuranceQuote.getTotalCoverageAmount()).isEqualTo(insuranceQuoteMessage.getTotalCoverageAmount());
        assertThat(insuranceQuote.getTotalMonthlyPremiumAmount()).isEqualTo(insuranceQuoteMessage.getTotalMonthlyPremiumAmount());
        assertThat(insuranceQuote.getCreatedAt()).isEqualTo(insuranceQuoteMessage.getCreatedAt());
        assertThat(insuranceQuote.getUpdatedAt()).isEqualTo(insuranceQuoteMessage.getUpdatedAt());
        assertThat(insuranceQuote.getFinishedAt()).isEqualTo(insuranceQuoteMessage.getFinishedAt());
    }

}