package br.pucminas.leads.application.service;

import br.pucminas.leads.application.domain.Lead;
import br.pucminas.leads.application.ports.in.ProcessLeadUseCase;
import br.pucminas.leads.application.ports.out.SaveLeadPort;
import br.pucminas.leads.application.ports.out.SendLeadPort;
import br.pucminas.leads.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@UseCase
@RequiredArgsConstructor
public class ProcessLeadService implements ProcessLeadUseCase {

    private final ApplyDiscountService applyDiscountService;
    private final SendLeadPort sendLeadPort;
    private final SaveLeadPort saveLeadPort;

    @Override
    public void process(Lead lead) {
        this.applyDiscountService.applyDiscount(lead);
        lead.setProcessedAt(LocalDateTime.now());
        this.sendLeadPort.send(lead);
        lead.setSent(true);
        this.saveLeadPort.save(lead);
    }

}
