package br.pucminas.leads.adapters.messaging.out.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.leads.application.support.LeadSupport.defaultLead;
import static org.assertj.core.api.Assertions.assertThat;

class LeadProducerMapperTest {

    @Test
    @DisplayName("Given Lead When Map Then Return Message")
    public void givenLeadWhenMapThenReturnMessage() {
        var lead = defaultLead().build();
        var leadProcessed = LeadProducerMapper.mapToMessage(lead);

        assertThat(leadProcessed).isNotNull();
        assertThat(leadProcessed).hasNoNullFieldsOrProperties();
        assertThat(leadProcessed.getInsuranceQuoteId()).isEqualTo(lead.getInsuranceQuoteId());
        assertThat(leadProcessed.getCreatedAt()).isEqualTo(lead.getCreatedAt());
        assertThat(leadProcessed.getProcessedAt()).isEqualTo(lead.getProcessedAt());
    }
}
