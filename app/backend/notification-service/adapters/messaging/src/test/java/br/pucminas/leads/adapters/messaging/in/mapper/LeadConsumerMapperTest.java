package br.pucminas.leads.adapters.messaging.in.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.leads.adapters.messaging.in.support.LeadProcessedMessageSupport.defaultLeadProcessedMessage;
import static org.assertj.core.api.Assertions.assertThat;

class LeadConsumerMapperTest {

    @Test
    @DisplayName("Given Lead Dto When Map Then Return Lead")
    public void givenLeadDtoWhenMapThenReturnLead() {
        var leadMessage = defaultLeadProcessedMessage().build();
        var lead = LeadConsumerMapper.mapToDomain(leadMessage);

        assertThat(lead).isNotNull();
        assertThat(lead).hasNoNullFieldsOrPropertiesExcept("notifiedAt");
        assertThat(lead.getId()).isEqualTo(leadMessage.getId());
        assertThat(lead.getCreatedAt()).isEqualTo(leadMessage.getCreatedAt());
        assertThat(lead.getProcessedAt()).isEqualTo(leadMessage.getProcessedAt());
    }

}
