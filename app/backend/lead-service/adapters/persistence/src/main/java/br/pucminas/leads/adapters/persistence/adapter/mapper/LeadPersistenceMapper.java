package br.pucminas.leads.adapters.persistence.adapter.mapper;

import br.pucminas.leads.adapters.persistence.entity.LeadEntity;
import br.pucminas.leads.application.domain.Lead;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LeadPersistenceMapper {

    public static LeadEntity mapToEntity(Lead lead) {
        if (lead == null) {
            return null;
        }
        var insuranceQuoteEntity = InsuranceQuotePersistenceMapper.mapToEntity(lead.getInsuranceQuote());
        return LeadEntity.builder()
                         .withId(lead.getId())
                         .withInsuranceQuote(insuranceQuoteEntity)
                         .withCreatedAt(lead.getCreatedAt())
                         .withProcessedAt(lead.getProcessedAt())
                         .withSent(lead.isSent())
                         .build();
    }

    public static Lead mapToDomain(LeadEntity leadEntity) {
        if (leadEntity == null) {
            return null;
        }
        var insuranceQuote = InsuranceQuotePersistenceMapper.mapToDomain(leadEntity.getInsuranceQuote());
        return Lead.builder()
                   .withId(leadEntity.getId())
                   .withInsuranceQuote(insuranceQuote)
                   .withCreatedAt(leadEntity.getCreatedAt())
                   .withProcessedAt(leadEntity.getProcessedAt())
                   .withSent(leadEntity.isSent())
                   .build();
    }

}
