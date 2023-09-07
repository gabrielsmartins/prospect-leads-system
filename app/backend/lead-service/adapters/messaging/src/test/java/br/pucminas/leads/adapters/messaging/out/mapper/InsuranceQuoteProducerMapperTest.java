package br.pucminas.leads.adapters.messaging.out.mapper;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.leads.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;
import static org.assertj.core.api.Assertions.assertThat;

class InsuranceQuoteProducerMapperTest {

    @Test
    @DisplayName("Given Insurance Quote When Map Then Return Insurance Quote Message")
    public void givenInsuranceQuoteWhenMapThenReturnInsuranceQuoteMessage() {
        var insuranceQuote = defaultInsuranceQuote().build();

        var insuranceQuoteMessage = InsuranceQuoteProducerMapper.mapToMessage(insuranceQuote);

        assertThat(insuranceQuoteMessage).isNotNull();
        assertThat(insuranceQuoteMessage).hasNoNullFieldsOrProperties();
        assertThat(insuranceQuoteMessage.getId()).isEqualTo(insuranceQuote.getId());
        assertThat(insuranceQuoteMessage.getType().name()).isEqualTo(insuranceQuote.getType());
        assertThat(insuranceQuoteMessage.getTotalMonthlyPremiumAmount()).isEqualTo(insuranceQuote.getTotalMonthlyPremiumAmount());
        assertThat(insuranceQuoteMessage.getTotalCoverageAmount()).isEqualTo(insuranceQuote.getTotalCoverageAmount());
        assertThat(insuranceQuoteMessage.getCoverages()).isEqualTo(insuranceQuote.getCoverages());
        assertThat(insuranceQuoteMessage.getAssistances()).isEqualTo(insuranceQuote.getAssistances());
        assertThat(insuranceQuoteMessage.getFinished()).isEqualTo(insuranceQuote.isFinished());
        assertThat(insuranceQuoteMessage.getCreatedAt()).isEqualTo(insuranceQuote.getCreatedAt());
        assertThat(insuranceQuoteMessage.getUpdatedAt()).isEqualTo(insuranceQuote.getUpdatedAt());
        assertThat(insuranceQuoteMessage.getFinishedAt()).isEqualTo(insuranceQuote.getFinishedAt());
    }

}