package br.pucminas.leads.application.service;

import br.pucminas.leads.application.domain.Lead;
import br.pucminas.leads.application.domain.exceptions.InsuranceQuoteNotFoundException;
import br.pucminas.leads.application.domain.exceptions.ProductNotFoundException;
import br.pucminas.leads.application.ports.out.SearchInsuranceQuotePort;
import br.pucminas.leads.application.ports.out.SearchProductPort;
import br.pucminas.leads.application.service.discount.CompoundDiscountApplier;
import br.pucminas.leads.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ApplyDiscountService {

    private final SearchInsuranceQuotePort searchInsuranceQuotePort;
    private final SearchProductPort searchProductPort;
    private final CompoundDiscountApplier compoundDiscountApplier;

    public void applyDiscount(Lead lead) {
        var insuranceQuoteId = lead.getInsuranceQuoteId();
        var insuranceQuote = this.searchInsuranceQuotePort.findById(insuranceQuoteId)
                                                          .orElseThrow(() -> new InsuranceQuoteNotFoundException(String.format("Insurance quote %s not found", insuranceQuoteId)));
        var productId = insuranceQuote.getProductId();
        var product = this.searchProductPort.findById(productId)
                                            .orElseThrow(() -> new ProductNotFoundException(String.format("Product %s not found", productId)));
        insuranceQuote.setProduct(product);
        this.compoundDiscountApplier.apply(insuranceQuote);
    }

}
