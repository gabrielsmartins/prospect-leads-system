package br.pucminas.leads.adapters.persistence.adapter;


import br.pucminas.leads.adapters.persistence.service.ILeadPersistenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static br.pucminas.leads.adapters.persistence.support.LeadEntitySupport.defaultLeadEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SearchLeadPersistenceAdapterTest {

    private SearchLeadPersistenceAdapter adapter;
    private ILeadPersistenceService service;

    @BeforeEach
    public void setup() {
        this.service = mock(ILeadPersistenceService.class);
        this.adapter = new SearchLeadPersistenceAdapter(this.service);
    }

    @Test
    @DisplayName("Given Id When Exists Then Return Lead")
    public void givenIdWhenExistsThenReturnLead() {
        var id = UUID.randomUUID();

        var leadEntity = defaultLeadEntity().build();
        when(this.service.findById(id)).thenReturn(Optional.of(leadEntity));

        var optionalLead = this.adapter.findById(id);

        assertThat(optionalLead).isPresent();
    }

    @Test
    @DisplayName("Given Datetime When Exists Then Return Leads")
    public void givenDatetimeWhenExistsThenReturnLeads() {
        var dateTime = LocalDateTime.now();

        var leadEntity = defaultLeadEntity().build();
        when(this.service.findAllPendingReceivedLessThan(dateTime)).thenReturn(List.of(leadEntity));

        var leads = this.adapter.findAllPendingReceivedLessThan(dateTime);

        assertThat(leads).isNotEmpty();
    }

}