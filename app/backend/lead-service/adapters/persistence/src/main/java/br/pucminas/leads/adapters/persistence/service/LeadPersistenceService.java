package br.pucminas.leads.adapters.persistence.service;

import br.pucminas.leads.adapters.persistence.entity.LeadEntity;
import br.pucminas.leads.adapters.persistence.repository.LeadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LeadPersistenceService implements ILeadPersistenceService {

    private final LeadRepository repository;

    @Override
    public LeadEntity save(LeadEntity lead) {
        return this.repository.save(lead);
    }

    @Override
    public Optional<LeadEntity> findById(UUID id) {
        return this.repository.findById(id);
    }

    @Override
    public List<LeadEntity> findAllPendingReceivedLessThan(LocalDateTime dateTime) {
        return this.repository.findAllPendingReceivedLessThan(dateTime);
    }
}
