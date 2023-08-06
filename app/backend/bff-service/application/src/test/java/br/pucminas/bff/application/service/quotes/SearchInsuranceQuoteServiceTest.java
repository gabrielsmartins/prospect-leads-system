package br.pucminas.bff.application.service.quotes;


import br.pucminas.bff.application.ports.out.quotes.SearchInsuranceQuotePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static br.pucminas.bff.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SearchInsuranceQuoteServiceTest {

    private SearchInsuranceQuoteService service;
    private SearchInsuranceQuotePort port;

    @BeforeEach
    public void setup() {
        this.port = mock(SearchInsuranceQuotePort.class);
        this.service = new SearchInsuranceQuoteService(this.port);
    }

    @Test
    @DisplayName("Given Id When Exists Then Return Insurance Quote")
    public void givenIdWhenExistsThenReturnInsuranceQuote() {
        var id = UUID.randomUUID();
        var insuranceQuote = defaultInsuranceQuote().build();
        when(this.port.findById(id)).thenReturn(Mono.just(insuranceQuote));
        this.service.findById(id)
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();
    }

}