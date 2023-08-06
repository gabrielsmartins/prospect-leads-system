package br.pucminas.quotes.application.service;


import br.pucminas.bff.common.stereotype.UseCase;
import br.pucminas.quotes.application.domain.InsuranceQuote;
import br.pucminas.quotes.application.domain.Product;
import br.pucminas.quotes.application.domain.exceptions.ProductNotFoundException;
import br.pucminas.quotes.application.ports.in.CreateInsuranceQuoteUseCase;
import br.pucminas.quotes.application.ports.out.SaveInsuranceQuotePort;
import br.pucminas.quotes.application.ports.out.SearchProductPort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@UseCase
@RequiredArgsConstructor
public class CreateInsuranceQuoteService implements CreateInsuranceQuoteUseCase {

    private final SaveInsuranceQuotePort saveInsuranceQuotePort;
    private final SearchProductPort searchProductPort;

    @Override
    public Mono<InsuranceQuote> create(InsuranceQuote quote) {
        var productId = quote.getProductId();
        quote.setCreatedAt(LocalDateTime.now());
        return this.searchProductPort.findById(productId)
                                     .switchIfEmpty(Mono.error(new ProductNotFoundException(String.format("Product %s does not exist", productId))))
                                     .flatMap(product -> updateWithProduct(quote, product));
    }

    private Mono<InsuranceQuote> updateWithProduct(InsuranceQuote quote, Product product) {
        if (!product.isActive()) {
            return Mono.error(new ProductNotFoundException(String.format("Product %s is not active", product)));
        }
        quote.setTotalMonthlyPremiumAmount(product.getTotalMonthlyPremiumAmount());
        quote.setTotalYearlyPremiumAmount(product.getTotalYearlyPremiumAmount());
        quote.setTotalCoverageAmount(product.getTotalCoverageAmount());
        product.getCoverages().forEach(quote::addCoverage);
        product.getAssistances().forEach(quote::addAssistance);
        return this.saveInsuranceQuotePort.save(quote);
    }

}
