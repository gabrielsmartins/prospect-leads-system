package br.pucminas.bff.adapters.web.in.mapper;

import br.pucminas.bff.adapters.web.in.products.dto.CategoryEnumDto;
import br.pucminas.bff.adapters.web.in.products.dto.CreateProductDto;
import br.pucminas.bff.application.domain.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateProductControllerMapper {

    public static CreateProductDto mapToDto(Product product) {
        if (product == null) {
            return null;
        }
        return CreateProductDto.builder()
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
                               .build();
    }

    public static Product mapToDomain(CreateProductDto productDto) {
        if (productDto == null) {
            return null;
        }
        var product = Product.builder()
                             .withId(productDto.getId())
                             .withName(productDto.getName())
                             .withCategory(productDto.getCategory().getDescription())
                             .withTotalYearlyPremiumAmount(productDto.getTotalYearlyPremiumAmount())
                             .withTotalMonthlyPremiumAmount(productDto.getTotalMonthlyPremiumAmount())
                             .withCoverages(productDto.getCoverages())
                             .withAssistances(productDto.getAssistances())
                             .build();
        productDto.getCoverages().forEach(product::addCoverage);
        productDto.getAssistances().forEach(product::addAssistance);
        return product;
    }

}
