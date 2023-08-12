package br.pucminas.quotes.adapters.web.out.products.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.quotes.adapters.web.out.support.ProductDtoSupport.defaultProductDto;
import static org.assertj.core.api.Assertions.assertThat;

class ProductWebAdapterMapperTest {

    @Test
    @DisplayName("Given Product Dto When Map Then Return Product")
    public void givenProductDtoWhenMapThenReturnProduct() {
        var productDto = defaultProductDto().build();

        var product = ProductWebAdapterMapper.mapToDomain(productDto);

        assertThat(product).isNotNull();
        assertThat(product).hasNoNullFieldsOrProperties();
        assertThat(product.getId()).isEqualTo(productDto.getId());
        assertThat(product.getName()).isEqualTo(productDto.getName());
        assertThat(product.getCategory()).isEqualTo(productDto.getCategory());
        assertThat(product.isActive()).isEqualTo(productDto.getActive());
        assertThat(product.getMinTotalMonthlyPremiumAmount()).isEqualTo(productDto.getMinTotalMonthlyPremiumAmount());
        assertThat(product.getMaxTotalMonthlyPremiumAmount()).isEqualTo(productDto.getMaxTotalMonthlyPremiumAmount());
        assertThat(product.getSuggestedTotalMonthlyPremiumAmount()).isEqualTo(productDto.getSuggestedTotalMonthlyPremiumAmount());
        assertThat(product.getTotalCoverageAmount()).isEqualTo(productDto.getTotalCoverageAmount());
        assertThat(product.getCreatedAt()).isEqualTo(productDto.getCreatedAt());
        assertThat(product.getUpdatedAt()).isEqualTo(productDto.getUpdatedAt());
        assertThat(product.getDeletedAt()).isEqualTo(productDto.getDeletedAt());
    }

}