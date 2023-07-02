package br.pucminas.products.adapters.persistence.service;


import br.pucminas.products.adapters.persistence.entity.ProductEntity;
import br.pucminas.products.adapters.persistence.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static br.pucminas.products.adapters.persistence.support.ProductEntitySupport.defaultProductEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductPersistenceServiceTest {

    private ProductPersistenceService service;
    private ProductRepository repository;

    @BeforeEach
    public void setup() {
        this.repository = mock(ProductRepository.class);
        this.service = new ProductPersistenceService(this.repository);
    }

    @Test
    @DisplayName("Given Product When Save Then Return Saved Product")
    public void givenProductWhenSaveThenReturnSavedProduct() {
        var productEntity = defaultProductEntity().build();

        when(this.repository.save(any(ProductEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var savedProductEntity = this.service.save(productEntity);

        assertThat(savedProductEntity).isNotNull();
    }

    @Test
    @DisplayName("Given Id When Exists Then Return Existing Product")
    public void givenIdWhenExistsThenReturnExistingProduct() {
        var productEntity = defaultProductEntity().build();

        when(this.repository.findById(anyInt())).thenReturn(Optional.of(productEntity));

        var id = 1;
        var optionalProductEntity = this.service.findById(id);

        assertThat(optionalProductEntity).isPresent();
    }

}