package br.pucminas.bff.adapters.web.in.mapper;

import br.pucminas.bff.adapters.web.in.products.dto.CategoryEnumDto;
import br.pucminas.bff.adapters.web.in.products.dto.UpdateProductDto;
import br.pucminas.bff.application.domain.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateProductControllerMapper {

    public static UpdateProductDto mapToDto(Product product) {
        if (product == null) {
            return null;
        }
        return UpdateProductDto.builder()
                               .withId(product.getId())
                               .withName(product.getName())
                               .withActive(product.isActive())
                               .withCategory(CategoryEnumDto.fromDescription(product.getCategory()))
                               .withTotalCoverageAmount(product.getTotalCoverageAmount())
                               .withTotalYearlyPremiumAmount(product.getTotalYearlyPremiumAmount())
                               .withTotalMonthlyPremiumAmount(product.getTotalMonthlyPremiumAmount())
                               .withCoverages(product.getCoverages())
                               .withAssistances(product.getAssistances())
                               .withCreatedAt(product.getCreatedAt())
                               .withUpdatedAt(product.getUpdatedAt())
                               .withDeletedAt(product.getDeletedAt())
                               .build();
    }

    public static Product mapToDomain(UpdateProductDto productDto) {
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
                      .build();
    }

}
