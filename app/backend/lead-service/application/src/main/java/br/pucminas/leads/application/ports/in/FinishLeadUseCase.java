package br.pucminas.leads.application.ports.in;

import java.util.UUID;

public interface FinishLeadUseCase {

    void finish(UUID id);

}
