package br.pucminas.products.adapters.web.in.products.mapper;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.products.application.support.ProductSupport.defaultProduct;
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
        assertThat(productDto.getCategory().getCode()).isEqualTo(product.getCategory().getCode());
        assertThat(productDto.getCreatedAt()).isEqualTo(product.getCreatedAt());
        assertThat(productDto.getUpdatedAt()).isEqualTo(product.getUpdatedAt());
        assertThat(productDto.getDeletedAt()).isEqualTo(product.getDeletedAt());
    }

}