package br.pucminas.leads.application.service;


import br.pucminas.leads.application.domain.Lead;
import br.pucminas.leads.application.ports.out.SaveLeadPort;
import br.pucminas.leads.application.ports.out.SearchLeadPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static br.pucminas.leads.application.support.LeadSupport.defaultLead;
import static org.mockito.Mockito.*;

class ReceiveLeadServiceTest {

    private ReceiveLeadService service;
    private EnrichLeadService enrichLeadService;
    private SearchLeadPort searchLeadPort;
    private SaveLeadPort saveLeadPort;

    @BeforeEach
    public void setup() {
        this.enrichLeadService = mock(EnrichLeadService.class);
        this.searchLeadPort = mock(SearchLeadPort.class);
        this.saveLeadPort = mock(SaveLeadPort.class);
        this.service = new ReceiveLeadService(this.enrichLeadService, this.searchLeadPort, this.saveLeadPort);
    }

    @Test
    @DisplayName("Given Lead When Not Exists Then Return")
    public void givenLeadWhenNotExistsThenReturn() {
        var lead = defaultLead().build();

        when(this.searchLeadPort.findById(any(UUID.class))).thenReturn(Optional.of(lead));

        this.service.receive(lead);
        verify(this.enrichLeadService, never()).enrich(lead);
        verify(this.saveLeadPort, never()).save(lead);
    }

    @Test
    @DisplayName("Given Lead When Exists Then Enrich And Save Lead")
    public void givenLeadWhenExistsThenEnrichAndSaveLead() {
        var lead = defaultLead().build();

        when(this.searchLeadPort.findById(any(UUID.class))).thenReturn(Optional.empty());

        this.service.receive(lead);
        verify(this.enrichLeadService, times(1)).enrich(lead);
        verify(this.saveLeadPort, times(1)).save(lead);
    }

}