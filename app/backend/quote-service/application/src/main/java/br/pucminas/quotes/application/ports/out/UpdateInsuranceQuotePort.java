package br.pucminas.quotes.application.ports.out;

import br.pucminas.quotes.application.domain.InsuranceQuote;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UpdateInsuranceQuotePort {

    Mono<InsuranceQuote> update(UUID id, InsuranceQuote quote);

}
