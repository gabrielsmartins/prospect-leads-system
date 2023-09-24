package br.pucminas.leads.adapters.web.in.leads.mapper;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.leads.application.support.LeadSupport.defaultLead;
import static org.assertj.core.api.Assertions.assertThat;

class LeadControllerMapperTest {

    @Test
    @DisplayName("Given Lead When Map Then Return Lead Dto")
    public void givenLeadWhenMapThenReturnLeadDto() {
        var lead = defaultLead().build();

        var leadDto = LeadControllerMapper.mapToDto(lead);

        assertThat(leadDto).isNotNull();
        assertThat(leadDto).hasNoNullFieldsOrProperties();
        assertThat(leadDto.getId()).isEqualTo(lead.getId());
        assertThat(leadDto.getCreatedAt()).isEqualTo(lead.getCreatedAt());
        assertThat(leadDto.getProcessedAt()).isEqualTo(lead.getProcessedAt());
        assertThat(leadDto.getFinishedAt()).isEqualTo(lead.getFinishedAt());
        assertThat(leadDto.isFinished()).isEqualTo(lead.isFinished());;
    }

}