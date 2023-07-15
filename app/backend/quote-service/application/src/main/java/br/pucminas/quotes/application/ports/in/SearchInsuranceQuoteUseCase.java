package br.pucminas.quotes.application.ports.in;

import br.pucminas.quotes.application.domain.InsuranceQuote;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SearchInsuranceQuoteUseCase {

    Mono<InsuranceQuote> findById(UUID id);

}
