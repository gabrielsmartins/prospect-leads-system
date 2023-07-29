package br.pucminas.bff.application.service;

import br.pucminas.bff.application.ports.out.products.SearchProductPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static br.pucminas.bff.application.support.ProductSupport.defaultProduct;
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
    @DisplayName("Given Id When Exists Then Existing Product")
    public void givenIdWhenExistsThenExistingProduct() {
        var id = 1;

        var product = defaultProduct().build();

        when(this.port.findById(id)).thenReturn(Mono.just(product));

        this.service.findById(id)
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();

        verify(this.port, times(1)).findById(id);
    }

}