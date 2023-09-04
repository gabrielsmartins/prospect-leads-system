package br.pucminas.leads.application.service;


import br.pucminas.leads.application.ports.out.SaveLeadPort;
import br.pucminas.leads.application.ports.out.SendLeadPort;
import br.pucminas.leads.application.service.discount.CompoundDiscountApplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.leads.application.support.LeadSupport.defaultLead;
import static org.mockito.Mockito.*;

class ProcessLeadServiceTest {

    private ProcessLeadService service;
    private CompoundDiscountApplier compoundDiscountApplier;
    private SendLeadPort sendLeadPort;
    private SaveLeadPort saveLeadPort;

    @BeforeEach
    public void setup() {
        this.compoundDiscountApplier = mock(CompoundDiscountApplier.class);
        this.sendLeadPort = mock(SendLeadPort.class);
        this.saveLeadPort = mock(SaveLeadPort.class);
        this.service = new ProcessLeadService(this.compoundDiscountApplier, this.sendLeadPort, this.saveLeadPort);
    }

    @Test
    @DisplayName("Given Lead When Process Then Send Lead")
    public void givenLeadWhenProcessThenSendLead() {
        var lead = defaultLead().build();
        this.service.process(lead);
        verify(this.compoundDiscountApplier, times(1)).apply(lead.getInsuranceQuote());
        verify(this.sendLeadPort, times(1)).send(lead);
        verify(this.saveLeadPort, times(1)).save(lead);
    }

}