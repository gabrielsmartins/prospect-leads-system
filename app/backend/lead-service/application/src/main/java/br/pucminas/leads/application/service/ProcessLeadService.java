package br.pucminas.leads.application.service;

import br.pucminas.leads.application.domain.Lead;
import br.pucminas.leads.application.domain.exceptions.InsuranceQuoteNotFoundException;
import br.pucminas.leads.application.ports.in.ProcessLeadUseCase;
import br.pucminas.leads.application.ports.out.SaveLeadPort;
import br.pucminas.leads.application.ports.out.SearchInsuranceQuotePort;
import br.pucminas.leads.application.ports.out.SearchLeadPort;
import br.pucminas.leads.application.ports.out.SendLeadPort;
import br.pucminas.leads.application.service.discount.CompoundDiscountApplier;
import br.pucminas.leads.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@UseCase
@RequiredArgsConstructor
public class ProcessLeadService implements ProcessLeadUseCase {

    private final SearchLeadPort searchLeadPort;
    private final CompoundDiscountApplier compoundDiscountApplier;
    private final SearchInsuranceQuotePort searchInsuranceQuotePort;
    private final SendLeadPort sendLeadPort;
    private final SaveLeadPort saveLeadPort;

    @Override
    public void process() {
        LocalDateTime datetime = LocalDateTime.now().minusMinutes(3);
        var leads = this.searchLeadPort.findAllPendingReceivedLessThan(datetime);
        leads.forEach(this::process);
    }

    private void process(Lead lead) {
        var insuranceQuoteId = lead.getInsuranceQuote().getId();
        var insuranceQuote = this.searchInsuranceQuotePort.findById(insuranceQuoteId)
                                                          .orElseThrow(() -> new InsuranceQuoteNotFoundException(String.format("Insurance quote %s not found", insuranceQuoteId)));
        if (insuranceQuote.isFinished()) {
            lead.setInsuranceQuote(insuranceQuote);
            lead.setFinished(true);
            this.saveLeadPort.save(lead);
            return;
        }
        lead.setInsuranceQuote(insuranceQuote);
        this.compoundDiscountApplier.apply(lead.getInsuranceQuote());
        lead.setProcessedAt(LocalDateTime.now());
        this.sendLeadPort.send(lead);
        lead.setFinished(true);
        this.saveLeadPort.save(lead);
    }

}
