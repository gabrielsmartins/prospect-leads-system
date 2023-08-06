package br.pucminas.bff.application.ports.out.quotes;


import br.pucminas.bff.application.domain.InsuranceQuote;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SearchInsuranceQuotePort {

    Mono<InsuranceQuote> findById(UUID id);

}
