package br.pucminas.quotes.adapters.persistence.service;

import br.pucminas.quotes.adapters.persistence.repository.InsuranceQuoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;


class InsuranceQuoteServiceTest {

    private InsuranceQuoteService service;
    private InsuranceQuoteRepository repository;

    @BeforeEach
    public void setup() {
        this.repository = mock(InsuranceQuoteRepository.class);
        this.service = new InsuranceQuoteService(this.repository);
    }

    @Test
    @DisplayName("Given Insurance Quote When Save Then Return Saved InsuranceQuote")
    public void givenInsuranceQuoteWhenSaveThenReturnSavedInsuranceQuote() {

    }


}