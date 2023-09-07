package br.pucminas.leads.adapters.persistence.support;

import br.pucminas.leads.adapters.persistence.entity.LeadEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

import static br.pucminas.leads.adapters.persistence.support.InsuranceQuoteEntitySupport.defaultInsuranceQuoteEntity;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LeadEntitySupport {

    public static LeadEntity.LeadEntityBuilder defaultLeadEntity() {
        return LeadEntity.builder()
                   .withId(UUID.randomUUID())
                   .withSent(false)
                   .withCreatedAt(LocalDateTime.now())
                   .withProcessedAt(LocalDateTime.now())
                   .withInsuranceQuote(defaultInsuranceQuoteEntity().build());
    }
}
