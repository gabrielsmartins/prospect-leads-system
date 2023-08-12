package br.pucminas.bff.adapters.web.in.products.mapper;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.bff.application.support.ProductSupport.defaultProduct;
import static org.assertj.core.api.Assertions.assertThat;

class SearchProductControllerMapperTest {

    @Test
    @DisplayName("Given Product When Map Then Return Product Dto")
    public void givenProductWhenMapThenReturnProductDto() {
        var product = defaultProduct().build();
        var productDto = SearchProductControllerMapper.mapToDto(product);

        assertThat(productDto).isNotNull();
        assertThat(productDto).hasNoNullFieldsOrProperties();
        assertThat(productDto.getId()).isEqualTo(product.getId());
        assertThat(productDto.getName()).isEqualTo(product.getName());
        assertThat(productDto.getActive()).isEqualTo(product.isActive());
        assertThat(productDto.getCategory().getDescription()).isEqualTo(product.getCategory());
        assertThat(productDto.getMinTotalMonthlyPremiumAmount()).isEqualByComparingTo(product.getMinTotalMonthlyPremiumAmount());
        assertThat(productDto.getMaxTotalMonthlyPremiumAmount()).isEqualByComparingTo(product.getMaxTotalMonthlyPremiumAmount());
        assertThat(productDto.getSuggestedTotalMonthlyPremiumAmount()).isEqualByComparingTo(product.getSuggestedTotalMonthlyPremiumAmount());
        assertThat(productDto.getTotalCoverageAmount()).isEqualByComparingTo(product.getTotalCoverageAmount());
        assertThat(productDto.getCoverages()).isEqualTo(product.getCoverages());
        assertThat(productDto.getAssistances()).isEqualTo(product.getAssistances());
        assertThat(productDto.getCreatedAt()).isEqualTo(product.getCreatedAt());
        assertThat(productDto.getUpdatedAt()).isEqualTo(product.getUpdatedAt());
        assertThat(productDto.getDeletedAt()).isEqualTo(product.getDeletedAt());
    }

}