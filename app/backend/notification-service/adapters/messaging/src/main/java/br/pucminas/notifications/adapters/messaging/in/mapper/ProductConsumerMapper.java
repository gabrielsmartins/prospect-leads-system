package br.pucminas.notifications.adapters.messaging.in.mapper;

import br.pucminas.notifications.application.domain.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductConsumerMapper {

    public static Product mapToDomain(br.pucminas.leads.schemas.lead_processed.Product productMessage) {
        if (productMessage == null) {
            return null;
        }
        return Product.builder()
                      .withId(productMessage.getId())
                      .withName(productMessage.getName())
                      .withActive(productMessage.getActive())
                      .withAssistances(productMessage.getAssistances())
                      .withCoverages(productMessage.getCoverages())
                      .withCategory(productMessage.getCategory().name())
                      .withTotalCoverageAmount(productMessage.getTotalCoverageAmount())
                      .withMinTotalMonthlyPremiumAmount(productMessage.getMinTotalMonthlyPremiumAmount())
                      .withMaxTotalMonthlyPremiumAmount(productMessage.getMaxTotalMonthlyPremiumAmount())
                      .withSuggestedTotalMonthlyPremiumAmount(productMessage.getSuggestedTotalMonthlyPremiumAmount())
                      .withCreatedAt(productMessage.getCreatedAt())
                      .withUpdatedAt(productMessage.getUpdatedAt())
                      .build();
    }
}
