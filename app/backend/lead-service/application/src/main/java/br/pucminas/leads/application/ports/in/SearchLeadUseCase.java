package br.pucminas.leads.application.ports.in;

import br.pucminas.leads.application.domain.Lead;

import java.util.UUID;

public interface SearchLeadUseCase {

    Lead findById(UUID id);

}
