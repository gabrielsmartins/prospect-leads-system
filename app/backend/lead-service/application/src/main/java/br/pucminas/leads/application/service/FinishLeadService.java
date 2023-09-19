package br.pucminas.leads.application.service;

import br.pucminas.leads.application.domain.exceptions.LeadNotFoundException;
import br.pucminas.leads.application.ports.in.FinishLeadUseCase;
import br.pucminas.leads.application.ports.out.SaveLeadPort;
import br.pucminas.leads.application.ports.out.SearchLeadPort;
import br.pucminas.leads.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@UseCase
@RequiredArgsConstructor
public class FinishLeadService implements FinishLeadUseCase {

    private final SearchLeadPort searchLeadPort;
    private final SaveLeadPort saveLeadPort;

    @Override
    public void finish(UUID id) {
        var lead = this.searchLeadPort.findById(id)
                                      .orElseThrow(() -> new LeadNotFoundException(String.format("Lead %s not found", id)));
        lead.setFinished(true);
        lead.setFinishedAt(LocalDateTime.now());
        this.saveLeadPort.save(lead);
    }

}
