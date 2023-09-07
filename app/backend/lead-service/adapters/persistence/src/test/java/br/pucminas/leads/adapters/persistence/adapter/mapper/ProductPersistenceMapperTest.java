package br.pucminas.leads.adapters.persistence.adapter.mapper;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.leads.adapters.persistence.support.ProductEntitySupport.defaultProductEntity;
import static br.pucminas.leads.application.support.ProductSupport.defaultProduct;
import static org.assertj.core.api.Assertions.assertThat;

class ProductPersistenceMapperTest {

    @Test
    @DisplayName("Given Product When Map Then Return Product Entity")
    public void givenProductWhenMapThenReturnProductMessage() {
        var product = defaultProduct().build();
        var productEntity = ProductPersistenceMapper.mapToEntity(product);

        assertThat(productEntity).isNotNull();
        assertThat(productEntity).hasNoNullFieldsOrProperties();
        assertThat(productEntity.getId()).isEqualTo(product.getId());
        assertThat(productEntity.getName()).isEqualTo(product.getName());
        assertThat(productEntity.getCategory()).isEqualTo(product.getCategory());
        assertThat(productEntity.getActive()).isEqualTo(product.isActive());
        assertThat(productEntity.getMaxTotalMonthlyPremiumAmount()).isEqualTo(product.getMaxTotalMonthlyPremiumAmount());
        assertThat(productEntity.getMinTotalMonthlyPremiumAmount()).isEqualTo(product.getMinTotalMonthlyPremiumAmount());
        assertThat(productEntity.getSuggestedTotalMonthlyPremiumAmount()).isEqualTo(product.getSuggestedTotalMonthlyPremiumAmount());
        assertThat(productEntity.getTotalCoverageAmount()).isEqualTo(product.getTotalCoverageAmount());
        assertThat(productEntity.getCoverages()).isEqualTo(product.getCoverages());
        assertThat(productEntity.getAssistances()).isEqualTo(product.getAssistances());
        assertThat(productEntity.getCreatedAt()).isEqualTo(product.getCreatedAt());
        assertThat(productEntity.getUpdatedAt()).isEqualTo(product.getUpdatedAt());
        assertThat(productEntity.getDeletedAt()).isEqualTo(product.getDeletedAt());
    }

    @Test
    @DisplayName("Given Product Entity When Map Then Return Product")
    public void givenProductEntityWhenMapThenReturnProduct() {
        var productEntity = defaultProductEntity().build();
        var product = ProductPersistenceMapper.mapToDomain(productEntity);

        assertThat(product).isNotNull();
        assertThat(product).hasNoNullFieldsOrProperties();
        assertThat(product.getId()).isEqualTo(productEntity.getId());
        assertThat(product.getName()).isEqualTo(productEntity.getName());
        assertThat(product.getCategory()).isEqualTo(productEntity.getCategory());
        assertThat(product.isActive()).isEqualTo(productEntity.getActive());
        assertThat(product.getMaxTotalMonthlyPremiumAmount()).isEqualTo(productEntity.getMaxTotalMonthlyPremiumAmount());
        assertThat(product.getMinTotalMonthlyPremiumAmount()).isEqualTo(productEntity.getMinTotalMonthlyPremiumAmount());
        assertThat(product.getSuggestedTotalMonthlyPremiumAmount()).isEqualTo(productEntity.getSuggestedTotalMonthlyPremiumAmount());
        assertThat(product.getTotalCoverageAmount()).isEqualTo(productEntity.getTotalCoverageAmount());
        assertThat(product.getCoverages()).isEqualTo(productEntity.getCoverages());
        assertThat(product.getAssistances()).isEqualTo(productEntity.getAssistances());
        assertThat(product.getCreatedAt()).isEqualTo(productEntity.getCreatedAt());
        assertThat(product.getUpdatedAt()).isEqualTo(productEntity.getUpdatedAt());
        assertThat(product.getDeletedAt()).isEqualTo(productEntity.getDeletedAt());
    }

}