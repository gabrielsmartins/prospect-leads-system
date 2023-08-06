package br.pucminas.quotes.adapters.web.advice;

import br.pucminas.quotes.adapters.web.config.ObjectMapperConfiguration;
import br.pucminas.quotes.adapters.web.in.CreateInsuranceQuoteController;
import br.pucminas.quotes.application.domain.InsuranceQuote;
import br.pucminas.quotes.application.ports.in.CreateInsuranceQuoteUseCase;
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

import static br.pucminas.quotes.adapters.web.config.ControllerRoutes.INSURANCE_QUOTES_ROUTE;
import static br.pucminas.quotes.adapters.web.in.support.InsuranceQuoteDtoSupport.defaultCreateInsuranceQuoteDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = CreateInsuranceQuoteController.class)
@Import(ObjectMapperConfiguration.class)
class ExceptionHandlerControllerTest {

    @MockBean
    private CreateInsuranceQuoteUseCase useCase;

    @Autowired
    private WebTestClient webClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Given Quote When Is Invalid Then Return Bad Request")
    public void givenQuoteWhenIsInvalidThenReturnBadRequest() throws JsonProcessingException {

        var quoteDto = defaultCreateInsuranceQuoteDto()
                .withType(null)
                .build();

        var body = objectMapper.writeValueAsString(quoteDto);

        webClient.post()
                .uri(INSURANCE_QUOTES_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(body))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("fields").isNotEmpty();
    }

    @Test
    @DisplayName("Given Quote When Error Then Return Internal Server Error")
    public void givenQuoteWhenErrorThenReturnInternalServerError() throws JsonProcessingException {

        var quoteDto = defaultCreateInsuranceQuoteDto()
                .build();

        var body = objectMapper.writeValueAsString(quoteDto);

        when(useCase.create(any(InsuranceQuote.class))).thenAnswer(invocation -> Mono.error(new RuntimeException("Error")));

        webClient.post()
                .uri(INSURANCE_QUOTES_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(body))
                .exchange()
                .expectStatus().is5xxServerError()
                .expectBody()
                .jsonPath("code").isNotEmpty();
    }

}