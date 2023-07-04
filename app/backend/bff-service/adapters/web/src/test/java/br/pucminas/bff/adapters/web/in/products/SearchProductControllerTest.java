package br.pucminas.bff.adapters.web.in.products;

import br.pucminas.bff.adapters.web.config.ObjectMapperConfiguration;
import br.pucminas.bff.application.ports.in.SearchProductUseCase;
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
import static br.pucminas.bff.application.support.ProductSupport.defaultProduct;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = SearchProductController.class)
@Import(ObjectMapperConfiguration.class)
class SearchProductControllerTest {

    @MockBean
    private SearchProductUseCase useCase;

    @Autowired
    private WebTestClient webClient;

    @Test
    @DisplayName("Given Id When Exists Then Return Existing Product")
    public void givenIdWhenExistsThenReturnExistingProduct() throws JsonProcessingException {

        var product = defaultProduct().build();

        var id = 1;
        when(useCase.findById(id)).thenReturn(Mono.just(product));

        webClient.get()
                .uri(PRODUCT_ROUTE + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("id").isNotEmpty();

        verify(this.useCase, times(1)).findById(anyInt());
    }

}