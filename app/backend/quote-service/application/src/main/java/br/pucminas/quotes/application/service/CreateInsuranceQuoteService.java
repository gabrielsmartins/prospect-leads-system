package br.pucminas.quotes.application.service;


import br.pucminas.quotes.application.domain.InsuranceQuote;
import br.pucminas.quotes.application.ports.in.CreateInsuranceQuoteUseCase;
import br.pucminas.quotes.application.ports.out.SaveInsuranceQuotePort;
import br.pucminas.bff.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
public class CreateInsuranceQuoteService implements CreateInsuranceQuoteUseCase {

    private final SaveInsuranceQuotePort port;

    @Override
    public Mono<InsuranceQuote> create(InsuranceQuote quote) {
        return this.port.save(quote);
    }

}
