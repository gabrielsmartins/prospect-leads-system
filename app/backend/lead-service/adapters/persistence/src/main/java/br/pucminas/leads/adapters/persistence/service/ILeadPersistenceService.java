package br.pucminas.leads.adapters.persistence.service;

import br.pucminas.leads.adapters.persistence.entity.LeadEntity;

import java.util.Optional;
import java.util.UUID;

public interface ILeadPersistenceService {

    LeadEntity save(LeadEntity leadEntity);

    Optional<LeadEntity> findById(UUID id);

}
