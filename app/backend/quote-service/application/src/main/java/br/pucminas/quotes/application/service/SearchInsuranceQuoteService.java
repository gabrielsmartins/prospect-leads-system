package br.pucminas.quotes.application.service;

import br.pucminas.quotes.application.domain.InsuranceQuote;
import br.pucminas.quotes.application.ports.in.SearchInsuranceQuoteUseCase;
import br.pucminas.quotes.application.ports.out.SearchInsuranceQuotePort;
import br.pucminas.quotes.common.stereotype.UseCase;
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
