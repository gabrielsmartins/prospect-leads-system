package br.pucminas.quotes.adapters.persistence.adapter;


import br.pucminas.quotes.adapters.persistence.service.InsuranceQuotePersistenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static br.pucminas.quotes.adapters.persistence.support.InsuranceQuoteEntitySupport.defaultInsuranceQuoteEntity;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SearchInsuranceQuotePersistenceAdapterTest {

    private SearchInsuranceQuotePersistenceAdapter adapter;
    private InsuranceQuotePersistenceService service;

    @BeforeEach
    public void setup() {
        this.service = mock(InsuranceQuotePersistenceService.class);
        this.adapter = new SearchInsuranceQuotePersistenceAdapter(this.service);
    }

    @Test
    @DisplayName("Given Id When Exists Then Return Insurance Quote")
    public void givenIdWhenExistsThenReturnInsuranceQuote() {
        var id = UUID.randomUUID();
        var insuranceQuoteEntity = defaultInsuranceQuoteEntity().build();
        when(this.service.findById(id)).thenReturn(Mono.just(insuranceQuoteEntity));
        this.adapter.findById(id)
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();
    }

}