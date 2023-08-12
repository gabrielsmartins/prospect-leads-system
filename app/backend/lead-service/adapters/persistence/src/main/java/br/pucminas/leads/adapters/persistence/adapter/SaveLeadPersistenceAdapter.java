package br.pucminas.leads.adapters.persistence.adapter;

import br.pucminas.leads.adapters.persistence.adapter.mapper.LeadPersistenceMapper;
import br.pucminas.leads.adapters.persistence.service.ILeadPersistenceService;
import br.pucminas.leads.application.domain.Lead;
import br.pucminas.leads.application.ports.out.SaveLeadPort;
import br.pucminas.leads.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static net.logstash.logback.marker.Markers.append;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
public class SaveLeadPersistenceAdapter implements SaveLeadPort {

    private final ILeadPersistenceService service;

    @Override
    public Lead save(Lead lead) {
        log.info(append("lead", lead), "Mapping lead");
        var leadEntity = LeadPersistenceMapper.mapToEntity(lead);
        log.info(append("lead", leadEntity), "Lead was mapped successfully");

        log.info(append("lead", lead), "Saving lead");
        var savedLeadEntity = this.service.save(leadEntity);
        log.info(append("lead", savedLeadEntity), "Lead was saved successfully");

        log.info(append("lead", savedLeadEntity), "Mapping saved lead");
        var savedLead = LeadPersistenceMapper.mapToDomain(savedLeadEntity);
        log.info(append("lead", savedLead), "Saved Lead was mapped successfully");
        return savedLead;
    }

}
