package br.pucminas.quotes.application.service;

import br.pucminas.bff.common.stereotype.UseCase;
import br.pucminas.quotes.application.domain.InsuranceQuote;
import br.pucminas.quotes.application.domain.exceptions.InsuranceQuoteNotFoundException;
import br.pucminas.quotes.application.ports.in.UpdateInsuranceQuoteUseCase;
import br.pucminas.quotes.application.ports.out.SearchInsuranceQuotePort;
import br.pucminas.quotes.application.ports.out.UpdateInsuranceQuotePort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class UpdateInsuranceQuoteService implements UpdateInsuranceQuoteUseCase {

    private final SearchInsuranceQuotePort searchInsuranceQuotePort;
    private final UpdateInsuranceQuotePort updateInsuranceQuotePort;

    @Override
    public Mono<InsuranceQuote> update(UUID id, InsuranceQuote quote) {
        return this.searchInsuranceQuotePort.findById(id)
                                            .switchIfEmpty(Mono.error(new InsuranceQuoteNotFoundException(String.format("Insurance quote %s not found", id))))
                                            .flatMap(existingQuote -> {
                                                if (!existingQuote.getId().equals(quote.getId())) {
                                                    return Mono.error(new InsuranceQuoteNotFoundException(String.format("Insurance quote id %s is not the same as the existing", id)));
                                                }
                                                return this.updateInsuranceQuotePort.update(id, quote);
                                            });

    }

}
