package br.pucminas.bff.application.service.quotes;

import br.pucminas.bff.application.ports.out.quotes.UpdateInsuranceQuotePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static br.pucminas.bff.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UpdateInsuranceQuoteServiceTest {

    private UpdateInsuranceQuoteService service;
    private UpdateInsuranceQuotePort port;

    @BeforeEach
    public void setup() {
        this.port = mock(UpdateInsuranceQuotePort.class);
        this.service = new UpdateInsuranceQuoteService(this.port);
    }

    @Test
    @DisplayName("Given Id And Insurance Quote When Update Then Updated Insurance Quote")
    public void givenIdAndInsuranceQuoteWhenUpdateThenUpdatedInsuranceQuote() {
        var id = UUID.randomUUID();
        var insuranceQuote = defaultInsuranceQuote().build();
        when(this.port.update(id, insuranceQuote)).thenAnswer(invocation -> Mono.just(invocation.getArgument(1)));
        this.service.update(id, insuranceQuote)
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();
    }

}