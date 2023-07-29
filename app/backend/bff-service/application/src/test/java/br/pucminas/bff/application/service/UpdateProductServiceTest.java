package br.pucminas.bff.application.service;

import br.pucminas.bff.application.ports.out.products.UpdateProductPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static br.pucminas.bff.application.support.ProductSupport.defaultProduct;
import static org.mockito.Mockito.*;

class UpdateProductServiceTest {

    private UpdateProductService service;
    private UpdateProductPort port;

    @BeforeEach
    public void setup() {
        this.port = mock(UpdateProductPort.class);
        this.service = new UpdateProductService(this.port);
    }

    @Test
    @DisplayName("Given Id When Exists Then Return Updated Product")
    public void givenIdWhenExistsThenReturnUpdatedProduct() {
        var id = 1;
        var product = defaultProduct().build();

        when(this.port.update(id, product)).thenReturn(Mono.just(product));

        this.service.update(id, product)
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();

        verify(this.port, times(1)).update(id, product);
    }

}