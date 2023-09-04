package br.pucminas.leads.application.ports.in;

import br.pucminas.leads.application.domain.Lead;

import java.util.List;

public interface ProcessLeadUseCase {

    void process(List<Lead> leads);

}
