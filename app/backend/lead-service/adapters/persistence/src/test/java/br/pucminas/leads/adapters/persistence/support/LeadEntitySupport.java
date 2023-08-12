package br.pucminas.leads.adapters.persistence.support;

import br.pucminas.leads.adapters.persistence.entity.LeadEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LeadEntitySupport {

    public static LeadEntity.LeadEntityBuilder defaultLeadEntity() {
        return LeadEntity.builder()
                   .withInsuranceQuoteId(UUID.randomUUID())
                   .withSent(false)
                   .withCreatedAt(LocalDateTime.now())
                   .withProcessedAt(LocalDateTime.now());
    }
}
