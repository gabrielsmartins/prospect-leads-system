package br.pucminas.bff.adapters.web.out.quotes.mapper;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.bff.adapters.web.out.support.InsuranceQuoteDtoSupport.defaultUpdateInsuranceQuoteDto;
import static br.pucminas.bff.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;
import static org.assertj.core.api.Assertions.assertThat;

class UpdateInsuranceQuoteWebAdapterMapperTest {

    @Test
    @DisplayName("Given Insurance Quote When Map Then Return Insurance Quote Dto")
    public void givenInsuranceQuoteWhenMapThenReturnInsuranceQuoteDto() {
        var insuranceQuote = defaultInsuranceQuote().build();

        var quoteDto = UpdateInsuranceQuoteWebAdapterMapper.mapToDto(insuranceQuote);

        assertThat(quoteDto).isNotNull();
        assertThat(quoteDto).hasNoNullFieldsOrPropertiesExcept("id", "createdAt", "updatedAt", "finishedAt");
        assertThat(quoteDto.getId()).isEqualTo(insuranceQuote.getId());
        assertThat(quoteDto.getType()).isEqualTo(insuranceQuote.getType());
        assertThat(quoteDto.isFinished()).isEqualTo(insuranceQuote.isFinished());
        assertThat(quoteDto.getProductId()).isEqualTo(insuranceQuote.getProductId());
    }

    @Test
    @DisplayName("Given Insurance Quote Dto When Map Then Return Insurance Quote")
    public void givenInsuranceQuoteDtoWhenMapThenReturnInsuranceQuote() {
        var insuranceQuoteDto = defaultUpdateInsuranceQuoteDto().build();

        var insuranceQuote = UpdateInsuranceQuoteWebAdapterMapper.mapToDomain(insuranceQuoteDto);

        assertThat(insuranceQuote).isNotNull();
        assertThat(insuranceQuote).hasAllNullFieldsOrPropertiesExcept("id", "productId", "type", "createdAt", "updatedAt", "finishedAt", "finished", "customer", "coverages", "assistances");
        assertThat(insuranceQuote.getId()).isEqualTo(insuranceQuoteDto.getId());
        assertThat(insuranceQuote.getType()).isEqualTo(insuranceQuoteDto.getType());
        assertThat(insuranceQuote.getProductId()).isEqualTo(insuranceQuoteDto.getProductId());
        assertThat(insuranceQuote.isFinished()).isEqualTo(insuranceQuoteDto.isFinished());
        assertThat(insuranceQuote.getCreatedAt()).isEqualTo(insuranceQuoteDto.getCreatedAt());
        assertThat(insuranceQuote.getUpdatedAt()).isEqualTo(insuranceQuoteDto.getUpdatedAt());
        assertThat(insuranceQuote.getFinishedAt()).isEqualTo(insuranceQuoteDto.getFinishedAt());
    }

}