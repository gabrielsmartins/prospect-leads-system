package br.pucminas.quotes.adapters.web.out.products.mapper;

import br.pucminas.quotes.adapters.web.out.products.dto.ProductDto;
import br.pucminas.quotes.application.domain.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductWebAdapterMapper {

    public static Product mapToDomain(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }
        var product = Product.builder()
                              .withId(productDto.getId())
                              .withName(productDto.getName())
                              .withCategory(productDto.getCategory())
                              .withActive(productDto.getActive())
                              .withTotalYearlyPremiumAmount(productDto.getTotalYearlyPremiumAmount())
                              .withTotalMonthlyPremiumAmount(productDto.getTotalMonthlyPremiumAmount())
                              .withTotalCoverageAmount(productDto.getTotalCoverageAmount())
                              .withCreatedAt(productDto.getCreatedAt())
                              .withUpdatedAt(productDto.getUpdatedAt())
                              .withDeletedAt(productDto.getDeletedAt())
                              .build();
        productDto.getCoverages().forEach(product::addCoverage);
        productDto.getAssistances().forEach(product::addAssistance);
        return product;
    }
}
