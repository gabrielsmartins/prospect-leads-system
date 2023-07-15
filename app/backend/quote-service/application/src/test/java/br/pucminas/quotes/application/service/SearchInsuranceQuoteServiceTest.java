package br.pucminas.quotes.application.service;

import br.pucminas.quotes.application.ports.out.SearchInsuranceQuotePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static br.pucminas.quotes.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;
import static org.mockito.Mockito.*;

class SearchInsuranceQuoteServiceTest {

    private SearchInsuranceQuoteService service;
    private SearchInsuranceQuotePort port;

    @BeforeEach
    public void setup() {
        this.port = mock(SearchInsuranceQuotePort.class);
        this.service = new SearchInsuranceQuoteService(this.port);
    }

    @Test
    @DisplayName("Given Id When Exists Then Existing Quote")
    public void givenIdWhenExistsThenExistingQuote() {
        var id = UUID.randomUUID();

        var product = defaultInsuranceQuote().build();

        when(this.port.findById(id)).thenReturn(Mono.just(product));

        this.service.findById(id)
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();

        verify(this.port, times(1)).findById(id);
    }

}