package br.pucminas.quotes.adapters.web.in.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.quotes.adapters.web.in.support.InsuranceQuoteDtoSupport.defaultUpdateInsuranceQuoteDto;
import static br.pucminas.quotes.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;
import static org.assertj.core.api.Assertions.assertThat;

class UpdateInsuranceQuoteControllerMapperTest {

    @Test
    @DisplayName("Given Quote When Map Then Return Quote Dto")
    public void givenQuoteWhenMapThenReturnQuoteDto() {

        var quote = defaultInsuranceQuote().build();

        var quoteDto = UpdateInsuranceQuoteControllerMapper.mapToDto(quote);

        assertThat(quoteDto).isNotNull();
        assertThat(quoteDto).hasNoNullFieldsOrProperties();
        assertThat(quoteDto.getId()).isEqualTo(quote.getId());
        assertThat(quoteDto.getType().getCode()).isEqualTo(quote.getType().getCode());
        assertThat(quoteDto.getCustomer()).isNotNull();
        assertThat(quoteDto.getProductId()).isEqualTo(quote.getProductId());
        assertThat(quoteDto.isFinished()).isEqualTo(quote.isFinished());
        assertThat(quoteDto.getCreatedAt()).isEqualTo(quote.getCreatedAt());
        assertThat(quoteDto.getUpdatedAt()).isEqualTo(quote.getUpdatedAt());
        assertThat(quoteDto.getFinishedAt()).isEqualTo(quote.getFinishedAt());
        assertThat(quoteDto.getTotalYearlyPremiumAmount()).isEqualByComparingTo(quote.getTotalYearlyPremiumAmount());
        assertThat(quoteDto.getTotalMonthlyPremiumAmount()).isEqualByComparingTo(quote.getTotalMonthlyPremiumAmount());
        assertThat(quoteDto.getTotalCoverageAmount()).isEqualByComparingTo(quote.getTotalCoverageAmount());
        assertThat(quoteDto.getCoverages()).isEqualTo(quote.getCoverages());
        assertThat(quoteDto.getAssistances()).isEqualTo(quote.getAssistances());
    }

    @Test
    @DisplayName("Given Quote Dto When Map Then Return Quote")
    public void givenQuoteDtoWhenMapThenReturnQuote() {

        var quoteDto = defaultUpdateInsuranceQuoteDto().build();

        var quote = UpdateInsuranceQuoteControllerMapper.mapToDomain(quoteDto);

        assertThat(quote).isNotNull();
        assertThat(quote).hasNoNullFieldsOrPropertiesExcept("createdAt", "updatedAt", "finishedAt");
        assertThat(quote.getId()).isEqualTo(quoteDto.getId());
        assertThat(quote.getType().getCode()).isEqualTo(quoteDto.getType().getCode());
        assertThat(quote.getCustomer()).isNotNull();
        assertThat(quote.getProductId()).isEqualTo(quoteDto.getProductId());
        assertThat(quote.isFinished()).isEqualTo(quoteDto.isFinished());
        assertThat(quote.getTotalYearlyPremiumAmount()).isEqualByComparingTo(quoteDto.getTotalYearlyPremiumAmount());
        assertThat(quote.getTotalMonthlyPremiumAmount()).isEqualByComparingTo(quoteDto.getTotalMonthlyPremiumAmount());
        assertThat(quote.getTotalCoverageAmount()).isNotZero();
        assertThat(quote.getCoverages()).isEqualTo(quoteDto.getCoverages());
        assertThat(quote.getAssistances()).isEqualTo(quoteDto.getAssistances());
    }

}