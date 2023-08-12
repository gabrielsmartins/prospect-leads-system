package br.pucminas.leads.application.service;


import br.pucminas.leads.application.ports.out.SaveLeadPort;
import br.pucminas.leads.application.ports.out.SendLeadPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.leads.application.support.LeadSupport.defaultLead;
import static org.mockito.Mockito.*;

class ProcessLeadServiceTest {

    private ProcessLeadService service;
    private ApplyDiscountService applyDiscountService;
    private SendLeadPort sendLeadPort;
    private SaveLeadPort saveLeadPort;

    @BeforeEach
    public void setup() {
        this.applyDiscountService = mock(ApplyDiscountService.class);
        this.sendLeadPort = mock(SendLeadPort.class);
        this.saveLeadPort = mock(SaveLeadPort.class);
        this.service = new ProcessLeadService(this.applyDiscountService, this.sendLeadPort, this.saveLeadPort);
    }

    @Test
    @DisplayName("Given Lead When Process Then Send Lead")
    public void givenLeadWhenProcessThenSendLead() {
        var lead = defaultLead().build();
        this.service.process(lead);
        verify(this.applyDiscountService, times(1)).applyDiscount(lead);
        verify(this.sendLeadPort, times(1)).send(lead);
        verify(this.saveLeadPort, times(1)).save(lead);
    }

}