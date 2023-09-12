package br.pucminas.leads.adapters.messaging.in.mapper;

import br.pucminas.leads.application.domain.Product;
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
                      .withMinTotalMonthlyPremiumAmount(productMessage.getMinTotalMonthlyPremiumAmount())
                      .withMaxTotalMonthlyPremiumAmount(productMessage.getMaxTotalMonthlyPremiumAmount())
                      .withSuggestedTotalMonthlyPremiumAmount(productMessage.getSuggestedTotalMonthlyPremiumAmount())
                      .withCreatedAt(productMessage.getCreatedAt())
                      .withUpdatedAt(productMessage.getUpdatedAt())
                      .withDeletedAt(productMessage.getDeletedAt())
                      .build();
    }
}
