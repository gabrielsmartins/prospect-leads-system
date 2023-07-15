package br.pucminas.bff.adapters.web.out.products.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.bff.adapters.web.out.support.ProductDtoSupport.defaultSearchProductDto;
import static org.assertj.core.api.Assertions.assertThat;

class SearchProductWebAdapterMapperTest {

    @Test
    @DisplayName("Given Product Dto When Map Then Return Product")
    public void givenProductDtoWhenMapThenReturnProduct() {
        var productDto = defaultSearchProductDto().build();

        var product = SearchProductWebAdapterMapper.mapToDomain(productDto);

        assertThat(product).isNotNull();
        assertThat(product).hasNoNullFieldsOrProperties();
        assertThat(product.getId()).isEqualTo(productDto.getId());
        assertThat(product.isActive()).isEqualTo(productDto.getActive());
        assertThat(product.getName()).isEqualTo(productDto.getName());
        assertThat(product.getCategory()).isEqualTo(productDto.getCategory().getDescription());
        assertThat(product.getTotalYearlyPremiumAmount()).isEqualByComparingTo(productDto.getTotalYearlyPremiumAmount());
        assertThat(product.getTotalMonthlyPremiumAmount()).isEqualByComparingTo(productDto.getTotalMonthlyPremiumAmount());
        assertThat(product.getTotalCoverageAmount()).isEqualByComparingTo(productDto.getTotalCoverageAmount());
        assertThat(product.getCoverages()).isEqualTo(productDto.getCoverages());
        assertThat(product.getAssistances()).isEqualTo(productDto.getAssistances());
        assertThat(product.getCreatedAt()).isEqualTo(productDto.getCreatedAt());
        assertThat(product.getUpdatedAt()).isEqualTo(productDto.getUpdatedAt());
        assertThat(product.getDeletedAt()).isEqualTo(productDto.getDeletedAt());
    }

}