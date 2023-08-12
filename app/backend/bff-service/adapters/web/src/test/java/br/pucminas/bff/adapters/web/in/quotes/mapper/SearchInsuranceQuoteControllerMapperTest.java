package br.pucminas.bff.adapters.web.in.quotes.mapper;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.bff.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;
import static org.assertj.core.api.Assertions.assertThat;

class SearchInsuranceQuoteControllerMapperTest {

    @Test
    @DisplayName("Given Quote When Map Then Return Quote Dto")
    public void givenQuoteWhenMapThenReturnQuoteDto() {
        var quote = defaultInsuranceQuote().build();
        var quoteDto = SearchInsuranceQuoteControllerMapper.mapToDto(quote);

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
        assertThat(quoteDto.getTotalMonthlyPremiumAmount()).isEqualByComparingTo(quote.getTotalMonthlyPremiumAmount());
        assertThat(quoteDto.getTotalCoverageAmount()).isEqualByComparingTo(quote.getTotalCoverageAmount());
        assertThat(quoteDto.getCoverages()).isEqualTo(quote.getCoverages());
        assertThat(quoteDto.getAssistances()).isEqualTo(quote.getAssistances());
    }

}