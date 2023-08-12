package br.pucminas.leads.application.ports.out;

import br.pucminas.leads.application.domain.Lead;

public interface SaveLeadPort {

    Lead save(Lead lead);

}
