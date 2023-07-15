package br.pucminas.quotes.application.ports.out;

import br.pucminas.quotes.application.domain.InsuranceQuote;
import reactor.core.publisher.Mono;

public interface SaveInsuranceQuotePort {

    Mono<InsuranceQuote> save(InsuranceQuote insuranceQuote);

}
