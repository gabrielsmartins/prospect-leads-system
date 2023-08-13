package br.pucminas.quotes.application.ports.in;

import br.pucminas.quotes.application.domain.InsuranceQuote;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UpdateInsuranceQuoteUseCase {

    Mono<InsuranceQuote> update(UUID id, Integer productId);

    Mono<InsuranceQuote> update(UUID id, InsuranceQuote insuranceQuote);

}
