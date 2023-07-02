package br.pucminas.products.adapters.web.in.products.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.products.adapters.web.support.ProductDtoSupport.defaultUpdateProductDto;
import static br.pucminas.products.application.support.ProductSupport.defaultProduct;
import static org.assertj.core.api.Assertions.assertThat;

class UpdateProductControllerMapperTest {

    @Test
    @DisplayName("Given Product When Map Then Return Product Dto")
    public void givenProductWhenMapThenReturnProductDto() {

        var product = defaultProduct().build();

        var productDto = UpdateProductControllerMapper.mapToDto(product);

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

    @Test
    @DisplayName("Given Product Dto When Map Then Return Product")
    public void givenProductDtoWhenMapThenReturnProduct() {

        var productDto = defaultUpdateProductDto().build();

        var product = UpdateProductControllerMapper.mapToDomain(productDto);

        assertThat(product).isNotNull();
        assertThat(product).hasNoNullFieldsOrPropertiesExcept("createdAt", "updatedAt", "deletedAt");
        assertThat(product.getId()).isEqualTo(productDto.getId());
        assertThat(product.getName()).isEqualTo(productDto.getName());
        assertThat(product.getCategory().getCode()).isEqualTo(productDto.getCategory().getCode());
    }

}