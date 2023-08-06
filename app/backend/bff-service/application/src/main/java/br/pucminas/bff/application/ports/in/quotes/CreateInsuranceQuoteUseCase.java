package br.pucminas.bff.application.ports.in.quotes;


import br.pucminas.bff.application.domain.InsuranceQuote;
import reactor.core.publisher.Mono;

public interface CreateInsuranceQuoteUseCase {

    Mono<InsuranceQuote> create(InsuranceQuote quote);

}
