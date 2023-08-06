package br.pucminas.bff.application.service.quotes;

import br.pucminas.bff.application.domain.InsuranceQuote;
import br.pucminas.bff.application.ports.in.quotes.CreateInsuranceQuoteUseCase;
import br.pucminas.bff.application.ports.out.quotes.CreateInsuranceQuotePort;
import br.pucminas.bff.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
public class CreateInsuranceQuoteService implements CreateInsuranceQuoteUseCase {

    private final CreateInsuranceQuotePort port;

    @Override
    public Mono<InsuranceQuote> create(InsuranceQuote quote) {
        return this.port.create(quote);
    }

}
