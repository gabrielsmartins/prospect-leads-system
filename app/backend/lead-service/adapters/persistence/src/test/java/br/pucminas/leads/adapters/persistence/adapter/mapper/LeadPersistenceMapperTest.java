package br.pucminas.leads.adapters.persistence.adapter.mapper;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.leads.adapters.persistence.support.LeadEntitySupport.defaultLeadEntity;
import static br.pucminas.leads.application.support.LeadSupport.defaultLead;
import static org.assertj.core.api.Assertions.assertThat;

class LeadPersistenceMapperTest {

    @Test
    @DisplayName("Given Lead When Map Then Return Lead Entity")
    public void givenLeadWhenMapThenReturnLeadEntity() {
        var lead = defaultLead().build();

        var leadEntity = LeadPersistenceMapper.mapToEntity(lead);

        assertThat(leadEntity).isNotNull();
        assertThat(leadEntity).hasNoNullFieldsOrProperties();
        assertThat(leadEntity.getInsuranceQuoteId()).isEqualTo(lead.getInsuranceQuoteId());
        assertThat(leadEntity.getCreatedAt()).isEqualTo(lead.getCreatedAt());
        assertThat(leadEntity.getProcessedAt()).isEqualTo(lead.getProcessedAt());
    }

    @Test
    @DisplayName("Given Lead Entity When Map Then Return Lead")
    public void givenLeadEntityWhenMapThenReturnLead() {
        var leadEntity = defaultLeadEntity().build();

        var lead = LeadPersistenceMapper.mapToDomain(leadEntity);

        assertThat(lead).isNotNull();
        assertThat(lead).hasNoNullFieldsOrProperties();
        assertThat(lead.getInsuranceQuoteId()).isEqualTo(leadEntity.getInsuranceQuoteId());
        assertThat(lead.getCreatedAt()).isEqualTo(leadEntity.getCreatedAt());
        assertThat(lead.getProcessedAt()).isEqualTo(leadEntity.getProcessedAt());
    }

}