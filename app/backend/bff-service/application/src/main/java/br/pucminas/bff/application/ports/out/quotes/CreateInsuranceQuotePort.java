package br.pucminas.bff.application.ports.out.quotes;


import br.pucminas.bff.application.domain.InsuranceQuote;
import reactor.core.publisher.Mono;

public interface CreateInsuranceQuotePort {

    Mono<InsuranceQuote> create(InsuranceQuote quote);

}
