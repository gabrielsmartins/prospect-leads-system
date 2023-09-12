package br.pucminas.leads.adapters.messaging.in.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.leads.adapters.messaging.in.support.LeadProcessedMessageSupport.defaultProductMessage;
import static org.assertj.core.api.Assertions.assertThat;

class ProductConsumerMapperTest {

    @Test
    @DisplayName("Given Product Message When Map Then Return Product")
    public void givenProductMessageWhenMapThenReturnProduct() {
        var productMessage = defaultProductMessage().build();
        var product = ProductConsumerMapper.mapToDomain(productMessage);

        assertThat(product).isNotNull();
        assertThat(product).hasNoNullFieldsOrProperties();
        assertThat(product.getId()).isEqualTo(productMessage.getId());
        assertThat(product.getName()).isEqualTo(productMessage.getName());
        assertThat(product.getCategory()).isEqualTo(productMessage.getCategory().name());
        assertThat(product.isActive()).isEqualTo(productMessage.getActive());
        assertThat(product.getAssistances()).isEqualTo(productMessage.getAssistances());
        assertThat(product.getCoverages()).isEqualTo(productMessage.getCoverages());
        assertThat(product.getMaxTotalMonthlyPremiumAmount()).isEqualTo(productMessage.getMaxTotalMonthlyPremiumAmount());
        assertThat(product.getMinTotalMonthlyPremiumAmount()).isEqualTo(productMessage.getMinTotalMonthlyPremiumAmount());
        assertThat(product.getSuggestedTotalMonthlyPremiumAmount()).isEqualTo(productMessage.getSuggestedTotalMonthlyPremiumAmount());
        assertThat(product.getCreatedAt()).isEqualTo(productMessage.getCreatedAt());
        assertThat(product.getUpdatedAt()).isEqualTo(productMessage.getUpdatedAt());
        assertThat(product.getDeletedAt()).isEqualTo(productMessage.getDeletedAt());
    }

}