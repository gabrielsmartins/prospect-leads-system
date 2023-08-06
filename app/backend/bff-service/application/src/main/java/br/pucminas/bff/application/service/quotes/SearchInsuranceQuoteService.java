package br.pucminas.bff.application.service.quotes;

import br.pucminas.bff.application.domain.InsuranceQuote;
import br.pucminas.bff.application.ports.in.quotes.SearchInsuranceQuoteUseCase;
import br.pucminas.bff.application.ports.out.quotes.SearchInsuranceQuotePort;
import br.pucminas.bff.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class SearchInsuranceQuoteService implements SearchInsuranceQuoteUseCase {

    private final SearchInsuranceQuotePort port;

    @Override
    public Mono<InsuranceQuote> findById(UUID id) {
        return this.port.findById(id);
    }

}
