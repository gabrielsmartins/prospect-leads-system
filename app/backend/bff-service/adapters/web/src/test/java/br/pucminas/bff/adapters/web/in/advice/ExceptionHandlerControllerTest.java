package br.pucminas.bff.adapters.web.in.advice;

import br.pucminas.bff.adapters.web.config.ObjectMapperConfiguration;
import br.pucminas.bff.adapters.web.in.products.CreateProductController;
import br.pucminas.bff.application.domain.Product;
import br.pucminas.bff.application.ports.in.products.CreateProductUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static br.pucminas.bff.adapters.web.config.ControllerRoutes.PRODUCTS_ROUTE;
import static br.pucminas.bff.adapters.web.in.support.ProductDtoSupport.defaultCreateProductDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = CreateProductController.class)
@Import(ObjectMapperConfiguration.class)
class ExceptionHandlerControllerTest {

    @MockBean
    private CreateProductUseCase useCase;

    @Autowired
    private WebTestClient webClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Given Product When Is Invalid Then Return Bad Request")
    public void givenProductWhenIsInvalidThenReturnBadRequest() throws JsonProcessingException {

        var productDto = defaultCreateProductDto()
                .withName(null)
                .build();

        var body = objectMapper.writeValueAsString(productDto);

        webClient.post()
                .uri(PRODUCTS_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(body))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("fields").isNotEmpty();
    }

    @Test
    @DisplayName("Given Product When Error Then Return Internal Server Error")
    public void givenProductWhenErrorThenReturnInternalServerError() throws JsonProcessingException {

        var productDto = defaultCreateProductDto()
                .build();

        var body = objectMapper.writeValueAsString(productDto);

        when(useCase.create(any(Product.class))).thenAnswer(invocation -> Mono.error(new RuntimeException("Error")));

        webClient.post()
                .uri(PRODUCTS_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(body))
                .exchange()
                .expectStatus().is5xxServerError()
                .expectBody()
                .jsonPath("code").isNotEmpty();
    }

}