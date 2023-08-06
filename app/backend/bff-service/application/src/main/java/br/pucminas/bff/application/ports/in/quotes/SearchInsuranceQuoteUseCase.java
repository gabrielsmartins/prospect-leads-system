package br.pucminas.bff.application.ports.in.quotes;


import br.pucminas.bff.application.domain.InsuranceQuote;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SearchInsuranceQuoteUseCase {

    Mono<InsuranceQuote> findById(UUID id);

}
