package br.pucminas.bff.adapters.web.out.products.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.bff.adapters.web.out.support.ProductDtoSupport.defaultCreateProductDto;
import static br.pucminas.bff.application.support.ProductSupport.defaultProduct;
import static org.assertj.core.api.Assertions.assertThat;

class CreateProductWebAdapterMapperTest {

    @Test
    @DisplayName("Given Product When Map Then Return Product Dto")
    public void givenProductWhenMapThenReturnProductDto() {
        var product = defaultProduct().build();

        var productDto = CreateProductWebAdapterMapper.mapToDto(product);

        assertThat(productDto).isNotNull();
        assertThat(productDto).hasAllNullFieldsOrPropertiesExcept("name", "category");
        assertThat(productDto.getName()).isEqualTo(product.getName());
        assertThat(productDto.getCategory().getDescription()).isEqualTo(product.getCategory());
    }

    @Test
    @DisplayName("Given Product Dto When Map Then Return Product")
    public void givenProductDtoWhenMapThenReturnProduct() {
        var productDto = defaultCreateProductDto().build();

        var product = CreateProductWebAdapterMapper.mapToDomain(productDto);

        assertThat(product).isNotNull();
        assertThat(product).hasNoNullFieldsOrPropertiesExcept("updatedAt", "deletedAt");
        assertThat(product.getId()).isEqualTo(productDto.getId());
        assertThat(product.isActive()).isEqualTo(productDto.getActive());
        assertThat(product.getName()).isEqualTo(productDto.getName());
        assertThat(product.getCategory()).isEqualTo(productDto.getCategory().getDescription());
        assertThat(product.getCreatedAt()).isEqualTo(productDto.getCreatedAt());
    }

}