package br.pucminas.leads.adapters.web.in.leads.mapper;

import br.pucminas.leads.adapters.web.in.leads.dto.ProductDto;
import br.pucminas.leads.application.domain.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductControllerMapper {

    public static ProductDto mapToDto(Product product) {
        if (product == null) {
            return null;
        }
        return ProductDto.builder()
                         .withId(product.getId())
                         .withName(product.getName())
                         .withActive(product.isActive())
                         .withCategory(product.getCategory())
                         .withCoverages(product.getCoverages())
                         .withAssistances(product.getAssistances())
                         .withTotalCoverageAmount(product.getTotalCoverageAmount())
                         .withSuggestedTotalMonthlyPremiumAmount(product.getSuggestedTotalMonthlyPremiumAmount())
                         .withMaxTotalMonthlyPremiumAmount(product.getMaxTotalMonthlyPremiumAmount())
                         .withMinTotalMonthlyPremiumAmount(product.getMinTotalMonthlyPremiumAmount())
                         .withCreatedAt(product.getCreatedAt())
                         .withUpdatedAt(product.getUpdatedAt())
                         .withDeletedAt(product.getDeletedAt())
                         .build();
    }
}
