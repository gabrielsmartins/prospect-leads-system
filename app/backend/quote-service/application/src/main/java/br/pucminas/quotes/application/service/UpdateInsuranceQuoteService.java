package br.pucminas.quotes.application.service;

import br.pucminas.bff.common.stereotype.UseCase;
import br.pucminas.quotes.application.domain.InsuranceQuote;
import br.pucminas.quotes.application.domain.Product;
import br.pucminas.quotes.application.domain.exceptions.InsuranceQuoteNotFoundException;
import br.pucminas.quotes.application.domain.exceptions.ProductNotFoundException;
import br.pucminas.quotes.application.ports.in.UpdateInsuranceQuoteUseCase;
import br.pucminas.quotes.application.ports.out.SaveInsuranceQuotePort;
import br.pucminas.quotes.application.ports.out.SearchInsuranceQuotePort;
import br.pucminas.quotes.application.ports.out.SearchProductPort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class UpdateInsuranceQuoteService implements UpdateInsuranceQuoteUseCase {

    private final SearchInsuranceQuotePort searchInsuranceQuotePort;
    private final SearchProductPort searchProductPort;
    private final SaveInsuranceQuotePort saveInsuranceQuotePort;

    @Override
    public Mono<InsuranceQuote> update(UUID id, InsuranceQuote quote) {
        return this.searchInsuranceQuotePort.findById(id)
                                            .switchIfEmpty(Mono.error(new InsuranceQuoteNotFoundException(String.format("Insurance quote %s not found", id))))
                                            .flatMap(existingQuote -> process(id, quote, existingQuote));
    }

    private Mono<InsuranceQuote> process(UUID id, InsuranceQuote quote, InsuranceQuote existingQuote) {
        if (!existingQuote.getId().equals(quote.getId())) {
            return Mono.error(new InsuranceQuoteNotFoundException(String.format("Insurance quote id %s is not the same as the existing", id)));
        }

        var productId = quote.getProductId();
        return this.searchProductPort.findById(productId)
                .switchIfEmpty(Mono.error(new ProductNotFoundException(String.format("Product %s does not exist", productId))))
                .flatMap(product -> updateWithProduct(existingQuote, product));
    }

    private Mono<InsuranceQuote> updateWithProduct(InsuranceQuote existingQuote, Product product) {
        if (!product.isActive()) {
            return Mono.error(new ProductNotFoundException(String.format("Product %s is not active", product)));
        }
        existingQuote.setTotalMonthlyPremiumAmount(product.getTotalMonthlyPremiumAmount());
        existingQuote.setTotalYearlyPremiumAmount(product.getTotalYearlyPremiumAmount());
        existingQuote.setTotalCoverageAmount(product.getTotalCoverageAmount());
        product.getCoverages().forEach(existingQuote::addCoverage);
        product.getAssistances().forEach(existingQuote::addAssistance);
        return this.saveInsuranceQuotePort.save(existingQuote);
    }

}
