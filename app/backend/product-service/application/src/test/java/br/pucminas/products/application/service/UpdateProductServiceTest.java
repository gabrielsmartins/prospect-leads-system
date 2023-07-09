package br.pucminas.products.application.service;

import br.pucminas.products.application.domain.enums.CategoryEnum;
import br.pucminas.products.application.domain.Product;
import br.pucminas.products.application.domain.exceptions.ProductNotFoundException;
import br.pucminas.products.application.ports.out.SaveProductPort;
import br.pucminas.products.application.ports.out.SearchProductPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static br.pucminas.products.application.support.ProductSupport.defaultProduct;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class UpdateProductServiceTest {

    private UpdateProductService service;
    private SearchProductPort searchProductPort;
    private SaveProductPort saveProductPort;

    @BeforeEach
    public void setup() {
        this.searchProductPort = mock(SearchProductPort.class);
        this.saveProductPort = mock(SaveProductPort.class);
        this.service = new UpdateProductService(this.searchProductPort, this.saveProductPort);
    }

    @Test
    @DisplayName("Given Id And Product When Exists Then Return Updated Product")
    public void givenIdAndProductWhenExistsThenReturnUpdatedProduct() {

        var id = 1;

        var existingProduct = defaultProduct()
                                    .withId(id)
                                    .withName("Foo")
                                    .withActive(false)
                                    .withCategory(CategoryEnum.ENTERPRISE)
                                    .build();

        when(this.searchProductPort.findById(id)).thenReturn(Optional.of(existingProduct));
        when(this.saveProductPort.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var product = defaultProduct()
                .withId(id)
                .build();

        var updatedProduct = this.service.update(id, product);

        assertThat(updatedProduct).isNotNull();
        verify(this.saveProductPort, times(1)).save(updatedProduct);
    }

    @Test
    @DisplayName("Given Id And Product When Exists And Id Is Invalid Then Throw Exception")
    public void givenIdAndProductWhenExistsAndIdIsInvalidThenThrowException() {

        var id = 1;

        var existingProduct = defaultProduct()
                .withId(id)
                .withName("Foo")
                .withActive(false)
                .withCategory(CategoryEnum.ENTERPRISE)
                .build();

        when(this.searchProductPort.findById(id)).thenReturn(Optional.of(existingProduct));
        when(this.saveProductPort.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var product = defaultProduct()
                .withId(2)
                .build();
        assertThatThrownBy(() -> this.service.update(id, product)).isInstanceOf(ProductNotFoundException.class);
    }

    @Test
    @DisplayName("Given Id And Product When Not Exists Then Throw Exception")
    public void givenIdAndProductWhenNotExistsThenThrowException() {

        var id = 1;
        var product = defaultProduct().build();

        when(this.searchProductPort.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> this.service.update(id, product)).isInstanceOf(ProductNotFoundException.class);
    }

}