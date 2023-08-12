package br.pucminas.bff.application.service.quotes;

import br.pucminas.bff.application.domain.InsuranceQuote;
import br.pucminas.bff.application.ports.in.quotes.CreateInsuranceQuoteUseCase;
import br.pucminas.bff.application.ports.out.leads.CaptureLeadPort;
import br.pucminas.bff.application.ports.out.quotes.CreateInsuranceQuotePort;
import br.pucminas.bff.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
public class CreateInsuranceQuoteService implements CreateInsuranceQuoteUseCase {

    private final CreateInsuranceQuotePort createInsuranceQuotePort;
    private final CaptureLeadPort captureLeadPort;

    @Override
    public Mono<InsuranceQuote> create(InsuranceQuote quote) {
        return this.createInsuranceQuotePort.create(quote)
                                            .flatMap(this::captureLead);
    }

    public Mono<InsuranceQuote> captureLead(InsuranceQuote quote) {
        var id = quote.getId();
        var productId = quote.getProductId();
        return this.captureLeadPort.capture(id)
                                   .then(Mono.just(quote));
    }

}
