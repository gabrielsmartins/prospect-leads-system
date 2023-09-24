package br.pucminas.leads.adapters.persistence.service;

import br.pucminas.leads.adapters.persistence.entity.LeadEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ILeadPersistenceService {

    LeadEntity save(LeadEntity leadEntity);
    List<LeadEntity> findAll();

    Optional<LeadEntity> findById(UUID id);

    List<LeadEntity> findAllPendingReceivedLessThan(LocalDateTime dateTime);

}
