package br.pucminas.leads.application.service;

import br.pucminas.leads.application.ports.out.SearchInsuranceQuotePort;
import br.pucminas.leads.application.ports.out.SearchProductPort;
import br.pucminas.leads.application.service.discount.CompoundDiscountApplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static br.pucminas.leads.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;
import static br.pucminas.leads.application.support.LeadSupport.defaultLead;
import static br.pucminas.leads.application.support.ProductSupport.defaultProduct;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ApplyDiscountServiceTest {

    private ApplyDiscountService service;
    private SearchInsuranceQuotePort searchInsuranceQuotePort;
    private SearchProductPort searchProductPort;
    private CompoundDiscountApplier compoundDiscountApplier;

    @BeforeEach
    public void setup() {
        this.searchInsuranceQuotePort = mock(SearchInsuranceQuotePort.class);
        this.searchProductPort = mock(SearchProductPort.class);
        this.compoundDiscountApplier = mock(CompoundDiscountApplier.class);
        this.service = new ApplyDiscountService(this.searchInsuranceQuotePort, this.searchProductPort, this.compoundDiscountApplier);
    }

    @Test
    @DisplayName("Given Lead When Product And Insurance Quote Exists Then Apply Discount")
    public void givenLeadWhenProductAndInsuranceQuoteExistsThenApplyDiscount() {
        var insuranceQuote = defaultInsuranceQuote().build();
        when(this.searchInsuranceQuotePort.findById(any(UUID.class))).thenReturn(Optional.of(insuranceQuote));

        var product = defaultProduct().build();
        when(this.searchProductPort.findById(anyInt())).thenReturn(Optional.of(product));

        var lead = defaultLead().build();
        this.service.applyDiscount(lead);
        assertThat(insuranceQuote.getProduct()).isEqualTo(product);
        verify(this.compoundDiscountApplier, times(1)).apply(insuranceQuote);
    }

}