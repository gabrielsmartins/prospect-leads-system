package br.pucminas.quotes.application.service;

import br.pucminas.quotes.application.domain.exceptions.ProductNotFoundException;
import br.pucminas.quotes.application.ports.out.SaveInsuranceQuotePort;
import br.pucminas.quotes.application.ports.out.SearchProductPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.HashMap;

import static br.pucminas.quotes.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;
import static br.pucminas.quotes.application.support.ProductSupport.defaultProduct;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CreateInsuranceQuoteServiceTest {

    private CreateInsuranceQuoteService service;
    private SaveInsuranceQuotePort saveInsuranceQuotePort;
    private SearchProductPort searchProductPort;

    @BeforeEach
    public void setup() {
        this.saveInsuranceQuotePort = mock(SaveInsuranceQuotePort.class);
        this.searchProductPort = mock(SearchProductPort.class);
        this.service = new CreateInsuranceQuoteService(this.saveInsuranceQuotePort, this.searchProductPort);
    }

    @Test
    @DisplayName("Given Quote When Create Then Return Created Quote")
    public void givenQuoteWhenCreateThenReturnCreatedQuote() {

        var quote = defaultInsuranceQuote()
                .withCoverages(new HashMap<>())
                .withAssistances(new ArrayList<>())
                .build();

        var product = defaultProduct()
                .withActive(true)
                .build();

        when(this.searchProductPort.findById(anyInt())).thenReturn(Mono.just(product));

        when(this.saveInsuranceQuotePort.save(quote)).thenAnswer(Mono::just);

        this.service.create(quote)
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();

        verify(this.saveInsuranceQuotePort, times(1)).save(quote);
        assertThat(quote.getCreatedAt()).isNotNull();
        assertThat(quote.getTotalMonthlyPremiumAmount()).isEqualByComparingTo(product.getSuggestedTotalMonthlyPremiumAmount());
        assertThat(quote.getTotalCoverageAmount()).isEqualByComparingTo(product.getTotalCoverageAmount());
        assertThat(quote.getCoverages()).isEqualTo(product.getCoverages());
        assertThat(quote.getAssistances()).isEqualTo(product.getAssistances());
    }

    @Test
    @DisplayName("Given Quote When Product Is Not Active Then Throw Exception")
    public void givenQuoteWhenProductIsNotActiveThenThrowException() {

        var quote = defaultInsuranceQuote().build();

        var product = defaultProduct()
                .withActive(false)
                .build();

        when(this.searchProductPort.findById(anyInt())).thenReturn(Mono.just(product));

        when(this.saveInsuranceQuotePort.save(quote)).thenAnswer(Mono::just);

        this.service.create(quote)
                .as(StepVerifier::create)
                .expectError(ProductNotFoundException.class)
                .verify();

        verify(this.saveInsuranceQuotePort, never()).save(quote);
    }

    @Test
    @DisplayName("Given Quote When Product Not Exists Then Throw Exception")
    public void givenQuoteWhenProductNotExistsThenThrowException() {

        var quote = defaultInsuranceQuote().build();

        when(this.searchProductPort.findById(anyInt())).thenReturn(Mono.empty());

        when(this.saveInsuranceQuotePort.save(quote)).thenAnswer(Mono::just);

        this.service.create(quote)
                    .as(StepVerifier::create)
                    .expectError(ProductNotFoundException.class)
                    .verify();

        verify(this.saveInsuranceQuotePort, never()).save(quote);
    }

}