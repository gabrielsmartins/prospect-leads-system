package br.pucminas.bff.adapters.web.out.quotes.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.bff.adapters.web.out.support.InsuranceQuoteDtoSupport.defaultCreateInsuranceQuoteDto;
import static br.pucminas.bff.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;
import static org.assertj.core.api.Assertions.assertThat;

class CreateInsuranceQuoteWebAdapterMapperTest {

    @Test
    @DisplayName("Given Insurance Quote When Map Then Return Insurance Quote Dto")
    public void givenInsuranceQuoteWhenMapThenReturnInsuranceQuoteDto() {
        var insuranceQuote = defaultInsuranceQuote().build();

        var quoteDto = CreateInsuranceQuoteWebAdapterMapper.mapToDto(insuranceQuote);

        assertThat(quoteDto).isNotNull();
        assertThat(quoteDto).hasNoNullFieldsOrPropertiesExcept("id", "createdAt");
        assertThat(quoteDto.getType()).isEqualTo(insuranceQuote.getType());
        assertThat(quoteDto.getProductId()).isEqualTo(insuranceQuote.getProductId());
    }

    @Test
    @DisplayName("Given Insurance Quote Dto When Map Then Return Insurance Quote")
    public void givenInsuranceQuoteDtoWhenMapThenReturnInsuranceQuote() {
        var insuranceQuoteDto = defaultCreateInsuranceQuoteDto().build();

        var insuranceQuote = CreateInsuranceQuoteWebAdapterMapper.mapToDomain(insuranceQuoteDto);

        assertThat(insuranceQuote).isNotNull();
        assertThat(insuranceQuote).hasAllNullFieldsOrPropertiesExcept("id", "productId", "type", "createdAt", "customer", "coverages", "assistances", "finished");
        assertThat(insuranceQuote.getId()).isEqualTo(insuranceQuoteDto.getId());
        assertThat(insuranceQuote.getCreatedAt()).isEqualTo(insuranceQuoteDto.getCreatedAt());
        assertThat(insuranceQuote.getType()).isEqualTo(insuranceQuoteDto.getType());
        assertThat(insuranceQuote.getProductId()).isEqualTo(insuranceQuoteDto.getProductId());
    }

}