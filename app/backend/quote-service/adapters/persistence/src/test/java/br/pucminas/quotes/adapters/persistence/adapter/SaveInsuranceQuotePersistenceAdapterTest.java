package br.pucminas.quotes.adapters.persistence.adapter;


import br.pucminas.quotes.adapters.persistence.entity.InsuranceQuoteEntity;
import br.pucminas.quotes.adapters.persistence.service.InsuranceQuotePersistenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static br.pucminas.quotes.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SaveInsuranceQuotePersistenceAdapterTest {

    private SaveInsuranceQuotePersistenceAdapter adapter;
    private InsuranceQuotePersistenceService service;

    @BeforeEach
    public void setup() {
        this.service = mock(InsuranceQuotePersistenceService.class);
        this.adapter = new SaveInsuranceQuotePersistenceAdapter(this.service);
    }

    @Test
    @DisplayName("Given Insurance Quote When Save Then Return Saved Insurance Quote")
    public void givenInsuranceQuoteWhenSaveThenReturnSavedInsuranceQuote() {
        var insuranceQuote = defaultInsuranceQuote().build();

        when(this.service.save(any(InsuranceQuoteEntity.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        this.adapter.save(insuranceQuote)
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();
    }

}