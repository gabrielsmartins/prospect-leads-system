package br.pucminas.leads.application.support;

import br.pucminas.leads.application.domain.Lead;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LeadSupport {

    public static Lead.LeadBuilder defaultLead() {
        return Lead.builder()
                   .withInsuranceQuoteId(UUID.randomUUID())
                   .withSent(false)
                   .withCreatedAt(LocalDateTime.now())
                   .withProcessedAt(LocalDateTime.now());
    }
}
