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

import static br.pucminas.leads.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;
import static br.pucminas.leads.application.support.LeadSupport.defaultLead;
import static org.mockito.Mockito.*;

class ProcessLeadServiceTest {

    private ProcessLeadService service;
    private SearchLeadPort searchLeadPort;
    private EnrichLeadService enrichLeadService;
    private CompoundDiscountApplier compoundDiscountApplier;
    private SendLeadPort sendLeadPort;
    private SaveLeadPort saveLeadPort;

    @BeforeEach
    public void setup() {
        this.searchLeadPort = mock(SearchLeadPort.class);
        this.enrichLeadService = mock(EnrichLeadService.class);
        this.compoundDiscountApplier = mock(CompoundDiscountApplier.class);
        this.sendLeadPort = mock(SendLeadPort.class);
        this.saveLeadPort = mock(SaveLeadPort.class);
        this.service = new ProcessLeadService(this.searchLeadPort, this.compoundDiscountApplier, this.enrichLeadService, this.sendLeadPort, this.saveLeadPort);
    }

    @Test
    @DisplayName("Given Lead When Insurance Quote Is Finished Then Save Lead")
    public void givenLeadWhenInsuranceQuoteIsFinishedThenSaveLead() {
        var insuranceQuote = defaultInsuranceQuote()
                                .withFinished(true)
                                .build();

        var leadOne = defaultLead().withInsuranceQuote(insuranceQuote).build();
        var leadTwo = defaultLead().withInsuranceQuote(insuranceQuote).build();
        var leads = List.of(leadOne, leadTwo);

        when(this.searchLeadPort.findAllPendingReceivedLessThan(any(LocalDateTime.class))).thenReturn(leads);

        this.service.process();
        verify(this.compoundDiscountApplier, never()).apply(any(InsuranceQuote.class));
        verify(this.sendLeadPort, never()).send(any(Lead.class));
        verify(this.saveLeadPort, times(leads.size())).save(any(Lead.class));
        verify(this.enrichLeadService, times(leads.size())).enrich(any(Lead.class));
    }

    @Test
    @DisplayName("Given Lead When Insurance Quote Is Not Finished Then Send Lead")
    public void givenLeadWhenInsuranceQuoteIsNotFinishedThenSendLead() {
        var insuranceQuote = defaultInsuranceQuote()
                                .withFinished(false)
                                .build();

        var leadOne = defaultLead().withInsuranceQuote(insuranceQuote).build();
        var leadTwo = defaultLead().withInsuranceQuote(insuranceQuote).build();
        var leads = List.of(leadOne, leadTwo);

        when(this.searchLeadPort.findAllPendingReceivedLessThan(any(LocalDateTime.class))).thenReturn(leads);

        this.service.process();
        verify(this.compoundDiscountApplier, times(leads.size())).apply(any(InsuranceQuote.class));
        verify(this.sendLeadPort, times(leads.size())).send(any(Lead.class));
        verify(this.saveLeadPort, times(leads.size())).save(any(Lead.class));
    }

}