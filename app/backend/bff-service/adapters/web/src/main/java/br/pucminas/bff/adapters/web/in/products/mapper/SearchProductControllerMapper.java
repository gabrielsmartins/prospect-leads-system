package br.pucminas.bff.adapters.web.in.products.mapper;

import br.pucminas.bff.adapters.web.in.products.dto.CategoryEnumDto;
import br.pucminas.bff.adapters.web.in.products.dto.SearchProductDto;
import br.pucminas.bff.application.domain.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchProductControllerMapper {

    public static SearchProductDto mapToDto(Product product) {
        if (product == null) {
            return null;
        }
        return SearchProductDto.builder()
                        .withId(product.getId())
                        .withName(product.getName())
                        .withActive(product.isActive())
                        .withCategory(CategoryEnumDto.fromDescription(product.getCategory()))
                        .withTotalCoverageAmount(product.getTotalCoverageAmount())
                        .withMinTotalMonthlyPremiumAmount(product.getMinTotalMonthlyPremiumAmount())
                        .withMaxTotalMonthlyPremiumAmount(product.getMaxTotalMonthlyPremiumAmount())
                        .withSuggestedTotalMonthlyPremiumAmount(product.getSuggestedTotalMonthlyPremiumAmount())
                        .withCoverages(product.getCoverages())
                        .withAssistances(product.getAssistances())
                        .withCreatedAt(product.getCreatedAt())
                        .withUpdatedAt(product.getUpdatedAt())
                        .withDeletedAt(product.getDeletedAt())
                        .build();
    }

}
