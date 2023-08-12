package br.pucminas.leads.adapters.persistence.adapter;

import br.pucminas.leads.adapters.persistence.entity.LeadEntity;
import br.pucminas.leads.adapters.persistence.service.ILeadPersistenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.leads.application.support.LeadSupport.defaultLead;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SaveLeadPersistenceAdapterTest {

    private SaveLeadPersistenceAdapter adapter;
    private ILeadPersistenceService service;

    @BeforeEach
    public void setup() {
        this.service = mock(ILeadPersistenceService.class);
        this.adapter = new SaveLeadPersistenceAdapter(this.service);
    }

    @Test
    @DisplayName("Given Lead When Save Then Return Saved Lead")
    public void givenLeadWhenSaveThenReturnSavedLead() {
        var lead = defaultLead().build();

        when(this.service.save(any(LeadEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var savedLead = this.adapter.save(lead);

        assertThat(savedLead).isNotNull();
    }

}