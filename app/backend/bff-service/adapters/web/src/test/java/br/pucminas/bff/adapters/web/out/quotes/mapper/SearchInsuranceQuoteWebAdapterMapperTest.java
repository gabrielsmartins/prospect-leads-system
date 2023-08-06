package br.pucminas.bff.adapters.web.out.quotes.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.bff.adapters.web.out.support.InsuranceQuoteDtoSupport.defaultSearchInsuranceQuoteDto;
import static org.assertj.core.api.Assertions.assertThat;

class SearchInsuranceQuoteWebAdapterMapperTest {

    @Test
    @DisplayName("Given Insurance Quote Dto When Map Then Return Insurance Quote")
    public void givenInsuranceQuoteDtoWhenMapThenReturnInsuranceQuote() {
        var insuranceQuoteDto = defaultSearchInsuranceQuoteDto().build();

        var insuranceQuote = SearchInsuranceQuoteWebAdapterMapper.mapToDomain(insuranceQuoteDto);

        assertThat(insuranceQuote).isNotNull();
        assertThat(insuranceQuote).hasNoNullFieldsOrProperties();
        assertThat(insuranceQuote.getId()).isEqualTo(insuranceQuoteDto.getId());
        assertThat(insuranceQuote.getType()).isEqualTo(insuranceQuoteDto.getType());
        assertThat(insuranceQuote.getProductId()).isEqualTo(insuranceQuoteDto.getProductId());
        assertThat(insuranceQuote.getTotalCoverageAmount()).isEqualTo(insuranceQuoteDto.getTotalCoverageAmount());
        assertThat(insuranceQuote.getTotalMonthlyPremiumAmount()).isEqualTo(insuranceQuoteDto.getTotalMonthlyPremiumAmount());
        assertThat(insuranceQuote.getTotalYearlyPremiumAmount()).isEqualTo(insuranceQuoteDto.getTotalYearlyPremiumAmount());
        assertThat(insuranceQuote.getAssistances()).isEqualTo(insuranceQuoteDto.getAssistances());
        assertThat(insuranceQuote.getCoverages()).isEqualTo(insuranceQuoteDto.getCoverages());
        assertThat(insuranceQuote.isFinished()).isEqualTo(insuranceQuoteDto.isFinished());
        assertThat(insuranceQuote.getFinishedAt()).isEqualTo(insuranceQuoteDto.getFinishedAt());
        assertThat(insuranceQuote.getCreatedAt()).isEqualTo(insuranceQuoteDto.getCreatedAt());
        assertThat(insuranceQuote.getUpdatedAt()).isEqualTo(insuranceQuoteDto.getUpdatedAt());
        assertThat(insuranceQuote.getFinishedAt()).isEqualTo(insuranceQuoteDto.getFinishedAt());
    }

}