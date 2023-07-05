package br.pucminas.bff.adapters.web.in.products;

import br.pucminas.bff.adapters.web.config.ObjectMapperConfiguration;
import br.pucminas.bff.application.domain.Product;
import br.pucminas.bff.application.ports.in.UpdateProductUseCase;
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
import static br.pucminas.bff.adapters.web.in.support.ProductDtoSupport.defaultUpdateProductDto;
import static br.pucminas.bff.application.support.ProductSupport.defaultProduct;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = UpdateProductController.class)
@Import(ObjectMapperConfiguration.class)
class UpdateProductControllerTest {

    @MockBean
    private UpdateProductUseCase useCase;

    @Autowired
    private WebTestClient webClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Given Id When Exists Then Return Updated Product")
    public void givenIdWhenExistsThenReturnUpdatedProduct() throws JsonProcessingException {

        var productDto = defaultUpdateProductDto().build();

        var body = objectMapper.writeValueAsString(productDto);

        var product = defaultProduct().build();
        when(useCase.update(anyInt(), any(Product.class))).thenAnswer(invocation -> Mono.just(product));

        webClient.patch()
                 .uri(PRODUCTS_ROUTE + "/{id}", productDto.getId())
                 .contentType(MediaType.APPLICATION_JSON)
                 .accept(MediaType.APPLICATION_JSON)
                 .body(BodyInserters.fromValue(body))
                 .exchange()
                 .expectStatus().isOk()
                 .expectBody()
                 .jsonPath("id").isNotEmpty();

        verify(this.useCase, times(1)).update(anyInt(), any(Product.class));
    }

}