package br.pucminas.bff.adapters.web.out.products.mapper;

import br.pucminas.bff.adapters.web.out.products.dto.CategoryEnumDto;
import br.pucminas.bff.adapters.web.out.products.dto.CreateProductDto;
import br.pucminas.bff.application.domain.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateProductWebAdapterMapper {

    public static CreateProductDto mapToDto(Product product) {
        if (product == null) {
            return null;
        }
        return CreateProductDto.builder()
                               .withName(product.getName())
                               .withCategory(CategoryEnumDto.fromDescription(product.getCategory()))
                               .withTotalCoverageAmount(product.getTotalCoverageAmount())
                               .withTotalYearlyPremiumAmount(product.getTotalYearlyPremiumAmount())
                               .withTotalMonthlyPremiumAmount(product.getTotalMonthlyPremiumAmount())
                               .withCoverages(product.getCoverages())
                               .withAssistances(product.getAssistances())
                               .build();
    }

    public static Product mapToDomain(CreateProductDto productDto) {
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
                      .build();
    }

}
