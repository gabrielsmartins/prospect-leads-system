package br.pucminas.leads.application.ports.out;

import br.pucminas.leads.application.domain.Lead;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SearchLeadPort {

    Optional<Lead> findById(UUID id);

    List<Lead> findAll();

    List<Lead> findAllPendingReceivedLessThan(LocalDateTime dateTime);

}
