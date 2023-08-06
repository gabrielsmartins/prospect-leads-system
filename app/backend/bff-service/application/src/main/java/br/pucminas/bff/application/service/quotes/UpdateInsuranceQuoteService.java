package br.pucminas.bff.application.service.quotes;

import br.pucminas.bff.application.domain.InsuranceQuote;
import br.pucminas.bff.application.ports.in.quotes.UpdateInsuranceQuoteUseCase;
import br.pucminas.bff.application.ports.out.quotes.UpdateInsuranceQuotePort;
import br.pucminas.bff.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class UpdateInsuranceQuoteService implements UpdateInsuranceQuoteUseCase {

    private final UpdateInsuranceQuotePort port;

    @Override
    public Mono<InsuranceQuote> update(UUID id, InsuranceQuote quote) {
        return this.port.update(id, quote);
    }

}
