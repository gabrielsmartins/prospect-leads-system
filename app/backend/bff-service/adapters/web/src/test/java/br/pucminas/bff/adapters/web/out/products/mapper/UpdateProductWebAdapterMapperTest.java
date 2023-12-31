package br.pucminas.bff.adapters.web.out.products.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.bff.adapters.web.out.support.ProductDtoSupport.defaultUpdateProductDto;
import static br.pucminas.bff.application.support.ProductSupport.defaultProduct;
import static org.assertj.core.api.Assertions.assertThat;

class UpdateProductWebAdapterMapperTest {

    @Test
    @DisplayName("Given Product When Map Then Return Product Dto")
    public void givenProductWhenMapThenReturnProductDto() {
        var product = defaultProduct().build();

        var productDto = UpdateProductWebAdapterMapper.mapToDto(product);

        assertThat(productDto).isNotNull();
        assertThat(productDto).hasNoNullFieldsOrPropertiesExcept("createdAt", "updatedAt", "deletedAt");
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
    }

    @Test
    @DisplayName("Given Product Dto When Map Then Return Product")
    public void givenProductDtoWhenMapThenReturnProduct() {
        var productDto = defaultUpdateProductDto().build();

        var product = UpdateProductWebAdapterMapper.mapToDomain(productDto);

        assertThat(product).isNotNull();
        assertThat(product).hasNoNullFieldsOrProperties();
        assertThat(product.getId()).isEqualTo(productDto.getId());
        assertThat(product.isActive()).isEqualTo(productDto.getActive());
        assertThat(product.getName()).isEqualTo(productDto.getName());
        assertThat(product.getCategory()).isEqualTo(productDto.getCategory().getDescription());
        assertThat(product.getMinTotalMonthlyPremiumAmount()).isEqualByComparingTo(productDto.getMinTotalMonthlyPremiumAmount());
        assertThat(product.getMaxTotalMonthlyPremiumAmount()).isEqualByComparingTo(productDto.getMaxTotalMonthlyPremiumAmount());
        assertThat(product.getSuggestedTotalMonthlyPremiumAmount()).isEqualByComparingTo(productDto.getSuggestedTotalMonthlyPremiumAmount());
        assertThat(product.getTotalCoverageAmount()).isEqualByComparingTo(productDto.getTotalCoverageAmount());
        assertThat(product.getCoverages()).isEqualTo(productDto.getCoverages());
        assertThat(product.getAssistances()).isEqualTo(productDto.getAssistances());
        assertThat(product.getCreatedAt()).isEqualTo(productDto.getCreatedAt());
        assertThat(product.getUpdatedAt()).isEqualTo(productDto.getUpdatedAt());
        assertThat(product.getDeletedAt()).isEqualTo(productDto.getDeletedAt());
    }

}