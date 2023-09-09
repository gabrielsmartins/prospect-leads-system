package br.pucminas.leads.adapters.messaging.out.mapper;

import br.pucminas.leads.schemas.lead_processed.Category;
import br.pucminas.leads.schemas.lead_processed.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductProducerMapper {

    public static Product mapToMessage(br.pucminas.leads.application.domain.Product product) {
        if (product == null) {
            return null;
        }
        return Product.newBuilder()
                      .setId(product.getId())
                      .setName(product.getName())
                      .setCategory(Category.valueOf(product.getCategory()))
                      .setActive(product.isActive())
                      .setMaxTotalMonthlyPremiumAmount(product.getMaxTotalMonthlyPremiumAmount())
                      .setMinTotalMonthlyPremiumAmount(product.getMinTotalMonthlyPremiumAmount())
                      .setSuggestedTotalMonthlyPremiumAmount(product.getSuggestedTotalMonthlyPremiumAmount())
                      .setTotalCoverageAmount(product.getTotalCoverageAmount())
                      .setCoverages(product.getCoverages())
                      .setAssistances(product.getAssistances())
                      .setCreatedAt(product.getCreatedAt())
                      .setUpdatedAt(product.getUpdatedAt())
                      .setDeletedAt(product.getDeletedAt())
                      .build();
    }
}
