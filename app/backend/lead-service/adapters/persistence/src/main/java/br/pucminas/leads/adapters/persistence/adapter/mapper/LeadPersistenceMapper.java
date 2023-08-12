package br.pucminas.leads.adapters.persistence.adapter.mapper;

import br.pucminas.leads.adapters.persistence.entity.LeadEntity;
import br.pucminas.leads.application.domain.Lead;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LeadPersistenceMapper {

    public static LeadEntity mapToEntity(Lead lead) {
        return LeadEntity.builder()
                         .withInsuranceQuoteId(lead.getInsuranceQuoteId())
                         .withCreatedAt(lead.getCreatedAt())
                         .withProcessedAt(lead.getProcessedAt())
                         .withSent(lead.isSent())
                         .build();
    }

    public static Lead mapToDomain(LeadEntity leadEntity) {
        return Lead.builder()
                   .withInsuranceQuoteId(leadEntity.getInsuranceQuoteId())
                   .withCreatedAt(leadEntity.getCreatedAt())
                   .withProcessedAt(leadEntity.getProcessedAt())
                   .withSent(leadEntity.isSent())
                   .build();
    }

}
