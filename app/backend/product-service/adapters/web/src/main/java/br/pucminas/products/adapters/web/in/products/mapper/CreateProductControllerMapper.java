package br.pucminas.products.adapters.web.in.products.mapper;

import br.pucminas.products.adapters.web.in.products.dto.CategoryEnumDto;
import br.pucminas.products.adapters.web.in.products.dto.CreateProductDto;
import br.pucminas.products.application.domain.enums.CategoryEnum;
import br.pucminas.products.application.domain.Product;
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
                               .withCategory(CategoryEnumDto.fromCode(product.getCategory().getCode()))
                               .withMinTotalMonthlyPremiumAmount(product.getMinTotalMonthlyPremiumAmount())
                               .withMaxTotalMonthlyPremiumAmount(product.getMaxTotalMonthlyPremiumAmount())
                               .withSuggestedTotalMonthlyPremiumAmount(product.getSuggestedTotalMonthlyPremiumAmount())
                               .withTotalCoverageAmount(product.getTotalCoverageAmount())
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
                             .withCategory(CategoryEnum.fromCode(productDto.getCategory().getCode()))
                             .withMinTotalMonthlyPremiumAmount(productDto.getMinTotalMonthlyPremiumAmount())
                             .withMaxTotalMonthlyPremiumAmount(productDto.getMaxTotalMonthlyPremiumAmount())
                             .build();
        product.setSuggestedTotalMonthlyPremiumAmount(productDto.getSuggestedTotalMonthlyPremiumAmount());
        productDto.getCoverages().forEach(product::addCoverage);
        productDto.getAssistances().forEach(product::addAssistance);
        return product;
    }
}
