package br.pucminas.leads.application.ports.out;

import br.pucminas.leads.application.domain.Lead;

public interface SaveLeadPort {

    void save(Lead lead);

}
