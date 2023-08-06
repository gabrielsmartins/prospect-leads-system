package br.pucminas.bff.adapters.web.out.products.mapper;

import br.pucminas.bff.adapters.web.out.products.dto.SearchProductDto;
import br.pucminas.bff.application.domain.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchProductWebAdapterMapper {

    public static Product mapToDomain(SearchProductDto productDto) {
        if (productDto == null) {
            return null;
        }
        return Product.builder()
                      .withId(productDto.getId())
                      .withName(productDto.getName())
                      .withActive(productDto.getActive())
                      .withCategory(productDto.getCategory().getDescription())
                      .withTotalCoverageAmount(productDto.getTotalCoverageAmount())
                      .withTotalYearlyPremiumAmount(productDto.getTotalYearlyPremiumAmount())
                      .withTotalMonthlyPremiumAmount(productDto.getTotalMonthlyPremiumAmount())
                      .withCoverages(productDto.getCoverages())
                      .withAssistances(productDto.getAssistances())
                      .withCreatedAt(productDto.getCreatedAt())
                      .withUpdatedAt(productDto.getUpdatedAt())
                      .withDeletedAt(productDto.getDeletedAt())
                      .build();
    }

}