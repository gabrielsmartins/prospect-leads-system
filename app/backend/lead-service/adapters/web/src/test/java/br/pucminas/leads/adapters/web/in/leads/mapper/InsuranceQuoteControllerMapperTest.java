package br.pucminas.leads.adapters.web.in.leads.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.leads.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;
import static org.assertj.core.api.Assertions.assertThat;
class InsuranceQuoteControllerMapperTest {

    @Test
    @DisplayName("Given Insurance Quote When Map Then Return Insurance Quote Dto")
    public void givenInsuranceQuoteWhenMapThenReturnInsuranceQuoteDto() {
        var insuranceQuote = defaultInsuranceQuote().build();

        var insuranceQuoteDto = InsuranceQuoteControllerMapper.mapToDto(insuranceQuote);

        assertThat(insuranceQuoteDto).isNotNull();
        assertThat(insuranceQuoteDto).hasNoNullFieldsOrProperties();
        assertThat(insuranceQuoteDto.getId()).isEqualTo(insuranceQuote.getId());
        assertThat(insuranceQuoteDto.getType()).isEqualTo(insuranceQuote.getType());
        assertThat(insuranceQuoteDto.getProductId()).isEqualTo(insuranceQuote.getProductId());
        assertThat(insuranceQuoteDto.getTotalCoverageAmount()).isEqualTo(insuranceQuote.getTotalCoverageAmount());
        assertThat(insuranceQuoteDto.getTotalMonthlyPremiumAmount()).isEqualTo(insuranceQuote.getTotalMonthlyPremiumAmount());
        assertThat(insuranceQuoteDto.getAssistances()).isEqualTo(insuranceQuote.getAssistances());
        assertThat(insuranceQuoteDto.getCoverages()).isEqualTo(insuranceQuote.getCoverages());
        assertThat(insuranceQuoteDto.isFinished()).isEqualTo(insuranceQuote.isFinished());
        assertThat(insuranceQuoteDto.getFinishedAt()).isEqualTo(insuranceQuote.getFinishedAt());
        assertThat(insuranceQuoteDto.getCreatedAt()).isEqualTo(insuranceQuote.getCreatedAt());
        assertThat(insuranceQuoteDto.getUpdatedAt()).isEqualTo(insuranceQuote.getUpdatedAt());
        assertThat(insuranceQuoteDto.getFinishedAt()).isEqualTo(insuranceQuote.getFinishedAt());
    }

}