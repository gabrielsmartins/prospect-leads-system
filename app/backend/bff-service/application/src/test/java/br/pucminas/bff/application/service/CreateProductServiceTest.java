package br.pucminas.bff.application.service;

import br.pucminas.bff.application.ports.out.products.CreateProductPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static br.pucminas.bff.application.support.ProductSupport.defaultProduct;
import static org.mockito.Mockito.*;

class CreateProductServiceTest {

    private CreateProductService service;
    private CreateProductPort port;

    @BeforeEach
    public void setup() {
        this.port = mock(CreateProductPort.class);
        this.service = new CreateProductService(this.port);
    }

    @Test
    @DisplayName("Given Product When Create Then Return Created Product")
    public void givenProductWhenCreateThenReturnCreatedProduct() {

        var product = defaultProduct().build();

        when(this.port.create(product)).thenAnswer(Mono::just);

        this.service.create(product)
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();

        verify(this.port, times(1)).create(product);
    }

}