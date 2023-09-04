package br.pucminas.leads.application.service;

import br.pucminas.leads.application.domain.InsuranceQuote;
import br.pucminas.leads.application.domain.Lead;
import br.pucminas.leads.application.ports.in.ProcessLeadUseCase;
import br.pucminas.leads.application.ports.in.ReceiveLeadUseCase;
import br.pucminas.leads.application.ports.out.SaveLeadPort;
import br.pucminas.leads.application.ports.out.SearchLeadPort;
import br.pucminas.leads.application.ports.out.SendLeadPort;
import br.pucminas.leads.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.UUID;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class ReceiveLeadService implements ReceiveLeadUseCase {

    private final EnrichLeadService enrichLeadService;
    private final SearchLeadPort searchLeadPort;
    private final SaveLeadPort saveLeadPort;

    @Override
    public void receive(Lead lead) {
        var insuranceQuote = lead.getInsuranceQuote();
        var id = insuranceQuote.getId();
        if (this.searchLeadPort.findById(id).isPresent()) {
            log.warn("Lead {} already exists", id);
            return;
        }
        this.enrichLeadService.enrich(lead);
        this.saveLeadPort.save(lead);
    }

}
