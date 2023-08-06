package br.pucminas.quotes.application.service;

import br.pucminas.quotes.application.domain.InsuranceQuote;
import br.pucminas.quotes.application.domain.exceptions.InsuranceQuoteNotFoundException;
import br.pucminas.quotes.application.domain.exceptions.ProductNotFoundException;
import br.pucminas.quotes.application.ports.out.SaveInsuranceQuotePort;
import br.pucminas.quotes.application.ports.out.SearchInsuranceQuotePort;
import br.pucminas.quotes.application.ports.out.SearchProductPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static br.pucminas.quotes.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;
import static br.pucminas.quotes.application.support.ProductSupport.defaultProduct;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UpdateInsuranceQuoteServiceTest {

    private UpdateInsuranceQuoteService service;
    private SearchInsuranceQuotePort searchInsuranceQuotePort;
    private SearchProductPort searchProductPort;
    private SaveInsuranceQuotePort saveInsuranceQuotePort;

    @BeforeEach
    public void setup() {
        this.searchInsuranceQuotePort = mock(SearchInsuranceQuotePort.class);
        this.searchProductPort = mock(SearchProductPort.class);
        this.saveInsuranceQuotePort = mock(SaveInsuranceQuotePort.class);
        this.service = new UpdateInsuranceQuoteService(this.searchInsuranceQuotePort, this.searchProductPort, this.saveInsuranceQuotePort);
    }

    @Test
    @DisplayName("Given Id When Product Exists And Insurance Quote Exists Then Return Updated Quote")
    public void givenIdWhenProductExistsAndInsuranceQuoteExistsThenReturnUpdatedQuote() {
        var id = UUID.randomUUID();
        var existingQuote = defaultInsuranceQuote()
                            .withId(id)
                            .build();

        var product = defaultProduct().withActive(true).build();
        when(this.searchProductPort.findById(anyInt())).thenReturn(Mono.just(product));
        when(this.searchInsuranceQuotePort.findById(id)).thenReturn(Mono.just(existingQuote));

        var quote= defaultInsuranceQuote()
                        .withId(id)
                        .build();

        when(this.saveInsuranceQuotePort.save(any(InsuranceQuote.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        this.service.update(id, quote)
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();

        verify(this.saveInsuranceQuotePort, times(1)).save(any(InsuranceQuote.class));
        assertThat(quote.getUpdatedAt()).isNotNull();
        assertThat(quote.getTotalYearlyPremiumAmount()).isEqualByComparingTo(product.getTotalYearlyPremiumAmount());
        assertThat(quote.getTotalMonthlyPremiumAmount()).isEqualByComparingTo(product.getTotalMonthlyPremiumAmount());
        assertThat(quote.getTotalCoverageAmount()).isEqualByComparingTo(product.getTotalCoverageAmount());
        assertThat(quote.getCoverages()).isEqualTo(product.getCoverages());
        assertThat(quote.getAssistances()).isEqualTo(product.getAssistances());
    }

    @Test
    @DisplayName("Given Id When Insurance Quote Exists And Is Invalid Then Throw Exception")
    public void givenIdWhenInsuranceQuoteExistsAndIsInvalidThenThrowException() {
        var id = UUID.randomUUID();
        var existingQuote = defaultInsuranceQuote()
                .withId(id)
                .build();

        when(this.searchInsuranceQuotePort.findById(id)).thenReturn(Mono.just(existingQuote));

        var quote= defaultInsuranceQuote()
                    .withId(UUID.randomUUID())
                    .build();

        when(this.saveInsuranceQuotePort.save(any(InsuranceQuote.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        this.service.update(id, quote)
                    .as(StepVerifier::create)
                    .expectError(InsuranceQuoteNotFoundException.class)
                    .verify();

        verify(this.saveInsuranceQuotePort, never()).save(any(InsuranceQuote.class));
    }

    @Test
    @DisplayName("Given Id When Insurance Quote Not Exists Then Throw Exception")
    public void givenIdWhenInsuranceQuoteNotExistsThenThrowException() {
        var id = UUID.randomUUID();

        when(this.searchInsuranceQuotePort.findById(id)).thenReturn(Mono.empty());

        var quote= defaultInsuranceQuote()
                .withId(UUID.randomUUID())
                .build();

        when(this.saveInsuranceQuotePort.save(any(InsuranceQuote.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        this.service.update(id, quote)
                .as(StepVerifier::create)
                .expectError(InsuranceQuoteNotFoundException.class)
                .verify();

        verify(this.saveInsuranceQuotePort, never()).save(any(InsuranceQuote.class));
    }

    @Test
    @DisplayName("Given Id When Product Not Exists And Insurance Quote Exists Then Return Updated Quote")
    public void givenIdWhenProductNotExistsAndInsuranceQuoteExistsThenReturnUpdatedQuote() {
        var id = UUID.randomUUID();
        var existingQuote = defaultInsuranceQuote()
                .withId(id)
                .build();

        when(this.searchProductPort.findById(anyInt())).thenReturn(Mono.empty());
        when(this.searchInsuranceQuotePort.findById(id)).thenReturn(Mono.just(existingQuote));

        var quote= defaultInsuranceQuote()
                .withId(id)
                .build();

        when(this.saveInsuranceQuotePort.save(any(InsuranceQuote.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        this.service.update(id, quote)
                    .as(StepVerifier::create)
                    .expectError(ProductNotFoundException.class)
                    .verify();

        verify(this.saveInsuranceQuotePort, never()).save(any(InsuranceQuote.class));
    }

    @Test
    @DisplayName("Given Id When Product Exists And Is Not Active And Insurance Quote Exists Then Return Updated Quote")
    public void givenIdWhenProductExistsAndIsNotActiveAndInsuranceQuoteExistsThenReturnUpdatedQuote() {
        var id = UUID.randomUUID();
        var existingQuote = defaultInsuranceQuote()
                .withId(id)
                .build();

        var product = defaultProduct().withActive(false).build();
        when(this.searchProductPort.findById(anyInt())).thenReturn(Mono.just(product));
        when(this.searchInsuranceQuotePort.findById(id)).thenReturn(Mono.just(existingQuote));

        var quote= defaultInsuranceQuote()
                .withId(id)
                .build();

        when(this.saveInsuranceQuotePort.save(any(InsuranceQuote.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        this.service.update(id, quote)
                .as(StepVerifier::create)
                .expectError(ProductNotFoundException.class)
                .verify();

        verify(this.saveInsuranceQuotePort, never()).save(any(InsuranceQuote.class));
    }

}