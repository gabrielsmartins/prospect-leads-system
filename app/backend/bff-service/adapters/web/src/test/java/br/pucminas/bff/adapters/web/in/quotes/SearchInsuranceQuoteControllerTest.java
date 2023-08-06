package br.pucminas.bff.adapters.web.in.quotes;

import br.pucminas.bff.adapters.web.config.ObjectMapperConfiguration;
import br.pucminas.bff.application.ports.in.quotes.SearchInsuranceQuoteUseCase;
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

import java.util.UUID;

import static br.pucminas.bff.adapters.web.config.ControllerRoutes.INSURANCE_QUOTES_ROUTE;
import static br.pucminas.bff.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = SearchInsuranceQuoteController.class)
@Import(ObjectMapperConfiguration.class)
class SearchInsuranceQuoteControllerTest {

    @MockBean
    private SearchInsuranceQuoteUseCase useCase;

    @Autowired
    private WebTestClient webClient;

    @Test
    @DisplayName("Given Id When Exists Then Return Existing Quote")
    public void givenIdWhenExistsThenReturnExistingQuote() throws JsonProcessingException {

        var insuranceQuote = defaultInsuranceQuote().build();

        var id = UUID.randomUUID();
        when(useCase.findById(id)).thenReturn(Mono.just(insuranceQuote));

        webClient.get()
                 .uri(INSURANCE_QUOTES_ROUTE + "/{id}", id)
                 .accept(MediaType.APPLICATION_JSON)
                 .exchange()
                 .expectStatus().isOk()
                 .expectBody()
                 .jsonPath("id").isNotEmpty();

        verify(this.useCase, times(1)).findById(any(UUID.class));
    }

}