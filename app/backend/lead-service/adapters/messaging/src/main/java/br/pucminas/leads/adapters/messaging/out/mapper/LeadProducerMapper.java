package br.pucminas.leads.adapters.messaging.out.mapper;

import br.pucminas.leads.application.domain.Lead;
import br.pucminas.notifications.schemas.lead_processed.LeadProcessed;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LeadProducerMapper {

    public static LeadProcessed mapToMessage(Lead lead) {
        if (lead == null) {
            return null;
        }
        return LeadProcessed.newBuilder()
                            .setInsuranceQuote(InsuranceQuoteProducerMapper.mapToMessage(lead.getInsuranceQuote()))
                            .setProduct()
                            .build();
    }
}
