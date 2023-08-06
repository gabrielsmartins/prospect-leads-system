package br.pucminas.bff.adapters.web.in.quotes;


import br.pucminas.bff.adapters.web.config.ObjectMapperConfiguration;
import br.pucminas.bff.application.domain.InsuranceQuote;
import br.pucminas.bff.application.ports.in.quotes.UpdateInsuranceQuoteUseCase;
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

import java.util.UUID;

import static br.pucminas.bff.adapters.web.config.ControllerRoutes.INSURANCE_QUOTES_ROUTE;
import static br.pucminas.bff.adapters.web.in.support.InsuranceQuoteDtoSupport.defaultUpdateInsuranceQuoteDto;
import static br.pucminas.bff.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = UpdateInsuranceQuoteController.class)
@Import(ObjectMapperConfiguration.class)
class UpdateInsuranceQuoteControllerTest {

    @MockBean
    private UpdateInsuranceQuoteUseCase useCase;

    @Autowired
    private WebTestClient webClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Given Id When Exists Then Return Updated Quote")
    public void givenIdWhenExistsThenReturnUpdatedQuote() throws JsonProcessingException {

        var quoteDto = defaultUpdateInsuranceQuoteDto().build();

        var body = objectMapper.writeValueAsString(quoteDto);

        var quote = defaultInsuranceQuote().build();
        when(useCase.update(any(UUID.class), any(InsuranceQuote.class))).thenAnswer(invocation -> Mono.just(quote));

        webClient.patch()
                 .uri(INSURANCE_QUOTES_ROUTE + "/{id}", quoteDto.getId())
                 .contentType(MediaType.APPLICATION_JSON)
                 .accept(MediaType.APPLICATION_JSON)
                 .body(BodyInserters.fromValue(body))
                 .exchange()
                 .expectStatus().isOk()
                 .expectBody()
                 .jsonPath("id").isNotEmpty();

        verify(this.useCase, times(1)).update(any(UUID.class), any(InsuranceQuote.class));
    }

}