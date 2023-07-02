package br.pucminas.products.adapters.persistence.adapter;


import br.pucminas.products.adapters.persistence.service.IProductPersistenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static br.pucminas.products.adapters.persistence.support.ProductEntitySupport.defaultProductEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SearchProductPersistenceAdapterTest {

    private SearchProductPersistenceAdapter adapter;
    private IProductPersistenceService service;

    @BeforeEach
    public void setup() {
        this.service = mock(IProductPersistenceService.class);
        this.adapter = new SearchProductPersistenceAdapter(this.service);
    }

    @Test
    @DisplayName("Given Id When Exists Then Return Existing Product")
    public void givenIdWhenExistsThenReturnExistingProduct() {
        var productEntity = defaultProductEntity().build();

        when(this.service.findById(anyInt())).thenReturn(Optional.of(productEntity));

        var id = 1;
        var optionalProduct = this.adapter.findById(id);

        assertThat(optionalProduct).isPresent();
    }

}