package br.pucminas.products.application.service;

import br.pucminas.products.application.domain.exceptions.ProductNotFoundException;
import br.pucminas.products.application.ports.out.SearchProductPort;
import br.pucminas.products.application.support.ProductSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class SearchProductServiceTest {

    private SearchProductService service;
    private SearchProductPort port;
    @BeforeEach
    public void setup() {
        this.port = mock(SearchProductPort.class);
        this.service = new SearchProductService(this.port);
    }

    @Test
    @DisplayName("Given Id And Product When Exists Then Return Existing Product")
    public void givenIdAndProductWhenExistsThenReturnExistingProduct() {

        var id = 1;

        var existingProduct = ProductSupport.defaultProduct()
                                .withDeletedAt(null)
                                .build();


        when(this.port.findById(id)).thenReturn(Optional.of(existingProduct));

        var product = this.service.findById(id);

        assertThat(product).isNotNull();
        verify(this.port, times(1)).findById(id);
    }

    @Test
    @DisplayName("Given Id And Product When Not Exists Then Throw Exception")
    public void givenIdAndProductWhenNotExistsThenThrowException() {

        var id = 1;

        when(this.port.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> this.service.findById(id)).isInstanceOf(ProductNotFoundException.class);
    }

}