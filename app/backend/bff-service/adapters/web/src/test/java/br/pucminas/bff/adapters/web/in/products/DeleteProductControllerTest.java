package br.pucminas.bff.adapters.web.in.products;

import br.pucminas.bff.adapters.web.config.ObjectMapperConfiguration;
import br.pucminas.bff.application.ports.in.DeleteProductUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static br.pucminas.bff.adapters.web.config.Routes.PRODUCT_ROUTE;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = DeleteProductController.class)
@Import(ObjectMapperConfiguration.class)
class DeleteProductControllerTest {

    @MockBean
    private DeleteProductUseCase useCase;

    @Autowired
    private WebTestClient webClient;

    @Test
    @DisplayName("Given Id When Exists Then Delete Product")
    public void givenIdWhenExistsThenDeleteProduct() throws JsonProcessingException {

        int id = 1;

        when(this.useCase.delete(id)).thenReturn(Mono.just(true).then());

        webClient.delete()
                .uri(PRODUCT_ROUTE + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNoContent()
                .expectBody()
                .isEmpty();

        verify(this.useCase, times(1)).delete(anyInt());
    }

}