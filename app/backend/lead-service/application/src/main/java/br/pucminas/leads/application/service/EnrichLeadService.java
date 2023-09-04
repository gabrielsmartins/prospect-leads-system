package br.pucminas.leads.application.service;

import br.pucminas.leads.application.domain.Lead;
import br.pucminas.leads.application.domain.exceptions.InsuranceQuoteNotFoundException;
import br.pucminas.leads.application.domain.exceptions.ProductNotFoundException;
import br.pucminas.leads.application.ports.out.SearchInsuranceQuotePort;
import br.pucminas.leads.application.ports.out.SearchProductPort;
import br.pucminas.leads.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class EnrichLeadService {

    private final SearchInsuranceQuotePort searchInsuranceQuotePort;
    private final SearchProductPort searchProductPort;

    public void enrich(Lead lead) {
        var insuranceQuote = lead.getInsuranceQuote();
        var insuranceQuoteId = insuranceQuote.getId();
        var existingInsuranceQuote = this.searchInsuranceQuotePort.findById(insuranceQuoteId)
                                                                  .orElseThrow(() -> new InsuranceQuoteNotFoundException(String.format("Insurance quote %s not found", insuranceQuoteId)));
        var productId = existingInsuranceQuote.getProductId();
        var product = this.searchProductPort.findById(productId)
                                            .orElseThrow(() -> new ProductNotFoundException(String.format("Product %s not found", productId)));
        existingInsuranceQuote.setProduct(product);
        lead.setInsuranceQuote(existingInsuranceQuote);
    }

}
