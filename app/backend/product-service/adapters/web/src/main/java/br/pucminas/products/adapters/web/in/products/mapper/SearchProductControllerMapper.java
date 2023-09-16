package br.pucminas.products.adapters.web.in.products.mapper;

import br.pucminas.products.adapters.web.in.products.dto.CategoryEnumDto;
import br.pucminas.products.adapters.web.in.products.dto.PageDto;
import br.pucminas.products.adapters.web.in.products.dto.SearchProductDto;
import br.pucminas.products.application.domain.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

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

    public static PageDto<SearchProductDto> mapToDto(Page<Product> page) {
        if (page == null) {
            return null;
        }
        var productsDto = page.getContent()
                               .stream()
                               .map(SearchProductControllerMapper::mapToDto)
                               .collect(Collectors.toList());
        var pageDto = new PageDto<SearchProductDto>();
        pageDto.setData(productsDto);
        pageDto.setPage(page.getNumber());
        pageDto.setPageSize(page.getSize());
        pageDto.setTotalElements(page.getTotalElements());
        pageDto.setTotalPages(pageDto.getTotalPages());
        pageDto.setFirst(page.isFirst());
        pageDto.setFirst(page.isLast());
        return pageDto;
    }

}
