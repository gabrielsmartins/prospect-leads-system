package br.pucminas.leads.application.service;


import br.pucminas.leads.application.domain.exceptions.LeadNotFoundException;
import br.pucminas.leads.application.ports.out.SaveLeadPort;
import br.pucminas.leads.application.ports.out.SearchLeadPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static br.pucminas.leads.application.support.LeadSupport.defaultLead;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class FinishLeadServiceTest {

    private FinishLeadService service;
    private SearchLeadPort searchLeadPort;
    private SaveLeadPort saveLeadPort;

    @BeforeEach
    public void setup() {
        this.searchLeadPort = mock(SearchLeadPort.class);
        this.saveLeadPort = mock(SaveLeadPort.class);
        this.service = new FinishLeadService(this.searchLeadPort, this.saveLeadPort);
    }

    @Test
    @DisplayName("Given Id When Exists Then Finish Lead")
    public void givenIdWhenExistsThenFinishLead() {
        var lead = defaultLead().build();

        var id = UUID.randomUUID();
        when(this.searchLeadPort.findById(id)).thenReturn(Optional.of(lead));

        this.service.finish(id);
        verify(this.saveLeadPort, times(1)).save(lead);
    }

    @Test
    @DisplayName("Given Id When Not Exists Then Throw Exception")
    public void givenIdWhenNotExistsThenThrowException() {
        var id = UUID.randomUUID();
        when(this.searchLeadPort.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> this.service.finish(id))
                         .isInstanceOf(LeadNotFoundException.class);
    }

}