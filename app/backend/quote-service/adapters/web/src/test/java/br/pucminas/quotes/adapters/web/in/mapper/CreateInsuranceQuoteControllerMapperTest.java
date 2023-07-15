package br.pucminas.quotes.adapters.web.in.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.quotes.adapters.web.in.support.InsuranceQuoteDtoSupport.defaultCreateInsuranceQuoteDto;
import static br.pucminas.quotes.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;
import static org.assertj.core.api.Assertions.assertThat;

class CreateInsuranceQuoteControllerMapperTest {

    @Test
    @DisplayName("Given Quote When Map Then Return Quote Dto")
    public void givenQuoteWhenMapThenReturnQuoteDto() {

        var quote = defaultInsuranceQuote().build();

        var quoteDto = CreateInsuranceQuoteControllerMapper.mapToDto(quote);

        assertThat(quoteDto).isNotNull();
        assertThat(quoteDto).hasNoNullFieldsOrProperties();
        assertThat(quoteDto.getId()).isEqualTo(quote.getId());
        assertThat(quoteDto.getType().getCode()).isEqualTo(quote.getType().getCode());
        assertThat(quoteDto.getCustomer()).isNotNull();
        assertThat(quoteDto.getProductId()).isEqualTo(quote.getProductId());
        assertThat(quoteDto.getCreatedAt()).isEqualTo(quote.getCreatedAt());
    }

    @Test
    @DisplayName("Given Quote Dto When Map Then Return Quote")
    public void givenQuoteDtoWhenMapThenReturnQuote() {

        var quoteDto = defaultCreateInsuranceQuoteDto().build();

        var quote = CreateInsuranceQuoteControllerMapper.mapToDomain(quoteDto);

        assertThat(quote).isNotNull();
        assertThat(quote).hasNoNullFieldsOrPropertiesExcept("coverages", "assistances", "totalYearlyPremiumAmount", "totalMonthlyPremiumAmount", "totalCoverageAmount", "createdAt", "updatedAt", "finishedAt");
        assertThat(quote.getId()).isEqualTo(quoteDto.getId());
        assertThat(quote.getType().getCode()).isEqualTo(quoteDto.getType().getCode());
        assertThat(quote.getCustomer()).isNotNull();
        assertThat(quote.getProductId()).isEqualTo(quoteDto.getProductId());
        assertThat(quote.getTotalCoverageAmount()).isNull();;
    }

}