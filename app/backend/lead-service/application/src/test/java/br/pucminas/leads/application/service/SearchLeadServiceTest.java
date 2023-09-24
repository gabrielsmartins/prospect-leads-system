package br.pucminas.leads.application.service;

import br.pucminas.leads.application.domain.exceptions.LeadNotFoundException;
import br.pucminas.leads.application.ports.out.SearchLeadPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static br.pucminas.leads.application.support.LeadSupport.defaultLead;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SearchLeadServiceTest {

    private SearchLeadService service;
    private SearchLeadPort port;

    @BeforeEach
    public void setup() {
        this.port = mock(SearchLeadPort.class);
        this.service = new SearchLeadService(this.port);
    }

    @Test
    @DisplayName("Given Id When Exists Then Return Lead")
    public void givenIdWhenExistsThenReturnLead() {
        var leadMock = defaultLead().build();

        when(this.port.findById(any(UUID.class))).thenReturn(Optional.of(leadMock));

        var id = UUID.randomUUID();
        var lead = this.service.findById(id);

        assertThat(lead).isNotNull();
    }

    @Test
    @DisplayName("Given Id When Not Exists Then Throw Exception")
    public void givenIdWhenNotExistsThenThrowException() {
        when(this.port.findById(any(UUID.class))).thenReturn(Optional.empty());

        var id = UUID.randomUUID();
        assertThatThrownBy(() -> this.service.findById(id))
                .isInstanceOf(LeadNotFoundException.class);
    }

    @Test
    @DisplayName("Given Pageable When Exists Then Return Leads")
    public void givenPageableWhenExistsThenReturnLeads() {
        var leadMock = defaultLead().build();

        when(this.port.findAll()).thenReturn(List.of(leadMock));

        var leads = this.service.findAll();

        assertThat(leads).isNotEmpty();
    }

}