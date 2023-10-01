package br.pucminas.notifications.adapters.messaging.in.mapper;

import br.pucminas.notifications.application.domain.InsuranceQuote;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InsuranceQuoteConsumerMapper {

    public static InsuranceQuote mapToDomain(br.pucminas.leads.schemas.lead_processed.InsuranceQuote insuranceQuoteMessage) {
        if (insuranceQuoteMessage == null) {
            return null;
        }
        return InsuranceQuote.builder()
                            .withId(insuranceQuoteMessage.getId())
                            .withType(insuranceQuoteMessage.getType().name())
                            .withFinished(insuranceQuoteMessage.getFinished())
                            .withAssistances(insuranceQuoteMessage.getAssistances())
                            .withCoverages(insuranceQuoteMessage.getCoverages())
                            .withProduct(ProductConsumerMapper.mapToDomain(insuranceQuoteMessage.getProduct()))
                            .withCustomer(CustomerConsumerMapper.mapToDomain(insuranceQuoteMessage.getCustomer()))
                            .withTotalCoverageAmount(insuranceQuoteMessage.getTotalCoverageAmount())
                            .withTotalMonthlyPremiumAmount(insuranceQuoteMessage.getTotalMonthlyPremiumAmount())
                            .withCreatedAt(insuranceQuoteMessage.getCreatedAt())
                            .withUpdatedAt(insuranceQuoteMessage.getUpdatedAt())
                            .withFinishedAt(insuranceQuoteMessage.getFinishedAt())
                            .build();
    }

}
