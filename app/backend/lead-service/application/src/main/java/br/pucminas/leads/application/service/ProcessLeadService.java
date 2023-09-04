package br.pucminas.leads.application.service;

import br.pucminas.leads.application.domain.Lead;
import br.pucminas.leads.application.ports.in.ProcessLeadUseCase;
import br.pucminas.leads.application.ports.out.SaveLeadPort;
import br.pucminas.leads.application.ports.out.SearchLeadPort;
import br.pucminas.leads.application.ports.out.SendLeadPort;
import br.pucminas.leads.application.service.discount.CompoundDiscountApplier;
import br.pucminas.leads.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ProcessLeadService implements ProcessLeadUseCase {

    private final SearchLeadPort searchLeadPort;
    private final CompoundDiscountApplier compoundDiscountApplier;
    private final SendLeadPort sendLeadPort;
    private final SaveLeadPort saveLeadPort;

    @Override
    public void process(List<Lead> leads) {
       leads.forEach(this::process);
    }

    private void process(Lead lead) {
        this.compoundDiscountApplier.apply(lead.getInsuranceQuote());
        lead.setProcessedAt(LocalDateTime.now());
        this.sendLeadPort.send(lead);
        lead.setSent(true);
        this.saveLeadPort.save(lead);
    }

}
