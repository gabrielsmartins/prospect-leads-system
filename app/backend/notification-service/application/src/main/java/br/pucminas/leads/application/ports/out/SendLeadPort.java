package br.pucminas.leads.application.ports.out;

import br.pucminas.leads.application.domain.Lead;

public interface SendLeadPort {

    void send(Lead lead);

}
