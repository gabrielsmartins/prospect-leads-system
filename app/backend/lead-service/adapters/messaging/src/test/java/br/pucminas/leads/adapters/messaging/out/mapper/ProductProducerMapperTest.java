package br.pucminas.leads.adapters.messaging.out.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.leads.application.support.ProductSupport.defaultProduct;
import static org.assertj.core.api.Assertions.assertThat;

class ProductProducerMapperTest {

    @Test
    @DisplayName("Given Product When Map Then Return Product Message")
    public void givenProductWhenMapThenReturnProductMessage() {
        var product = defaultProduct().build();
        var productMessage = ProductProducerMapper.mapToMessage(product);

        assertThat(productMessage).isNotNull();
        assertThat(productMessage).hasNoNullFieldsOrProperties();
        assertThat(productMessage.getId()).isEqualTo(product.getId());
        assertThat(productMessage.getName()).isEqualTo(product.getName());
        assertThat(productMessage.getCategory().name()).isEqualTo(product.getCategory());
        assertThat(productMessage.getActive()).isEqualTo(product.isActive());
        assertThat(productMessage.getMaxTotalMonthlyPremiumAmount()).isEqualTo(product.getMaxTotalMonthlyPremiumAmount());
        assertThat(productMessage.getMinTotalMonthlyPremiumAmount()).isEqualTo(product.getMinTotalMonthlyPremiumAmount());
        assertThat(productMessage.getSuggestedTotalMonthlyPremiumAmount()).isEqualTo(product.getSuggestedTotalMonthlyPremiumAmount());
        assertThat(productMessage.getTotalCoverageAmount()).isEqualTo(product.getTotalCoverageAmount());
        assertThat(productMessage.getCoverages()).isEqualTo(product.getCoverages());
        assertThat(productMessage.getAssistances()).isEqualTo(product.getAssistances());
        assertThat(productMessage.getCreatedAt()).isEqualTo(product.getCreatedAt());
        assertThat(productMessage.getUpdatedAt()).isEqualTo(product.getUpdatedAt());
    }

}