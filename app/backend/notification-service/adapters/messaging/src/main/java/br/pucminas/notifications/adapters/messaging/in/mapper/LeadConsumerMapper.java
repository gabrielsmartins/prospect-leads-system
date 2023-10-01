package br.pucminas.notifications.adapters.messaging.in.mapper;

import br.pucminas.notifications.application.domain.Notification;
import br.pucminas.leads.schemas.lead_processed.LeadProcessed;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LeadConsumerMapper {

    public static Notification mapToDomain(LeadProcessed leadProcessed) {
        if (leadProcessed == null) {
            return null;
        }
        return Notification.builder()
                           .withId(leadProcessed.getId())
                           .withInsuranceQuote(InsuranceQuoteConsumerMapper.mapToDomain(leadProcessed.getInsuranceQuote()))
                           .withCreatedAt(leadProcessed.getCreatedAt())
                           .withProcessedAt(leadProcessed.getProcessedAt())
                           .build();
    }

}
