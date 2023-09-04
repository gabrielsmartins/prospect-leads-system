package br.pucminas.leads.application.service;


import br.pucminas.leads.application.domain.InsuranceQuote;
import br.pucminas.leads.application.domain.Product;
import br.pucminas.leads.application.domain.exceptions.InsuranceQuoteNotFoundException;
import br.pucminas.leads.application.domain.exceptions.ProductNotFoundException;
import br.pucminas.leads.application.ports.out.SearchInsuranceQuotePort;
import br.pucminas.leads.application.ports.out.SearchProductPort;
import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static br.pucminas.leads.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;
import static br.pucminas.leads.application.support.LeadSupport.defaultLead;
import static br.pucminas.leads.application.support.ProductSupport.defaultProduct;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnrichLeadServiceTest {

    private EnrichLeadService service;
    private SearchInsuranceQuotePort searchInsuranceQuotePort;
    private SearchProductPort searchProductPort;

    @BeforeEach
    public void setup() {
        this.searchInsuranceQuotePort = mock(SearchInsuranceQuotePort.class);
        this.searchProductPort = mock(SearchProductPort.class);
        this.service = new EnrichLeadService(this.searchInsuranceQuotePort, this.searchProductPort);
    }

    @Test
    @DisplayName("Given Lead When Insurance Quote Exists And Product Exists Then Enrich")
    public void givenLeadWhenInsuranceQuoteExistsAndProductExistsThenEnrich() {
        var insuranceQuote = defaultInsuranceQuote().build();
        when(this.searchInsuranceQuotePort.findById(any(UUID.class))).thenReturn(Optional.of(insuranceQuote));

        var product = defaultProduct().build();
        when(this.searchProductPort.findById(anyInt())).thenReturn(Optional.of(product));

        var lead = defaultLead().build();
        this.service.enrich(lead);
        assertThat(lead.getInsuranceQuote()).isEqualTo(insuranceQuote);
        assertThat(lead.getInsuranceQuote().getProduct()).isEqualTo(product);
    }

    @Test
    @DisplayName("Given Lead When Insurance Quote Not Exists Then Throw Exception")
    public void givenLeadWhenInsuranceQuoteExistsThenThrowException() {
        when(this.searchInsuranceQuotePort.findById(any(UUID.class))).thenReturn(Optional.empty());

        var product = defaultProduct().build();
        when(this.searchProductPort.findById(anyInt())).thenReturn(Optional.of(product));

        var lead = defaultLead().build();
        assertThatThrownBy(() -> this.service.enrich(lead))
                .isInstanceOf(InsuranceQuoteNotFoundException.class);
    }

    @Test
    @DisplayName("Given Lead When Product Not Exists Then Throw Exception")
    public void givenLeadWhenProductExistsThenThrowException() {
        var insuranceQuote = defaultInsuranceQuote().build();
        when(this.searchInsuranceQuotePort.findById(any(UUID.class))).thenReturn(Optional.of(insuranceQuote));

        when(this.searchProductPort.findById(anyInt())).thenReturn(Optional.empty());

        var lead = defaultLead().build();
        assertThatThrownBy(() -> this.service.enrich(lead))
                .isInstanceOf(ProductNotFoundException.class);
    }

}