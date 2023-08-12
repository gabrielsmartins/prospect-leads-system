package br.pucminas.products.adapters.web.in.products.mapper;

import br.pucminas.products.adapters.web.in.products.dto.CategoryEnumDto;
import br.pucminas.products.adapters.web.in.products.dto.UpdateProductDto;
import br.pucminas.products.application.domain.Product;
import br.pucminas.products.application.domain.enums.CategoryEnum;
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
                               .withCategory(CategoryEnumDto.fromCode(product.getCategory().getCode()))
                               .withMinTotalMonthlyPremiumAmount(product.getMinTotalMonthlyPremiumAmount())
                               .withMaxTotalMonthlyPremiumAmount(product.getMaxTotalMonthlyPremiumAmount())
                               .withSuggestedTotalMonthlyPremiumAmount(product.getSuggestedTotalMonthlyPremiumAmount())
                               .withTotalCoverageAmount(product.getTotalCoverageAmount())
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
        var product = Product.builder()
                             .withId(productDto.getId())
                             .withName(productDto.getName())
                             .withActive(productDto.getActive())
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
