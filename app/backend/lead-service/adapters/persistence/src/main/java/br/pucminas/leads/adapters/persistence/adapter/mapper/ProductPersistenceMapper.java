package br.pucminas.leads.adapters.persistence.adapter.mapper;

import br.pucminas.leads.adapters.persistence.entity.ProductEntity;
import br.pucminas.leads.application.domain.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductPersistenceMapper {

    public static ProductEntity mapToEntity(Product product) {
        if (product == null) {
            return null;
        }
        return ProductEntity.builder()
                            .withId(product.getId())
                            .withName(product.getName())
                            .withCategory(product.getCategory())
                            .withActive(product.isActive())
                            .withMaxTotalMonthlyPremiumAmount(product.getMaxTotalMonthlyPremiumAmount())
                            .withMinTotalMonthlyPremiumAmount(product.getMinTotalMonthlyPremiumAmount())
                            .withSuggestedTotalMonthlyPremiumAmount(product.getSuggestedTotalMonthlyPremiumAmount())
                            .withTotalCoverageAmount(product.getTotalCoverageAmount())
                            .withCoverages(product.getCoverages())
                            .withAssistances(product.getAssistances())
                            .withCreatedAt(product.getCreatedAt())
                            .withUpdatedAt(product.getUpdatedAt())
                            .withDeletedAt(product.getDeletedAt())
                            .build();
    }

    public static Product mapToDomain(ProductEntity productEntity) {
        if (productEntity == null) {
            return null;
        }
        return Product.builder()
                        .withId(productEntity.getId())
                        .withName(productEntity.getName())
                        .withCategory(productEntity.getCategory())
                        .withActive(productEntity.getActive())
                        .withMaxTotalMonthlyPremiumAmount(productEntity.getMaxTotalMonthlyPremiumAmount())
                        .withMinTotalMonthlyPremiumAmount(productEntity.getMinTotalMonthlyPremiumAmount())
                        .withSuggestedTotalMonthlyPremiumAmount(productEntity.getSuggestedTotalMonthlyPremiumAmount())
                        .withTotalCoverageAmount(productEntity.getTotalCoverageAmount())
                        .withCoverages(productEntity.getCoverages())
                        .withAssistances(productEntity.getAssistances())
                        .withCreatedAt(productEntity.getCreatedAt())
                        .withUpdatedAt(productEntity.getUpdatedAt())
                        .withDeletedAt(productEntity.getDeletedAt())
                        .build();
    }

}
