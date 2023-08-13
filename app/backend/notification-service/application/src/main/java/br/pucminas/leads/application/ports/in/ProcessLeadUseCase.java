package br.pucminas.leads.application.ports.in;

import br.pucminas.leads.application.domain.Lead;

public interface ProcessLeadUseCase {

    void process(Lead lead);

}
