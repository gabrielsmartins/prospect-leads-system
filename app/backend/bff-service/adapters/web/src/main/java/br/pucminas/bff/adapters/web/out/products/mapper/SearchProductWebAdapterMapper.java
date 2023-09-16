package br.pucminas.bff.adapters.web.out.products.mapper;

import br.pucminas.bff.adapters.web.out.products.dto.PageDto;
import br.pucminas.bff.adapters.web.out.products.dto.SearchProductDto;
import br.pucminas.bff.application.domain.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.stream.Collectors;

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
                      .withMinTotalMonthlyPremiumAmount(productDto.getMinTotalMonthlyPremiumAmount())
                      .withMaxTotalMonthlyPremiumAmount(productDto.getMaxTotalMonthlyPremiumAmount())
                      .withSuggestedTotalMonthlyPremiumAmount(productDto.getSuggestedTotalMonthlyPremiumAmount())
                      .withCoverages(productDto.getCoverages())
                      .withAssistances(productDto.getAssistances())
                      .withCreatedAt(productDto.getCreatedAt())
                      .withUpdatedAt(productDto.getUpdatedAt())
                      .withDeletedAt(productDto.getDeletedAt())
                      .build();
    }

    public static Page<Product> mapToDomain(PageDto<SearchProductDto> pageDto) {
        if (pageDto == null) {
            return null;
        }
        var products = pageDto.getData()
                           .stream()
                           .map(SearchProductWebAdapterMapper::mapToDomain)
                           .collect(Collectors.toList());
        var pageable = PageRequest.of(pageDto.getPage(), pageDto.getPageSize());
        return new PageImpl<>(products, pageable, pageDto.getTotalElements());
    }

}
