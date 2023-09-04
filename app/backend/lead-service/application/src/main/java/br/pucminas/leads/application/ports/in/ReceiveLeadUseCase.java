package br.pucminas.leads.application.ports.in;

import br.pucminas.leads.application.domain.Lead;

public interface ReceiveLeadUseCase {

    void receive(Lead lead);

}
