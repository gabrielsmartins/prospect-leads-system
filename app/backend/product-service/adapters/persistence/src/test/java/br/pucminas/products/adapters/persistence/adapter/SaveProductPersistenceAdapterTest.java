package br.pucminas.products.adapters.persistence.adapter;

import br.pucminas.products.adapters.persistence.entity.ProductEntity;
import br.pucminas.products.adapters.persistence.service.IProductPersistenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.products.application.support.ProductSupport.defaultProduct;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SaveProductPersistenceAdapterTest {

    private SaveProductPersistenceAdapter adapter;
    private IProductPersistenceService service;

    @BeforeEach
    public void setup() {
        this.service = mock(IProductPersistenceService.class);
        this.adapter = new SaveProductPersistenceAdapter(this.service);
    }

    @Test
    @DisplayName("Given Product When Save Then Return Saved Product")
    public void givenProductWhenSaveThenReturnSavedProduct() {
        var product = defaultProduct().build();

        when(this.service.save(any(ProductEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var savedProductEntity = this.adapter.save(product);

        assertThat(savedProductEntity).isNotNull();
    }

}