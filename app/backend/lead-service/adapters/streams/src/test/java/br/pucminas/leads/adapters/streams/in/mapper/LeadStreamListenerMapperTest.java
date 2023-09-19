package br.pucminas.leads.adapters.streams.in.mapper;

import br.pucminas.leads.adapters.streams.in.support.LeadDtoSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LeadStreamListenerMapperTest {

    @Test
    @DisplayName("Given Lead Dto When Map Then Return Lead")
    public void givenLeadDtoWhenMapThenReturnLead() {
        var leadDto = LeadDtoSupport.defaultLeadDto().build();
        var lead = LeadStreamListenerMapper.mapToDomain(leadDto);

        assertThat(lead).isNotNull();
        assertThat(lead).hasNoNullFieldsOrPropertiesExcept("id", "product", "processedAt", "finishedAt");
        assertThat(lead.getInsuranceQuote().getId()).isEqualTo(leadDto.getInsuranceQuoteId());
        assertThat(lead.getCreatedAt()).isEqualTo(leadDto.getCreatedAt());
    }

}
