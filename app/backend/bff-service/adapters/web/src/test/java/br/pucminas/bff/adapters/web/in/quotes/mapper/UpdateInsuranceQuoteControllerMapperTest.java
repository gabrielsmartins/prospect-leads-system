package br.pucminas.bff.adapters.web.in.quotes.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.bff.adapters.web.in.support.InsuranceQuoteDtoSupport.defaultUpdateInsuranceQuoteDto;
import static br.pucminas.bff.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;
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
        assertThat(quoteDto.getType()).isEqualTo(quote.getType());
        assertThat(quoteDto.getCustomer()).isNotNull();
        assertThat(quoteDto.getProductId()).isEqualTo(quote.getProductId());
        assertThat(quoteDto.isFinished()).isEqualTo(quote.isFinished());
        assertThat(quoteDto.getCreatedAt()).isEqualTo(quote.getCreatedAt());
        assertThat(quoteDto.getUpdatedAt()).isEqualTo(quote.getUpdatedAt());
        assertThat(quoteDto.getFinishedAt()).isEqualTo(quote.getFinishedAt());
    }

    @Test
    @DisplayName("Given Quote Dto When Map Then Return Quote")
    public void givenQuoteDtoWhenMapThenReturnQuote() {

        var quoteDto = defaultUpdateInsuranceQuoteDto().build();

        var quote = UpdateInsuranceQuoteControllerMapper.mapToDomain(quoteDto);

        assertThat(quote).isNotNull();
        assertThat(quote).hasNoNullFieldsOrPropertiesExcept("totalYearlyPremiumAmount", "totalMonthlyPremiumAmount", "totalCoverageAmount", "createdAt", "updatedAt", "finishedAt");
        assertThat(quote.getId()).isEqualTo(quoteDto.getId());
        assertThat(quote.getType()).isEqualTo(quoteDto.getType());
        assertThat(quote.getCustomer()).isNotNull();
        assertThat(quote.getProductId()).isEqualTo(quoteDto.getProductId());
        assertThat(quote.isFinished()).isEqualTo(quoteDto.isFinished());
    }

}