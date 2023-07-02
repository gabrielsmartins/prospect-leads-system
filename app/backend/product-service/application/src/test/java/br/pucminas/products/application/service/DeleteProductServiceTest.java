package br.pucminas.products.application.service;


import br.pucminas.products.application.domain.Product;
import br.pucminas.products.application.domain.exceptions.ProductNotFoundException;
import br.pucminas.products.application.ports.out.SaveProductPort;
import br.pucminas.products.application.ports.out.SearchProductPort;
import br.pucminas.products.application.support.ProductSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class DeleteProductServiceTest {

    private DeleteProductService service;
    private SearchProductPort searchProductPort;
    private SaveProductPort saveProductPort;

    @BeforeEach
    public void setup() {
        this.searchProductPort = mock(SearchProductPort.class);
        this.saveProductPort = mock(SaveProductPort.class);
        this.service = new DeleteProductService(this.searchProductPort, this.saveProductPort);
    }

    @Test
    @DisplayName("Given Id And Product When Exists Then Return Deleted Product")
    public void givenIdAndProductWhenExistsThenReturnDeletedProduct() {

        var id = 1;

        var existingProduct = ProductSupport.defaultProduct()
                                    .withDeletedAt(null)
                                    .build();

        var argumentCaptor = ArgumentCaptor.forClass(Product.class);

        when(this.searchProductPort.findById(id)).thenReturn(Optional.of(existingProduct));
        when(this.saveProductPort.save(argumentCaptor.capture())).thenAnswer(invocation -> invocation.getArgument(0));

        this.service.delete(id);

        var deletedProduct = argumentCaptor.getValue();

        assertThat(deletedProduct).isNotNull();
        assertThat(deletedProduct.isActive()).isFalse();
        assertThat(deletedProduct.getDeletedAt()).isNotNull();
        verify(this.saveProductPort, times(1)).save(existingProduct);
    }

    @Test
    @DisplayName("Given Id And Product When Not Exists Then Throw Exception")
    public void givenIdAndProductWhenNotExistsThenThrowException() {

        var id = 1;

        when(this.searchProductPort.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> this.service.delete(id)).isInstanceOf(ProductNotFoundException.class);
    }

}