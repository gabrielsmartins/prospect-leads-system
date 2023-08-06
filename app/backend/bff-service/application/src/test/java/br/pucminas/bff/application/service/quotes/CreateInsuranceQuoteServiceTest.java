package br.pucminas.bff.application.service.quotes;


import br.pucminas.bff.application.ports.out.quotes.CreateInsuranceQuotePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static br.pucminas.bff.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreateInsuranceQuoteServiceTest {

    private CreateInsuranceQuoteService service;
    private CreateInsuranceQuotePort port;

    @BeforeEach
    public void setup() {
        this.port = mock(CreateInsuranceQuotePort.class);
        this.service = new CreateInsuranceQuoteService(this.port);
    }

    @Test
    @DisplayName("Given Insurance Quote When Create Then Return Created Insurance Quote")
    public void givenInsuranceQuoteWhenCreateThenReturnCreatedInsuranceQuote() {
        var insuranceQuote = defaultInsuranceQuote().build();
        when(this.port.create(insuranceQuote)).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));
        this.service.create(insuranceQuote)
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();
    }

}