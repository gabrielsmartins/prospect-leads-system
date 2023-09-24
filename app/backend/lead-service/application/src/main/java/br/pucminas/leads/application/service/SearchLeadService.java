package br.pucminas.leads.application.service;

import br.pucminas.leads.application.domain.Lead;
import br.pucminas.leads.application.domain.exceptions.LeadNotFoundException;
import br.pucminas.leads.application.ports.in.SearchLeadUseCase;
import br.pucminas.leads.application.ports.out.SearchLeadPort;
import br.pucminas.leads.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class SearchLeadService implements SearchLeadUseCase {

    private final SearchLeadPort port;
    @Override
    public Lead findById(UUID id) {
        return this.port.findById(id)
                        .orElseThrow(() -> new LeadNotFoundException(String.format("Lead %s not found", id)));
    }

}
