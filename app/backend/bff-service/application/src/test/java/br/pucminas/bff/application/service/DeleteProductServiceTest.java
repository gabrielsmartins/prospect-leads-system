package br.pucminas.bff.application.service;

import br.pucminas.bff.application.ports.out.DeleteProductPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class DeleteProductServiceTest {

    private DeleteProductService service;
    private DeleteProductPort port;

    @BeforeEach
    public void setup() {
        this.port = mock(DeleteProductPort.class);
        this.service = new DeleteProductService(this.port);
    }

    @Test
    @DisplayName("Given Id When Exists Then Delete Product")
    public void givenIdWhenExistsThenDeleteProduct() {
        var id = 1;


        when(this.port.delete(id)).thenReturn(Mono.just(true).then());

        this.service.delete(id)
                    .as(StepVerifier::create)
                    .expectComplete()
                    .verify();

        verify(this.port, times(1)).delete(id);
    }

}