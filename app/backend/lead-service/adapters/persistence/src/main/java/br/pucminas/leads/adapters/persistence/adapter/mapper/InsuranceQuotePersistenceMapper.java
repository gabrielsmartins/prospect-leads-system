package br.pucminas.leads.adapters.persistence.adapter.mapper;

import br.pucminas.leads.adapters.persistence.entity.InsuranceQuoteEntity;
import br.pucminas.leads.application.domain.InsuranceQuote;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InsuranceQuotePersistenceMapper {

    public static InsuranceQuoteEntity mapToEntity(InsuranceQuote insuranceQuote) {
        if (insuranceQuote == null) {
            return null;
        }
        var customerEntity = CustomerPersistenceMapper.mapToEntity(insuranceQuote.getCustomer());
        return InsuranceQuoteEntity.builder()
                                   .withId(insuranceQuote.getId())
                                   .withType(insuranceQuote.getType())
                                   .withTotalMonthlyPremiumAmount(insuranceQuote.getTotalMonthlyPremiumAmount())
                                   .withTotalCoverageAmount(insuranceQuote.getTotalCoverageAmount())
                                   .withCoverages(insuranceQuote.getCoverages())
                                   .withAssistances(insuranceQuote.getAssistances())
                                   .withProductId(insuranceQuote.getProductId())
                                   .withProduct(ProductPersistenceMapper.mapToEntity(insuranceQuote.getProduct()))
                                   .withFinished(insuranceQuote.isFinished())
                                   .withCreatedAt(insuranceQuote.getCreatedAt())
                                   .withUpdatedAt(insuranceQuote.getUpdatedAt())
                                   .withFinishedAt(insuranceQuote.getFinishedAt())
                                   .withCustomer(customerEntity)
                                   .build();
    }

    public static InsuranceQuote mapToDomain(InsuranceQuoteEntity insuranceQuoteEntity) {
        if (insuranceQuoteEntity == null) {
            return null;
        }
        var customer = CustomerPersistenceMapper.mapToDomain(insuranceQuoteEntity.getCustomer());
        return InsuranceQuote.builder()
                            .withId(insuranceQuoteEntity.getId())
                            .withType(insuranceQuoteEntity.getType())
                            .withTotalMonthlyPremiumAmount(insuranceQuoteEntity.getTotalMonthlyPremiumAmount())
                            .withTotalCoverageAmount(insuranceQuoteEntity.getTotalCoverageAmount())
                            .withCoverages(insuranceQuoteEntity.getCoverages())
                            .withAssistances(insuranceQuoteEntity.getAssistances())
                            .withProductId(insuranceQuoteEntity.getProductId())
                            .withProduct(ProductPersistenceMapper.mapToDomain(insuranceQuoteEntity.getProduct()))
                            .withFinished(insuranceQuoteEntity.getFinished())
                            .withCreatedAt(insuranceQuoteEntity.getCreatedAt())
                            .withUpdatedAt(insuranceQuoteEntity.getUpdatedAt())
                            .withFinishedAt(insuranceQuoteEntity.getFinishedAt())
                            .withCustomer(customer)
                            .build();
    }

}
