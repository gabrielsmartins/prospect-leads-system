package br.pucminas.leads.adapters.messaging.in.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.leads.adapters.messaging.in.support.LeadDtoSupport.defaultLeadDto;
import static org.assertj.core.api.Assertions.assertThat;

class LeadStreamListenerMapperTest {

    @Test
    @DisplayName("Given Lead Dto When Map Then Return Lead")
    public void givenLeadDtoWhenMapThenReturnLead() {
        var leadDto = defaultLeadDto().build();
        var lead = LeadStreamListenerMapper.mapToDomain(leadDto);

        assertThat(lead).isNotNull();
        assertThat(lead).hasNoNullFieldsOrProperties();
        assertThat(lead.getInsuranceQuoteId()).isEqualTo(leadDto.getInsuranceQuoteId());
        assertThat(lead.getCreatedAt()).isEqualTo(leadDto.getCreatedAt());
    }

}
