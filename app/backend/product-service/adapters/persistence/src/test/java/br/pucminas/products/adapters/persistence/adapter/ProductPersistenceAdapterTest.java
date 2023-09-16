package br.pucminas.products.adapters.persistence.adapter;

import br.pucminas.products.adapters.persistence.entity.ProductEntity;
import br.pucminas.products.adapters.persistence.service.IProductPersistenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static br.pucminas.products.adapters.persistence.support.ProductEntitySupport.defaultProductEntity;
import static br.pucminas.products.application.support.ProductSupport.defaultProduct;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductPersistenceAdapterTest {

    private ProductPersistenceAdapter adapter;
    private IProductPersistenceService service;

    @BeforeEach
    public void setup() {
        this.service = mock(IProductPersistenceService.class);
        this.adapter = new ProductPersistenceAdapter(this.service);
    }

    @Test
    @DisplayName("Given Product When Save Then Return Saved Product")
    public void givenProductWhenSaveThenReturnSavedProduct() {
        var product = defaultProduct().build();

        when(this.service.save(any(ProductEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var savedProductEntity = this.adapter.save(product);

        assertThat(savedProductEntity).isNotNull();
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

    @Test
    @DisplayName("Given Pageable When Exist Then Return Products")
    public void givenPageableWhenExistThenReturnProducts() {
        var productEntity = defaultProductEntity().build();

        var pageRequest = PageRequest.of(1, 30);
        var pageable = new PageImpl<>(List.of(productEntity), pageRequest, 1);
        when(this.service.findAll(any(Pageable.class))).thenReturn(pageable);

        var page = this.adapter.findAll(pageRequest);

        assertThat(page).isNotNull();
    }
}