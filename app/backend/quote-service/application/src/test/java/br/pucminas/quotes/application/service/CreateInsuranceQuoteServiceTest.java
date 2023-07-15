package br.pucminas.quotes.application.service;

import br.pucminas.quotes.application.ports.out.SaveInsuranceQuotePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static br.pucminas.quotes.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;
import static org.mockito.Mockito.*;

class CreateInsuranceQuoteServiceTest {

    private CreateInsuranceQuoteService service;
    private SaveInsuranceQuotePort port;

    @BeforeEach
    public void setup() {
        this.port = mock(SaveInsuranceQuotePort.class);
        this.service = new CreateInsuranceQuoteService(this.port);
    }

    @Test
    @DisplayName("Given Quote When Create Then Return Created Quote")
    public void givenQuoteWhenCreateThenReturnCreatedQuote() {

        var quote = defaultInsuranceQuote().build();

        when(this.port.save(quote)).thenAnswer(Mono::just);

        this.service.create(quote)
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();

        verify(this.port, times(1)).save(quote);
    }

}