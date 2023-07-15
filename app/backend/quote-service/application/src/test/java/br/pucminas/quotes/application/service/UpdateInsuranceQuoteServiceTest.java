package br.pucminas.quotes.application.service;

import br.pucminas.quotes.application.domain.exceptions.InsuranceQuoteNotFoundException;
import br.pucminas.quotes.application.ports.out.SearchInsuranceQuotePort;
import br.pucminas.quotes.application.ports.out.UpdateInsuranceQuotePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static br.pucminas.quotes.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;
import static org.mockito.Mockito.*;

class UpdateInsuranceQuoteServiceTest {

    private UpdateInsuranceQuoteService service;
    private SearchInsuranceQuotePort searchInsuranceQuotePort;
    private UpdateInsuranceQuotePort updateInsuranceQuotePort;

    @BeforeEach
    public void setup() {
        this.searchInsuranceQuotePort = mock(SearchInsuranceQuotePort.class);
        this.updateInsuranceQuotePort = mock(UpdateInsuranceQuotePort.class);
        this.service = new UpdateInsuranceQuoteService(this.searchInsuranceQuotePort, this.updateInsuranceQuotePort);
    }

    @Test
    @DisplayName("Given Id When Exists Then Return Updated Quote")
    public void givenIdWhenExistsThenReturnUpdatedQuote() {
        var id = UUID.randomUUID();
        var existingQuote = defaultInsuranceQuote()
                            .withId(id)
                            .build();

        when(this.searchInsuranceQuotePort.findById(id)).thenReturn(Mono.just(existingQuote));

        var quote= defaultInsuranceQuote()
                        .withId(id)
                        .build();

        when(this.updateInsuranceQuotePort.update(id, quote)).thenReturn(Mono.just(quote));

        this.service.update(id, quote)
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();

        verify(this.updateInsuranceQuotePort, times(1)).update(id, quote);
    }

    @Test
    @DisplayName("Given Id When Exists And Is Invalid Then Throw Exception")
    public void givenIdWhenExistsAndIsInvalidThenThrowException() {
        var id = UUID.randomUUID();
        var existingQuote = defaultInsuranceQuote()
                .withId(id)
                .build();

        when(this.searchInsuranceQuotePort.findById(id)).thenReturn(Mono.just(existingQuote));

        var quote= defaultInsuranceQuote()
                    .withId(UUID.randomUUID())
                    .build();

        when(this.updateInsuranceQuotePort.update(id, existingQuote)).thenReturn(Mono.just(quote));

        this.service.update(id, quote)
                    .as(StepVerifier::create)
                    .expectError(InsuranceQuoteNotFoundException.class)
                    .verify();

        verify(this.updateInsuranceQuotePort, never()).update(id, quote);
    }

    @Test
    @DisplayName("Given Id When Not Exists Then Throw Exception")
    public void givenIdWhenNotExistsThenThrowException() {
        var id = UUID.randomUUID();

        when(this.searchInsuranceQuotePort.findById(id)).thenReturn(Mono.empty());

        var quote= defaultInsuranceQuote()
                .withId(UUID.randomUUID())
                .build();

        when(this.updateInsuranceQuotePort.update(id, quote)).thenReturn(Mono.just(quote));

        this.service.update(id, quote)
                .as(StepVerifier::create)
                .expectError(InsuranceQuoteNotFoundException.class)
                .verify();

        verify(this.updateInsuranceQuotePort, never()).update(id, quote);
    }

}