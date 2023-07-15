package br.pucminas.quotes.application.ports.in;

import br.pucminas.quotes.application.domain.InsuranceQuote;
import reactor.core.publisher.Mono;

public interface CreateInsuranceQuoteUseCase {

    Mono<InsuranceQuote> create(InsuranceQuote quote);

}
