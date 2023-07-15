package br.pucminas.quotes.adapters.persistence.adapter.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.quotes.adapters.persistence.support.InsuranceQuoteEntitySupport.defaultInsuranceQuoteEntity;
import static br.pucminas.quotes.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;
import static org.assertj.core.api.Assertions.assertThat;

class InsuranceQuotePersistenceMapperTest {

    @Test
    @DisplayName("Given Insurance Quote Entity When Map Then Return Insurance Quote")
    public void givenInsuranceQuoteEntityWhenMapThenReturnInsuranceQuote() {
        var insuranceQuoteEntity = defaultInsuranceQuoteEntity().build();
        var insuranceQuote = InsuranceQuotePersistenceMapper.mapToDomain(insuranceQuoteEntity);

        assertThat(insuranceQuote).isNotNull();
        assertThat(insuranceQuote).hasNoNullFieldsOrProperties();
        assertThat(insuranceQuote.getId()).isEqualTo(insuranceQuoteEntity.getId());
        assertThat(insuranceQuote.getType()).isEqualTo(insuranceQuoteEntity.getType());
        assertThat(insuranceQuote.getCustomer()).isNotNull();
        assertThat(insuranceQuote.getProductId()).isEqualTo(insuranceQuoteEntity.getProductId());
        assertThat(insuranceQuote.getTotalYearlyPremiumAmount()).isEqualTo(insuranceQuoteEntity.getTotalYearlyPremiumAmount());
        assertThat(insuranceQuote.getTotalMonthlyPremiumAmount()).isEqualTo(insuranceQuoteEntity.getTotalMonthlyPremiumAmount());
        assertThat(insuranceQuote.getTotalCoverageAmount()).isNotNull();
        assertThat(insuranceQuote.getCoverages()).isEqualTo(insuranceQuoteEntity.getCoverages());
        assertThat(insuranceQuote.getAssistances()).isEqualTo(insuranceQuoteEntity.getAssistances());
        assertThat(insuranceQuote.isFinished()).isEqualTo(insuranceQuoteEntity.isFinished());
        assertThat(insuranceQuote.getCreatedAt()).isEqualTo(insuranceQuoteEntity.getCreatedAt());
        assertThat(insuranceQuote.getUpdatedAt()).isEqualTo(insuranceQuoteEntity.getUpdatedAt());
        assertThat(insuranceQuote.getFinishedAt()).isEqualTo(insuranceQuoteEntity.getFinishedAt());
    }

    @Test
    @DisplayName("Given Insurance Quote When Map Then Return Insurance Quote Entity")
    public void givenInsuranceQuoteWhenMapThenReturnInsuranceQuoteEntity() {
        var insuranceQuote = defaultInsuranceQuote().build();
        var insuranceQuoteEntity = InsuranceQuotePersistenceMapper.mapToEntity(insuranceQuote);

        assertThat(insuranceQuoteEntity).isNotNull();
        assertThat(insuranceQuoteEntity).hasNoNullFieldsOrProperties();
        assertThat(insuranceQuoteEntity.getId()).isEqualTo(insuranceQuote.getId());
        assertThat(insuranceQuoteEntity.getType()).isEqualTo(insuranceQuote.getType());
        assertThat(insuranceQuoteEntity.getCustomer()).isNotNull();
        assertThat(insuranceQuoteEntity.getProductId()).isEqualTo(insuranceQuote.getProductId());
        assertThat(insuranceQuoteEntity.getTotalYearlyPremiumAmount()).isEqualTo(insuranceQuote.getTotalYearlyPremiumAmount());
        assertThat(insuranceQuoteEntity.getTotalMonthlyPremiumAmount()).isEqualTo(insuranceQuote.getTotalMonthlyPremiumAmount());
        assertThat(insuranceQuoteEntity.getTotalCoverageAmount()).isEqualTo(insuranceQuote.getTotalCoverageAmount());
        assertThat(insuranceQuoteEntity.getCoverages()).isEqualTo(insuranceQuote.getCoverages());
        assertThat(insuranceQuoteEntity.getAssistances()).isEqualTo(insuranceQuote.getAssistances());
        assertThat(insuranceQuoteEntity.isFinished()).isEqualTo(insuranceQuote.isFinished());
        assertThat(insuranceQuoteEntity.getCreatedAt()).isEqualTo(insuranceQuote.getCreatedAt());
        assertThat(insuranceQuoteEntity.getUpdatedAt()).isEqualTo(insuranceQuote.getUpdatedAt());
        assertThat(insuranceQuoteEntity.getFinishedAt()).isEqualTo(insuranceQuote.getFinishedAt());
    }

}