package br.pucminas.leads.application.support;

import br.pucminas.leads.application.domain.Lead;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

import static br.pucminas.leads.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LeadSupport {

    public static Lead.LeadBuilder defaultLead() {
        return Lead.builder()
                   .withId(UUID.randomUUID())
                   .withInsuranceQuote(defaultInsuranceQuote().build())
                   .withSent(false)
                   .withCreatedAt(LocalDateTime.now())
                   .withProcessedAt(LocalDateTime.now());
    }

}
