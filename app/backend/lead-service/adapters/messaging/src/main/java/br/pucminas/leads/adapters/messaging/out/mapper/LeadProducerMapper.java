package br.pucminas.leads.adapters.messaging.out.mapper;

import br.pucminas.leads.application.domain.Lead;
import br.pucminas.leads.schemas.lead_processed.LeadProcessed;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LeadProducerMapper {

    public static LeadProcessed mapToMessage(Lead lead) {
        if (lead == null) {
            return null;
        }
        var insuranceQuoteMessage = InsuranceQuoteProducerMapper.mapToMessage(lead.getInsuranceQuote());
        return LeadProcessed.newBuilder()
                            .setId(lead.getId())
                            .setInsuranceQuote(insuranceQuoteMessage)
                            .setCreatedAt(lead.getCreatedAt())
                            .setProcessedAt(lead.getProcessedAt())
                            .build();
    }
}
