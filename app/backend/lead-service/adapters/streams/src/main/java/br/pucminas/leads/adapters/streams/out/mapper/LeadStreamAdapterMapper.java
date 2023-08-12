package br.pucminas.leads.adapters.streams.out.mapper;

import br.pucminas.leads.application.domain.Lead;
import br.pucminas.notifications.schemas.lead_processed.LeadProcessed;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LeadStreamAdapterMapper {

    public static LeadProcessed mapToMessage(Lead lead) {
        return LeadProcessed.newBuilder()
                            .setInsuranceQuoteId(lead.getInsuranceQuoteId())
                            .setCreatedAt(lead.getCreatedAt())
                            .setProcessedAt(lead.getProcessedAt())
                            .build();
    }
}
