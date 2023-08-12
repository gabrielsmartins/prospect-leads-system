package br.pucminas.products.adapters.persistence.adapter.mapper;


import br.pucminas.products.adapters.persistence.entity.ProductEntity;
import br.pucminas.products.application.domain.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductPersistenceMapper {

    public static ProductEntity mapToEntity(Product product){
        if (product == null) {
            return null;
        }
        return ProductEntity.builder()
                            .withId(product.getId())
                            .withName(product.getName())
                            .withActive(product.isActive())
                            .withCategory(product.getCategory())
                            .withTotalCoverageAmount(product.getTotalCoverageAmount())
                            .withMaxTotalMonthlyPremiumAmount(product.getMaxTotalMonthlyPremiumAmount())
                            .withMinTotalMonthlyPremiumAmount(product.getMinTotalMonthlyPremiumAmount())
                            .withSuggestedTotalMonthlyPremiumAmount(product.getSuggestedTotalMonthlyPremiumAmount())
                            .withCoverages(product.getCoverages())
                            .withAssistances(product.getAssistances())
                            .withCreatedAt(product.getCreatedAt())
                            .withUpdatedAt(product.getUpdatedAt())
                            .withDeletedAt(product.getDeletedAt())
                            .build();
    }

    public static Product mapToDomain(ProductEntity productEntity){
        if (productEntity == null) {
            return null;
        }
        var product =  Product.builder()
                              .withId(productEntity.getId())
                              .withName(productEntity.getName())
                              .withActive(productEntity.getActive())
                              .withCategory(productEntity.getCategory())
                              .withMaxTotalMonthlyPremiumAmount(productEntity.getMaxTotalMonthlyPremiumAmount())
                              .withMinTotalMonthlyPremiumAmount(productEntity.getMinTotalMonthlyPremiumAmount())
                              .withSuggestedTotalMonthlyPremiumAmount(productEntity.getSuggestedTotalMonthlyPremiumAmount())
                              .withCreatedAt(productEntity.getCreatedAt())
                              .withUpdatedAt(productEntity.getUpdatedAt())
                              .withDeletedAt(productEntity.getDeletedAt())
                              .build();
        productEntity.getCoverages().forEach(product::addCoverage);
        productEntity.getAssistances().forEach(product::addAssistance);
        return product;
    }

}
