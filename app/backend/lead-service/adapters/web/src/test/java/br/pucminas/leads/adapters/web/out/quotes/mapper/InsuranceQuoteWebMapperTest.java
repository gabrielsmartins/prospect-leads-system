package br.pucminas.leads.adapters.web.out.quotes.mapper;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.leads.adapters.web.support.InsuranceQuoteDtoSupport.defaultSearchInsuranceQuoteDto;
import static br.pucminas.leads.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;
import static org.assertj.core.api.Assertions.assertThat;

class InsuranceQuoteWebMapperTest {

    @Test
    @DisplayName("Given Insurance Quote Dto When Map Then Return Insurance Quote")
    public void givenInsuranceQuoteDtoWhenMapThenReturnInsuranceQuote() {
        var insuranceQuoteDto = defaultSearchInsuranceQuoteDto().build();

        var insuranceQuote = InsuranceQuoteWebMapper.mapToDomain(insuranceQuoteDto);

        assertThat(insuranceQuote).isNotNull();
        assertThat(insuranceQuote).hasNoNullFieldsOrPropertiesExcept("product");
        assertThat(insuranceQuote.getId()).isEqualTo(insuranceQuoteDto.getId());
        assertThat(insuranceQuote.getType()).isEqualTo(insuranceQuoteDto.getType());
        assertThat(insuranceQuote.getProductId()).isEqualTo(insuranceQuoteDto.getProductId());
        assertThat(insuranceQuote.getTotalCoverageAmount()).isEqualTo(insuranceQuoteDto.getTotalCoverageAmount());
        assertThat(insuranceQuote.getTotalMonthlyPremiumAmount()).isEqualTo(insuranceQuoteDto.getTotalMonthlyPremiumAmount());
        assertThat(insuranceQuote.getAssistances()).isEqualTo(insuranceQuoteDto.getAssistances());
        assertThat(insuranceQuote.getCoverages()).isEqualTo(insuranceQuoteDto.getCoverages());
        assertThat(insuranceQuote.isFinished()).isEqualTo(insuranceQuoteDto.isFinished());
        assertThat(insuranceQuote.getFinishedAt()).isEqualTo(insuranceQuoteDto.getFinishedAt());
        assertThat(insuranceQuote.getCreatedAt()).isEqualTo(insuranceQuoteDto.getCreatedAt());
        assertThat(insuranceQuote.getUpdatedAt()).isEqualTo(insuranceQuoteDto.getUpdatedAt());
        assertThat(insuranceQuote.getFinishedAt()).isEqualTo(insuranceQuoteDto.getFinishedAt());
    }

    @Test
    @DisplayName("Given Insurance Quote When Map Then Return Insurance Quote Dto")
    public void givenInsuranceQuoteWhenMapThenReturnInsuranceQuoteDto() {
        var insuranceQuote = defaultInsuranceQuote().build();

        var insuranceQuoteDto = InsuranceQuoteWebMapper.mapToDto(insuranceQuote);

        assertThat(insuranceQuoteDto).isNotNull();
        assertThat(insuranceQuoteDto).hasNoNullFieldsOrPropertiesExcept("product");
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