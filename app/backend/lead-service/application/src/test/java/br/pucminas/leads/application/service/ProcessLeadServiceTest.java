package br.pucminas.leads.application.service;


import br.pucminas.leads.application.domain.InsuranceQuote;
import br.pucminas.leads.application.domain.Lead;
import br.pucminas.leads.application.ports.out.SaveLeadPort;
import br.pucminas.leads.application.ports.out.SearchLeadPort;
import br.pucminas.leads.application.ports.out.SendLeadPort;
import br.pucminas.leads.application.service.discount.CompoundDiscountApplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static br.pucminas.leads.application.support.LeadSupport.defaultLead;
import static org.mockito.Mockito.*;

class ProcessLeadServiceTest {

    private ProcessLeadService service;
    private SearchLeadPort searchLeadPort;
    private CompoundDiscountApplier compoundDiscountApplier;
    private SendLeadPort sendLeadPort;
    private SaveLeadPort saveLeadPort;

    @BeforeEach
    public void setup() {
        this.searchLeadPort = mock(SearchLeadPort.class);
        this.compoundDiscountApplier = mock(CompoundDiscountApplier.class);
        this.sendLeadPort = mock(SendLeadPort.class);
        this.saveLeadPort = mock(SaveLeadPort.class);
        this.service = new ProcessLeadService(this.searchLeadPort, this.compoundDiscountApplier, this.sendLeadPort, this.saveLeadPort);
    }

    @Test
    @DisplayName("Given Lead When Process Then Send Lead")
    public void givenLeadWhenProcessThenSendLead() {
        var leadOne = defaultLead().build();
        var leadTwo = defaultLead().build();
        var leads = List.of(leadOne, leadTwo);

        when(this.searchLeadPort.findAllPendingReceivedLessThan(any(LocalDateTime.class))).thenReturn(leads);

        this.service.process();
        verify(this.compoundDiscountApplier, times(leads.size())).apply(any(InsuranceQuote.class));
        verify(this.sendLeadPort, times(leads.size())).send(any(Lead.class));
        verify(this.saveLeadPort, times(leads.size())).save(any(Lead.class));
    }

}