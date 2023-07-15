package br.pucminas.quotes.adapters.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.test.StepVerifier;

import static br.pucminas.quotes.adapters.persistence.support.InsuranceQuoteEntitySupport.defaultInsuranceQuoteEntity;

@DataMongoTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class InsuranceQuoteRepositoryTest {

    private final InsuranceQuoteRepository repository;

    @Test
    @DisplayName("Given Insurance Quote When Save Then Return Saved InsuranceQuote")
    public void givenInsuranceQuoteWhenSaveThenReturnSavedInsuranceQuote() {
        var insuranceQuoteEntity = defaultInsuranceQuoteEntity().build();
        this.repository.save(insuranceQuoteEntity)
                       .as(StepVerifier::create)
                       .expectNextCount(1)
                       .verifyComplete();
    }

    @Test
    @DisplayName("Given Id When Exists Then Return Insurance Quote")
    public void givenIdWhenExistsThenReturnInsuranceQuote() {
        var insuranceQuoteEntity = defaultInsuranceQuoteEntity().build();
        this.repository.save(insuranceQuoteEntity).block();
        this.repository.findById(insuranceQuoteEntity.getId())
                       .as(StepVerifier::create)
                       .expectNextCount(1)
                       .verifyComplete();
    }

}