package br.pucminas.quotes.adapters.persistence.adapter.mapper;

import br.pucminas.quotes.adapters.persistence.entity.InsuranceQuoteEntity;
import br.pucminas.quotes.application.domain.InsuranceQuote;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InsuranceQuotePersistenceMapper {

    public static InsuranceQuoteEntity mapToEntity(InsuranceQuote insuranceQuote) {
        if (insuranceQuote == null) {
            return null;
        }
        return InsuranceQuoteEntity.builder()
                                    .withId(insuranceQuote.getId())
                                    .withType(insuranceQuote.getType())
                                    .withCustomer(CustomerPersistenceMapper.mapToEntity(insuranceQuote.getCustomer()))
                                    .withProductId(insuranceQuote.getProductId())
                                    .withTotalMonthlyPremiumAmount(insuranceQuote.getTotalMonthlyPremiumAmount())
                                    .withTotalCoverageAmount(insuranceQuote.getTotalCoverageAmount())
                                    .withCoverages(insuranceQuote.getCoverages())
                                    .withAssistances(insuranceQuote.getAssistances())
                                    .withFinished(insuranceQuote.isFinished())
                                    .withCreatedAt(insuranceQuote.getCreatedAt())
                                    .withUpdatedAt(insuranceQuote.getUpdatedAt())
                                    .withFinishedAt(insuranceQuote.getFinishedAt())
                                    .build();
    }

    public static InsuranceQuote mapToDomain(InsuranceQuoteEntity insuranceQuoteEntity) {
        if (insuranceQuoteEntity == null) {
            return null;
        }
        var insuranceQuote = InsuranceQuote.builder()
                                            .withId(insuranceQuoteEntity.getId())
                                            .withType(insuranceQuoteEntity.getType())
                                            .withCustomer(CustomerPersistenceMapper.mapToDomain(insuranceQuoteEntity.getCustomer()))
                                            .withProductId(insuranceQuoteEntity.getProductId())
                                            .withTotalMonthlyPremiumAmount(insuranceQuoteEntity.getTotalMonthlyPremiumAmount())
                                            .withTotalCoverageAmount(insuranceQuoteEntity.getTotalCoverageAmount())
                                            .withFinished(insuranceQuoteEntity.isFinished())
                                            .withCreatedAt(insuranceQuoteEntity.getCreatedAt())
                                            .withUpdatedAt(insuranceQuoteEntity.getUpdatedAt())
                                            .withFinishedAt(insuranceQuoteEntity.getFinishedAt())
                                            .build();
        insuranceQuoteEntity.getCoverages().forEach(insuranceQuote::addCoverage);
        insuranceQuoteEntity.getAssistances().forEach(insuranceQuote::addAssistance);
        return insuranceQuote;
    }

}
