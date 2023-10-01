package br.pucminas.quotes.application.service;

import br.pucminas.quotes.common.stereotype.UseCase;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class UpdateInsuranceQuoteService implements UpdateInsuranceQuoteUseCase {

    private final SearchInsuranceQuotePort searchInsuranceQuotePort;
    private final SearchProductPort searchProductPort;
    private final SaveInsuranceQuotePort saveInsuranceQuotePort;

    @Override
    public Mono<InsuranceQuote> update(UUID id, Integer productId) {
        return this.findInsuranceQuote(id)
                   .flatMap(existingInsuranceQuote -> this.findActiveProduct(productId)
                                                          .flatMap(product -> this.updateWithProduct(existingInsuranceQuote, product)));
    }

    @Override
    public Mono<InsuranceQuote> update(UUID id, InsuranceQuote insuranceQuote) {
        return this.findInsuranceQuote(id)
                   .flatMap(existingInsuranceQuote -> {
                       var productId = insuranceQuote.getProductId();
                       return this.findActiveProduct(productId)
                                  .flatMap(product -> this.update(insuranceQuote));
                   });
    }


    private Mono<InsuranceQuote> findInsuranceQuote(UUID id) {
        return this.searchInsuranceQuotePort.findById(id)
                                            .switchIfEmpty(Mono.error(new InsuranceQuoteNotFoundException(String.format("Insurance quote %s not found", id))));
    }

    private Mono<Product> findActiveProduct(Integer productId) {
        return this.searchProductPort.findById(productId)
                                     .switchIfEmpty(Mono.error(new ProductNotFoundException(String.format("Product %s does not exist", productId))))
                                     .flatMap(product -> {
                                        if (!product.isActive()) {
                                            return Mono.error(new ProductNotFoundException(String.format("Product %s is not active", product.getId())));
                                        }
                                        return Mono.just(product);
                                     });
    }


    private Mono<InsuranceQuote> update(InsuranceQuote quote) {
        quote.setUpdatedAt(LocalDateTime.now());
        return this.saveInsuranceQuotePort.save(quote);
    }

    private Mono<InsuranceQuote> updateWithProduct(InsuranceQuote quote, Product product) {
        quote.setTotalMonthlyPremiumAmount(product.getSuggestedTotalMonthlyPremiumAmount());
        quote.setTotalCoverageAmount(product.getTotalCoverageAmount());
        var coverageIds = new ArrayList<>(quote.getCoverages().keySet());
        coverageIds.forEach(quote::removeCoverage);
        quote.getAssistances().forEach(quote::removeAssistance);

        product.getCoverages().forEach(quote::addCoverage);
        product.getAssistances().forEach(quote::addAssistance);
        quote.setUpdatedAt(LocalDateTime.now());
        return this.saveInsuranceQuotePort.save(quote);
    }

}
