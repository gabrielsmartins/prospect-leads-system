package br.pucminas.bff.application.ports.in.quotes;


import br.pucminas.bff.application.domain.InsuranceQuote;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UpdateInsuranceQuoteUseCase {

    Mono<InsuranceQuote> update(UUID id, InsuranceQuote quote);

}
