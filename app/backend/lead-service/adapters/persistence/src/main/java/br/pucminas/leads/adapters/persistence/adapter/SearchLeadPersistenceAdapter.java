package br.pucminas.leads.adapters.persistence.adapter;

import br.pucminas.leads.adapters.persistence.adapter.mapper.LeadPersistenceMapper;
import br.pucminas.leads.adapters.persistence.service.ILeadPersistenceService;
import br.pucminas.leads.application.domain.Lead;
import br.pucminas.leads.application.ports.out.SearchLeadPort;
import br.pucminas.leads.common.stereotype.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static net.logstash.logback.argument.StructuredArguments.kv;
import static net.logstash.logback.marker.Markers.append;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
public class SearchLeadPersistenceAdapter implements SearchLeadPort {

    private final ILeadPersistenceService service;

    @Override
    public Optional<Lead> findById(UUID id) {
        log.info("Searching lead by id {}", kv("id", id));
        return this.service.findById(id)
                           .map(LeadPersistenceMapper::mapToDomain);
    }

    @Override
    public List<Lead> findAllPendingReceivedLessThan(LocalDateTime dateTime) {
        log.info("Searching all by received less than {}", kv("datetime", dateTime));
        return this.service.findAllPendingReceivedLessThan(dateTime)
                           .stream()
                           .peek(leadEntity -> log.info(append("lead", leadEntity), "Mapping lead"))
                           .map(LeadPersistenceMapper::mapToDomain)
                           .peek(lead -> log.info(append("lead", lead), "Lead was mapped successfully"))
                           .collect(Collectors.toList());
    }

}
