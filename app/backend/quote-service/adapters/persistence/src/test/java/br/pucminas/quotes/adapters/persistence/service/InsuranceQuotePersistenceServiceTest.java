package br.pucminas.quotes.adapters.persistence.service;

import br.pucminas.quotes.adapters.persistence.repository.InsuranceQuoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static br.pucminas.quotes.adapters.persistence.support.InsuranceQuoteEntitySupport.defaultInsuranceQuoteEntity;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class InsuranceQuotePersistenceServiceTest {

    private InsuranceQuotePersistenceService service;
    private InsuranceQuoteRepository repository;

    @BeforeEach
    public void setup() {
        this.repository = mock(InsuranceQuoteRepository.class);
        this.service = new InsuranceQuotePersistenceService(this.repository);
    }

    @Test
    @DisplayName("Given Insurance Quote When Save Then Return Saved InsuranceQuote")
    public void givenInsuranceQuoteWhenSaveThenReturnSavedInsuranceQuote() {
        var insuranceQuoteEntity = defaultInsuranceQuoteEntity().build();

        when(this.repository.save(insuranceQuoteEntity)).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        this.service.save(insuranceQuoteEntity)
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();
    }

    @Test
    @DisplayName("Given Id When Exists Then Return Insurance Quote")
    public void givenIdWhenExistsThenReturnInsuranceQuote() {
        var id = UUID.randomUUID();
        var insuranceQuoteEntity = defaultInsuranceQuoteEntity().build();
        when(this.repository.findById(id)).thenReturn(Mono.just(insuranceQuoteEntity));
        this.service.findById(id)
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();
    }

}